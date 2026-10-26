(ns clojure-shadcn.stories.scroll-button.scroll-button-standalone
  (:require
   [clojure-shadcn.ui.components.chat-container :as chat-container]
   [clojure-shadcn.ui.components.scroll-button  :as scroll-button]))

(defn example
  []
  [:div {:class "p-6"}
   [chat-container/chat-container-root {:class "relative h-40 w-full rounded-md border"}
    [chat-container/chat-container-content {:class "p-4 space-y-3"}
     (for [idx (range 1 12)]
       ^{:key idx}
       [:div {:class "rounded-lg bg-muted px-3 py-2 text-sm"}
        (str "Update " idx)])
     [chat-container/chat-container-scroll-anchor {}]]
    [scroll-button/scroll-button {:class "absolute bottom-3 right-3"}]]])
