(ns clojure-shadcn.stories.checkbox.checkbox-field-disabled
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.field    :as field]))

(defn example
  []
  [:div {:class "max-w-md p-6"}
   [field/field {:orientation :horizontal
                 :data-disabled true}
    [checkbox/checkbox {:id "checkbox-field-disabled"
                        :disabled true
                        :checked true}]
    [field/field-content {}
     [field/field-label {:html-for "checkbox-field-disabled"}
      "Share analytics"]
     [field/field-description {}
      "Managed by your organization."]]]])
