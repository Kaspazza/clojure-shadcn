(ns clojure-shadcn.stories.toggle.toggle-outline-disabled
  (:require
   ["lucide-react"                      :refer [Bold Italic]]
   [clojure-shadcn.ui.components.toggle :as toggle]))

(defn example
  []
  [:div {:class "flex gap-3 p-6"}
   [toggle/toggle {:variant :outline
                   :default-pressed true}
    [:> Bold]
    "Bold"]
   [toggle/toggle {:variant :outline
                   :disabled true}
    [:> Italic]
    "Italic"]])
