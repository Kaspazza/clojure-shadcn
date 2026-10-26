(ns clojure-shadcn.stories.item.item-rtl
  (:require
   ["lucide-react"                      :refer [FileText MoreHorizontal]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.item   :as item]))

(defn example
  []
  [:div {:dir "rtl"
         :class "max-w-xl text-right"}
   [item/item {:variant :outline}
    [item/item-media {:variant :icon}
     [:> FileText]]
    [item/item-content {}
     [item/item-title {}
      "التقرير الفصلي"]
     [item/item-description {}
      "ملف PDF بحجم 2.4 ميجابايت"]]
    [item/item-actions {}
     (button/button {:variant :ghost
                     :size :icon
                     :aria-label "المزيد"}
                    [:> MoreHorizontal])]]])
