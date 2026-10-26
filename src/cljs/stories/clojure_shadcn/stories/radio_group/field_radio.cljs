(ns clojure-shadcn.stories.radio-group.field-radio
  (:require
   [clojure-shadcn.ui.components.field       :as field]
   [clojure-shadcn.ui.components.radio-group :as radio-group]
   [reagent.core                             :as r]))

(defn example
  []
  (let [value (r/atom "monthly")]
    (fn [] [:div {:class "p-6 max-w-md"}
            [field/field-set {}
             [field/field-label {}
              "Subscription Plan"]
             [field/field-description {}
              "Yearly and lifetime plans offer significant savings."]
             [radio-group/radio-group {:value @value
                                       :on-value-change #(reset! value %)}
              [field/field {:orientation :horizontal}
               [radio-group/radio-group-item {:value "monthly"
                                              :id "plan-monthly"}]
               [field/field-label {:html-for "plan-monthly"
                                   :class "font-normal"}
                "Monthly ($9.99/month)"]]
              [field/field {:orientation :horizontal}
               [radio-group/radio-group-item {:value "yearly"
                                              :id "plan-yearly"}]
               [field/field-label {:html-for "plan-yearly"
                                   :class "font-normal"}
                "Yearly ($99.99/year)"]]
              [field/field {:orientation :horizontal}
               [radio-group/radio-group-item {:value "lifetime"
                                              :id "plan-lifetime"}]
               [field/field-label {:html-for "plan-lifetime"
                                   :class "font-normal"}
                "Lifetime ($299.99)"]]]]])))
