(ns clojure-shadcn.stories.theme-toggle.theme-toggle-basic
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
  [:div {:class "p-6"}
   [controlled-toggle]])
