(ns clojure-shadcn.stories.slider.slider-rtl
  (:require
   [clojure-shadcn.ui.components.slider :as slider]))

(defn example
  []
  [:div {:dir "rtl"
         :class "w-[420px] space-y-3 p-6"}
   [:p {:class "text-sm"}
    "مستوى الصوت"]
   [slider/slider {:dir "rtl"
                   :default-value [65]
                   :aria-label "مستوى الصوت"}]])
