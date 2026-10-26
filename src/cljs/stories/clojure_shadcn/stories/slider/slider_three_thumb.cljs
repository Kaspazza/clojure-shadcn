(ns clojure-shadcn.stories.slider.slider-three-thumb
  (:require
   [clojure-shadcn.ui.components.slider :as slider]))

(defn example
  []
  [:div {:class "w-[420px] p-6"}
   [slider/slider {:default-value [20 50 80]
                   :min 0
                   :max 100
                   :thumb-labels ["Low threshold" "Target" "High threshold"]}]])
