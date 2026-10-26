(ns clojure-shadcn.stories.marker.marker-shimmer-and-spinner-status
  (:require
   [clojure-shadcn.ui.components.loader :as loader]
   [clojure-shadcn.ui.components.marker :as marker]))

(defn example
  []
  [:div {:class "max-w-lg space-y-4"}
   [marker/marker {:variant :separator}
    [marker/marker-icon {}
     [loader/loader {:variant :circular
                     :size :sm
                     :label "Syncing"}]]
    [marker/marker-content {}
     "Syncing records"]]
   [marker/marker {:variant :border}
    [marker/marker-icon {}
     [loader/loader {:variant :pulse-dot
                     :size :sm
                     :label "Indexing"}]]
    [marker/marker-content {:class "animate-pulse motion-reduce:animate-none"}
     "Indexing search data"]]])
