(ns clojure-shadcn.stories.bubble.bubble-interactive-compositions
  (:require
   [clojure-shadcn.ui.components.bubble :as bubble]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.collapsible :as collapsible]
   [clojure-shadcn.ui.components.popover :as popover]
   [clojure-shadcn.ui.components.tooltip :as tooltip]))

(defn example []
  [:> tooltip/tooltip-provider-component {}
   [bubble/bubble-group {:class "max-w-lg"}
    [collapsible/collapsible {}
     [bubble/bubble {:variant :outline}
      [collapsible/collapsible-trigger {:as-child true}
       [bubble/bubble-content {:as-child? true}
        [:button {:type "button"} "Show delivery details"]]]]
     [collapsible/collapsible-content {:class "px-3 py-2 text-sm text-muted-foreground"}
      "Delivered to the project channel at 09:42."]]
    [bubble/bubble {:variant :tinted}
     [tooltip/tooltip {:trigger (bubble/bubble-content {:as-child? true}
                                                      [:button {:type "button"} "Hover for response timing"])
                       :trigger-as-child? true
                       :content "Estimated response time: under a minute"}]]
    [popover/popover {}
     [popover/popover-trigger {:asChild true}
      (button/button {:variant :ghost :size :sm} "Open response details")]
     [popover/popover-content {:align "start"}
      [:p {:class "text-sm"} "Generated from three indexed project documents."]]]]])
