(ns clojure-shadcn.stories.accordion.accordion-enclosing-border
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [accordion/accordion {:type "single" :collapsible true
                        :class "max-w-xl overflow-hidden rounded-lg border px-4"}
   [accordion/accordion-item {:value "one"}
    [accordion/accordion-trigger {} "Is the border a component variant?"]
    [accordion/accordion-content {} "No. It is composed with classes on the root."]]
   [accordion/accordion-item {:value "two" :class "border-b-0"}
    [accordion/accordion-trigger {} "Do items remain separated?"]
    [accordion/accordion-content {} "Yes. Item dividers preserve the row structure."]]])
