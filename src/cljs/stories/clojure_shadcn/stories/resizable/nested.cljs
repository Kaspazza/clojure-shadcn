(ns clojure-shadcn.stories.resizable.nested
  (:require
   [clojure-shadcn.ui.components.resizable :as resizable]))

(defn example
  []
  [:div {:class "h-[420px] w-[720px] overflow-hidden rounded-lg border"}
   [resizable/panel-group {:orientation :horizontal}
    [resizable/panel {:defaultSize 28
                      :minSize 18}
     [:div {:class "grid h-full place-items-center bg-muted/30"}
      "Files"]]
    [resizable/handle {:with-handle? true
                       :aria-label "Resize files and workspace"}]
    [resizable/panel {:defaultSize 72}
     [resizable/panel-group {:orientation :vertical}
      [resizable/panel {:defaultSize 70}
       [:div {:class "grid h-full place-items-center"}
        "Editor"]]
      [resizable/handle {:with-handle? true
                         :aria-label "Resize editor and terminal"}]
      [resizable/panel {:defaultSize 30
                        :minSize 15}
       [:div {:class "grid h-full place-items-center bg-muted/30"}
        "Terminal"]]]]]])
