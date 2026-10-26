(ns clojure-shadcn.stories.native-select.native-select-demo
  (:require
   [clojure-shadcn.ui.components.native-select :as native-select]))

(defn example
  []
  [native-select/native-select {:aria-label "Choose a framework"
                                :default-value ""}
   [native-select/native-select-option {:value ""
                                        :disabled true}
    "Select framework"]
   [native-select/native-select-optgroup {:label "Frontend"}
    [native-select/native-select-option {:value "reagent"}
     "Reagent"]
    [native-select/native-select-option {:value "react"}
     "React"]]
   [native-select/native-select-optgroup {:label "Backend"}
    [native-select/native-select-option {:value "clojure"}
     "Clojure"]]])
