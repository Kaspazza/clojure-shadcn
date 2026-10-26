(ns clojure-shadcn.stories.badge.badge-outline
  (:require
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  []
  [:div {:class "p-6"}
   [badge/badge {:variant :outline}
    "Outline"]])
