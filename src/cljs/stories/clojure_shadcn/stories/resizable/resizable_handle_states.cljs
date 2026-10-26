(ns clojure-shadcn.stories.resizable.resizable-handle-states
  (:require
   [clojure-shadcn.ui.components.resizable :as resizable]))

(defn example
  []
  [:div {:class "grid gap-6"}
   [:div {:class "h-32 w-[520px] overflow-hidden rounded-lg border"}
    [resizable/panel-group {:orientation :horizontal}
     [resizable/panel {:defaultSize 40}
      [:div {:class "grid h-full place-items-center"}
       "No icon"]]
     [resizable/handle {:aria-label "Resize plain panels"}]
     [resizable/panel {:defaultSize 60}
      [:div {:class "grid h-full place-items-center bg-muted/30"}
       "Still draggable"]]]
    [:div {:class "h-32 w-[520px] overflow-hidden rounded-lg border"}
     [resizable/panel-group {:orientation :horizontal}
      [resizable/panel {:defaultSize 40}
       [:div {:class "grid h-full place-items-center"}
        "Focused"]]
      [resizable/handle {:with-handle? true
                         :aria-label "Resize focused panels"
                         :class "ring-[3px] ring-ring/50 border-ring"}]
      [resizable/panel {:defaultSize 60}
       [:div {:class "grid h-full place-items-center bg-muted/30"}
        "Handle"]]]]]])
