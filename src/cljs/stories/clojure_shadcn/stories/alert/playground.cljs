(ns clojure-shadcn.stories.alert.playground
  (:require
   ["lucide-react" :refer [CheckCircle2]]
   [clojure-shadcn.ui.components.alert :as alert]))

(defn example
  ([]
   (example {:variant :default
             :title "Success"
             :description "Your changes have been saved."}))
  ([args]
  [:div {:class "max-w-xl p-6"}
   [alert/alert (select-keys args [:variant])
    [:> CheckCircle2]
    [alert/alert-title {} (:title args)]
    [alert/alert-description {} (:description args)]]]))
