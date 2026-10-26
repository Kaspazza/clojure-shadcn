(ns clojure-shadcn.stories.item.item-variants
  (:require
   ["lucide-react"                      :refer [FileText MoreHorizontal]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.item   :as item]))

(defn example
  []
  [item/item-group {:class "max-w-xl"}
   [item/item {:variant :outline}
    [item/item-media {:variant :icon}
     [:> FileText]]
    [item/item-content {}
     [item/item-title {}
      "Quarterly report"]
     [item/item-description {}
      "PDF · 2.4 MB"]]
    [item/item-actions {}
     (button/button {:variant :ghost
                     :size :icon}
                    [:> MoreHorizontal])]]
   [item/item-separator {}]
   [item/item {:variant :muted
               :size :sm}
    [item/item-content {}
     [item/item-title {}
      "Compact item"]
     [item/item-description {}
      "Muted, small variant"]]]])
