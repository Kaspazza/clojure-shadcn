(ns clojure-shadcn.stories.badge.playground
  (:require
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  ([]
   (example {:variant :default
             :label "Badge"}))
  ([args]
  [:div {:class "p-6"}
   [badge/badge (select-keys args [:variant]) (:label args)]]))
