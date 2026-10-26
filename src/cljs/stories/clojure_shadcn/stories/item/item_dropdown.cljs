(ns clojure-shadcn.stories.item.item-dropdown
  (:require
   ["lucide-react"                             :refer [FileText MoreHorizontal]]
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]
   [clojure-shadcn.ui.components.item          :as item]))

(defn example
  []
  [item/item {:variant :outline
              :class "max-w-xl"}
   [item/item-media {:variant :icon}
    [:> FileText]]
   [item/item-content {}
    [item/item-title {}
     "Quarterly report"]
    [item/item-description {}
     "PDF · 2.4 MB"]]
   [item/item-actions {}
    [dropdown-menu/dropdown-menu {}
     [dropdown-menu/dropdown-menu-trigger {:as-child true}
      (button/button {:variant :ghost
                      :size :icon
                      :aria-label "Report actions"}
                     [:> MoreHorizontal])]
     [dropdown-menu/dropdown-menu-content {:align "end"}
      [dropdown-menu/dropdown-menu-item {}
       "Download"]
      [dropdown-menu/dropdown-menu-item {}
       "Share"]
      [dropdown-menu/dropdown-menu-separator {}]
      [dropdown-menu/dropdown-menu-item {:variant :destructive}
       "Delete"]]]]])
