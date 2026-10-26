(ns clojure-shadcn.stories.progress.progress-label
  (:require
   [clojure-shadcn.ui.components.progress :as progress]))

(defn example
  []
  [:div {:class "w-80 space-y-2"}
   [:div {:class "flex items-center justify-between text-sm"}
    [:span {:id "storage-label"
            :class "font-medium"}
     "Storage used"]
    [:span {:class "text-muted-foreground tabular-nums"}
     "32 of 50 GB"]]
   [progress/progress {:value 64
                       :aria-labelledby "storage-label"}]])
