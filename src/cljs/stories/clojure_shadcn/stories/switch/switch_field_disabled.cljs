(ns clojure-shadcn.stories.switch.switch-field-disabled
  (:require
   [clojure-shadcn.ui.components.field  :as field]
   [clojure-shadcn.ui.components.switch :as switch]))

(defn example
  []
  [:div {:class "max-w-md p-6"}
   [field/field {:orientation :horizontal
                 :data-disabled true}
    [field/field-content {}
     [field/field-label {:html-for "switch-disabled-field"}
      "Automatic updates"]
     [field/field-description {}
      "Required by your administrator."]]
    [switch/switch {:id "switch-disabled-field"
                    :disabled true
                    :checked true}]]])
