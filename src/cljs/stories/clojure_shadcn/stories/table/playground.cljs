(ns clojure-shadcn.stories.table.playground
  (:require
   [clojure-shadcn.ui.components.table :as table]))

(defn example
  ([] (example {:class ""}))
  ([args]
   [table/table
    (select-keys args [:class])
    [table/table-header {}
     [table/table-row {}
      [table/table-head {}
       "Name"]
      [table/table-head {}
       "Status"]]]
    [table/table-body {}
     [table/table-row {}
      [table/table-cell {}
       "Acme Inc."]
      [table/table-cell {}
       "Active"]]]]))
