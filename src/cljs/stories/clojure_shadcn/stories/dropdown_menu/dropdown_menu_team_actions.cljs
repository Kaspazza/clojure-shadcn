(ns clojure-shadcn.stories.dropdown-menu.dropdown-menu-team-actions
  (:require
   ["lucide-react"                             :refer [Mail Settings UserPlus]]
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]))

(defn example
  []
  [dropdown-menu/dropdown-menu {}
   [dropdown-menu/dropdown-menu-trigger {:as-child true}
    [button/button {:variant :outline}
     "Acme team"]]
   [dropdown-menu/dropdown-menu-content {:class "w-60"
                                         :align "start"}
    [dropdown-menu/dropdown-menu-label {}
     "Team workspace"]
    [dropdown-menu/dropdown-menu-group {}
     [dropdown-menu/dropdown-menu-item {}
      [:> UserPlus {:aria-hidden true}]
      "Invite member"
      [dropdown-menu/dropdown-menu-shortcut {}
       "⌘I"]]
     [dropdown-menu/dropdown-menu-item {}
      [:> Mail {:aria-hidden true}]
      "Email team"]]
    [dropdown-menu/dropdown-menu-separator {}]
    [dropdown-menu/dropdown-menu-sub {}
     [dropdown-menu/dropdown-menu-sub-trigger {}
      [:> Settings {:aria-hidden true}]
      "Preferences"]
     [dropdown-menu/dropdown-menu-sub-content {}
      [dropdown-menu/dropdown-menu-item {}
       "Notifications"]
      [dropdown-menu/dropdown-menu-item {}
       "Permissions"]]]]])
