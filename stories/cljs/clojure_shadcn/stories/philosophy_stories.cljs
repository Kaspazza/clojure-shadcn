(ns clojure-shadcn.stories.philosophy-stories
  "Docs page: architectural rationale, tradeoffs, and project boundaries."
  (:require
   [clojure-shadcn.stories.helpers     :as helpers]
   [clojure-shadcn.ui.components.badge :as badge]
   [reagent.core                       :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [defdoc]]))

(def ^:export default
  #js {:title "Docs/Philosophy"
       :parameters #js {:layout "padded"}})

(defn- section
  [title & children]
  [:section {:class "space-y-3"}
   [:h2 {:class "text-xl font-semibold tracking-tight"}
    title]
   (into [:div {:class "space-y-3 text-sm leading-6 text-muted-foreground"}]
         children)])

(defn- principle-card
  [eyebrow title body]
  [:article {:class "rounded-lg border bg-card p-4"}
   [:p {:class "mb-2 text-xs font-medium uppercase tracking-wider text-primary"}
    eyebrow]
   [:h3 {:class "mb-2 font-semibold text-card-foreground"}
    title]
   [:p {:class "text-sm leading-6 text-muted-foreground"}
    body]])

(defn- callout
  [title & children]
  [:div {:class "rounded-lg border border-primary/20 bg-primary/5 p-4"}
   [:h3 {:class "mb-2 text-sm font-semibold text-foreground"}
    title]
   (into [:div {:class "space-y-2 text-sm leading-6 text-muted-foreground"}]
         children)])

(defn- bullet-list
  [& items]
  (into [:ul {:class "list-disc space-y-2 pl-5"}]
        (map (fn [item] [:li {}
                         item])
             items)))

(defdoc
  Rationale
  "Why clojure-shadcn separates behavior, styling, and Clojure-facing APIs."
  []
  (r/as-element
   (helpers/wrap-component
    [:main {:class "mx-auto max-w-4xl space-y-10 p-6"}
     [:header {:class "space-y-4"}
      [badge/badge {:variant :secondary}
       "Rationale"]
      [:h1 {:class "text-3xl font-bold tracking-tight md:text-4xl"}
       "Own the UI, but don't rebuild it from scratch"]
      [:p {:class "max-w-3xl text-base leading-7 text-muted-foreground"}
       "Manufaktura adapts the shadcn/ui copy-first idea for ClojureScript and Reagent."]]
     [section
      "The problem is coupling, not a lack of widgets"
      [:p {}
       "A UI from the outside perspective sounds simple, business wants a button so you just need to use a html component add some css and that's it. It is 5 seconds to add right?
       But in reality, there is focus/blur managment, keyboard interactions, aria semantics, animations, accessability, consistency between components, reusability and regular software complexity that grows with your html,css and cljs files."]
      [:p {}
       "Most UI solutions for that are frameworks and as with every framework it is easy to start with, but far from simple. As your needs will grow for components outside of available components or you find yourself wanting to adjust one of the components it will give you a lot of pain as it has coupled multiple areas and made a lot of decisions for you. Before you know it, you have so many components, global hooks/scripts, css running for it that changing to other UI system is a giantic cost. On other hand there are libraries solving specific problems, like headless components, css framework utilties, but to make that to feel good and consistent takes much more time and requires a designer on a team, which is often not possible."]
      [callout
       "The project chooses a middle path"
       [:p {}
        "Use focused tools for independent concerns, connect them through small Reagent components, and distribute the result as readable source rather than a remote abstraction."]]]
     [:div {:class "grid gap-4 md:grid-cols-3"}
      [principle-card
       "Behavior"
       "Focused React primitives"
       "Radix UI and other focused libraries handle difficult browser behavior such as focus, keyboard navigation, portals, and ARIA interaction patterns."]
      [principle-card
       "Presentation"
       "Tailwind CSS"
       "Utilities keep visual decisions next to the markup, while CSS custom properties provide shared semantic color, radius, and shadow tokens."]
      [principle-card
       "Application API"
       "ClojureScript and Reagent"
       "Callers use props maps, keyword variants, Hiccup children, and ordinary functions. JavaScript interop stays inside the copied component."]]
     [section
      "A glue layer, not another framework"
      [:p {}
       "This separation is the central design choice. The library supplies thoughtful defaults and contains mechanical interop, but it should not hide the browser or create a second component runtime."]
      [:p {}
       "Props and events are simply the component interface. The consuming application decides what data to provide and what an interaction means. The library does not prescribe re-frame, routing, data fetching, dependency injection, or a domain architecture."]]
     [section
      "Why copy the source?"
      [:p {}
       "A packaged dependency is convenient to add, but the application then coordinates with its public API, styling mechanism, compatibility constraints, and release schedule. Copying requires more attention when adopting a component, but the resulting code is local and directly changeable."]
      [bullet-list
       "Change markup, variants, animation, and defaults without designing a universal override API."
       "Upgrade Radix, Tailwind, or another primitive on the application's schedule."
       "Read and debug the complete component without crossing a package boundary."
       "Delete or replace one component without migrating the entire catalogue."]
      [callout
       "Forking is the workflow"
       [:p {}
        "Local divergence is expected. The upstream component is a considered starting point; the copied file is your application's component."]]]
     [section
      "Ownership has a cost"
      [:p {}
       "Copy-first is not free. Fixes do not arrive automatically, local copies can drift, and application teams own testing, dependency review, and maintenance. Upstream improvements become changes to evaluate rather than upgrades to accept blindly."]
      [:p {}
       "That tradeoff should remain explicit. A future installer may automate file copying or record provenance, but it must not turn the catalogue into an opaque runtime dependency."]]
     [section
      "Composition over configuration"
      [:p {}
       "Complex components are exposed as small pieces that callers assemble with Hiccup. A dialog can have a trigger, overlay, content, title, description, actions, and close control without one component accumulating a prop for every possible arrangement."]
      [:p {}
       "Composition does not mean every arrangement is valid. Documentation and stories must preserve the semantic and accessibility constraints required by the underlying behavior."]]
     [section
      "Accessible foundations, not an accessibility guarantee"
      [:p {}
       "Using Radix provides a stronger baseline than casually reimplementing interaction patterns. It does not remove the component author's responsibility to preserve semantics or the application's responsibility to provide labels, suitable content, and real workflow testing."]
      [bullet-list
       "Do not break keyboard and focus behavior while changing composition."
       "Name controls and provide meaningful descriptions in application context."
       "Test representative workflows with keyboards and assistive technology."
       "Treat accessibility regressions as behavior regressions, not visual polish."]]
     [section
      "Why this fits Clojure"
      [:div {:class "grid gap-4 md:grid-cols-2"}
       [principle-card
        "Data"
        "Maps and keywords"
        "Component options remain ordinary data that can be constructed, transformed, inspected, and passed through functions."]
       [principle-card
        "Simplicity"
        "Separate independent concerns"
        "Behavior primitives, styling utilities, design defaults, and application state should not become one indivisible abstraction."]
       [principle-card
        "Transparency"
        "Prefer visible source"
        "Classes, interop, dependencies, and markup remain inspectable. Shared helpers must remove mechanical repetition without hiding important distinctions."]
       [principle-card
        "Pragmatism"
        "Reuse the strongest ecosystem tools"
        "The goal is not a JavaScript-free dependency graph. It is a maintainable Clojure-facing boundary built on tools that solve real user problems well."]]]
     [section
      "The project contract"
      [:div {:class "grid gap-6 md:grid-cols-2"}
       [:div {:class "space-y-3"}
        [:h3 {:class "font-semibold text-foreground"}
         "What we aim to provide"]
        [bullet-list
         "Idiomatic Reagent/Hiccup source and consistent props conventions."
         "Editable Tailwind styling and CSS-variable design tokens."
         "Accessible behavior built on suitable focused primitives."
         "Storybook examples, API guidance, and explicit dependencies."
         "Source understandable after it leaves this repository."]]
       [:div {:class "space-y-3"}
        [:h3 {:class "font-semibold text-foreground"}
         "What we do not promise"]
        [bullet-list
         "Automatic upgrades after a component is copied."
         "Zero JavaScript dependencies or interop."
         "Accessibility without correct application content and testing."
         "A finished brand or complete organizational design system."
         "Opinions about application state, routing, data, or domain architecture."]]]]
     [callout
      "Decision rule"
      [:p {}
       "Before adding a shared helper, component option, wrapper, or dependency, ask whether it removes real incidental complexity while keeping a copied file understandable. If composition is clearer, or duplication is cheaper than coordination, do not add the abstraction."]]
     [:footer {:class "border-t pt-6 text-sm leading-6 text-muted-foreground"}
      "The library succeeds when ClojureScript teams can start from a polished, accessible baseline, keep control of their UI, and still understand the code years later."]])))
