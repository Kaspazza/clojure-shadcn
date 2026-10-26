(ns clojure-shadcn.stories.marker.marker-timeline
  (:require
   ["lucide-react"                      :refer [CheckCircle]]
   [clojure-shadcn.ui.components.marker :as marker]))

(defn example
  []
  [:ol {:class "max-w-lg"}
   (for [[copy time] [["Build started" "09:30"] ["Tests passed" "09:32"] ["Deployed" "09:35"]]]
     ^{:key copy}
     [:li
      [marker/marker {:variant :border
                      :class "py-3"}
       [marker/marker-icon {}
        [:> CheckCircle]]
       [marker/marker-content {:class "flex flex-1 justify-between"}
        [:span copy]
        [:time time]]]])])
