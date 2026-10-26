(ns clojure-shadcn.stories.direction.playground
  (:require
   [clojure-shadcn.ui.components.direction :as direction]))

(defn example
  ([] (example {:dir "ltr"}))
  ([args]
   [direction/direction-provider
    (select-keys args [:dir])
    [:div {:dir (:dir args)
           :class "rounded-md border p-4"}
     "Direction-aware content"]]))
