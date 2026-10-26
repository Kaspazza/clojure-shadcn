(ns clojure-shadcn.stories.date-picker.input-and-time
  (:require
   [clojure-shadcn.ui.components.date-picker :as date-picker]
   [clojure-shadcn.ui.components.input       :as input]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (let [date-label
           (fn [date]
             (if date (.toLocaleDateString date "en-US" #js {:dateStyle "long"}) "Pick a date"))
           date->iso (fn [date]
                       (when date
                         (str (.getFullYear date)
                              "-" (.padStart (str (inc (.getMonth date))) 2 "0")
                              "-" (.padStart (str (.getDate date)) 2 "0"))))
           iso->date
           (fn [value]
             (when-let [[_ year month day] (re-matches #"(\d{4})-(\d{2})-(\d{2})" value)]
               (js/Date. (js/parseInt year 10) (dec (js/parseInt month 10)) (js/parseInt day 10))))]
       (r/with-let
        [selected
         (r/atom (js/Date. 2026 2 12))
         date-input
         (r/atom "2026-03-12")
         time
         (r/atom "09:00")]
        [:div {:class "grid max-w-sm gap-3"}
         [input/input {:aria-label "Date"
                       :placeholder "yyyy-MM-dd"
                       :value @date-input
                       :on-change (fn* [%1]
                                    (let [value (.. %1 -target -value)]
                                      (reset! date-input value)
                                      (when-let [date (iso->date value)] (reset! selected date))))}]
         [date-picker/composed-date-picker {}
          [date-picker/date-picker-trigger {}
           [date-picker/date-picker-button {}
            (date-label @selected)]]
          [date-picker/date-picker-content {}
           [date-picker/date-picker-calendar
            {:mode "single"
             :selected @selected
             :on-select (fn* [%1]
                          (when %1 (reset! selected %1) (reset! date-input (date->iso %1))))}]]]
         [input/input {:aria-label "Appointment time"
                       :type "time"
                       :value @time
                       :on-change (fn* [%1] (reset! time (.. %1 -target -value)))}]
         [:p {:class "text-sm text-muted-foreground"}
          (str "Form value: " @date-input "T" @time)]])))])
