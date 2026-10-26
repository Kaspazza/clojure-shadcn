(ns clojure-shadcn.stories.toggle-group.toggle-group-multiple
  (:require
   ["lucide-react"                            :refer [Bold Italic Underline]]
   [clojure-shadcn.ui.components.toggle-group :as toggle-group]))

(defn example
  []
  [:div {:class "p-6"}
   [toggle-group/toggle-group {:type "multiple"
                               :default-value ["bold" "italic"]
                               :size :lg
                               :aria-label "Text formatting"}
    [toggle-group/toggle-group-item {:value "bold"
                                     :aria-label "Bold"}
     [:> Bold]]
    [toggle-group/toggle-group-item {:value "italic"
                                     :aria-label "Italic"}
     [:> Italic]]
    [toggle-group/toggle-group-item {:value "underline"
                                     :disabled true
                                     :aria-label "Underline unavailable"}
     [:> Underline]]]])
