(ns clojure-shadcn.stories.navigation-menu.navigation-menu-rtl
  (:require
   [clojure-shadcn.ui.components.navigation-menu :as navigation-menu]))

(defn example
  []
  [:div {:dir "rtl"}
   [navigation-menu/navigation-menu {:dir "rtl"}
    [navigation-menu/navigation-menu-list {}
     [navigation-menu/navigation-menu-item {}
      [navigation-menu/navigation-menu-trigger {}
       "البدء"]
      [navigation-menu/navigation-menu-content {:dir "rtl"}
       [:ul {:class "grid w-72 gap-2 p-2"}
        [:li
         [navigation-menu/navigation-menu-link {:href "#intro"}
          "مقدمة"]]
        [:li
         [navigation-menu/navigation-menu-link {:href "#install"}
          "التثبيت"]]]]]
     [navigation-menu/navigation-menu-item {}
      [navigation-menu/navigation-menu-link {:href "#docs"
                                             :class (navigation-menu/navigation-menu-trigger-style)}
       "التوثيق"]]]]])
