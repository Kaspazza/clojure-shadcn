(ns clojure-shadcn.stories.date-picker.right-to-left
  (:require
   ["date-fns/locale"                        :refer [arSA]]
   [clojure-shadcn.ui.components.date-picker :as date-picker]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (r/with-let [selected (r/atom nil)]
                 [:div {:dir "rtl"}
                  [date-picker/composed-date-picker {}
                   [date-picker/date-picker-trigger {}
                    [date-picker/date-picker-button {:data-placeholder (nil? @selected)}
                     (if @selected (.toLocaleDateString @selected "ar-SA") "اختر تاريخًا")]]
                   [date-picker/date-picker-content {:align "start"}
                    [date-picker/date-picker-calendar {:mode "single"
                                                       :dir "rtl"
                                                       :locale arSA
                                                       :selected @selected
                                                       :on-select (fn* [%1]
                                                                    (reset! selected %1))}]]]]))])
