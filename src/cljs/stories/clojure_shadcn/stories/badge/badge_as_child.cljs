(ns clojure-shadcn.stories.badge.badge-as-child
  (:require
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  []
  [:div {:class "p-6"}
   [badge/badge {:as-child true}
    [:a {:href "#"
         :class "inline-flex items-center gap-1"}
     "View status"]]])
