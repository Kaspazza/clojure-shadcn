(ns clojure-shadcn.stories.accordion.accordion-disabled-comparison
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [accordion/accordion {:type "single" :collapsible true :class "max-w-xl"}
   [accordion/accordion-item {:value "available"}
    [accordion/accordion-trigger {} "Available section"]
    [accordion/accordion-content {} "This section can be opened and closed."]]
   [accordion/accordion-item {:value "unavailable" :disabled true}
    [accordion/accordion-trigger {} "Unavailable section"]
    [accordion/accordion-content {} "This content cannot be opened."]]])
