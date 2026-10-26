(ns clojure-shadcn.stories.popover.playground
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.popover :as popover]))

(defn example
  ([] (example {:side "bottom"}))
  ([args]
   [popover/popover {}
    [popover/popover-trigger {:as-child true}
     [button/button {:variant :outline}
      "Open popover"]]
    [popover/popover-content (select-keys args [:side]) "Place content here."]]))
