(ns clojure-shadcn.stories.breadcrumb.breadcrumb-dropdown
  (:require
   ["lucide-react"                             :refer [ChevronDown Slash]]
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
     [breadcrumb/breadcrumb-separator {}
      [:> Slash]]
     [breadcrumb/breadcrumb-item {}
      [dropdown-menu/dropdown-menu {}
       [dropdown-menu/dropdown-menu-trigger {:as-child true}
        [:button {:class "flex items-center gap-1"}
         "Components"
         [:> ChevronDown {:class "size-3.5"}]]]
       [dropdown-menu/dropdown-menu-content {:align "start"}
        [dropdown-menu/dropdown-menu-item {}
         "Documentation"]
        [dropdown-menu/dropdown-menu-item {}
         "Themes"]
        [dropdown-menu/dropdown-menu-item {}
         "GitHub"]]]]
     [breadcrumb/breadcrumb-separator {}
      [:> Slash]]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-page {}
       "Breadcrumb"]]]]])
