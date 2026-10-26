(ns clojure-shadcn.stories.toggle.toggle-icon-and-text
  (:require
   ["lucide-react"                      :refer [Bold Underline]]
   [clojure-shadcn.ui.components.toggle :as toggle]))

(defn example
  []
  [:div {:class "flex gap-3 p-6"}
   [toggle/toggle {:aria-label "Toggle bold"}
    [:> Bold]
    "Bold"]
   [toggle/toggle {:variant :outline
                   :aria-label "Toggle underline"}
    [:> Underline]
    "Underline"]])
