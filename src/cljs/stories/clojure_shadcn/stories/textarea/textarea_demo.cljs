(ns clojure-shadcn.stories.textarea.textarea-demo
  (:require
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:class "p-6 max-w-sm"}
   [textarea/textarea {:placeholder "Type your message here."}]])
