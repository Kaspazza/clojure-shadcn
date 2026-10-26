(ns clojure-shadcn.stories.system-message.system-message-warning
  (:require
   [clojure-shadcn.ui.components.system-message :as system-message]))

(defn example
  []
  [:div {:class "p-6"}
   [system-message/system-message {:variant :warning}
    "Warning: please review your inputs."]])
