(ns clojure-shadcn.stories.checkbox.playground
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]))

(defn example
  ([]
   (example {:default-checked false
             :disabled false
             :required false}))
  ([args]
  [:div {:class "p-6"}
   [checkbox/checkbox (assoc args :id "checkbox-playground")]]))
