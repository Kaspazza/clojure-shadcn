(ns clojure-shadcn.stories.input.input-badge-label
  (:require
   [clojure-shadcn.ui.components.badge :as badge]
   [clojure-shadcn.ui.components.field :as field]
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  []
  [:div {:class "max-w-sm p-6"}
   [field/field {}
    [field/field-label {:html-for "work-email"}
     "Work email"
     [badge/badge {:variant :secondary}
      "Recommended"]]
    [input/input {:id "work-email"
                  :type "email"
                  :placeholder "you@company.com"}]]])
