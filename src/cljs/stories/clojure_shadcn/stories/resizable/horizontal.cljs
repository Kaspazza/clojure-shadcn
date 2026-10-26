(ns clojure-shadcn.stories.resizable.horizontal
  (:require
   [clojure-shadcn.ui.components.resizable :as resizable]))

(defn example
  []
  [:div {:class "h-48 w-[600px] overflow-hidden rounded-lg border"}
   [resizable/panel-group {:orientation :horizontal}
    [resizable/panel {:defaultSize 35}
     [:div {:class "grid h-full place-items-center"}
      "Navigation"]]
    [resizable/handle {:with-handle? true}]
    [resizable/panel {:defaultSize 65}
     [:div {:class "grid h-full place-items-center"}
      "Content"]]]])
