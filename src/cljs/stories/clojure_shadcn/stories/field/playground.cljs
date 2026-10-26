(ns clojure-shadcn.stories.field.playground
  (:require
   [clojure-shadcn.ui.components.field :as field]
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  ([]
   (example ((fn [args] (update args :orientation keyword))
             {:orientation "vertical"
              :placeholder "Jane Doe"
              :disabled false})))
  ([args]
   [:div {:class "max-w-md p-6"}
    [field/field {:orientation (:orientation args)}
     [field/field-label {:html-for "field-playground"}
      "Name"]
     [input/input {:id "field-playground"
                   :placeholder (:placeholder args)
                   :disabled (:disabled args)}]]]))
