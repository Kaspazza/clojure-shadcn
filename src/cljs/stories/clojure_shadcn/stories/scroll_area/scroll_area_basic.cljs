(ns clojure-shadcn.stories.scroll-area.scroll-area-basic
  (:require
   [clojure-shadcn.ui.components.scroll-area :as scroll-area]))

(defn example
  []
  [:div {:class "p-6"}
   [scroll-area/scroll-area {:class "h-72 w-48 rounded-md border"}
    [:div {:class "p-4"}
     [:h4 {:class "mb-4 text-sm font-medium"}
      "Tags"]
     (for [i (range 30)]
       ^{:key i}
       [:div {:class "border-b py-2 text-sm"}
        (str "v1.2." i)])]]])
