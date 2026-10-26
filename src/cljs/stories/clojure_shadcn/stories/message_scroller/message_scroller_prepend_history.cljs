(ns clojure-shadcn.stories.message-scroller.message-scroller-prepend-history
  (:require
   [clojure-shadcn.ui.components.message-scroller :as message-scroller]
   [reagent.core                                  :as r]))

(defn example
  []
  [(fn []
     (let [messages (r/atom (vec (range 11 21)))]
       (fn [] [:div {:class "max-w-xl space-y-2"}
               [:button {:type "button"
                         :class "rounded-md border px-3 py-2 text-sm"
                         :on-click #(swap! messages (fn [items]
                                                      (into (vec (range (- (first items) 5)
                                                                        (first items)))
                                                            items)))}
                "Load previous messages"]
               [:div {:class "h-72 rounded-lg border"}
                [message-scroller/message-scroller-provider {:defaultScrollPosition "start"}
                 [message-scroller/message-scroller {}
                  [message-scroller/message-scroller-viewport {:preserveScrollOnPrepend true}
                   [message-scroller/message-scroller-content {:class "gap-3 p-4"}
                    (for [n @messages]
                      ^{:key n}
                      [message-scroller/message-scroller-item
                       {:message-id (str "history-" n)
                        :class "transition-opacity duration-300 starting:opacity-0"}
                       [:div {:class "rounded-lg bg-muted p-3 text-sm"}
                        (str "Message " n)]])]]]]]])))])
