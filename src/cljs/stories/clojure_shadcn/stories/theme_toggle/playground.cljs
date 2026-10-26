(ns clojure-shadcn.stories.theme-toggle.playground
  (:require
   [clojure-shadcn.ui.components.theme-toggle :as theme-toggle]
   [reagent.core                              :as r]))

(defn example
  ([] (example {:class ""}))
  ([_args]
   (let [theme (r/atom :light)]
     (fn [args] [theme-toggle/theme-toggle
                 {:theme @theme
                  :class (:class args)
                  :on-toggle #(swap! theme (fn [current] (if (= current :dark) :light :dark)))}]))))
