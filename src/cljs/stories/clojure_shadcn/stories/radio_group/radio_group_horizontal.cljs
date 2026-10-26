(ns clojure-shadcn.stories.radio-group.radio-group-horizontal
  (:require
   [clojure-shadcn.ui.components.label       :as label]
   [clojure-shadcn.ui.components.radio-group :as radio-group]
   [reagent.core                             :as r]))

(defn example
  []
  (let [value (r/atom "monthly")]
    (fn [] [:div {:class "p-6"}
            [radio-group/radio-group {:value @value
                                      :orientation :horizontal
                                      :class "flex items-center gap-6"
                                      :on-value-change #(reset! value %)}
             [:div {:class "flex items-center gap-2"}
              [radio-group/radio-group-item {:value "monthly"
                                             :id "cycle-monthly"}]
              [label/label {:html-for "cycle-monthly"}
               "Monthly"]]
             [:div {:class "flex items-center gap-2"}
              [radio-group/radio-group-item {:value "yearly"
                                             :id "cycle-yearly"}]
              [label/label {:html-for "cycle-yearly"}
               "Yearly"]]]])))
