(ns clojure-shadcn.stories.collapsible.playground
  (:require
   [clojure-shadcn.ui.components.collapsible :as collapsible]))

(defn example
  ([] (example {:default-open false}))
  ([args]
   [collapsible/collapsible
    (select-keys args [:default-open])
    [collapsible/collapsible-trigger {:class "rounded-md border px-3 py-2"}
     "Toggle details"]
    [collapsible/collapsible-content {:class "pt-2 text-sm"}
     "This content can be expanded."]]))
