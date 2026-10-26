(ns clojure-shadcn.stories.progress.progress-slider-controlled
  (:require
   [clojure-shadcn.ui.components.progress :as progress]
   [clojure-shadcn.ui.components.slider   :as slider]
   [reagent.core                          :as r]))

(defn example
  []
  (let [value (r/atom 40)]
    (fn [] [:div {:class "w-80 space-y-5"}
            [:div {:class "flex justify-between text-sm"}
             [:span "Completion"]
             [:output {:class "tabular-nums"}
              (str @value "%")]]
            [progress/progress {:value @value
                                :aria-label "Completion"}]
            [slider/slider {:value [@value]
                            :on-value-change #(reset! value (first %))
                            :aria-label "Set completion"}]])))
