(ns clojure-shadcn.stories.native-select.native-select-rtl
  (:require
   [clojure-shadcn.ui.components.native-select :as native-select]))

(defn example
  []
  [:div {:dir "rtl"
         :class "max-w-sm p-6"}
   [native-select/native-select {:default-value "ar"
                                 :aria-label "اللغة"}
    [native-select/native-select-option {:value "ar"}
     "العربية"]
    [native-select/native-select-option {:value "en"}
     "الإنجليزية"]]])
