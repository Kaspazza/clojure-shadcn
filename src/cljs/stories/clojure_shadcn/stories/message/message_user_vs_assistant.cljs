(ns clojure-shadcn.stories.message.message-user-vs-assistant
  (:require
   [clojure-shadcn.ui.components.bubble  :as bubble]
   [clojure-shadcn.ui.components.message :as message]))

(defn example
  []
  [:div {:class "p-6 space-y-4"}
   [message/message {:align :end}
    [message/message-avatar {:src "https://placehold.co/40x40/png"
                             :alt "User"
                             :fallback "ME"}]
    [message/message-content {}
     [bubble/bubble {:align :end}
      [bubble/bubble-content {}
       "User message aligned right."]]]]
   [message/message {}
    [message/message-avatar {:src "https://placehold.co/40x40/png"
                             :alt "Assistant"
                             :fallback "AI"}]
    [message/message-content {}
     [bubble/bubble {:variant :muted}
      [bubble/bubble-content {}
       "Assistant response aligned left."]]]]])
