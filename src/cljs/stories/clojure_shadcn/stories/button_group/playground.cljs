(ns clojure-shadcn.stories.button-group.playground
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.button-group :as button-group]))

(defn example
  ([]
   (example {:orientation :horizontal
             :first-label "Back"
             :second-label "Next"}))
  ([args]
  [button-group/button-group
   (select-keys args [:orientation])
   [button/button {:variant :outline}
    (:first-label args)]
   [button/button {:variant :outline}
    (:second-label args)]]))
