(ns clojure-shadcn.stories.native-select.native-select-invalid
  (:require
   [clojure-shadcn.ui.components.native-select :as native-select]))

(defn example
  []
  [:div {:class "max-w-sm space-y-2 p-6"}
   [native-select/native-select {:default-value ""
                                 :required true
                                 :aria-invalid true
                                 :aria-describedby "framework-error"
                                 :aria-label "Framework"}
    [native-select/native-select-option {:value ""
                                         :disabled true}
     "Select a framework"]
    [native-select/native-select-option {:value "reagent"}
     "Reagent"]]
   [:p {:id "framework-error"
        :class "text-sm text-destructive"}
    "Choose a framework."]])
