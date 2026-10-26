(ns clojure-shadcn.stories.scroll-area.scroll-area-horizontal
  (:require
   [clojure-shadcn.ui.components.scroll-area :as scroll-area]))

(defn example
  []
  [:div {:class "p-6"}
   [scroll-area/scroll-area {:class "w-96 whitespace-nowrap rounded-md border"
                             :orientation :horizontal}
    [:div {:class "flex w-max gap-4 p-4"}
     (for [i (range 8)]
       ^{:key i}
       [:div {:class "h-24 w-32 rounded bg-muted grid place-items-center"}
        i])]]])
