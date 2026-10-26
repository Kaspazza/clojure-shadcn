(ns clojure-shadcn.stories.message-scroller.message-scroller-group-chat
  (:require
   [clojure-shadcn.ui.components.bubble           :as bubble]
   [clojure-shadcn.ui.components.message          :as message]
   [clojure-shadcn.ui.components.message-scroller :as message-scroller]))

(defn example
  []
  [:div {:class "h-96 max-w-2xl rounded-lg border"}
   [message-scroller/message-scroller-provider {:defaultScrollPosition "end"
                                                :scrollPreviousItemPeek 56}
    [message-scroller/message-scroller {}
     [message-scroller/message-scroller-viewport {:aria-label "Design team transcript"}
      [message-scroller/message-scroller-content {:class "gap-4 p-4"}
       (for [{:keys [id sender initials align text]}
             [{:id "ava-1"
               :sender "Ava"
               :initials "AV"
               :align :start
               :text "The interaction notes are ready for review."}
              {:id "noah-1"
               :sender "Noah"
               :initials "NO"
               :align :start
               :text "I’ll check keyboard and reduced-motion behavior."}
              {:id "you-1"
               :sender "You"
               :initials "ME"
               :align :end
               :text "Great. I’ll verify the responsive states."}
              {:id "ava-2"
               :sender "Ava"
               :initials "AV"
               :align :start
               :text "Please keep the previous turn visible when opening this reply."}]]
         ^{:key id}
         [message-scroller/message-scroller-item {:message-id id
                                                  :scroll-anchor true}
          [message/message {:align align}
           [message/message-avatar {:src ""
                                    :alt sender
                                    :fallback initials}]
           [message/message-content {}
            [message/message-header {}
             sender]
            [bubble/bubble {:align align
                            :variant (if (= align :end) :default :muted)}
             [bubble/bubble-content {}
              text]]]]])]]
     [message-scroller/message-scroller-button {}]
     [message-scroller/message-scroller-button {:direction "start"}]]]])
