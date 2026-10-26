(ns clojure-shadcn.stories.tooltip.disabled-trigger
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
   [compound-tooltip
    {:trigger
     [:span
      {:tab-index 0
       :aria-label "Why publishing is unavailable"
       :class
       "inline-flex rounded-md focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring"}
      (button/button {:disabled true
                      :class "pointer-events-none"}
                     "Publish")]
     :content "Complete all required fields before publishing."}]])
