(ns clojure-shadcn.stories.accordion.accordion-non-collapsible
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [:div {:class "max-w-xl p-6"}
   [:p {:class "mb-3 text-sm text-muted-foreground"}
    "One section always remains open because :collapsible is false."]
   [accordion/accordion {:type "single" :collapsible false :default-value "one" :class "w-full"}
    [accordion/accordion-item {:value "one"}
     [accordion/accordion-trigger {} "Can I close the active section?"]
     [accordion/accordion-content {} "No. With :collapsible false, a single accordion keeps one item open."]]
    [accordion/accordion-item {:value "two"}
     [accordion/accordion-trigger {} "Can I open a different section?"]
     [accordion/accordion-content {} "Yes. Opening this section closes the previously active section."]]]])
