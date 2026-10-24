# ClojureScript UI Components

## Manufaktura

**Copy-first UI components for ClojureScript, Reagent, and Tailwind CSS.**

**ClojureScript UI Components** is the descriptive, discoverable project name.
**Manufaktura**  a collection of components made to be understood, copied, and adapted.

> “A chair. And something more amazing, another chair!
> Not a big deal? [chuckles]
> They are identical.
> Once upon a time, every object was made individually, by hand, which means
> each one looked unique. Horrible. Luckily, we now have Western manufacturing,
> which means we can now have two identical copies. Or a dozen.”
>
> — Jan Paweł Adamczewski, *1670*

Manufaktura brings the idea behind [shadcn/ui](https://ui.shadcn.com/) to
ClojureScript: choose a component, copy its source into your application, and
make it yours. The components combine Clojure-friendly APIs with accessible
React primitives and a coherent Tailwind-based visual language.

This is not a conventional component dependency or a framework. It is a
collection of readable starting points for building your own UI system.

> **Project status: early development.** The repository already contains a
> broad component catalogue, but APIs, examples, and the copy workflow are
> still evolving. Review copied code before relying on it in production.

## Why this exists

ClojureScript teams should not have to choose between two poor extremes:

- opaque JavaScript UI libraries whose styling, lifecycle, and release cycle
  become application-wide constraints; or
- rebuilding dialogs, menus, focus management, keyboard navigation, and a
  consistent visual system from scratch.

This project separates those concerns:

| Concern | Tool | Responsibility |
| --- | --- | --- |
| Interaction and accessibility | [Radix UI](https://www.radix-ui.com/) and other focused React primitives | Focus management, keyboard interaction, ARIA behavior, overlays, and other difficult browser details |
| Styling method | [Tailwind CSS v4](https://tailwindcss.com/) | Local, explicit utility classes and design-token integration |
| Design defaults | Manufaktura | Coherent variants, spacing, states, composition, and component examples |
| Application API | ClojureScript + Reagent | Props maps, keyword variants, Hiccup children, and contained JavaScript interop |

The result is a **glue layer**, not another UI framework. Behavior,
presentation, and application state remain separate, and the final source
lives in your repository.

Read [Philosophy and design rationale](docs/philosophy.md) for the full
argument, tradeoffs, and project boundaries.

## What you get

- **ClojureScript-native APIs** — Reagent functions, props maps, Hiccup, and
  keyword values such as `:outline`, `:destructive`, and `:sm`.
- **Accessible foundations** — complex components build on focused primitives
  such as Radix UI rather than reimplementing accessibility behavior casually.
- **Tailwind CSS v4 styling** — visual decisions are visible where they are
  used and can be changed without overriding a package's private CSS.
- **Theme tokens** — light and dark palettes use CSS custom properties and
  OKLCH values in [`resources/css/app.css`](resources/css/app.css).
- **Source ownership** — copied components can be read, changed, tested, or
  deleted on your schedule.
- **Isolated examples** — Storybook provides previews, component stories, API
  notes, and source examples.

## The ownership model

There is intentionally no `clojure-shadcn` runtime artifact to add to
`deps.edn` and no npm package containing the component implementation.
Adoption happens per component:

1. Find the component and its story in this repository or Storybook.
2. Copy the component namespace into your project's source tree.
3. Copy any shared namespaces it requires, commonly utilities under
   [`src/cljs/clojure_shadcn/utils`](src/cljs/clojure_shadcn/utils).
4. Install only the JavaScript dependencies required by that component.
5. Add or adapt the design tokens from
   [`resources/css/app.css`](resources/css/app.css).
6. Rename namespaces and modify the source to fit your application.

Copying is a deliberate tradeoff: upstream changes are not automatically
applied to your application. In return, your UI is not coupled to this
project's release cycle or abstraction boundaries.

## Example

A component remains ordinary Reagent/Hiccup at the call site:

```clojure
(ns example.actions
  (:require
   [clojure-shadcn.ui.components.button :refer [button]]))

(defn delete-action []
  [button {:variant :destructive
           :size :sm
           :on-click #(js/console.log "delete")}
   "Delete"])
```

The namespace above reflects this repository's layout. After copying, use the
namespace you chose for your application.

Components conventionally accept a props map first and children after it.
JavaScript interop and prop normalization stay inside the component, while
callers work with Clojure data.

## Requirements

The exact dependencies depend on the components you copy. This repository is
currently developed with:

- ClojureScript compiled by `shadow-cljs`
- Reagent 2
- React 19 (the current `package.json` baseline)
- Tailwind CSS 4
- component-specific npm packages such as Radix UI primitives

Treat these as the tested development baseline, not as a claim that every
component requires every dependency listed in `package.json`.

## Installation in your application

Installing npm dependencies alone is **not enough**: the copied source needs
Tailwind-generated CSS, semantic theme tokens, and a stylesheet loaded by your
page. The steps below assume an existing, working ClojureScript/Reagent app.
They are separate from running this repository's Storybook.

### 1. Check your ClojureScript and React setup

Ensure your source directory (for example, `src/cljs`) is on your compiler's
source path, and add Reagent to your existing `deps.edn` dependencies if needed:

```clojure
reagent/reagent {:mvn/version "2.0.1"}
```

For a new app using the repository's React baseline:

```bash
npm install react@^19.2.0 react-dom@^19.2.0
```

Keep `react` and `react-dom` on matching versions. If your app already uses
React, check compatibility before upgrading; copying a component should not
silently replace your application's runtime setup. You do not need this
repository's Storybook, Vite, or Babashka tooling to use the components.

### 2. Copy a component and its dependencies

For a first button, copy these files into matching paths under your source root:

- [`ui/components/button.cljs`](src/cljs/clojure_shadcn/ui/components/button.cljs)
- [`utils/props.cljs`](src/cljs/clojure_shadcn/utils/props.cljs)
- [`utils/styles.cljs`](src/cljs/clojure_shadcn/utils/styles.cljs)

Either preserve their `clojure-shadcn.*` namespaces and directory structure, or
rename the namespaces **and all corresponding requires** to your own prefix.
Install the button's npm dependencies, including the class-merging utility:

```bash
npm install @radix-ui/react-slot tailwind-merge@^3
```

For each additional component, follow its `:require` entries recursively:
copy required local components, utilities, and hooks, and install the npm
packages named by string requires. For example, a dialog needs
`@radix-ui/react-dialog`, and a component importing `lucide-react` needs that
package too. Consult [`package.json`](package.json) for the versions used
here; do not install the entire catalogue just to use one component.

### 3. Add a Tailwind CSS v4 build

If your app already compiles Tailwind v4, keep that integration and continue to
the stylesheet configuration below. Otherwise, the CLI is a simple option that
works independently of your ClojureScript compiler:

```bash
npm install -D tailwindcss@^4 @tailwindcss/cli@^4 tw-animate-css
```

`tw-animate-css` supplies classes such as `animate-in` and `fade-in-0` used by
animated components. It can be omitted only if you also omit its CSS import and
do not use components that rely on those classes.

Tailwind v4 uses CSS-first configuration. The setup below does not require a
`tailwind.config.js`, the v3 `@tailwind` directives, or `autoprefixer`.
If using Vite instead of the CLI, install `@tailwindcss/vite@^4`, register
`tailwindcss()` in your Vite plugins, and import your CSS entry from the JS
entry. Choose one CSS build integration rather than running both.

### 4. Configure your CSS entry, tokens, and source scanning

Create `resources/css/app.css` in your app with this header:

```css
@import "tailwindcss";
@import "tw-animate-css";

/* Paths are relative to this stylesheet, not the project root. */
@source "../../src";

@custom-variant dark (&:is(.dark *));
```

Point `@source` at every directory containing your app's Hiccup and copied
component source, including shared `.cljs` or `.cljc` files outside `src`.
Tailwind scans source text, not the running Reagent tree: keep complete utility
names in strings rather than constructing names such as `"bg-" + color`.

Then copy these **complete blocks** from
[`resources/css/app.css`](resources/css/app.css), after the header:

| CSS block | Why it is needed |
| --- | --- |
| `:root` | Light-mode color, radius, font, spacing, and shadow variables |
| `.dark` | Dark-mode values for those semantic tokens |
| `@theme inline` | Maps tokens to Tailwind utilities such as `bg-primary`, `text-muted-foreground`, and `rounded-lg` |
| `@layer base` | Default border/focus colors, page background/text, and button cursor |

**Copying only `:root` is insufficient.** The `@theme inline` mapping is what
makes the semantic utility classes available to Tailwind. This is the
**design-token pattern**: components use semantic names while CSS owns their
actual values. Change the values to brand your app without rewriting each
component.

Some components also need styles beyond the core theme:

- Copy the `@utility` rules and `@keyframes` at the end of the same CSS file
  when using components that reference them (for example, shimmer text,
  scrolling helpers, and loading indicators). They are not built into Tailwind.
- For components using `prose` classes, install
  `npm install -D @tailwindcss/typography` and add
  `@plugin "@tailwindcss/typography";` to your CSS entry.
- The Google Fonts import, `[data-theme]` / `[data-base]` theme-gallery rules,
  and `.showcase-fonts` rules are showcase features, not installation
  requirements. Keep your own fonts and omit those blocks unless you want the
  gallery behavior. If copying the whole file instead, its typography plugin
  must be installed and its external font request should be reviewed.

For class-based dark mode, toggle `dark` on the document's `<html>` element.
Putting it only on an app container can leave portalled dialogs and menus
outside the dark theme. No theme-switching npm package is required for this.

### 5. Compile and load the stylesheet

For the CLI setup, run this from your application root alongside your
ClojureScript watcher (create the output directory if it does not exist):

```bash
npx @tailwindcss/cli -i ./resources/css/app.css -o ./resources/public/css/app.css --watch
```

If `resources/public` is your server's public root, include the generated file
in your HTML page:

```html
<link rel="stylesheet" href="/css/app.css">
```

Adjust the output path and URL to your app's static-file setup. Do not serve
the uncompiled CSS entry directly: the browser cannot process Tailwind's
`@theme`, `@apply`, or package imports. Shadow CLJS compilation alone does not
run the Tailwind CLI.

Build production CSS before packaging your public assets:

```bash
npx @tailwindcss/cli -i ./resources/css/app.css -o ./resources/public/css/app.css --minify
```

### 6. Verify the integration

Render the button from the [example above](#example) using your copied
namespace, then check:

- The button has spacing, a background color, and visible keyboard focus.
- Adding `dark` to `<html>` changes the theme, including portalled overlays
  when you add them.
- Editing a utility class in a `.cljs` file updates the generated CSS.
- A production build includes the generated stylesheet, not only development
  watcher output.

If layout utilities work but semantic colors do not, check the token blocks
and `@theme inline`. If no utilities work, check the CSS URL, running build,
and `@source` paths. If only animations or prose styling are missing, check
the optional imports and component-specific styles above.

## Explore and develop locally

Prerequisites: a JDK, Clojure CLI, [Babashka](https://babashka.org/), and
Node.js/npm.

```bash
npm install
bb dev
```

`bb dev` generates the story module configuration, compiles the ClojureScript
stories, starts the Shadow CLJS watcher, and serves Storybook on
<http://localhost:6010>.

Useful tasks:

```bash
bb watch-stories     # watch ClojureScript stories only
bb compile-stories   # compile stories once
bb storybook         # prepare stories and run Storybook
bb build-storybook   # build the static Storybook site
bb clean             # remove generated build artifacts
```

`bb build-storybook` (or `npm run build-storybook`) prepares the ClojureScript
stories and builds the static web app into `storybook-static/`. Deploy the
contents of that directory to a static web host; no Clojure runtime is needed
on the host.

## Deploy the interactive documentation with Docker

The Docker image publishes the complete static Storybook site: component
previews, interactive controls, API notes, and source examples. It is not the
Storybook development server. A multi-stage build keeps Node.js, the JDK,
Clojure CLI, Babashka, and source files out of the final Nginx image.

With Docker running, execute these commands from the repository root:

```bash
docker build -t manufaktura-docs:local .
docker run --detach --name manufaktura-docs --publish 8081:8080 --restart unless-stopped manufaktura-docs:local
```

Open <http://localhost:8080>. Docker is the only local prerequisite; all
compilation happens inside the build container. The first build downloads npm
and Maven dependencies and may take several minutes. Subsequent builds reuse
cached dependency layers and downloads. Builds need internet access to the
container registry, npm, and Maven repositories.

Useful operations:

```bash
docker logs manufaktura-docs
docker inspect --format '{{.State.Health.Status}}' manufaktura-docs
docker stop manufaktura-docs
docker rm manufaktura-docs
```

For hosting, build and push the image to your container registry, then deploy
it using your platform's normal container workflow. Configure the platform's
HTTP target port as **8080**, and use `/index.html` as its health-check path.
The container runs Nginx as a non-root user and needs no database, application
secrets, or persistent volume. Rebuild and redeploy the image whenever stories
or components change; runtime environment variables do not modify the compiled
site.

Serve the site at the domain root (for example, `https://ui.example.com/`).
Subpath hosting such as `/docs/` is not configured by this Dockerfile. Terminate
HTTPS at your hosting platform or reverse proxy; the container itself serves
HTTP. When using a reverse proxy on the same machine, bind the published port
to loopback (`--publish 127.0.0.1:8080:8080`) instead of exposing it publicly.

This publishes every story, source example, and documentation page included in
the build, so review the catalogue before making it public. Some examples also
use external resources or browser APIs; containerizing the site does not make
those resources available offline or remove browser permission requirements.
Base images use maintained tags; pin them to reviewed digests if your deployment
requires immutable toolchain versions, and rebuild regularly for security fixes.

## Repository layout

```text
src/cljs/clojure_shadcn/ui/components/  component source
src/cljs/clojure_shadcn/utils/          shared prop and class utilities
src/cljs/clojure_shadcn/ui/hooks/       reusable UI hooks
src/cljs/stories/clojure_shadcn/stories/ Storybook examples and documentation
resources/css/app.css                   Tailwind imports and design tokens
docs/philosophy.md                      architectural rationale and boundaries
```

Stories retain their `clojure-shadcn.stories.*` namespaces: `src/cljs/stories`
is a separate source root in the development aliases, alongside `src/cljs`
for component source and shared story helpers.

## Scope

Manufaktura provides UI building blocks. It does not prescribe re-frame,
routing, remote data, dependency injection, or domain architecture. Keep those
application concerns outside copied components and connect them through props
and events.

The project also does not promise automatic upgrades for copied files. Once a
component enters your codebase, your team owns its behavior and maintenance.
That is the feature, not an omission.

## Contributing

Contributions should preserve the central constraints:

- expose an idiomatic ClojureScript/Reagent API;
- keep application and domain logic out of UI components;
- prefer composition over large configuration surfaces;
- preserve or improve keyboard and assistive-technology behavior;
- document component-specific npm dependencies and meaningful tradeoffs; and
- keep copied source understandable without knowledge of repository internals.

For architectural context, start with [`docs/philosophy.md`](docs/philosophy.md).

## License

[MIT](LICENSE) © Mateusz Mazurczak
