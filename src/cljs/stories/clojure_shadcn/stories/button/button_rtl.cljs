(ns clojure-shadcn.stories.button.button-rtl
  (:require
   ["lucide-react"                      :refer [ArrowLeft ArrowRight]]
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:dir "rtl"
         :class "flex items-center gap-3 p-6"}
   (button/button {} [:> ArrowRight] "التالي")
   (button/button {:variant :outline} "السابق" [:> ArrowLeft])])
