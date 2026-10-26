(ns clojure-shadcn.stories.field.fieldset-multiple-fields
  (:require
   [clojure-shadcn.ui.components.field :as field]
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  []
  [:div {:class "p-6 max-w-md"}
   [field/field-set {}
    [field/field-legend {}
     "Billing"]
    [field/field-group {}
     [field/field {}
      [field/field-label {:html-for "card"}
       "Card Number"]
      [field/field-content {}
       [input/input {:id "card"
                     :placeholder "1234 5678 9012 3456"}]]]
     [field/field {:orientation :responsive}
      [field/field-label {:html-for "zip"}
       "ZIP"]
      [field/field-content {}
       [input/input {:id "zip"
                     :placeholder "94105"}]]]]]])
