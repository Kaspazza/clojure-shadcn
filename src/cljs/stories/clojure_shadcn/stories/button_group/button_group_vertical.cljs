(ns clojure-shadcn.stories.button-group.button-group-vertical
  (:require
   ["lucide-react"                            :refer [Bold Italic Underline]]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.button-group :as button-group]))

(defn example
  []
  [button-group/button-group {:orientation :vertical}
   [button/button {:variant :outline
                   :size :icon
                   :aria-label "Bold"}
    [:> Bold]]
   [button/button {:variant :outline
                   :size :icon
                   :aria-label "Italic"}
    [:> Italic]]
   [button/button {:variant :outline
                   :size :icon
                   :aria-label "Underline"}
    [:> Underline]]])
