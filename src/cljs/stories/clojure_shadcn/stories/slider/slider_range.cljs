(ns clojure-shadcn.stories.slider.slider-range
  (:require
   [clojure-shadcn.ui.components.slider :as slider]))

(defn example
  []
  [:div {:class "w-[420px] p-6"}
   [slider/slider {:default-value [25 75]
                   :min 0
                   :max 100
                   :thumb-labels ["Minimum price" "Maximum price"]}]])
