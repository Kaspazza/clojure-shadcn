(ns clojure-shadcn.stories.select.field-select
  (:require
   [clojure-shadcn.ui.components.field  :as field]
   [clojure-shadcn.ui.components.select :as select]))

(defn example
  []
  [:div {:class "p-6 max-w-md"}
   [field/field {}
    [field/field-label {}
     "Department"]
    [select/select {:default-value "design"}
     [select/select-trigger {}
      [select/select-value {:placeholder "Choose department"}]]
     [select/select-content {}
      [select/select-item {:value "engineering"}
       "Engineering"]
      [select/select-item {:value "design"}
       "Design"]
      [select/select-item {:value "marketing"}
       "Marketing"]
      [select/select-item {:value "sales"}
       "Sales"]
      [select/select-item {:value "support"}
       "Customer Support"]
      [select/select-item {:value "hr"}
       "Human Resources"]]]
    [field/field-description {}
     "Select your department or area of work."]]])
