(ns clojure-shadcn.stories.field.field-with-error
  (:require
   [clojure-shadcn.ui.components.field :as field]
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  []
  [:div {:class "p-6 max-w-md"}
   [field/field {}
    [field/field-label {:html-for "email"}
     "Email"]
    [field/field-content {}
     [input/input {:id "email"
                   :placeholder "name@example.com"
                   :aria-invalid true}]
     [field/field-error {:errors [{:message "Email is required"}
                                  {:message "Email must be valid"}]}]]]])
