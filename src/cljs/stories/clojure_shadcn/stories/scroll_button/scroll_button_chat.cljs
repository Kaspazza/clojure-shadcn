(ns clojure-shadcn.stories.scroll-button.scroll-button-chat
  (:require
   [clojure-shadcn.ui.components.chat-container :as chat-container]
   [clojure-shadcn.ui.components.scroll-button  :as scroll-button]))

(defn example
  ([]
   (example ((fn [{:keys [variant size]
                   :as args}]
               (cond-> args
                 variant (update :variant keyword)
                 size (update :size keyword)))
             {:variant "outline"
              :size "sm"})))
  ([args]
   [:div {:class "p-6"}
    [chat-container/chat-container-root {:class "relative h-64 w-full rounded-md border"}
     [chat-container/chat-container-content {:class "p-4 space-y-3"}
      (for [idx (range 1 20)]
        ^{:key idx}
        [:div {:class "rounded-lg bg-muted px-3 py-2 text-sm"}
         (str "Line " idx)])
      [chat-container/chat-container-scroll-anchor {}]]
     [scroll-button/scroll-button
      (assoc (select-keys args [:variant :size]) :class "absolute bottom-4 right-4")]]]))
