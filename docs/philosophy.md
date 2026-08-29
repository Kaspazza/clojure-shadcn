# Philosophy and design rationale

> Build on proven behavior, express it through Clojure data, make styling
> explicit, and let application teams own the result.

`clojure-shadcn` is inspired by [shadcn/ui](https://ui.shadcn.com/), but it is
not a mechanical translation of a JavaScript catalogue. It applies the same
copy-first ownership model to ClojureScript and asks a Clojure-specific
question:

**How can we reuse the strongest parts of the React UI ecosystem without
letting JavaScript abstractions dictate the shape of a Clojure application?**

Our answer is a thin, inspectable layer of Reagent components over focused
behavior primitives, styled with Tailwind CSS and distributed as source.

## The problem is coupling, not a shortage of widgets

A button is easy to draw. A reliable component system is not. Dialogs need
focus trapping and restoration; menus need keyboard navigation; overlays need
layering, dismissal, and scroll behavior; every component needs consistent
states and visual language.

Common approaches solve part of this problem while coupling unrelated
concerns.

### Full UI suites

A traditional suite can make the first screen fast to build. Over time, its
component API, theme provider, styling runtime, internal markup, versioning,
and override mechanism become constraints on the whole application. A small
visual change may require understanding a large abstraction. An upstream
upgrade can invalidate local workarounds.

The problem is not that these libraries are badly engineered. The problem is
that behavior, presentation, design policy, and distribution are often shipped
as one indivisible decision.

### Building everything locally

Complete ownership avoids framework lock-in, but implementing robust browser
behavior is expensive. WAI-ARIA interaction patterns, roving focus, portal
behavior, focus restoration, and assistive-technology testing are specialized
work. Reimplementing them for every application is neither simple nor a good
use of a small ClojureScript team's time.

### Headless primitives alone

Headless libraries correctly separate behavior from appearance. They do not,
however, provide a visual language. A team still has to make and maintain
hundreds of small decisions about spacing, hierarchy, focus states, radii,
color, motion, and composition. Those decisions are real product work, and an
inconsistent answer is visible to users.

### Clojure-specific binary dependencies

A packaged Clojure UI library can offer an idiomatic API, but packaging does
not remove coupling. Consumers still inherit its public abstractions, release
cycle, styling assumptions, and compatibility constraints. Small ecosystem
size can amplify the maintenance risk.

## Three concerns, three layers

The architecture follows **separation of concerns**: assign each independent
problem to a tool with a narrow responsibility.

### 1. Behavior: focused React primitives

Radix UI and other focused React libraries provide difficult interaction
behavior: keyboard handling, focus management, ARIA semantics, portals, and
overlay mechanics. They are used as implementation details, not as the public
language of the application.

This is pragmatic reuse. React's ecosystem already contains mature solutions
to browser-level problems. Rewriting them merely to keep every line in
ClojureScript would optimize for language purity at the expense of users and
maintainers.

Accessibility is not guaranteed by a dependency name. Radix provides a strong
baseline; component authors must still preserve semantics, label controls,
choose suitable composition, and test real workflows. The promise is
**accessible foundations**, not permission to stop thinking about
accessibility.

### 2. Styling: Tailwind CSS

Tailwind utilities keep visual decisions close to the markup that they affect.
A class such as `rounded-md` or `bg-primary` has a small, visible meaning. It
does not require navigating a private theme API or guessing which selector
wins.

This project uses CSS custom properties as design tokens and maps them into
Tailwind's theme. The flow is:

```text
CSS custom properties -> Tailwind utilities -> component classes
```

That structure gives the default catalogue a coherent appearance while
keeping branding centralized. A team can change color, radius, shadow, and
theme values without replacing component behavior.

Tailwind has costs: long class strings can be noisy, build-time source
detection must include copied files, and arbitrary utilities can create
inconsistency if used without discipline. We accept those costs because the
result is local, inspectable, ordinary CSS behavior rather than a second
runtime styling system.

### 3. Application-facing API: ClojureScript and Reagent

The public component interface should feel like Clojure:

- props are maps;
- variants and sizes are keywords;
- children are Hiccup;
- components are ordinary Reagent functions; and
- JavaScript prop conversion is contained inside implementation namespaces.

This is an **Adapter pattern**: a narrow Clojure-facing API translates Clojure
data into the contracts expected by React primitives. The adapter is useful
only while it remains thin. It should not grow into a second framework that
hides the behavior of the underlying browser or component.

## Why distribute source instead of an artifact?

The copy-first model is the project's central architectural decision.
Components are examples intended to become application code, not remote
objects controlled by this repository.

### Ownership beats override APIs

When a copied component needs another state, a different animation, or
application-specific markup, the team edits it directly. There is no need to
add a generic prop for every possible consumer, fork an npm package, or wait
for an upstream release.

A conventional dependency is often *easy* to add but can become structurally
complex: your UI is coupled to an external API, release schedule, styling
mechanism, and transitive dependency graph. Copying asks for more attention at
adoption time but removes that ongoing coordination.

This follows the distinction in Rich Hickey's
[Simple Made Easy](https://www.infoq.com/presentations/Simple-Made-Easy/):
convenience and simplicity are not synonyms.

### Forking is the workflow, not a failure state

In most libraries, changing vendored source means the abstraction failed. In
this project, local divergence is expected. The upstream component is a
well-considered starting point. The copied component is your application's
version.

That changes the upgrade model:

- upstream improvements are inputs to review, not automatic updates;
- local changes do not need to remain generally configurable;
- application teams decide when dependency upgrades are worthwhile; and
- ownership includes maintenance, tests, and security review.

We should be honest about the cost. Copying can duplicate fixes across
projects, provenance can become unclear, and comparing a heavily modified file
with upstream may be difficult. Good commit history and recording the source
version or URL can help. A future installer may automate copying and metadata,
but it must not take ownership away from the application.

## Composition over configuration

Components should expose small pieces that callers compose rather than a
single object with an ever-growing props surface.

A dialog, for example, naturally consists of trigger, overlay, content, title,
description, actions, and close behavior. Keeping those parts visible lets a
caller omit, reorder, or replace them using Hiccup. Adding a boolean option for
every possible arrangement would create a configuration language more complex
than the markup it replaces.

This is the **Composite pattern** expressed through Hiccup: simple nodes and
compound structures share the same representation, so larger interfaces are
built from smaller components without a separate builder API.

Composition has a boundary. Some invariants are necessary for accessibility
and behavior. The component documentation should explain those constraints
rather than pretending every arrangement is valid.

## Design tokens are policy; utilities are application

The default theme is a set of useful design decisions, not a universal brand.
CSS variables define shared policy—semantic colors, radii, and shadows—while
component classes apply that policy in context.

This distinction supports several adoption levels:

1. **Copy one component.** Take only the component and the few utilities and
   npm packages it needs.
2. **Keep behavior, change appearance.** Edit utility classes while preserving
   the underlying primitive and its interaction contract.
3. **Retheme the catalogue.** Change semantic CSS variables so components
   inherit a new visual language.
4. **Replace a component.** Local components do not need to conform to an
   upstream release or global registry.
5. **Use the catalogue as a design-system seed.** Treat the defaults as a
   coherent baseline, then evolve tokens and components with product needs.

The tokens are a single source of visual policy, not proof that a complete
design system exists. A design system also requires usage guidance,
accessibility standards, content rules, governance, and product-specific
decisions.

## Keep domain and application concerns outside

These components belong to the UI layer. They may manage ephemeral interaction
state required to function, but they should not know about application routes,
remote APIs, business entities, re-frame subscriptions, or domain workflows.

The boundary is:

```text
domain/application data -> props -> UI component -> events -> application
```

A reusable confirmation dialog may emit a confirm event. It should not know
how an invoice is deleted. A table may render rows and sorting controls. It
should not decide which customers a user is authorized to see.

Props and events are simply the component interface. The consuming application
decides what data to provide and what an event means. Keeping that interface
free of business decisions makes the components reusable without claiming an
application architecture for the projects that adopt them.

## Clojure values, applied pragmatically

The project aligns with several Clojure values without treating them as dogma.

### Data over bespoke APIs

Maps and keywords are easy to construct, transform, merge, inspect, and pass
through ordinary functions. Component options should remain data wherever
possible.

### Explicitness over magic

Source, classes, wrappers, and dependencies are visible. Shared utilities are
acceptable when they remove mechanical repetition, but hidden registries,
implicit global state, and macro-generated component systems demand a strong
justification.

### Small abstractions

A useful abstraction removes incidental repetition while preserving the
important distinctions. If a wrapper merely renames every JavaScript prop, it
adds little value. If it contains interop and establishes a stable,
Clojure-shaped convention, it earns its place.

### Pragmatism over language purity

Radix UI, Tailwind, and focused npm libraries are used because they solve real
problems well. The goal is not a JavaScript-free dependency graph. The goal is
a Clojure-friendly application boundary and maintainable software for users.

## Project contract

### What this project aims to provide

- idiomatic Reagent/Hiccup source with props maps and keyword options;
- coherent, editable Tailwind styling and CSS-variable theme tokens;
- accessible behavior built on suitable primitives;
- isolated Storybook examples and API guidance;
- explicit component-specific dependencies; and
- source that remains understandable after it leaves this repository.

### What this project does not promise

- automatic compatibility or upgrades after a component is copied;
- zero JavaScript dependencies or interop;
- accessibility without correct application-level labels, content, and tests;
- a finished brand or complete organizational design system;
- every domain-specific component an application may need; or
- opinions about re-frame, routing, data fetching, or backend architecture.

## Decision test for new abstractions

Before adding a shared helper, wrapper, component option, or dependency, ask:

1. Which independent problem does it solve?
2. Is that problem repeated enough to justify coordination?
3. Does the abstraction make the code simpler, or merely shorter?
4. Can a developer understand the copied file without this repository in
   their head?
5. Does it preserve the ability to modify or replace one component locally?
6. Does it improve user behavior or accessibility, rather than only author
   convenience?
7. Is composition clearer than adding another option?

If the answers are weak, duplication may be cheaper than coupling.

## The intended outcome

`clojure-shadcn` should help a ClojureScript team start from a polished,
accessible baseline without renting its UI architecture from a package. The
project succeeds when teams copy less reinvention, keep more control, and can
still understand the code years later.

It is not about rewriting the React ecosystem in Clojure. It is about putting
a small, honest Clojure boundary around the parts worth reusing—and then
getting out of the application's way.
