(ns clojure-shadcn.stories.chat-container.chat-container-composition
  (:require [clojure-shadcn.ui.components.chat-container :as chat-container]))

(defn example []
  [:div {:class "p-6"}
   [:div {:class "rounded-md border"}
    [:div {:class "border-b px-4 py-2 text-sm font-medium"} "Team Chat"]
    [chat-container/chat-container-root {:class "h-56"}
     [chat-container/chat-container-content {:class "p-4 space-y-3"}
      (for [idx (range 1 10)]
        ^{:key idx}
        [:div {:class "rounded-lg bg-muted px-3 py-2 text-sm"} (str "Message " idx)])
      [chat-container/chat-container-scroll-anchor {}]]]
    [:div {:class "border-t px-4 py-2 text-xs text-muted-foreground"}
     "Typing indicator goes here."]]])
