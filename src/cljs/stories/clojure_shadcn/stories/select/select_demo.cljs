(ns clojure-shadcn.stories.select.select-demo
  (:require
   [clojure-shadcn.ui.components.select :as select]
   [reagent.core                        :as r]))

(defn example
  []
  (let [value (r/atom "banana")]
    (fn [] [:div {:class "p-6"}
            [select/select {:value @value
                            :on-value-change #(reset! value %)}
             [select/select-trigger {:class "w-[180px]"}
              [select/select-value {:placeholder "Select a fruit"}]]
             [select/select-content {}
              [select/select-group {}
               [select/select-label {}
                "Fruits"]
               [select/select-item {:value "apple"}
                "Apple"]
               [select/select-item {:value "banana"}
                "Banana"]
               [select/select-item {:value "blueberry"}
                "Blueberry"]
               [select/select-item {:value "grapes"}
                "Grapes"]
               [select/select-item {:value "pineapple"}
                "Pineapple"]]]]])))
