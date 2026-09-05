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

(defn- overview-section
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
        (map (fn [item]
               [:li {} item])
             items)))

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
  "What Manufaktura is, why it exists, and when to use it."
  []
  (r/as-element
   (helpers/wrap-component
    [:main {:class "mx-auto max-w-4xl space-y-10 p-6"}
     [:header {:class "space-y-4"}
      [badge/badge {:variant :secondary}
       "Overview"]
      [:h1 {:class "text-3xl font-bold tracking-tight md:text-4xl"}
       "Copy-first UI components for ClojureScript"]
      [:p {:class "max-w-3xl text-base leading-7 text-muted-foreground"}
       "Manufaktura provides Reagent components styled with Tailwind CSS and built on React libraries such as Radix UI. Copy the components you need and adapt them in your application."]]
     [callout
      "Project status"
      [:p {}
       "Manufaktura is based on more than a decade of experience building frontend applications and is used in two production projects."]]
     [overview-section
      "Where Manufaktura fits"
      [:p {}
       "A button is easy to add. A UI that stays consistent and accessible is not. Focus management, keyboard interaction, ARIA semantics, animations and component state all add complexity as an application grows."]
      [:p {}
       "A full UI framework handles much of this work, but it couples multpile boundries, brings its own component API, styling rules and release cycle. Headless libraries and CSS tools give you more control, but connecting them into a consistent UI takes time and design knowledge."]
      [:p {}
       "Manufaktura sits between those approaches. It connects focused tools through small Reagent components, then gives you the source."]]
     [:div {:class "grid gap-4 md:grid-cols-3"}
      [principle-card
       "Behavior"
       "Headless libraries"
       "Radix UI and other React libraries handle focus, keyboard navigation, portals and ARIA attributes."]
      [principle-card
       "Presentation"
       "Tailwind CSS"
       "Styles live in the component. Shared CSS variables provide theme values."]
      [principle-card
       "Application API"
       "ClojureScript and Reagent"
       "Components use props maps, keyword variants and Hiccup children. JavaScript interop stays inside the component."]]
     [overview-section
      "Not an application framework"
      [:p {}
       "Manufaktura does not require, global js dependencies, re-frame or make decisions about state, routing, data fetching or domain code. Your application decides what data components receive and what their events mean."]]
     [overview-section
      "Copy what you need"
      [:p {}
       "Manufaktura is not a runtime dependency. Choose a component, install the JavaScript packages it uses and copy its source into your project."]
      [bullet-list
       "Edit its markup, styles, variants and defaults directly."
       "Read and debug it alongside your application code."
       "Update its dependencies when your application is ready."
       "Change one component without upgrading the rest."]
      [callout
       "Copied code is your responsibility"
       [:p {}
        "Copied components do not update automatically. Your team is responsible for testing and maintaining them."]]]
     [overview-section
      "Compose the parts"
      [:p {}
       "Larger components are split into parts that you arrange with Hiccup. A dialog, for example, has separate trigger, content, title, description and close components instead of an option for every layout."]
      [:p {}
       "Some structures are required for correct behavior and accessibility. The example stories show how those parts fit together."]]
     [overview-section
      "Accessibility"
      [:p {}
       "Headless libraries provide a foundation for keyboard interaction, focus management and ARIA semantics, but they cannot make the final application accessible. Your team must preserve that behavior, label controls and test real workflows."]]])))

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
       "Components are written for "
       [:a {:href "https://github.com/reagent-project/reagent"
            :target "_blank"
            :rel "noopener noreferrer"
            :class "text-primary underline underline-offset-2 hover:opacity-80"}
        "Reagent"]
       " and styled with Tailwind CSS. Add Reagent to your Clojure dependencies, tailwind-merge for deterministic class conflict resolution, and tw-animate-css for component animations."]
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
