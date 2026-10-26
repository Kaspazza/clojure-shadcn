(ns clojure-shadcn.stories.textarea.textarea-invalid
  (:require
   [clojure-shadcn.ui.components.label    :as label]
   [clojure-shadcn.ui.components.textarea :as textarea]))

(defn example
  []
  [:div {:class "p-6 max-w-sm space-y-2"}
   [label/label {:html-for "feedback-invalid"}
    "Feedback"]
   [textarea/textarea {:id "feedback-invalid"
                       :aria-invalid true
                       :default-value "bad"
                       :placeholder "Tell us what happened..."}]
   [:p {:class "text-destructive text-sm"}
    "Feedback should be at least 10 characters."]])
