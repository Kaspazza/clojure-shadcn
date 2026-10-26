(ns clojure-shadcn.stories.chat-container.chat-container-streaming
  (:require
   [clojure-shadcn.ui.components.chat-container :as chat-container]
   [reagent.core :as r]))

(defn example []
  (let [chunks ["Streaming" " keeps" " the" " current" " response" " visible" " as" " it" " grows."]
        text (r/atom (first chunks))
        timer (atom nil)]
    (r/create-class
     {:component-did-mount
      (fn [_]
        (let [index (atom 1)]
          (reset! timer
                  (js/setInterval
                   (fn []
                     (if (< @index (count chunks))
                       (do (swap! text str (nth chunks @index))
                           (swap! index inc))
                       (js/clearInterval @timer)))
                   450))))
      :component-will-unmount #(when @timer (js/clearInterval @timer))
      :reagent-render
      (fn []
        [chat-container/chat-container-root {:class "h-56 max-w-xl rounded-md border"}
         [chat-container/chat-container-content {:class "space-y-3 p-4"}
          [:div {:class "rounded-lg bg-muted px-3 py-2 text-sm"}
           "Summarize the release status."]
          [:div {:class "rounded-lg border px-3 py-2 text-sm"} @text]
          [chat-container/chat-container-scroll-anchor {}]]])})))
