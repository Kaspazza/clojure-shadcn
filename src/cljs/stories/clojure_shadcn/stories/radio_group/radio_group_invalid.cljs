(ns clojure-shadcn.stories.radio-group.radio-group-invalid
  (:require
   [clojure-shadcn.ui.components.label       :as label]
   [clojure-shadcn.ui.components.radio-group :as radio-group]))

(defn example
  []
  [:div {:class "p-6 space-y-2"}
   [radio-group/radio-group {:default-value nil}
    [:div {:class "flex items-center gap-3"}
     [radio-group/radio-group-item {:value "free"
                                    :id "plan-free"
                                    :aria-invalid true}]
     [label/label {:html-for "plan-free"}
      "Free"]]
    [:div {:class "flex items-center gap-3"}
     [radio-group/radio-group-item {:value "pro"
                                    :id "plan-pro"
                                    :aria-invalid true}]
     [label/label {:html-for "plan-pro"}
      "Pro"]]]
   [:p {:class "text-destructive text-sm"}
    "Please select a billing plan."]])
