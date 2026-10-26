(ns clojure-shadcn.stories.radio-group.radio-group-disabled
  (:require
   [clojure-shadcn.ui.components.label       :as label]
   [clojure-shadcn.ui.components.radio-group :as radio-group]))

(defn example
  []
  [:div {:class "p-6"}
   [radio-group/radio-group {:default-value "standard"}
    [:div {:class "flex items-center gap-3"}
     [radio-group/radio-group-item {:value "standard"
                                    :id "plan-standard"}]
     [label/label {:html-for "plan-standard"}
      "Standard"]]
    [:div {:class "flex items-center gap-3"}
     [radio-group/radio-group-item {:value "premium"
                                    :id "plan-premium"
                                    :disabled true}]
     [label/label {:html-for "plan-premium"
                   :class "text-muted-foreground"}
      "Premium (coming soon)"]]]])
