(ns clojure-shadcn.stories.breadcrumb.breadcrumb-demo
  (:require
   [clojure-shadcn.ui.components.breadcrumb    :as breadcrumb]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]))

(defn example
  []
  [:div {:class "p-6"}
   [breadcrumb/breadcrumb {}
    [breadcrumb/breadcrumb-list {}
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:href "#"}
       "Home"]]
     [breadcrumb/breadcrumb-separator {}]
     [breadcrumb/breadcrumb-item {}
      [dropdown-menu/dropdown-menu {}
       [dropdown-menu/dropdown-menu-trigger {:class "flex items-center gap-1 cursor-pointer"}
        [breadcrumb/breadcrumb-ellipsis {:class "size-4"}]
        [:span {:class "sr-only"}
         "Toggle menu"]]
       [dropdown-menu/dropdown-menu-content {:align "start"}
        [dropdown-menu/dropdown-menu-item {:class "cursor-pointer"}
         "Documentation"]
        [dropdown-menu/dropdown-menu-item {:class "cursor-pointer"}
         "Themes"]
        [dropdown-menu/dropdown-menu-item {:class "cursor-pointer"}
         "GitHub"]]]]
     [breadcrumb/breadcrumb-separator {}]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:href "#"}
       "Components"]]
     [breadcrumb/breadcrumb-separator {}]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-page {}
       "Breadcrumb"]]]]])
