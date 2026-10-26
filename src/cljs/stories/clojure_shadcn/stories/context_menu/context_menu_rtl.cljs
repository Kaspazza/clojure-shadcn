(ns clojure-shadcn.stories.context-menu.context-menu-rtl
  (:require
   ["lucide-react"                            :refer [Copy Share2]]
   [clojure-shadcn.ui.components.context-menu :as context-menu]))

(defn example
  []
  [context-menu/context-menu {:dir "rtl"}
   [context-menu/context-menu-trigger
    {:class "flex h-36 w-72 items-center justify-center rounded-md border text-sm"
     :dir "rtl"}
    "انقر بزر الفأرة الأيمن"]
   [context-menu/context-menu-content {:class "w-56"}
    [context-menu/context-menu-group {}
     [context-menu/context-menu-item {}
      [:> Copy {:aria-hidden true}]
      "نسخ"]
     [context-menu/context-menu-item {}
      [:> Share2 {:aria-hidden true}]
      "مشاركة"]]
    [context-menu/context-menu-separator {}]
    [context-menu/context-menu-item {:disabled true}
     "إعادة تسمية"]]])
