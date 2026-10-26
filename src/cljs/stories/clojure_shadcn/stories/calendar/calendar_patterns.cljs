(ns clojure-shadcn.stories.calendar.calendar-patterns
  (:require
   ["date-fns/locale" :refer [arSA]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.calendar :as calendar]
   [reagent.core :as r]))

(defn example
  []
  (r/with-let
   [selected (r/atom (js/Date. 2026 2 12)) time (r/atom "09:00")]
   [:div {:class "grid gap-8 lg:grid-cols-2"}
    [:section {:class "space-y-3"}
     [:h3 {:class "font-medium"}
      "Presets and appointment time"]
     [:div {:class "flex flex-wrap gap-2"}
      [button/button {:variant :outline
                      :size :sm
                      :on-click #(reset! selected (js/Date.))}
       "Today"]
      [button/button {:variant :outline
                      :size :sm
                      :on-click #(reset! selected (js/Date. 2026 2 20))}
       "March 20"]]
     [calendar/calendar {:mode "single"
                         :selected @selected
                         :on-select #(when % (reset! selected %))
                         :disabled #js [(js/Date. 2026 2 14) (js/Date. 2026 2 18)]
                         :show-week-number true}]
     [:label {:class "grid gap-1 text-sm"}
      "Time"
      [:input {:type "time"
               :value @time
               :on-change #(reset! time (.. % -target -value))
               :class "h-9 rounded-md border bg-background px-3"}]]
     [:p {:class "text-sm text-muted-foreground"}
      "Booked dates are disabled; time remains a separate native control."]]
    [:section {:class "space-y-3"
               :dir "rtl"}
     [:h3 {:class "font-medium"}
      "Arabic locale, RTL, fixed time zone"]
     [calendar/calendar {:mode "single"
                         :locale arSA
                         :dir "rtl"
                         :time-zone "Asia/Riyadh"
                         :show-week-number true}]
     [:p {:class "text-sm text-muted-foreground"}
      "Locale, direction, week numbers, and IANA time zone are forwarded to React DayPicker."]]]))
