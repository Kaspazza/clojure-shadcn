(ns clojure-shadcn.stories.message-scroller.message-scroller-anchors-and-open-positions
  (:require
   [clojure-shadcn.ui.components.message-scroller :as message-scroller]))

(defn example
  []
  [:div {:class "grid gap-4 lg:grid-cols-3"}
   (for [position ["start" "end" "last-anchor"]]
     ^{:key position}
     [:div {:class "space-y-2"}
      [:p {:class "text-sm font-medium"}
       (str "Open at " position)]
      [:div {:class "h-72 rounded-lg border"}
       [message-scroller/message-scroller-provider {:defaultScrollPosition position
                                                    :scrollPreviousItemPeek 72}
        [message-scroller/message-scroller {}
         [message-scroller/message-scroller-viewport {}
          [message-scroller/message-scroller-content {:class "gap-3 p-4"}
           (for [n (range 1 7)]
             ^{:key n}
             [message-scroller/message-scroller-item {:message-id (str "position-" position "-" n)
                                                      :scroll-anchor (contains? #{2 4 6} n)}
              [:div {:class "space-y-1"}
               (when (contains? #{2 4 6} n)
                 [:p {:class "text-xs font-medium text-primary"}
                  (str "Turn " (/ n 2))])
               [:div {:class "rounded-lg bg-muted p-3 text-sm"}
                (str (if (odd? n) "Previous assistant context " "User anchor ") n)]]])]]
         [message-scroller/message-scroller-button {}]
         [message-scroller/message-scroller-button {:direction "start"}]]]]])])
