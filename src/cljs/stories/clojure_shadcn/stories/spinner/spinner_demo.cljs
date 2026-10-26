(ns clojure-shadcn.stories.spinner.spinner-demo
  (:require
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [:div {:class "p-6"}
   [:div {:class
          "flex w-full max-w-xs items-center justify-between rounded-lg border bg-muted/50 p-4"}
    [:div {:class "flex items-center gap-3"}
     [spinner/spinner {}]
     [:div {:class "text-sm font-medium"}
      "Processing payment..."]]
    [:span {:class "text-sm tabular-nums"}
     "$100.00"]]])
