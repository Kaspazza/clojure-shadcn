(ns clojure-shadcn.stories.system-message.system-message-filled
  (:require
   [clojure-shadcn.ui.components.system-message :as system-message]))

(defn example
  []
  [:div {:class "p-6"}
   [system-message/system-message {:variant :warning
                                   :fill true}
    "Maintenance window starts in 10 minutes."]])
