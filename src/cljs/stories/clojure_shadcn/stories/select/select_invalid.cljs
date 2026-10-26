(ns clojure-shadcn.stories.select.select-invalid
  (:require
   [clojure-shadcn.ui.components.field  :as field]
   [clojure-shadcn.ui.components.select :as select]))

(defn example
  []
  [:div {:class "p-6 max-w-sm space-y-2"}
   [field/field {}
    [field/field-label {}
     "Department"]
    [select/select {:default-value nil}
     [select/select-trigger {:aria-invalid true}
      [select/select-value {:placeholder "Choose department"}]]
     [select/select-content {}
      [select/select-item {:value "engineering"}
       "Engineering"]
      [select/select-item {:value "design"}
       "Design"]]]
    [field/field-description {:class "text-destructive"}
     "Department is required."]]])
