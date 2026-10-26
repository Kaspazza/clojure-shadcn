(ns clojure-shadcn.stories.slider.playground
  (:require
   [clojure-shadcn.ui.components.slider :as slider]))

(defn example
  ([]
   (example {:default-value [40]
             :min 0
             :max 100
             :step 1
             :disabled false}))
  ([args]
   [:div {:class "w-[420px] p-6"}
    [slider/slider (assoc args :aria-label "Value")]]))
