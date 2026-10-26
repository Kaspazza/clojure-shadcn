(ns clojure-shadcn.stories.accordion.accordion-disabled
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [:div {:class "max-w-xl p-6"}
   [accordion/accordion {:type "single"}
    [accordion/accordion-item {:value "locked" :disabled true}
     [accordion/accordion-trigger {} "Unavailable section"]
     [accordion/accordion-content {} "Cannot be opened."]]]])
