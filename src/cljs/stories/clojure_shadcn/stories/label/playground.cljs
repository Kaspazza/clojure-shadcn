(ns clojure-shadcn.stories.label.playground
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.label    :as label]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:label "Accept terms and conditions"
                      :disabled false}
                     consumer-args)]
     [:div {:class "p-6"}
      [:div {:class "flex items-center gap-2"}
       [checkbox/checkbox {:id "terms"
                           :disabled (:disabled args)}]
       [label/label {:html-for "terms"}
        (:label args)]]])))
