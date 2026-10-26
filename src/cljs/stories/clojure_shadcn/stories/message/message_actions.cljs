(ns clojure-shadcn.stories.message.message-actions
  (:require
   ["lucide-react"                       :refer [Copy Trash2]]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.message :as message]))

(defn example
  []
  [:div {:class "p-6"}
   [message/message {}
    [message/message-avatar {:src "https://placehold.co/40x40/png"
                             :alt "User"
                             :fallback "JD"}]
    [:div {:class "flex flex-col gap-2"}
     [message/message-content {}
      "Here is a message with actions."]
     [message/message-actions {}
      [message/message-action {:tooltip "Copy"}
       (button/button {:variant :ghost
                       :size :icon}
                      [:> Copy {:class "size-4"}])]
      [message/message-action {:tooltip "Delete"}
       (button/button {:variant :ghost
                       :size :icon}
                      [:> Trash2 {:class "size-4"}])]]]]])
