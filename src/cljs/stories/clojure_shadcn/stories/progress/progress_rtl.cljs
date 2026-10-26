(ns clojure-shadcn.stories.progress.progress-rtl
  (:require
   [clojure-shadcn.ui.components.progress :as progress]))

(defn example
  []
  [:div {:dir "rtl"
         :class "w-80 space-y-2 text-right"}
   [:div {:class "flex justify-between text-sm"}
    [:span {:id "rtl-progress-label"}
     "اكتمل التحميل"]
    [:span "٧٥٪"]]
   [progress/progress {:value 75
                       :aria-labelledby "rtl-progress-label"}]])
