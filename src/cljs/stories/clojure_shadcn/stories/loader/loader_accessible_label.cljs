(ns clojure-shadcn.stories.loader.loader-accessible-label
  (:require
   [clojure-shadcn.ui.components.loader :as loader]))

(defn example
  []
  [:div {:class "p-6 flex items-center gap-3"}
   [loader/loader {:variant :circular
                   :label "Saving account settings"}]
   [:span {:class "text-sm"}
    "Saving account settings"]])
