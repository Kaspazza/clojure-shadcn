(ns clojure-shadcn.stories.select.select-disabled-items
  (:require
   [clojure-shadcn.ui.components.select :as select]))

(defn example
  []
  [:div {:class "p-6"}
   [select/select {:default-value "pro"}
    [select/select-trigger {:class "w-[200px]"}
     [select/select-value {:placeholder "Choose a plan"}]]
    [select/select-content {}
     [select/select-item {:value "starter"}
      "Starter"]
     [select/select-item {:value "pro"}
      "Pro"]
     [select/select-item {:value "enterprise"
                          :disabled true}
      "Enterprise (contact us)"]]]])
