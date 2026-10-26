(ns clojure-shadcn.stories.switch.playground
  (:require
   [clojure-shadcn.ui.components.switch :as switch]))

(defn example
  ([]
   (example {:default-checked false
             :disabled false
             :required false}))
  ([args]
   [:div {:class "p-6"}
    [switch/switch (assoc args :id "switch-playground")]]))
