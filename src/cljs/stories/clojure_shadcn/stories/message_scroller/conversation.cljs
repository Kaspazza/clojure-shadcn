(ns clojure-shadcn.stories.message-scroller.conversation
  (:require
   [clojure-shadcn.ui.components.message-scroller :as message-scroller]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:scroll-anchor true
                      :direction "end"}
                     consumer-args)]
     [:div {:class "h-80 max-w-xl rounded-lg border"}
      [message-scroller/message-scroller-provider {}
       [message-scroller/message-scroller {}
        [message-scroller/message-scroller-viewport {}
         [message-scroller/message-scroller-content {:class "p-4"}
          (for [n (range 1 9)]
            ^{:key n}
            [message-scroller/message-scroller-item {:scroll-anchor (and (:scroll-anchor args)
                                                                         (= n 8))}
             [:div {:class "rounded-lg bg-muted p-3 text-sm"}
              (str "Conversation message " n)]])]]
        [message-scroller/message-scroller-button (select-keys args [:direction])]]]])))
