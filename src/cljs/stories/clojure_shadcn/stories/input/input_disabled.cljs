(ns clojure-shadcn.stories.input.input-disabled
  (:require
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  []
  [:div {:class "p-6 max-w-sm"}
   [input/input {:disabled true
                 :type "email"
                 :placeholder "Email"}]])
