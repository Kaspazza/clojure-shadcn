(ns clojure-shadcn.stories.dropdown-menu.playground
  (:require
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]))

(defn example
  ([] (example {:modal true}))
  ([args]
   [dropdown-menu/dropdown-menu
    (select-keys args [:modal])
    [dropdown-menu/dropdown-menu-trigger {:as-child true}
     [button/button {:variant :outline}
      "Open menu"]]
    [dropdown-menu/dropdown-menu-content {}
     [dropdown-menu/dropdown-menu-item {}
      "Profile"]
     [dropdown-menu/dropdown-menu-item {}
      "Settings"]]]))
