# clojure-shadcn

**Copy-first UI components for ClojureScript, Reagent, and Tailwind CSS.**

`clojure-shadcn` brings the idea behind [shadcn/ui](https://ui.shadcn.com/)
to ClojureScript: choose a component, copy its source into your application,
and make it yours. The components combine Clojure-friendly APIs with
accessible React primitives and a coherent Tailwind-based visual language.

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
| Design defaults | `clojure-shadcn` | Coherent variants, spacing, states, composition, and component examples |
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
- React 18
- Tailwind CSS 4
- component-specific npm packages such as Radix UI primitives

Treat these as the tested development baseline, not as a claim that every
component requires every dependency listed in `package.json`.

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

## Repository layout

```text
src/cljs/clojure_shadcn/ui/components/  component source
src/cljs/clojure_shadcn/utils/          shared prop and class utilities
src/cljs/clojure_shadcn/ui/hooks/       reusable UI hooks
stories/cljs/clojure_shadcn/stories/    Storybook examples and documentation
resources/css/app.css                   Tailwind imports and design tokens
docs/philosophy.md                      architectural rationale and boundaries
```

## Scope

`clojure-shadcn` provides UI building blocks. It does not prescribe re-frame,
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
