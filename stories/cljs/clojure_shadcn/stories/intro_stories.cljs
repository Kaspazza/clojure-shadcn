(ns clojure-shadcn.stories.intro-stories
  "Docs page: Introduction, install, and basic usage of clojure-shadcn."
  (:require
   [clojure-shadcn.stories.helpers     :as helpers]
   [clojure-shadcn.ui.components.badge :as badge]
   [reagent.core                       :as r]))

(def ^:export default
  #js {:title      "Docs/Introduction"
       :parameters #js {:layout "padded"}})

(defn- feature-card
  "Consistent docs feature card body."
  [title body]
  [:div {:class "rounded-md border p-3"}
   [:h3 {:class "text-sm font-semibold mb-1"}
    title]
   [:p {:class "text-xs text-muted-foreground"}
    body]])

(defn- section
  "Consistent docs section block."
  [title & children]
  [:section {:class "mb-10"}
   [:h2 {:class "text-xl font-semibold mb-3"}
    title]
   (into [:div {:class "space-y-3 text-sm"}] children)])

(defn- code-snippet
  "Simple mono code block used in docs."
  [code]
  [:pre {:class "rounded-md bg-muted p-3 overflow-x-auto text-xs"}
   [:code {}
    code]])

(defn ^:export Overview
  "What clojure-shadcn is and when to use it."
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "max-w-3xl p-6 space-y-2"}
     [badge/badge {:variant :secondary
                   :class "mb-4"}
      "Docs"]
     [:h1 {:class "text-3xl font-bold tracking-tight mb-1"}
      "clojure-shadcn"]
     [:p {:class "text-base text-muted-foreground mb-6"}
      "shadcn/ui-style copy-paste component library for ClojureScript / Reagent. You don't npm-install components — you copy the .cljs file into your project and own the source."]
     [:div {:class "rounded-md border p-4 bg-card mb-6"}
      [:h3 {:class "text-sm font-semibold mb-2"}
       "Philosophy — copy, don't install"]
      [:p {:class "text-sm text-muted-foreground leading-relaxed"}
       "Every component ships as a plain ClojureScript namespace.  You copy it under src/cljs/ in your project, and from that point you own the code.  If you need a different default, change it — no upstream dependency, no version churn.  Inspired by shadcn/ui, adapted for the ClojureScript / Reagent world."]]
     [:div {:class "grid grid-cols-2 md:grid-cols-3 gap-3"}
      [feature-card "Copy, own" "Source lives in your repo."]
      [feature-card "Style-free" "Pure Tailwind; no JS utility framework."]
      [feature-card "Modular" "Each component is one namespace."]
      [feature-card "API-ref docs" "Automation-generated prop tables."]
      [feature-card "Controlled" "State stays in your app; components are pure."]
      [feature-card "Radix under the hood" "Accessible primitives where needed."]]])))

(defn ^:export Installation
  "How to bring a component into your project."
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "max-w-3xl p-6 space-y-2"}
     [:h1 {:class "text-2xl font-bold mb-4"}
      "Installation"]
     [section "1. Copy the component source"
      [:p {}
       "Browse to a component's Installation story and either expand the embedded source block or copy the whole file into src/cljs/clojure_shadcn/ui/components/<name>.cljs."]
      [code-snippet "src/cljs/clojure_shadcn/ui/components/badge.cljs"]]
     [section "2. Install the npm primitives it uses"
      [code-snippet "npm install @radix-ui/react-<primitive>"]
      [:p {}
       "The Installation story tells you the exact primitive (Radix, cmdk, sonner, vaul, ...).  Most self-contained components only need Lucide for icons."]
      [code-snippet "npm install lucide-react"]]
     [section "3. Import the theme tokens"
      [:p {}
       "Either copy resources/css/app.css from this repo, or wire your own Tailwind theme tokens — see the Theming page."]]
     [section "4. Use it"
      [code-snippet
       "(:require\n  [clojure-shadcn.ui.components.badge :as badge])\n\n[badge/badge {:variant :secondary} \"Hello\"]"]]
     [section "Why this works"
      [:p {}
       "Because you copy the source, there's no npm package to depend on.  Your build compiles the .cljs directly; the only upstreams are npm primitives and the Tailwind tokens."]]])))

(defn ^:export UsageExamples
  "Common patterns for using clojure-shadcn components."
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "max-w-3xl p-6 space-y-2"}
     [:h1 {:class "text-2xl font-bold mb-4"}
      "Usage"]
     [section "Compose them"
      [code-snippet
       "[:div {:class \"flex items-center gap-3\"}\n [badge/badge {:variant :secondary} \"New\"]\n [button/button {:variant :outline} \"Open docs\"]]"]
      [:p {}
       "Each component returns a plain Reagent vector. No wrapper magic, no controlled state you can't reach."]]
     [section "Controlled components"
      [:p {}
       "Components like theme-toggle take a :theme value and a :on-toggle handler. You own the state (atom, re-frame, app-db context). That keeps Storybook demos (and production) deterministic."]
      [code-snippet
       "[theme-toggle/theme-toggle\n  {:theme @theme\n   :on-toggle #(swap! theme {:light :dark :light})}"]]
     [section "Merge Tailwind classes safely"
      [:p {}
       "clojure-shadcn.utils.styles/merge-classes runs through tailwind-merge, so later classes override earlier ones deterministically — use it when combining base classes with overrides."]
      [code-snippet
       "(styles/merge-classes \"px-4 text-sm\" \"px-2\") ; => \"text-sm px-2\""]]]))) 
