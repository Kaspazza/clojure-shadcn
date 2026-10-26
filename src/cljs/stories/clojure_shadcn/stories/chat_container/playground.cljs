(ns clojure-shadcn.stories.chat-container.playground
  (:require [clojure-shadcn.ui.components.chat-container :as chat-container]))

(defn example
  ([]
   (example {:resize "smooth"
             :initial "instant"}))
  ([args]
  [:div {:class "p-6"}
   [chat-container/chat-container-root
    (assoc (select-keys args [:resize :initial]) :class "h-64 w-full rounded-md border")
    [chat-container/chat-container-content {:class "p-4 space-y-3"}
     (for [idx (range 1 8)]
       ^{:key idx}
       [:div {:class "rounded-lg bg-muted px-3 py-2 text-sm"} (str "Message " idx)])
     [chat-container/chat-container-scroll-anchor {}]]]]))
