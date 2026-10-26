(ns clojure-shadcn.stories.menubar.playground
  (:require
   [clojure-shadcn.ui.components.menubar :as menubar]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:class ""} consumer-args)]
     [menubar/menubar
      (select-keys args [:class])
      [menubar/menubar-menu {}
       [menubar/menubar-trigger {}
        "File"]
       [menubar/menubar-content {}
        [menubar/menubar-item {}
         "New tab"]
        [menubar/menubar-item {}
         "Close"]]]])))
