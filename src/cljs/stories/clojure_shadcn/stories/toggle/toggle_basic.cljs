(ns clojure-shadcn.stories.toggle.toggle-basic
  (:require
   ["lucide-react"                      :refer [Bold Italic]]
   [clojure-shadcn.ui.components.toggle :as toggle]))

(defn example
  []
  [:div {:class "flex gap-3 p-6"}
   [toggle/toggle {:aria-label "Toggle bold"}
    [:> Bold]]
   [toggle/toggle {:variant :outline
                   :aria-label "Toggle italic"}
    [:> Italic]]])
