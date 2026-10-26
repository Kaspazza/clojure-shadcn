(ns clojure-shadcn.stories.input-group.playground
  (:require
   ["lucide-react"                           :refer [Search]]
   [clojure-shadcn.ui.components.input-group :as input-group]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args ((fn [args] (update args :addon-align keyword))
               (merge {:placeholder "Search…"
                       :disabled false
                       :addon-align "inline-end"}
                      consumer-args))]
     [:div {:class "w-80"}
      [input-group/input-group {}
       [input-group/input-group-addon {:align (:addon-align args)}
        [:> Search {:aria-hidden true}]]
       [input-group/input-group-input {:aria-label "Search"
                                       :placeholder (:placeholder args)
                                       :disabled (:disabled args)}]]])))
