(ns clojure-shadcn.stories.tooltip.simple-tooltip-compatibility
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.tooltip :as tooltip]))

(defn example
  []
  [tooltip/tooltip-provider {}
   [tooltip/simple-tooltip {:trigger (button/button {:variant :outline} "Convenience API")
                            :trigger-as-child? true
                            :content "One map still composes all Radix parts."
                            :side :bottom
                            :arrow? true}]])
