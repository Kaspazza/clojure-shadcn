(ns clojure-shadcn.stories.theming-stories
  "Docs page: Theming — Tailwind tokens, dark mode, merge-classes."
  (:require
   [clojure-shadcn.stories.helpers     :as helpers]
   [clojure-shadcn.ui.components.badge :as badge]
   [reagent.core                       :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [defdoc]]))

(def ^:export default
  #js {:title "Docs/Theming"
       :parameters #js {:layout "padded"}})

(defn- code-snippet
  "Simple mono code block used in docs."
  [code]
  [:pre {:class "rounded-md bg-muted p-3 overflow-x-auto text-xs"}
   [:code {}
    code]])

(defn- section
  [title & children]
  [:section {:class "mb-10"}
   [:h2 {:class "text-xl font-semibold mb-3"}
    title]
   (into [:div {:class "space-y-3 text-sm"}]
         children)])

(defn- scanner-item
  "One row in the semantic tokens list."
  [name desc]
  [:li [:code name] (str ": " desc)])

(defdoc
 ThemeTokens
 "Reference of the OKLCH design tokens the components depend on."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-3xl p-6 space-y-2"}
    [badge/badge {:variant :secondary
                  :class "mb-4"}
     "Docs"]
    [:h1 {:class "text-2xl font-bold mb-1"}
     "Theme Tokens"]
    [:p {:class "text-sm text-muted-foreground mb-6"}
     "Every clojure-shadcn component is palette-indexed: it only touches the semantic tokens below.  You never have to touch component code to re-brand."]
    [section
     "Semantic tokens (light mode example)"
     [code-snippet
      ":root {\n  --background: oklch(0.9700 0.008 90);\n  --foreground: oklch(0.1448 0 0);\n  /* ... */\n}"]
     [:p {}
      "Full token list lives in resources/css/app.css — copy it into your project as the foundation."]]
    [section
     "Semantic naming"
     [:ul {:class "list-disc pl-4 space-y-1 text-sm"}
      [scanner-item ":background" "page surface"]
      [scanner-item ":foreground" "default text"]
      [scanner-item ":card / :card-foreground" "inner surfaces"]
      [scanner-item ":primary / :primary-foreground" "call-to-action"]
      [scanner-item ":secondary / :secondary-foreground" "lower emphasis"]
      [scanner-item ":muted / :muted-foreground" "de-emphasized"]
      [scanner-item ":accent / :accent-foreground" "emphasis within muted"]
      [scanner-item ":destructive" "errors"]
      [scanner-item ":border" "default borders"]
      [scanner-item ":input / :ring" "form elements"]
      [scanner-item ":radius" "border-radius scale"]]]])))

(defdoc
 DarkMode
 "How dark mode is wired via the Tailwind `dark` class strategy."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-3xl p-6 space-y-2"}
    [:h1 {:class "text-2xl font-bold mb-1"}
     "Dark Mode"]
    [:p {:class "text-sm text-muted-foreground mb-6"}
     "Class-based: toggle a `.dark` class on <html>; every token override in `resources/css/app.css` flips automatically."]
    [section
     "The toggle handler"
     [code-snippet
      "(defn toggle-theme! []\n  (let [next (if @dark? :light :dark)]\n    (.toggle (.-classList js/document.documentElement)\n             \"dark\" (= next :dark))\n    (reset! dark? (= next :dark))))"]
     [:p {}
      "The ThemeToggle component only fires a callback — you wire the class toggle, persistence, etc."]]
    [section
     "How overrides kick in"
     [code-snippet
      ".dark {\n  --background: oklch(0.205 0 0);\n  --foreground: oklch(0.9851 0 0);\n  --primary: oklch(0.5136 0.2031 305.5256);\n  /* ... */\n}"]
     [:p {}
      "Tailwind's `dark:` variant class strategy means every `dark:bg-gray-800` or `dark:text-gray-200` in the source can also be respected."]]
    [section
     "Storybook demo"
     [:p {}
      "The toolbar in the Storybook UI flips the `dark` class live — components respond immediately to the class because they use the semantic tokens."]]])))

(defdoc
 MergeClasses
 "When to use clojure-shadcn.utils.styles/merge-classes to override classes."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-3xl p-6 space-y-2"}
    [:h1 {:class "text-2xl font-bold mb-1"}
     "Merging classes"]
    [:p {:class "text-sm text-muted-foreground mb-6"}
     "Many components accept a `:class` prop.  When that prop merges with base classes, use merge-classes so conflicting utilities resolve deterministically."]
    [section
     "When are conflicts introduced?"
     [:p {}
      "tailwind-merge makes sensible decisions: only one padding/flex-color/font rule survives.  That's safer than naive concatenation when you spread overrides over defaults."]
     [code-snippet "(styles/merge-classes \"px-4 text-sm\" \"px-2\")  ;; => \"text-sm px-2\""]]
    [section
     "How components use it"
     [:p {}
      "Components that spread over base classes pipe the union through merge-classes, so consumer-provided overrides actually win."]
     [code-snippet "[:button {:class (styles/merge-classes base-classes custom-class)}  ...]"]]
    [section
     "Check yourself"
     [:p {}
      "If you see two classes of the same family in source (px-2 and px-4, text-sm and text-lg), a consumer override likely won't win unless you merge deterministically — always route through merge-classes."]]])))
