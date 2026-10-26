(ns clojure-shadcn.stories.system-message.system-message-filled-cta-custom-icon
  (:require
   ["lucide-react"                              :refer [Bell]]
   [clojure-shadcn.ui.components.system-message :as system-message]))

(defn example
  []
  [:div {:class "p-6"}
   [system-message/system-message {:variant :action
                                   :fill true
                                   :icon Bell
                                   :cta {:label "Review"
                                         :on-click #(js/console.log "review invitations")}}
    "Three workspace invitations need your review."]])
