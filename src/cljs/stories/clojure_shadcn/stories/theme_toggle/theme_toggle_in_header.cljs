(ns clojure-shadcn.stories.theme-toggle.theme-toggle-in-header
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
     [controlled-toggle]]]])
