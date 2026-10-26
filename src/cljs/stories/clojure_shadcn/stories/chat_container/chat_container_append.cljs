(ns clojure-shadcn.stories.chat-container.chat-container-append
  (:require
   [clojure-shadcn.ui.components.chat-container :as chat-container]
   [reagent.core :as r]))

(defn example []
  (let [messages (r/atom ["Project created" "Repository connected" "First build passed"])]
    (fn []
      [:div {:class "max-w-xl space-y-3"}
       [chat-container/chat-container-root {:class "h-56 rounded-md border"}
        [chat-container/chat-container-content {:class "space-y-3 p-4"}
         (for [[index message] (map-indexed vector @messages)]
           ^{:key index}
           [:div {:class "rounded-lg bg-muted px-3 py-2 text-sm"} message])
         [chat-container/chat-container-scroll-anchor {}]]]
       [:button {:type "button" :class "rounded-md border px-3 py-2 text-sm"
                 :on-click #(swap! messages conj (str "Update " (inc (count @messages))))}
        "Append message"]])))
