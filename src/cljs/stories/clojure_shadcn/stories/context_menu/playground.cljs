(ns clojure-shadcn.stories.context-menu.playground
  (:require
   [clojure-shadcn.ui.components.context-menu :as context-menu]))

(defn example
  ([] (example {:class ""}))
  ([args]
   [context-menu/context-menu {}
    [context-menu/context-menu-trigger
     {:class (str "flex h-32 w-64 items-center justify-center rounded-md border " (:class args))}
     "Right click here"]
    [context-menu/context-menu-content {}
     [context-menu/context-menu-item {}
      "Back"]
     [context-menu/context-menu-item {:disabled true}
      "Forward"]]]))
