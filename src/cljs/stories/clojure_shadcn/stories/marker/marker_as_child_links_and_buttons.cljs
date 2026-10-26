(ns clojure-shadcn.stories.marker.marker-as-child-links-and-buttons
  (:require
   ["lucide-react"                      :refer [Clock Info]]
   [clojure-shadcn.ui.components.marker :as marker]))

(defn example
  []
  [:div {:class "max-w-lg space-y-3"}
   [marker/marker {:as-child? true}
    [:a {:href "https://example.com"
         :target "_blank"
         :rel "noreferrer"}
     [marker/marker-icon {}
      [:> Info]]
     [marker/marker-content {}
      "Open deployment details"]]]
   [marker/marker {:as-child? true
                   :variant :border}
    [:button {:type "button"
              :on-click #(js/alert "Retry requested")}
     [marker/marker-icon {}
      [:> Clock]]
     [marker/marker-content {}
      "Retry deployment"]]]])
