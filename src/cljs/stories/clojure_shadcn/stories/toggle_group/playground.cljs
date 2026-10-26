(ns clojure-shadcn.stories.toggle-group.playground
  (:require
   [clojure-shadcn.ui.components.toggle-group :as toggle-group]))

(defn example
  ([] (example {:disabled false}))
  ([args]
   [toggle-group/toggle-group
    (assoc (select-keys args [:disabled]) :type "single" :default-value "left" :variant :outline)
    [toggle-group/toggle-group-item {:value "left"}
     "Left"]
    [toggle-group/toggle-group-item {:value "center"}
     "Center"]
    [toggle-group/toggle-group-item {:value "right"}
     "Right"]]))
