(ns clojure-shadcn.stories.message.playground
  (:require
   [clojure-shadcn.ui.components.message :as message]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args ((fn [{:keys [align]
                     :as args}]
                 (cond-> args
                   align (update :align keyword)))
               (merge {:align "start"
                       :content "Hello! This is a basic message."
                       :fallback "JD"}
                      consumer-args))]
     [:div {:class "p-6"}
      [message/message
       (select-keys args [:align])
       [message/message-avatar {:src "https://placehold.co/40x40/png"
                                :alt "User"
                                :fallback (:fallback args)}]
       [message/message-content {}
        (:content args)]]])))
