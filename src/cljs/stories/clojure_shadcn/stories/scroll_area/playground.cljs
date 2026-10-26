(ns clojure-shadcn.stories.scroll-area.playground
  (:require
   [clojure-shadcn.ui.components.scroll-area :as scroll-area]))

(defn example
  ([] (example {:class "h-48 w-72 rounded-md border"}))
  ([args]
   [scroll-area/scroll-area
    (select-keys args [:class])
    [:div {:class "p-4"}
     (for [n (range 1 21)]
       ^{:key n}
       [:p {:class "text-sm"}
        (str "Item " n)])]]))
