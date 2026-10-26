(ns clojure-shadcn.stories.input.input-field-disabled
  (:require
   [clojure-shadcn.ui.components.field :as field]
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  []
  [:div {:class "max-w-sm p-6"}
   [field/field {:data-disabled true}
    [field/field-label {:html-for "company-managed"}
     "Organization"]
    [input/input {:id "company-managed"
                  :disabled true
                  :value "Acme Inc."
                  :read-only true}]
    [field/field-description {}
     "Managed by your administrator."]]])
