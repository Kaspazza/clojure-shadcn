(ns clojure-shadcn.stories.native-select.playground
  (:require
   [clojure-shadcn.ui.components.native-select :as native-select]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args ((fn [args] (update args :size keyword))
               (merge {:default-value "reagent"
                       :size "default"
                       :disabled false
                       :required false}
                      consumer-args))]
     [native-select/native-select
      (assoc args :aria-label "Framework")
      [native-select/native-select-option {:value "reagent"}
       "Reagent"]
      [native-select/native-select-option {:value "react"}
       "React"]
      [native-select/native-select-option {:value "clojure"}
       "Clojure"]])))
