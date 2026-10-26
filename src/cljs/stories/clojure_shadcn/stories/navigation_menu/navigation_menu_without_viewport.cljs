(ns clojure-shadcn.stories.navigation-menu.navigation-menu-without-viewport
  (:require
   [clojure-shadcn.ui.components.navigation-menu :as navigation-menu]))

(defn example
  []
  [navigation-menu/navigation-menu {:viewport false}
   [navigation-menu/navigation-menu-list {}
    [navigation-menu/navigation-menu-item {}
     [navigation-menu/navigation-menu-trigger {}
      "Direct panel"]
     [navigation-menu/navigation-menu-content {:class "w-64"}
      [navigation-menu/navigation-menu-link {:href "#"}
       "A non-viewport menu link"]]]]])
