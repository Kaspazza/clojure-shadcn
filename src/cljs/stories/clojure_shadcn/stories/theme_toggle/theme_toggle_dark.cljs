(ns clojure-shadcn.stories.theme-toggle.theme-toggle-dark
  (:require
   [clojure-shadcn.ui.components.theme-toggle :as theme-toggle]))

(defn example
  []
  [:div {:class "p-6"}
   [theme-toggle/theme-toggle {:theme :dark
                               :aria-label "Dark theme specimen"
                               :disabled true}]])
