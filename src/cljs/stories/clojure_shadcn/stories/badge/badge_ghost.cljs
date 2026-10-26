(ns clojure-shadcn.stories.badge.badge-ghost
  (:require
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  []
  [:div {:class "p-6"}
   [badge/badge {:variant :ghost}
    "Ghost"]])
