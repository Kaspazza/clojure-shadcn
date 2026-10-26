(ns clojure-shadcn.stories.dropdown-menu.dropdown-menu-demo
  (:require
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]))

(defn example
  []
  [:div {:class "p-6"}
   [dropdown-menu/dropdown-menu {}
    [dropdown-menu/dropdown-menu-trigger {:as-child true}
     (button/button {:variant :outline} "Open")]
    [dropdown-menu/dropdown-menu-content {:class "w-56"
                                          :align "start"}
     [dropdown-menu/dropdown-menu-label {}
      "My Account"]
     [dropdown-menu/dropdown-menu-group {}
      [dropdown-menu/dropdown-menu-item {}
       "Profile"
       [dropdown-menu/dropdown-menu-shortcut {}
        "⇧⌘P"]]
      [dropdown-menu/dropdown-menu-item {}
       "Billing"
       [dropdown-menu/dropdown-menu-shortcut {}
        "⌘B"]]
      [dropdown-menu/dropdown-menu-item {}
       "Settings"
       [dropdown-menu/dropdown-menu-shortcut {}
        "⌘S"]]
      [dropdown-menu/dropdown-menu-item {}
       "Keyboard shortcuts"
       [dropdown-menu/dropdown-menu-shortcut {}
        "⌘K"]]]
     [dropdown-menu/dropdown-menu-separator {}]
     [dropdown-menu/dropdown-menu-group {}
      [dropdown-menu/dropdown-menu-item {}
       "Team"]
      [dropdown-menu/dropdown-menu-sub {}
       [dropdown-menu/dropdown-menu-sub-trigger {}
        "Invite users"]
       [dropdown-menu/dropdown-menu-sub-content {}
        [dropdown-menu/dropdown-menu-item {}
         "Email"]
        [dropdown-menu/dropdown-menu-item {}
         "Message"]
        [dropdown-menu/dropdown-menu-separator {}]
        [dropdown-menu/dropdown-menu-item {}
         "More..."]]]
      [dropdown-menu/dropdown-menu-item {}
       "New Team"
       [dropdown-menu/dropdown-menu-shortcut {}
        "⌘+T"]]]
     [dropdown-menu/dropdown-menu-separator {}]
     [dropdown-menu/dropdown-menu-item {}
      "GitHub"]
     [dropdown-menu/dropdown-menu-item {}
      "Support"]
     [dropdown-menu/dropdown-menu-item {:disabled true}
      "API"]
     [dropdown-menu/dropdown-menu-separator {}]
     [dropdown-menu/dropdown-menu-item {}
      "Log out"
      [dropdown-menu/dropdown-menu-shortcut {}
       "⇧⌘Q"]]]]])
