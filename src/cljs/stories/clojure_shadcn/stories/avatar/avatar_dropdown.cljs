(ns clojure-shadcn.stories.avatar.avatar-dropdown
  (:require
   ["lucide-react"                             :refer [ChevronsUpDown LogOut Settings]]
   [clojure-shadcn.ui.components.avatar        :as avatar]
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown]))

(defn example
  []
  [dropdown/dropdown-menu {}
   [dropdown/dropdown-menu-trigger {:as-child true}
    (button/button {:variant :ghost
                    :class "h-auto gap-3 px-2 py-1.5"}
                   [avatar/avatar {:size :sm}
                    [avatar/avatar-image {:src "https://github.com/shadcn.png"
                                          :alt "@shadcn"}]
                    [avatar/avatar-fallback {}
                     "CN"]]
                   [:span {:class "grid text-left text-sm leading-tight"}
                    [:span {:class "font-medium"}
                     "shadcn"]
                    [:span {:class "text-xs text-muted-foreground"}
                     "m@example.com"]]
                   [:> ChevronsUpDown {:class "size-4 text-muted-foreground"}])]
   [dropdown/dropdown-menu-content {:align "end"
                                    :class "w-56"}
    [dropdown/dropdown-menu-label {}
     "My account"]
    [dropdown/dropdown-menu-separator {}]
    [dropdown/dropdown-menu-item {}
     [:> Settings]
     "Settings"]
    [dropdown/dropdown-menu-item {}
     [:> LogOut]
     "Log out"]]])
