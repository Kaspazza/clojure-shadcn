(ns clojure-shadcn.stories.resizable.vertical
  (:require
   [clojure-shadcn.ui.components.resizable :as resizable]))

(defn example
  []
  [:div {:class "h-[420px] w-[520px] overflow-hidden rounded-lg border"}
   [resizable/panel-group {:orientation :vertical}
    [resizable/panel {:defaultSize 35
                      :minSize 20}
     [:div {:class "grid h-full place-items-center bg-muted/30"}
      "Preview"]]
    [resizable/handle {:with-handle? true
                       :aria-label "Resize preview and console"}]
    [resizable/panel {:defaultSize 65
                      :minSize 25}
     [:div {:class "grid h-full place-items-center"}
      "Console"]]]])
