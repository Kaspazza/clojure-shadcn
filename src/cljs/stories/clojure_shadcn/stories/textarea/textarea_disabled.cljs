(ns clojure-shadcn.stories.textarea.textarea-disabled
  (:require
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:class "p-6 max-w-sm"}
   [textarea/textarea {:placeholder "Type your message here."
                       :disabled true}]])
