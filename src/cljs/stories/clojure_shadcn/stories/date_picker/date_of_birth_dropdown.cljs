(ns clojure-shadcn.stories.date-picker.date-of-birth-dropdown
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
                     [date-picker/date-picker-button {:data-placeholder (nil? @selected)}
                      (if @selected (date-label @selected) "Date of birth")]]
                    [date-picker/date-picker-content {}
                     [date-picker/date-picker-calendar {:mode "single"
                                                        :caption-layout :dropdown
                                                        :start-month (js/Date. 1900 0)
                                                        :end-month (js/Date.)
                                                        :default-month (js/Date. 1990 0)
                                                        :selected @selected
                                                        :on-select (fn* [%1]
                                                                     (reset! selected %1))}]]])))])
