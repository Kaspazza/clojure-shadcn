(ns clojure-shadcn.stories.button.button-icon
  (:require
   ["lucide-react"                      :refer [ArrowUpRight]]
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "p-6"}
   (button/button {:variant :outline
                   :size :icon
                   :aria-label "Open"}
                  [:> ArrowUpRight])])
