(ns clojure-shadcn.stories.slider.slider-vertical
  (:require
   [clojure-shadcn.ui.components.slider :as slider]))

(defn example
  []
  [:div {:class "flex h-64 justify-center p-6"}
   [slider/slider {:orientation :vertical
                   :default-value [35]
                   :aria-label "Volume"}]])
