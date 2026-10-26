(ns clojure-shadcn.stories.textarea.textarea-with-text
  (:require
   [clojure-shadcn.ui.components.label    :as label]
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:class "p-6 max-w-sm space-y-2"}
   [label/label {:html-for "message-2"}
    "Your message"]
   [textarea/textarea {:id "message-2"
                       :placeholder "Type your message here."}]
   [:p {:class "text-muted-foreground text-sm"}
    "Your message will be routed to the support team."]])
