(ns clojure-shadcn.stories.navigation-menu.playground
  (:require
   [clojure-shadcn.ui.components.navigation-menu :as navigation-menu]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:viewport true} consumer-args)]
     [navigation-menu/navigation-menu
      (select-keys args [:viewport])
      [navigation-menu/navigation-menu-list {}
       [navigation-menu/navigation-menu-item {}
        [navigation-menu/navigation-menu-trigger {}
         "Getting started"]
        [navigation-menu/navigation-menu-content {}
         [navigation-menu/navigation-menu-link {:href "#"}
          "Introduction"]]]]])))
