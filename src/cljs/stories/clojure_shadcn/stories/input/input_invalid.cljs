(ns clojure-shadcn.stories.input.input-invalid
  (:require
   [clojure-shadcn.ui.components.input :as input]
   [clojure-shadcn.ui.components.label :as label]))

(defn example
  []
  [:div {:class "p-6 max-w-sm space-y-2"}
   [label/label {:html-for "email-invalid"}
    "Email"]
   [input/input {:id "email-invalid"
                 :type "email"
                 :aria-invalid true
                 :default-value "not-an-email"}]
   [:p {:class "text-destructive text-sm"}
    "Please enter a valid email address."]])
