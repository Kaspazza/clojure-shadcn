(ns clojure-shadcn.stories.tooltip.sides
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.tooltip :as tooltip]))

(defn- compound-tooltip
  [{:keys [trigger content root content-props trigger-props portal arrow-props]}]
  [tooltip/tooltip-root
   root
   [tooltip/tooltip-trigger (merge {:as-child true} trigger-props) trigger]
   [tooltip/tooltip-portal
    portal
    [tooltip/tooltip-content
     content-props
     content
     (when arrow-props [tooltip/tooltip-arrow arrow-props])]]])

(defn example
  []
  [tooltip/tooltip-provider {}
   [:div {:class "grid min-h-72 grid-cols-2 place-items-center gap-20 p-16"}
    (for [side [:top :right :bottom :left]]
      ^{:key side}
      [compound-tooltip {:trigger (button/button {:variant :outline} (name side))
                         :content (str "Tooltip on the " (name side))
                         :content-props {:side side
                                         :side-offset 8}}])]])
