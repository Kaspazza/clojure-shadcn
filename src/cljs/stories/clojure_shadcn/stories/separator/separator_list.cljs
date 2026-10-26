(ns clojure-shadcn.stories.separator.separator-list
  (:require
   [clojure-shadcn.ui.components.separator :as separator]))

(defn example
  []
  [:div {:class "max-w-md rounded-lg border px-4"}
   [:div {:class "py-4"}
    [:p {:class "font-medium"}
     "Notifications"]
    [:p {:class "text-sm text-muted-foreground"}
     "Email and push preferences"]]
   [separator/separator {:decorative false}]
   [:div {:class "py-4"}
    [:p {:class "font-medium"}
     "Privacy"]
    [:p {:class "text-sm text-muted-foreground"}
     "Visibility and data sharing"]]
   [separator/separator {:decorative false}]
   [:div {:class "py-4"}
    [:p {:class "font-medium"}
     "Security"]
    [:p {:class "text-sm text-muted-foreground"}
     "Password and active sessions"]]])
