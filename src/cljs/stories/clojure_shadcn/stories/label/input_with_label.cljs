(ns clojure-shadcn.stories.label.input-with-label
  (:require
   [clojure-shadcn.ui.components.input :as input]
   [clojure-shadcn.ui.components.label :as label]))

(defn example
  []
  [:div {:class "p-6 max-w-sm space-y-2"}
   [label/label {:html-for "email"}
    "Email"]
   [input/input {:id "email"
                 :type "email"
                 :placeholder "Email"}]])
