(ns clojure-shadcn.stories.calendar.single-date
  (:require
   [clojure-shadcn.ui.components.calendar :as calendar]
   [reagent.core :as r]))

(defn example
  []
  (r/with-let [selected (r/atom nil)]
    [calendar/calendar {:mode "single"
                        :selected @selected
                        :on-select #(reset! selected %)}]))
