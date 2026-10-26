(ns clojure-shadcn.stories.switch.switch-invalid
  (:require
   [clojure-shadcn.ui.components.label  :as label]
   [clojure-shadcn.ui.components.switch :as switch]))

(defn example
  []
  [:div {:class "p-6 space-y-2"}
   [:div {:class "flex items-center gap-2"}
    [switch/switch {:id "terms-invalid"
                    :aria-invalid true
                    :checked false}]
    [label/label {:html-for "terms-invalid"}
     "Accept terms"]]
   [:p {:class "text-destructive text-sm"}
    "You must accept terms to continue."]])
