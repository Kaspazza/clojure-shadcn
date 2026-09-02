(ns clojure-shadcn.stories.intro-stories
  "Docs page: Introduction, install, and basic usage of clojure-shadcn."
  (:require
   [clojure-shadcn.stories.helpers     :as helpers]
   [clojure-shadcn.ui.components.badge :as badge]
   [reagent.core                       :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [defdoc]]))

(def ^:export default
  #js {:title "Docs/Introduction"
       :parameters #js {:layout "padded"}})

(defn- section
  "Consistent docs section block."
  [title & children]
  [:section {:class "mb-10"}
   [:h2 {:class "text-xl font-semibold mb-3"}
    title]
   (into [:div {:class "space-y-3 text-sm"}]
         children)])

(defn- code-snippet
  "Simple mono code block used in docs."
  [code]
  [:pre {:class "rounded-md bg-muted p-3 overflow-x-auto text-xs"}
   [:code {}
    code]])

(def ^:private default-theme-css
  ":root {
  --background: oklch(1 0 0);
  --foreground: oklch(0.145 0 0);
  --card: oklch(1 0 0);
  --card-foreground: oklch(0.145 0 0);
  --popover: oklch(1 0 0);
  --popover-foreground: oklch(0.145 0 0);
  --primary: oklch(0.205 0 0);
  --primary-foreground: oklch(0.985 0 0);
  --secondary: oklch(0.97 0 0);
  --secondary-foreground: oklch(0.205 0 0);
  --muted: oklch(0.97 0 0);
  --muted-foreground: oklch(0.556 0 0);
  --accent: oklch(0.97 0 0);
  --accent-foreground: oklch(0.205 0 0);
  --destructive: oklch(0.577 0.245 27.325);
  --destructive-foreground: oklch(0.985 0 0);
  --border: oklch(0.922 0 0);
  --input: oklch(0.922 0 0);
  --ring: oklch(0.708 0 0);
  --chart-1: oklch(0.646 0.222 41.116);
  --chart-2: oklch(0.6 0.118 184.704);
  --chart-3: oklch(0.398 0.07 227.392);
  --chart-4: oklch(0.828 0.189 84.429);
  --chart-5: oklch(0.769 0.188 70.08);
  --sidebar: oklch(0.985 0 0);
  --sidebar-foreground: oklch(0.145 0 0);
  --sidebar-primary: oklch(0.205 0 0);
  --sidebar-primary-foreground: oklch(0.985 0 0);
  --sidebar-accent: oklch(0.97 0 0);
  --sidebar-accent-foreground: oklch(0.205 0 0);
  --sidebar-border: oklch(0.922 0 0);
  --sidebar-ring: oklch(0.708 0 0);
  --radius: 0.625rem;
}

.dark {
  --background: oklch(0.145 0 0);
  --foreground: oklch(0.985 0 0);
  --card: oklch(0.205 0 0);
  --card-foreground: oklch(0.985 0 0);
  --popover: oklch(0.205 0 0);
  --popover-foreground: oklch(0.985 0 0);
  --primary: oklch(0.922 0 0);
  --primary-foreground: oklch(0.205 0 0);
  --secondary: oklch(0.269 0 0);
  --secondary-foreground: oklch(0.985 0 0);
  --muted: oklch(0.269 0 0);
  --muted-foreground: oklch(0.708 0 0);
  --accent: oklch(0.269 0 0);
  --accent-foreground: oklch(0.985 0 0);
  --destructive: oklch(0.704 0.191 22.216);
  --destructive-foreground: oklch(0.985 0 0);
  --border: oklch(1 0 0 / 10%);
  --input: oklch(1 0 0 / 15%);
  --ring: oklch(0.556 0 0);
  --chart-1: oklch(0.488 0.243 264.376);
  --chart-2: oklch(0.696 0.17 162.48);
  --chart-3: oklch(0.769 0.188 70.08);
  --chart-4: oklch(0.627 0.265 303.9);
  --chart-5: oklch(0.645 0.246 16.439);
  --sidebar: oklch(0.205 0 0);
  --sidebar-foreground: oklch(0.985 0 0);
  --sidebar-primary: oklch(0.488 0.243 264.376);
  --sidebar-primary-foreground: oklch(0.985 0 0);
  --sidebar-accent: oklch(0.269 0 0);
  --sidebar-accent-foreground: oklch(0.985 0 0);
  --sidebar-border: oklch(1 0 0 / 10%);
  --sidebar-ring: oklch(0.556 0 0);
}

@theme inline {
  --color-background: var(--background);
  --color-foreground: var(--foreground);
  --color-card: var(--card);
  --color-card-foreground: var(--card-foreground);
  --color-popover: var(--popover);
  --color-popover-foreground: var(--popover-foreground);
  --color-primary: var(--primary);
  --color-primary-foreground: var(--primary-foreground);
  --color-secondary: var(--secondary);
  --color-secondary-foreground: var(--secondary-foreground);
  --color-muted: var(--muted);
  --color-muted-foreground: var(--muted-foreground);
  --color-accent: var(--accent);
  --color-accent-foreground: var(--accent-foreground);
  --color-destructive: var(--destructive);
  --color-destructive-foreground: var(--destructive-foreground);
  --color-border: var(--border);
  --color-input: var(--input);
  --color-ring: var(--ring);
  --color-chart-1: var(--chart-1);
  --color-chart-2: var(--chart-2);
  --color-chart-3: var(--chart-3);
  --color-chart-4: var(--chart-4);
  --color-chart-5: var(--chart-5);
  --color-sidebar: var(--sidebar);
  --color-sidebar-foreground: var(--sidebar-foreground);
  --color-sidebar-primary: var(--sidebar-primary);
  --color-sidebar-primary-foreground: var(--sidebar-primary-foreground);
  --color-sidebar-accent: var(--sidebar-accent);
  --color-sidebar-accent-foreground: var(--sidebar-accent-foreground);
  --color-sidebar-border: var(--sidebar-border);
  --color-sidebar-ring: var(--sidebar-ring);
  --radius-sm: calc(var(--radius) - 4px);
  --radius-md: calc(var(--radius) - 2px);
  --radius-lg: var(--radius);
  --radius-xl: calc(var(--radius) + 4px);
}")

(defdoc
  Overview
  "What clojure-shadcn is and when to use it."
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "max-w-3xl p-6 space-y-2"}
     [badge/badge {:variant :secondary
                   :class "mb-4"}
      "Docs"]
     [:h1 {:class "text-3xl font-bold tracking-tight mb-1"}
      "Manufaktura - cljs components"]
     [:p {:class "text-base text-muted-foreground mb-6"}
      "Copy-paste component library for ClojureScript / Reagent / tailwindcss. Build your own component library with code you can customize, extend, and make your own."]
     [:div {:class "rounded-md border p-4 bg-card mb-6"}
      [:h3 {:class "text-sm font-semibold mb-2"}
       "Philosophy — copy, don't install"]
      [:p {:class "text-sm text-muted-foreground leading-relaxed"}
       "Every component ships as a plain ClojureScript namespace.  You copy it under src/cljs/ in your project, and from that point you own the code.  If you need a different default, change it — no upstream dependency, no version churn.  Inspired by shadcn/ui, adapted for the ClojureScript / Reagent world."]]])))

(defdoc
  Installation
  "How to bring a component into your project."
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "max-w-3xl p-6 space-y-2"}
     [:h1 {:class "text-2xl font-bold mb-4"}
      "Installation"]
     [section
      "Prerequisites"
      [:p {}
       "Components are written for Reagent and styled with Tailwind CSS. Add Reagent to your Clojure dependencies, tailwind-merge for deterministic class conflict resolution, and tw-animate-css for component animations."]
      [code-snippet
       "{:deps {reagent/reagent {:mvn/version \"2.0.1\"}}}"]
      [code-snippet "npm install tailwindcss tailwind-merge tw-animate-css"]]
     [section
      "Configure Tailwind CSS"
      [:p {}
       "Create a CSS entry point and make sure Tailwind scans your ClojureScript source. Adjust the @source path relative to the CSS file."]
      [code-snippet
       "@import \"tailwindcss\";\n@import \"tw-animate-css\";\n@source \"../../src/cljs\";\n\n@custom-variant dark (&:is(.dark *));\n\n@layer base {\n  * {\n    @apply border-border outline-ring/50;\n  }\n\n  body {\n    @apply bg-background text-foreground;\n  }\n}"]
      [:p {}
       "Copy this default light and dark theme below the imports. The @theme mapping exposes the semantic variables as Tailwind utilities such as bg-background, text-primary, and border-border."]
      [code-snippet default-theme-css]
      [:p {}
       "Customize these values for your project or choose another palette on the Theming page."]]
     [section
      "Add the class merging utility"
      [:p {}
       "Components use merge-classes to combine defaults with caller-provided Tailwind classes without keeping conflicting utilities."]
      [code-snippet
       "(ns your-app.utils.styles\n  (:require\n   [\"tailwind-merge\" :refer [twMerge]]))\n\n(defn merge-classes [& classes]\n  (twMerge (clj->js (remove nil? classes))))"]]
     [section
      "Install a component"
      [:p {}
       "Open the component you want, follow its Installation section to install only the npm primitives it imports, and copy its ClojureScript source into your project. Update the copied namespace and the merge-classes require to match your project."]]])))

(defdoc
  UsageExamples
  "Common patterns for using clojure-shadcn components."
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "max-w-3xl p-6 space-y-2"}
     [:h1 {:class "text-2xl font-bold mb-4"}
      "Usage"]
     [section
      "Compose them"
      [code-snippet
       "[:div {:class \"flex items-center gap-3\"}\n [badge/badge {:variant :secondary} \"New\"]\n [button/button {:variant :outline} \"Open docs\"]]"]
      [:p {}
       "Each component returns a plain Reagent vector. No wrapper magic, no controlled state you can't reach."]]
     [section
      "Controlled components"
      [:p {}
       "Components like theme-toggle take a :theme value and a :on-toggle handler. You own the state (atom, re-frame, app-db context). That keeps Storybook demos (and production) deterministic."]
      [code-snippet
       "[theme-toggle/theme-toggle\n  {:theme @theme\n   :on-toggle #(swap! theme {:light :dark :light})}"]]
     [section
      "Merge Tailwind classes safely"
      [:p {}
       "clojure-shadcn.utils.styles/merge-classes runs through tailwind-merge, so later classes override earlier ones deterministically — use it when combining base classes with overrides."]
      [code-snippet "(styles/merge-classes \"px-4 text-sm\" \"px-2\") ; => \"text-sm px-2\""]]])))
