(ns clojure-shadcn.stories.menubar.menubar-demo
  (:require
   ["lucide-react"                       :refer [Copy Scissors Undo2]]
   [clojure-shadcn.ui.components.menubar :as menubar]))

(defn example
  []
  [menubar/menubar {}
   [menubar/menubar-menu {}
    [menubar/menubar-trigger {}
     "File"]
    [menubar/menubar-content {}
     [menubar/menubar-item {}
      "New tab"
      [menubar/menubar-shortcut {}
       "⌘T"]]
     [menubar/menubar-item {}
      "New window"
      [menubar/menubar-shortcut {}
       "⌘N"]]
     [menubar/menubar-separator {}]
     [menubar/menubar-sub {}
      [menubar/menubar-sub-trigger {}
       "Share"]
      [menubar/menubar-sub-content {}
       [menubar/menubar-item {}
        "Email"]
       [menubar/menubar-item {}
        "Messages"]]]
     [menubar/menubar-item {:variant :destructive}
      "Close"]]]
   [menubar/menubar-menu {}
    [menubar/menubar-trigger {}
     "Edit"]
    [menubar/menubar-content {}
     [menubar/menubar-item {}
      [:> Undo2 {:aria-hidden true}]
      "Undo"
      [menubar/menubar-shortcut {}
       "⌘Z"]]
     [menubar/menubar-item {:inset true
                            :disabled true}
      "Redo"]
     [menubar/menubar-separator {}]
     [menubar/menubar-group {}
      [menubar/menubar-label {:inset true}
       "Clipboard"]
      [menubar/menubar-item {}
       [:> Scissors {:aria-hidden true}]
       "Cut"]
      [menubar/menubar-item {}
       [:> Copy {:aria-hidden true}]
       "Copy"]]]]])
