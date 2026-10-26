(ns clojure-shadcn.stories.input.input-required
  (:require
   [clojure-shadcn.ui.components.field :as field]
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  []
  [:div {:class "max-w-sm p-6"}
   [field/field {}
    [field/field-label {:html-for "required-email"}
     "Email "
     [:span {:aria-hidden true
             :class "text-destructive"}
      "*"]]
    [input/input {:id "required-email"
                  :type "email"
                  :required true
                  :aria-required true}]]])
