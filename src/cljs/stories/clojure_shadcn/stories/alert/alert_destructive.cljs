(ns clojure-shadcn.stories.alert.alert-destructive
  (:require
   ["lucide-react" :refer [CircleAlert]]
   [clojure-shadcn.ui.components.alert :as alert]))

(defn example []
  [:div {:class "max-w-xl p-6"}
   [alert/alert {:variant :destructive}
    [:> CircleAlert]
    [alert/alert-title {} "Unable to continue"]
    [alert/alert-description {} "Resolve the validation errors and try again."]]])
