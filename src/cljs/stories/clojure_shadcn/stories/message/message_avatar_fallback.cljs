(ns clojure-shadcn.stories.message.message-avatar-fallback
  (:require
   [clojure-shadcn.ui.components.message :as message]))

(defn example
  []
  [:div {:class "p-6"}
   [message/message {}
    [message/message-avatar {:src ""
                             :alt "User"
                             :fallback "AL"}]
    [message/message-content {}
     "Fallback initials are shown."]]])
