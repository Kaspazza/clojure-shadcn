(ns clojure-shadcn.stories.tooltip.delayed
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
  [tooltip/tooltip-provider {:delay-duration 1200
                             :skip-delay-duration 300}
   [:div {:class "flex gap-4 p-6"}
    [compound-tooltip {:trigger (button/button {:variant :outline} "1.2 second delay")
                       :content "Uses the provider delay."}]
    [compound-tooltip {:root {:delay-duration 0}
                       :trigger (button/button {:variant :outline} "Instant override")
                       :content "This root opens immediately."}]]])
