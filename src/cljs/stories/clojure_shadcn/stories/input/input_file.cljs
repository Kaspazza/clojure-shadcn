(ns clojure-shadcn.stories.input.input-file
  (:require
   [clojure-shadcn.ui.components.input :as input]
   [clojure-shadcn.ui.components.label :as label]))

(defn example
  []
  [:div {:class "p-6 max-w-sm space-y-2"}
   [label/label {:html-for "resume"}
    "Resume"]
   [input/input {:id "resume"
                 :type "file"}]])
