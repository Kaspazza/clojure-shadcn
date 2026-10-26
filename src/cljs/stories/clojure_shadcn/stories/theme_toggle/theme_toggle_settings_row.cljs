(ns clojure-shadcn.stories.theme-toggle.theme-toggle-settings-row
  (:require
   [clojure-shadcn.ui.components.theme-toggle :as theme-toggle]
   [reagent.core                              :as r]))





(defn- controlled-toggle
  []
  (r/with-let [theme (r/atom :light)]
              [theme-toggle/theme-toggle
               {:theme @theme
                :on-toggle
                (fn []
                  (let [next-theme (if (= @theme :dark) :light :dark)]
                    (.toggle (.-classList js/document.documentElement) "dark" (= next-theme :dark))
                    (reset! theme next-theme)))}]))

(defn example
  []
  [:div {:class "p-6 max-w-sm"}
   [:div {:class "flex items-center justify-between rounded-md border px-4 py-3"}
    [:div {:class "space-y-1"}
     [:p {:class "text-sm font-medium"}
      "Dark mode"]
     [:p {:class "text-xs text-muted-foreground"}
      "Switch theme for the application."]]
    [controlled-toggle]]])
