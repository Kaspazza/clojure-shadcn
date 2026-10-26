(ns clojure-shadcn.stories.message-scroller.message-scroller-commands-and-visibility
  (:require
   [clojure-shadcn.ui.components.message-scroller :as message-scroller]
   [reagent.core                                  :refer [defc]]))

(defc scroller-controls
 []
 (let [^js commands (message-scroller/use-message-scroller)
       ^js scrollable (message-scroller/use-message-scroller-scrollable)
       ^js visibility (message-scroller/use-message-scroller-visibility)
       ^js visible (.-visibleMessageIds visibility)]
   [:div {:class "flex flex-wrap items-center gap-2 border-t p-2 text-xs"}
    [:button {:type "button"
              :class "rounded border px-2 py-1"
              :on-click #((.-scrollToStart commands) #js {:behavior "smooth"})}
     "Start"]
    [:button {:type "button"
              :class "rounded border px-2 py-1"
              :on-click #((.-scrollToMessage commands)
                           "turn-3"
                           #js {:align "center"
                                :behavior "smooth"})}
     "Turn 3"]
    [:button {:type "button"
              :class "rounded border px-2 py-1"
              :on-click #((.-scrollToEnd commands) #js {:behavior "smooth"})}
     "End"]
    [:span {:class "text-muted-foreground"}
     (str "scrollable: " (when (.-start scrollable) "start ") (when (.-end scrollable) "end"))]
    [:span {:class "text-muted-foreground"}
     (str "anchor: " (or (.-currentAnchorId visibility) "none")
          " · visible: " (if visible (.join visible ", ") "none"))]]))

(defn example
  []
  [:div {:class "h-80 max-w-xl rounded-lg border"}
   [message-scroller/message-scroller-provider {:scrollMargin 16}
    [message-scroller/message-scroller {}
     [message-scroller/message-scroller-viewport {:aria-label "Command demo transcript"}
      [message-scroller/message-scroller-content {:class "gap-3 p-4"}
       (for [n (range 1 9)]
         ^{:key n}
         [message-scroller/message-scroller-item {:message-id (str "turn-" n)
                                                  :scroll-anchor (odd? n)}
          [:div {:class "rounded-lg bg-muted p-4 text-sm"}
           (str "Turn " n)]])]]
     [message-scroller/message-scroller-button {}]
     [message-scroller/message-scroller-button {:direction "start"}]]
    [scroller-controls]]])
