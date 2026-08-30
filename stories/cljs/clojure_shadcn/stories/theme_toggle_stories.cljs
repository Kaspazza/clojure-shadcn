(ns clojure-shadcn.stories.theme-toggle-stories
  "Storybook stories for the Theme Toggle component."
  (:require
   [clojure-shadcn.stories.helpers            :as helpers]
   [clojure-shadcn.ui.components.theme-toggle :as sut]
   [reagent.core                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/ThemeToggle"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Controlled theme toggle button for switching between light and dark themes."
           :npm-install "npm install lucide-react"
           :source-code (embed-source "clojure-shadcn.ui.components.theme-toggle")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/theme_toggle.cljs"
           :filename "theme_toggle.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "theme-toggle"
       :description
       "Controlled theme switch button. Pure presentation: reads nothing from global state; the caller passes :theme and :on-toggle."
       :props [{:name ":theme"
                :type "keyword"
                :default ":light"
                :description ":light | :dark — decides which icon (Sun/Moon) is shown."}
               {:name ":on-toggle"
                :type "fn"
                :default nil
                :description "(fn []) click handler; flip theme in whatever state layer you use."}
               {:name ":class"
                :type "string"
                :default nil
                :description
                "Extra Tailwind classes merged with the base ones via tailwind-merge."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Controlled component: render as [theme-toggle {:theme @theme :on-toggle #(swap! ... )}]."]
       [:li
        "The component never touches document or state — wire the theme change yourself (e.g. toggle the `dark` class on document.documentElement, persist to localStorage, dispatch to re-frame)."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"flex items-center justify-between\"}\n [:span {:class \"text-sm\"} \"Theme\"]\n [theme-toggle {:theme @theme\n                :on-toggle #(toggle-theme!)}]]"]]]]])))

;; ── Private helpers (no ^:export — not stories)
;; ──────────────────────────────

(defonce ^:private demo-theme (r/atom :light))

(defn- toggle-demo-theme!
  "Flip the demo atom and mirror it onto document.documentElement's dark class."
  []
  (let [next (if (= @demo-theme :dark) :light :dark)]
    (.toggle (.-classList js/document.documentElement) "dark" (= next :dark))
    (reset! demo-theme next)))

(defn- controlled-toggle
  "Live demo: a reagent atom owns the theme, document class follows."
  []
  [sut/theme-toggle {:theme @demo-theme
                     :on-toggle toggle-demo-theme!}])

;; ── Stories ─────────────────────────────────────────────────────────────────

(defstory
 ThemeToggleBasic
 "Live theme toggle. Clicking flips an atom and mirrors it onto the
  document root's `dark` class — the standard wiring for Tailwind dark
  mode. The component itself stays pure: it only fires :on-toggle."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [controlled-toggle]])))

(defstory ThemeToggleLight
          "Static render pinned to :light (Sun icon visible)."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/theme-toggle {:theme :light}]])))

(defstory ThemeToggleDark
          "Static render pinned to :dark (Moon icon visible)."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/theme-toggle {:theme :dark}]])))

(defstory ThemeToggleInHeader
          "Composition demo: toggle inside a minimal header."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "relative h-20 rounded-md border bg-background"}
                          [:div {:class "flex h-full items-center justify-between px-4"}
                           [:span {:class "text-sm font-semibold"}
                            "Brand"]
                           [:div {:class "flex items-center gap-4"}
                            [:a {:href "#"
                                 :class "text-sm"}
                             "Home"]
                            [:a {:href "#"
                                 :class "text-sm"}
                             "Docs"]
                            [controlled-toggle]]]])))

(defstory ThemeToggleSettingsRow
          "Settings panel row with descriptive text next to the live toggle."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "p-6 max-w-sm"}
                          [:div {:class
                                 "flex items-center justify-between rounded-md border px-4 py-3"}
                           [:div {:class "space-y-1"}
                            [:p {:class "text-sm font-medium"}
                             "Dark mode"]
                            [:p {:class "text-xs text-muted-foreground"}
                             "Switch theme for the application."]]
                           [controlled-toggle]]])))
