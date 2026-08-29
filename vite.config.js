import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import tailwindcss from '@tailwindcss/vite';

// shadow-cljs ESM files still install Closure namespaces on globalThis.
// Re-evaluating one runtime module through Vite HMR therefore tries to install
// namespaces such as cljs.core a second time. Reload the preview document so
// every CLJS runtime update starts with a fresh global environment instead.
const cljsFullReload = {
  name: 'cljs-full-reload',
  handleHotUpdate({ file, server }) {
    if (file.includes('/cljs-stories/')) {
      server.ws.send({ type: 'full-reload' });
      return [];
    }
  },
};

// Vite config used by Storybook (react-vite framework).
// Story modules are compiled by shadow-cljs as native ESM,
// so no custom CJS->ESM transform is required.
export default defineConfig({
  plugins: [
    cljsFullReload,
    react(),
    tailwindcss(),
  ],

  define: {
    global: 'globalThis',
  },
});
