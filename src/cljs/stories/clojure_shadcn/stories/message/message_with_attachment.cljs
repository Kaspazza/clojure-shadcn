(ns clojure-shadcn.stories.message.message-with-attachment
  (:require
   ["lucide-react"                          :refer [FileText]]
   [clojure-shadcn.ui.components.attachment :as attachment]
   [clojure-shadcn.ui.components.bubble     :as bubble]
   [clojure-shadcn.ui.components.message    :as message]))

(defn example
  []
  [message/message {:class "max-w-2xl"}
   [message/message-avatar {:src ""
                            :alt "Assistant"
                            :fallback "AI"}]
   [message/message-content {}
    [message/message-header {}
     "Assistant"]
    [bubble/bubble {:variant :ghost}
     [bubble/bubble-content {}
      "Here is the requested project brief."]]
    [attachment/attachment {}
     [attachment/attachment-media {}
      [:> FileText]]
     [attachment/attachment-content {}
      [attachment/attachment-title {}
       "project-brief.pdf"]
      [attachment/attachment-description {}
       "PDF · 1.8 MB"]]]]])
