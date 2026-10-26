(ns clojure-shadcn.stories.popover.popover-text-only
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.popover :as popover]))

(defn example
  []
  [:div {:class "p-6"}
   [popover/popover {}
    [popover/popover-trigger {:as-child true}
     (button/button {:variant :outline} "Why this matters")]
    [popover/popover-content {:class "w-72"}
     [:p {:class "text-sm text-muted-foreground"}
      "Popover content can be lightweight explanatory text or callouts."]]]])
