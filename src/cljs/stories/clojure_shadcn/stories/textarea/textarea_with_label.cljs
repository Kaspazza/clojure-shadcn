(ns clojure-shadcn.stories.textarea.textarea-with-label
  (:require
   [clojure-shadcn.ui.components.label    :as label]
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:class "p-6 max-w-sm space-y-2"}
   [label/label {:html-for "message"}
    "Your message"]
   [textarea/textarea {:id "message"
                       :placeholder "Type your message here."}]])
