(ns clojure-shadcn.stories.message-scroller.message-scroller-streaming
  (:require
   [clojure-shadcn.ui.components.message-scroller :as message-scroller]
   [reagent.core                                  :as r]))

(defn example
  []
  [(fn []
     (let [text (r/atom "Generating")
           timer (atom nil)]
       (r/create-class
        {:component-did-mount
         (fn [_]
           (let [chunks
                 [" a" " concise" " release" " summary" " from" " current" " build" " results."]
                 index (atom 0)]
             (reset! timer (js/setInterval #(if (< @index (count chunks))
                                              (do (swap! text str (nth chunks @index))
                                                  (swap! index inc))
                                              (js/clearInterval @timer))
                                           400))))
         :component-will-unmount #(when @timer (js/clearInterval @timer))
         :reagent-render
         (fn [] [:div {:class "h-72 max-w-xl rounded-lg border"}
                 [message-scroller/message-scroller-provider {:autoScroll true}
                  [message-scroller/message-scroller {}
                   [message-scroller/message-scroller-viewport {}
                    [message-scroller/message-scroller-content {:class "gap-3 p-4"}
                     [message-scroller/message-scroller-item {:message-id "question"
                                                              :scroll-anchor true}
                      [:div {:class "rounded-lg bg-primary p-3 text-sm text-primary-foreground"}
                       "Summarize this release."]]
                     [message-scroller/message-scroller-item {:message-id "stream"}
                      [:div {:class "rounded-lg bg-muted p-3 text-sm"}
                       @text]]]]
                   [message-scroller/message-scroller-button {}]]]])})))])
