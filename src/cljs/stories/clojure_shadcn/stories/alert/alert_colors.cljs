(ns clojure-shadcn.stories.alert.alert-colors
  (:require
   ["lucide-react" :refer [CheckCircle2 CircleAlert]]
   [clojure-shadcn.ui.components.alert :as alert]))

(defn example []
  [:div {:class "grid max-w-2xl gap-3"}
   [alert/alert
    {:class "border-emerald-600/40 text-emerald-700 dark:text-emerald-400 [&>svg]:text-emerald-600"}
    [:> CheckCircle2]
    [alert/alert-title {} "Deployment complete"]
    [alert/alert-description {} "The production release is now available."]]
   [alert/alert
    {:class "border-amber-600/40 text-amber-800 dark:text-amber-300 [&>svg]:text-amber-600"}
    [:> CircleAlert]
    [alert/alert-title {} "Maintenance scheduled"]
    [alert/alert-description {} "Editing will be unavailable for approximately five minutes."]]
   [alert/alert
    {:class "border-sky-600/40 text-sky-800 dark:text-sky-300 [&>svg]:text-sky-600"}
    [:> CircleAlert]
    [alert/alert-title {} "New workspace policy"]
    [alert/alert-description {} "Review the updated access requirements before Friday."]]])
