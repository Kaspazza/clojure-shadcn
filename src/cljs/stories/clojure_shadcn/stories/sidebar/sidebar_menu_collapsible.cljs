(ns clojure-shadcn.stories.sidebar.sidebar-menu-collapsible
  (:require
   ["lucide-react"                       :refer [Folder Kanban Users]]
   [clojure-shadcn.ui.components.sidebar :as sidebar]))

(defn example
  []
  [:div {:class "p-6"}
   [sidebar/sidebar {:open? false
                     :is-mobile false
                     :collapsible "icon"}
    [sidebar/sidebar-content {}
     [sidebar/sidebar-menu {}
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {:tooltip "Dashboard"}
        [:> Kanban]
        "Dashboard"]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {:tooltip "Projects"}
        [:> Folder]
        "Projects"]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {:tooltip "Team"}
        [:> Users]
        "Team"]]]]]])
