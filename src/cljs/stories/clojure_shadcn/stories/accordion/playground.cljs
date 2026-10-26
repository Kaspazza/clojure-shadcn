(ns clojure-shadcn.stories.accordion.playground
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example
  ([]
   (example {:collapsible true}))
  ([args]
  [accordion/accordion {:type "single" :collapsible (:collapsible args)}
   [accordion/accordion-item {:value "item-1"}
    [accordion/accordion-trigger {} "Can I close this section after opening it?"]
    [accordion/accordion-content {} "Yes when collapsible is enabled; otherwise this section remains open."]]]))
