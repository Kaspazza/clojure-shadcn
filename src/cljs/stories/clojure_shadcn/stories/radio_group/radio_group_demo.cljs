(ns clojure-shadcn.stories.radio-group.radio-group-demo
  (:require
   [clojure-shadcn.ui.components.label       :as label]
   [clojure-shadcn.ui.components.radio-group :as radio-group]
   [reagent.core                             :as r]))

(defn example
  []
  (let [value (r/atom "comfortable")]
    (fn [] [:div {:class "p-6"}
            [radio-group/radio-group {:value @value
                                      :on-value-change #(reset! value %)}
             [:div {:class "flex items-center gap-3"}
              [radio-group/radio-group-item {:value "default"
                                             :id "density-default"}]
              [label/label {:html-for "density-default"}
               "Default"]]
             [:div {:class "flex items-center gap-3"}
              [radio-group/radio-group-item {:value "comfortable"
                                             :id "density-comfortable"}]
              [label/label {:html-for "density-comfortable"}
               "Comfortable"]]
             [:div {:class "flex items-center gap-3"}
              [radio-group/radio-group-item {:value "compact"
                                             :id "density-compact"}]
              [label/label {:html-for "density-compact"}
               "Compact"]]]])))
