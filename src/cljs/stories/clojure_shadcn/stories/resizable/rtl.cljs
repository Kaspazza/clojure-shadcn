(ns clojure-shadcn.stories.resizable.rtl
  (:require
   [clojure-shadcn.ui.components.resizable :as resizable]))

(defn example
  []
  [:div {:dir "rtl"
         :class "h-48 w-[600px] overflow-hidden rounded-lg border"}
   [resizable/panel-group {:orientation :horizontal}
    [resizable/panel {:defaultSize 35}
     [:div {:class "grid h-full place-items-center"}
      "التنقل"]]
    [resizable/handle {:with-handle? true
                       :aria-label "تغيير حجم اللوحات"}]
    [resizable/panel {:defaultSize 65}
     [:div {:class "grid h-full place-items-center"}
      "المحتوى"]]]])
