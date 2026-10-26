(ns clojure-shadcn.stories.badge.badge-link
  (:require
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  []
  [:div {:class "p-6"}
   [badge/badge {:variant :link}
    [:a {:href "#"}
     "Link"]]])
