(ns clojure-shadcn.stories.navigation-menu.navigation-menu-custom-link
  (:require
   [clojure-shadcn.ui.components.navigation-menu :as navigation-menu]))

(defn example
  []
  [navigation-menu/navigation-menu {}
   [navigation-menu/navigation-menu-list {}
    [navigation-menu/navigation-menu-item {}
     [navigation-menu/navigation-menu-link {:as-child true}
      [:a {:href "#guides"
           :class (navigation-menu/navigation-menu-trigger-style)
           :data-router-link true}
       "Guides"]]]
    [navigation-menu/navigation-menu-item {}
     [navigation-menu/navigation-menu-link {:href "#api"
                                            :class (navigation-menu/navigation-menu-trigger-style)}
      "API reference"]]]])
