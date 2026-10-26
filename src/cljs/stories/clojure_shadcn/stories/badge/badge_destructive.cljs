(ns clojure-shadcn.stories.badge.badge-destructive
  (:require
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  []
  [:div {:class "p-6"}
   [badge/badge {:variant :destructive}
    "Destructive"]])
