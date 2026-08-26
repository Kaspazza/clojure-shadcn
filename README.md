# clojure-shadcn

shadcn/ui, but for ClojureScript. A copy-paste component library for Reagent
built on Radix UI primitives and Tailwind CSS — the same philosophy as
[shadcn/ui](https://ui.shadcn.com/): you own the code.

> **Status: early development.** Components are being ported from a production
> website and new shadcn coverage is being added.

## What is this?

Not an npm package. Not a black box. Every component lives in your codebase:

- **Radix UI primitives** underneath (accessibility, behavior, keyboard nav)
- **Tailwind CSS v4** with shadcn design tokens (OKLCH, CSS variables)
- **Reagent** idioms — keyword props, hiccup children, no JS interop required
- Browse the docs site, copy the code, paste it into your project. Done.

## Documentation (Storybook)

```bash
bb dev
```

Opens Storybook with all components, live previews, API reference tables,
and copyable source for every component.

## Usage

1. Add the npm dependencies for the component you want (listed on each
   component's page).
2. Copy the component source into your project.
3. Make sure you have the [design tokens](resources/css/app.css) in your CSS.

### Requirements

- ClojureScript (shadow-cljs)
- Reagent >= 2.0
- Tailwind CSS v4
- React 18/19

## License

MIT © Mateusz Mazurczak
