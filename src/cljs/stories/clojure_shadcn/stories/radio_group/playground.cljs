(ns clojure-shadcn.stories.radio-group.playground
  (:require
   [clojure-shadcn.ui.components.label       :as label]
   [clojure-shadcn.ui.components.radio-group :as radio-group]))

(defn example
  ([]
   (example ((fn [args] (update args :orientation keyword))
             {:default-value "comfortable"
              :orientation "vertical"
              :disabled false
              :required false})))
  ([args]
   [:div {:class "p-6"}
    [radio-group/radio-group
     args
     [:div {:class "flex items-center gap-2"}
      [radio-group/radio-group-item {:id "density-default-playground"
                                     :value "default"}]
      [label/label {:html-for "density-default-playground"}
       "Default"]]
     [:div {:class "flex items-center gap-2"}
      [radio-group/radio-group-item {:id "density-comfortable-playground"
                                     :value "comfortable"}]
      [label/label {:html-for "density-comfortable-playground"}
       "Comfortable"]]]]))
