(ns clojure-shadcn.stories.context-menu.context-menu-demo
  (:require
   [clojure-shadcn.ui.components.context-menu :as context-menu]))

(defn example
  []
  [context-menu/context-menu {}
   [context-menu/context-menu-trigger
    {:class "flex h-40 w-72 items-center justify-center rounded-md border border-dashed text-sm"}
    "Right click here"]
   [context-menu/context-menu-content {:class "w-56"}
    [context-menu/context-menu-item {}
     "Back"
     [context-menu/context-menu-shortcut {}
      "⌘["]]
    [context-menu/context-menu-item {:disabled true}
     "Forward"]
    [context-menu/context-menu-sub {}
     [context-menu/context-menu-sub-trigger {}
      "Share"]
     [context-menu/context-menu-sub-content {:class "w-44"}
      [context-menu/context-menu-item {}
       "Email"]
      [context-menu/context-menu-item {}
       "Messages"]]]
    [context-menu/context-menu-separator {}]
    [context-menu/context-menu-item {:variant :destructive}
     "Delete"]]])
