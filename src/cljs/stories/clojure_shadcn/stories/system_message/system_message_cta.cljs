(ns clojure-shadcn.stories.system-message.system-message-cta
  (:require
   [clojure-shadcn.ui.components.system-message :as system-message]))

(defn example
  []
  [:div {:class "p-6"}
   [system-message/system-message {:variant :error
                                   :cta {:label "Retry"
                                         :on-click #(js/console.log "retry")}}
    "A network error occurred."]])
