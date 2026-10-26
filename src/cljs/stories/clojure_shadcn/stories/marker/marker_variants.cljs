(ns clojure-shadcn.stories.marker.marker-variants
  (:require
   ["lucide-react"                      :refer [CheckCircle]]
   [clojure-shadcn.ui.components.marker :as marker]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args ((fn [{:keys [variant]
                     :as args}]
                 (cond-> args
                   variant (update :variant keyword)))
               (merge {:variant "default"
                       :content "Deployment completed"}
                      consumer-args))]
     [:div {:class "max-w-lg"}
      [marker/marker
       (select-keys args [:variant])
       [marker/marker-icon {}
        [:> CheckCircle]]
       [marker/marker-content {}
        (:content args)]]])))
