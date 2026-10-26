(ns clojure-shadcn.stories.date-picker.controlled-range
  (:require
   [clojure-shadcn.ui.components.date-picker :as date-picker]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (let [date-label
           (fn [date]
             (if date (.toLocaleDateString date "en-US" #js {:dateStyle "long"}) "Pick a date"))]
       (r/with-let [selected (r/atom nil)]
                   [date-picker/composed-date-picker {}
                    [date-picker/date-picker-trigger {}
                     [date-picker/date-picker-button {:class "w-[320px]"}
                      (if-let [from (some-> @selected
                                            .-from)]
                        (str (date-label from)
                             (when-let [to (aget @selected "to")] (str " – " (date-label to))))
                        "Pick a date range")]]
                    [date-picker/date-picker-content {}
                     [date-picker/date-picker-calendar {:mode "range"
                                                        :number-of-months 2
                                                        :selected @selected
                                                        :on-select (fn* [%1]
                                                                     (reset! selected %1))}]]])))])
