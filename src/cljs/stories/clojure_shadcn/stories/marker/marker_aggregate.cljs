(ns clojure-shadcn.stories.marker.marker-aggregate
  (:require
   ["lucide-react"                      :refer [CheckCircle Clock Info]]
   [clojure-shadcn.ui.components.marker :as marker]))

(defn example
  []
  [:div {:class "w-full max-w-xl space-y-5"}
   (for [[variant copy icon] [[:default "Informational marker" [:> Info]]
                              [:separator "Separated milestone" [:> CheckCircle]]
                              [:border "Bordered status row" [:> Clock]]]]
     ^{:key variant}
     [marker/marker {:variant variant}
      [marker/marker-icon {}
       icon]
      [marker/marker-content {}
       copy]])])
