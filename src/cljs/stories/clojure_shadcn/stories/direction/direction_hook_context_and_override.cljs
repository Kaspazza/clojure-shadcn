(ns clojure-shadcn.stories.direction.direction-hook-context-and-override
  (:require
   [clojure-shadcn.ui.components.direction :as direction]))

(defn- direction-reader
  [{:keys [override]}]
  (let [direction (if override (direction/use-direction override) (direction/use-direction))]
    [:output {:class "rounded bg-muted px-2 py-1 font-mono text-xs"}
     (name direction)]))

(defn example
  []
  [direction/direction-provider {:direction :rtl}
   [:div {:class "flex items-center gap-4 rounded-md border p-4"}
    [:span "Context:"]
    [:f> direction-reader {}]
    [:span "Local override:"]
    [:f> direction-reader {:override :ltr}]]])
