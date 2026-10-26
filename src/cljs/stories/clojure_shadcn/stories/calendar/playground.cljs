(ns clojure-shadcn.stories.calendar.playground
  (:require
   [clojure-shadcn.ui.components.calendar :as calendar]))

(defn example
  ([] (example {:show-outside-days true
                :caption-layout "label"
                :number-of-months 1}))
  ([args]
   [calendar/calendar (-> args
                          (update :caption-layout keyword)
                          (assoc :mode "single"))]))
