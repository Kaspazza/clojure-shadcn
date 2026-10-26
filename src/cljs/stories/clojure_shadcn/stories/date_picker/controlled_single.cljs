(ns clojure-shadcn.stories.date-picker.controlled-single
  (:require
   [clojure-shadcn.ui.components.date-picker :as date-picker]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (let [date-label
           (fn [date]
             (if date (.toLocaleDateString date "en-US" #js {:dateStyle "long"}) "Pick a date"))]
       (r/with-let [selected (r/atom nil) open? (r/atom false)]
                   [date-picker/composed-date-picker {:open @open?
                                                      :on-open-change (fn* [%1] (reset! open? %1))}
                    [date-picker/date-picker-trigger {}
                     [date-picker/date-picker-button {:data-placeholder (nil? @selected)}
                      (date-label @selected)]]
                    [date-picker/date-picker-content {}
                     [date-picker/date-picker-calendar
                      {:mode "single"
                       :selected @selected
                       :on-select (fn* [%1]
                                    (when %1 (reset! selected %1) (reset! open? false)))}]]])))])
