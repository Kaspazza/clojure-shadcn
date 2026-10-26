(ns clojure-shadcn.stories.dropdown-menu.dropdown-menu-avatar
  (:require
   ["lucide-react"                             :refer [CircleUser LogOut Settings]]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]))

(defn example
  []
  [dropdown-menu/dropdown-menu {}
   [dropdown-menu/dropdown-menu-trigger {:as-child true}
    [:button {:type "button"
              :aria-label "Open user menu"
              :class "flex size-10 items-center justify-center rounded-full border bg-muted"}
     [:>
      CircleUser
      {:aria-hidden true
       :class "size-6"}]]]
   [dropdown-menu/dropdown-menu-content {:class "w-56"
                                         :align "end"}
    [dropdown-menu/dropdown-menu-label {}
     "Maya Chen"]
    [dropdown-menu/dropdown-menu-separator {}]
    [dropdown-menu/dropdown-menu-group {}
     [dropdown-menu/dropdown-menu-item {}
      [:> Settings {:aria-hidden true}]
      "Account settings"]
     [dropdown-menu/dropdown-menu-item {}
      [:> LogOut {:aria-hidden true}]
      "Sign out"]]]])
