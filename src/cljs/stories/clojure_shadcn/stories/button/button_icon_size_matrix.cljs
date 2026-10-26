(ns clojure-shadcn.stories.button.button-icon-size-matrix
  (:require
   ["lucide-react"                      :refer [ArrowUpRight]]
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "flex items-center gap-3 p-6"}
   (for [[size label]
         [[:icon-xs "Extra-small"] [:icon-sm "Small"] [:icon "Default"] [:icon-lg "Large"]]]
     ^{:key (name size)}
     (button/button {:variant :outline
                     :size size
                     :aria-label (str label " external link")}
                    [:> ArrowUpRight]))])
