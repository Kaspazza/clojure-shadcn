(ns clojure-shadcn.stories.separator.separator-custom
  (:require
   [clojure-shadcn.ui.components.separator :as separator]))

(defn example
  []
  [:div {:class "p-6 space-y-3"}
   [:p {:class "text-sm"}
    "Primary accent"]
   [separator/separator {:class "bg-primary h-[2px]"}]
   [:p {:class "text-sm"}
    "Muted divider"]
   [separator/separator {:class "bg-muted h-[2px]"}]])
