(ns clojure-shadcn.stories.checkbox.checkbox-with-text
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.label    :as label]))

(defn example
  []
  [:div {:class "p-6"}
   [:div {:class "flex items-start gap-2"}
    [checkbox/checkbox {:id "terms-3"}]
    [:div {:class "grid gap-1.5 leading-none"}
     [label/label {:html-for "terms-3"}
      "Accept terms and conditions"]
     [:p {:class "text-muted-foreground text-sm"}
      "You agree to our Terms of Service and Privacy Policy."]]]])
