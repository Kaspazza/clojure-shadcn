(ns clojure-shadcn.stories.slider.slider-basic
  (:require
   [clojure-shadcn.ui.components.slider :as slider]
   [reagent.core                        :as r]))

(defn example
  []
  (let [value (r/atom [40])]
    (fn [] [:div {:class "w-[420px] p-6 space-y-3"}
            [:p {:class "text-sm"}
             (str "Volume: " (first @value))]
            [slider/slider {:value @value
                            :on-value-change #(reset! value (js->clj %))
                            :max 100
                            :step 1
                            :aria-label "Volume"}]])))
