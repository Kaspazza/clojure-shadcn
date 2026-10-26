(ns clojure-shadcn.stories.empty.empty-card
  (:require
   ["lucide-react"                      :refer [Inbox Plus]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.card   :as card]
   [clojure-shadcn.ui.components.empty  :as empty]))

(defn example
  []
  [card/card {:class "w-full max-w-xl"}
   [card/card-content {}
    [empty/empty {:class "min-h-72 border-0 p-0"}
     [empty/empty-header {}
      [empty/empty-media {:variant :icon}
       [:> Inbox]]
      [empty/empty-title {}
       "No messages"]
      [empty/empty-description {}
       "When a teammate contacts you, the conversation will appear here."]]
     [empty/empty-content {}
      (button/button {} [:> Plus] "New message")]]]])
