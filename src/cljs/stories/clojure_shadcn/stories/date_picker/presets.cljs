(ns clojure-shadcn.stories.date-picker.presets
  (:require
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.date-picker :as date-picker]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (let [date-label
           (fn [date]
             (if date (.toLocaleDateString date "en-US" #js {:dateStyle "long"}) "Pick a date"))
           add-days (fn [date days]
                      (doto (js/Date. (.getTime date)) (.setDate (+ (.getDate date) days))))]
       (r/with-let [selected (r/atom (js/Date.))]
                   [:div {:class "flex flex-col gap-3"}
                    [:div {:class "flex gap-2"}
                     [button/button {:variant :outline
                                     :size :sm
                                     :on-click (fn* [] (reset! selected (js/Date.)))}
                      "Today"]
                     [button/button {:variant :outline
                                     :size :sm
                                     :on-click (fn* [] (reset! selected (add-days (js/Date.) 7)))}
                      "In one week"]]
                    [date-picker/composed-date-picker {}
                     [date-picker/date-picker-trigger {}
                      [date-picker/date-picker-button {}
                       (date-label @selected)]]
                     [date-picker/date-picker-content {}
                      [date-picker/date-picker-calendar
                       {:mode "single"
                        :selected @selected
                        :on-select (fn* [%1] (when %1 (reset! selected %1)))}]]]])))])
