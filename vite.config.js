import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import tailwindcss from '@tailwindcss/vite';
import { build } from 'esbuild';
import path from 'path';

// ── shadow-cljs CJS → ESM plugin ───────────────────────────────────────────
//
// Problem: shadow-cljs :npm-module emits CJS into cljs-stories/.  Vite's dev
// server serves files as native ESM — browsers can't parse require()/module.exports.
//
// Key constraint: all CJS files in cljs-stories/ share state via a $CLJS
// singleton (exported by cljs_env.js).  If we bundle each story entry with
// esbuild (inlining all relative requires), each bundle gets its OWN copy of
// cljs_env / cljs.core / reagent — separate $CLJS singletons corrupt shared
// state when Storybook lazy-loads a second story file.
//
// Solution: bundle ONCE.  Use a single esbuild invocation with ALL story
// entry points bundled together, sharing one copy of every CLJS module.
// npm packages are externalised and hoisted to ESM imports.
//
// Ported from the chatbot project.

function cljsStoriesPlugin() {
  const cljsDir = path.resolve('cljs-stories');
  const cache = new Map();

  return {
    name: 'cljs-stories-cjs-to-esm',
    enforce: 'pre',

    async load(id) {
      if (!id.startsWith(cljsDir) || !id.endsWith('.js')) return null;
      if (cache.has(id)) return cache.get(id);

      // Bundle this entry — but externalise BOTH npm packages AND other
      // cljs-stories/ files.  This way each file is transformed to ESM
      // individually, and Vite resolves the import graph so every module
      // (especially cljs_env.js / $CLJS) is loaded exactly ONCE.
      const result = await build({
        entryPoints: [id],
        bundle: true,
        format: 'esm',
        write: false,
        platform: 'browser',
        define: { global: 'globalThis' },
        logLevel: 'warning',
        plugins: [{
          name: 'externalize-all-deps',
          setup(b) {
            // Bare specifiers (react, @radix-ui/...) → external
            b.onResolve({ filter: /^[^./]/ }, (args) => ({
              path: args.path,
              external: true,
            }));
            // Relative requires (./cljs_env, ./cljs.core) → ALSO external.
            // This prevents inlining shared CLJS modules into each entry.
            // Vite will resolve these and each file gets transformed by
            // this same plugin, but loaded only once.
            b.onResolve({ filter: /^\./ }, (args) => ({
              path: args.path,
              external: true,
            }));
          },
        }],
      });

      let code = result.outputFiles[0].text;

      // ── Hoist __require("pkg") → top-level ESM imports ───────────────
      //
      // esbuild emits a __require shim for CJS requires that survive in
      // the ESM output.  We find them all, generate real ESM imports, and
      // swap the calls.
      const requirePattern = /__require\("([^"]+)"\)/g;
      const packages = new Map();
      let counter = 0;
      let match;

      while ((match = requirePattern.exec(code)) !== null) {
        const pkg = match[1];
        if (!packages.has(pkg)) {
          packages.set(pkg, `__ext_${counter++}`);
        }
      }

      if (packages.size > 0) {
        const imports = [];
        for (const [pkg, binding] of packages) {
          if (pkg.startsWith('.')) {
            // Relative requires (./cljs_env, ./cljs.core) return the mutable
            // module.exports object — that's the ESM default export.
            // `import * as` would give a frozen Module namespace object,
            // breaking $CLJS.clojure = {} assignments.
            imports.push(`import ${binding} from ${JSON.stringify(pkg)};`);
          } else {
            // npm packages — namespace import works because their ESM
            // exports match what CJS require() returns.
            imports.push(`import * as ${binding} from ${JSON.stringify(pkg)};`);
          }
        }
        code = code.replace(requirePattern, (_, pkg) => packages.get(pkg));
        code = imports.join('\n') + '\n' + code;
      }

      cache.set(id, code);
      return code;
    },
  };
}

// Vite config used by Storybook (react-vite framework).
// Tailwind v4 requires the @tailwindcss/vite plugin instead of PostCSS.
export default defineConfig({
  plugins: [
    cljsStoriesPlugin(),
    react(),
    tailwindcss(),
  ],

  define: {
    global: 'globalThis',
  },
});
