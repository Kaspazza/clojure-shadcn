(ns clojure-shadcn.stories.system-message.playground
  (:require
   [clojure-shadcn.ui.components.system-message :as system-message]))

(defn example
  ([]
   (example ((fn [{:keys [variant]
                   :as args}]
               (cond-> args
                 variant (update :variant keyword)))
             {:variant "action"
              :fill false
              :icon-hidden? false
              :message "This is an informational system message."})))
  ([args]
   [:div {:class "p-6"}
    [system-message/system-message
     (select-keys args [:variant :fill :icon-hidden?])
     (:message args)]]))
