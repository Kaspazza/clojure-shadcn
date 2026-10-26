(ns clojure-shadcn.stories.context-menu.context-menu-groups-and-icons
  (:require
   ["lucide-react"                            :refer [Copy Mail Share2 Trash2]]
   [clojure-shadcn.ui.components.context-menu :as context-menu]))

(defn example
  []
  [context-menu/context-menu {}
   [context-menu/context-menu-trigger
    {:class "flex h-36 w-72 items-center justify-center rounded-md border border-dashed text-sm"}
    "Right click the document"]
   [context-menu/context-menu-content {:class "w-56"}
    [context-menu/context-menu-group {}
     [context-menu/context-menu-item {}
      [:> Copy {:aria-hidden true}]
      "Duplicate"]
     [context-menu/context-menu-item {}
      [:> Share2 {:aria-hidden true}]
      "Share"]]
    [context-menu/context-menu-separator {}]
    [context-menu/context-menu-group {}
     [context-menu/context-menu-item {}
      [:> Mail {:aria-hidden true}]
      "Send by email"]
     [context-menu/context-menu-item {:variant :destructive}
      [:> Trash2 {:aria-hidden true}]
      "Move to trash"]]]])
