(ns clojure-shadcn.stories.textarea.textarea-with-button
  (:require
   [clojure-shadcn.ui.components.button   :as button]
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:class "p-6 max-w-sm space-y-2"}
   [textarea/textarea {:placeholder "Type your message here."}]
   (button/button {} "Send message")])
