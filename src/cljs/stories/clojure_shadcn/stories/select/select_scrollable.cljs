(ns clojure-shadcn.stories.select.select-scrollable
  (:require
   [clojure-shadcn.ui.components.select :as select]
   [reagent.core                        :as r]))

(defn example
  []
  (let [value (r/atom "cet")]
    (fn [] [:div {:class "p-6"}
            [select/select {:value @value
                            :on-value-change #(reset! value %)}
             [select/select-trigger {:class "w-[280px]"}
              [select/select-value {:placeholder "Select a timezone"}]]
             [select/select-content {}
              [select/select-group {}
               [select/select-label {}
                "North America"]
               [select/select-item {:value "est"}
                "Eastern Standard Time (EST)"]
               [select/select-item {:value "cst"}
                "Central Standard Time (CST)"]
               [select/select-item {:value "mst"}
                "Mountain Standard Time (MST)"]
               [select/select-item {:value "pst"}
                "Pacific Standard Time (PST)"]
               [select/select-item {:value "akst"}
                "Alaska Standard Time (AKST)"]
               [select/select-item {:value "hst"}
                "Hawaii Standard Time (HST)"]]
              [select/select-group {}
               [select/select-label {}
                "Europe & Africa"]
               [select/select-item {:value "gmt"}
                "Greenwich Mean Time (GMT)"]
               [select/select-item {:value "cet"}
                "Central European Time (CET)"]
               [select/select-item {:value "eet"}
                "Eastern European Time (EET)"]
               [select/select-item {:value "west"}
                "Western European Summer Time (WEST)"]
               [select/select-item {:value "cat"}
                "Central Africa Time (CAT)"]
               [select/select-item {:value "eat"}
                "East Africa Time (EAT)"]]]]])))
