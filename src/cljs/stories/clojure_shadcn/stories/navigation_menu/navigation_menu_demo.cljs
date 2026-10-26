(ns clojure-shadcn.stories.navigation-menu.navigation-menu-demo
  (:require
   [clojure-shadcn.ui.components.navigation-menu :as navigation-menu]))

(defn example
  []
  [navigation-menu/navigation-menu {}
   [navigation-menu/navigation-menu-list {}
    [navigation-menu/navigation-menu-item {}
     [navigation-menu/navigation-menu-trigger {}
      "Getting started"]
     [navigation-menu/navigation-menu-content {}
      [:ul {:class "grid w-[400px] gap-2 p-2"}
       [:li
        [navigation-menu/navigation-menu-link {:href "#"}
         [:div {:class "font-medium"}
          "Introduction"]
         [:p {:class "text-muted-foreground"}
          "Reusable data-oriented Reagent components."]]]
       [:li
        [navigation-menu/navigation-menu-link {:href "#"}
         [:div {:class "font-medium"}
          "Installation"]
         [:p {:class "text-muted-foreground"}
          "Add only the modules your application needs."]]]]]]
    [navigation-menu/navigation-menu-item {}
     [navigation-menu/navigation-menu-link {:href "#"
                                            :class (navigation-menu/navigation-menu-trigger-style)}
      "Documentation"]]
    [navigation-menu/navigation-menu-item {}
     [navigation-menu/navigation-menu-trigger {}
      "Components"]
     [navigation-menu/navigation-menu-content {}
      [:ul {:class "grid w-[500px] grid-cols-2 gap-2 p-2"}
       (for [label ["Button" "Card" "Dialog" "Menu"]]
         ^{:key label}
         [:li
          [navigation-menu/navigation-menu-link {:href "#"}
           label]])]]]]])
