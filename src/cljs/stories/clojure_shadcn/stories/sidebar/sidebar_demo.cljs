(ns clojure-shadcn.stories.sidebar.sidebar-demo
  (:require
   ["lucide-react"                       :refer [Folder Kanban Settings Users]]
   [clojure-shadcn.ui.components.sidebar :as sidebar]))

(defn example
  []
  [:div {:class "p-6"}
   [sidebar/sidebar {:open? true
                     :is-mobile false
                     :collapsible "none"
                     :variant "sidebar"}
    [sidebar/sidebar-header {}
     [:div {:class "text-sm font-semibold px-2"}
      "Workspace"]]
    [sidebar/sidebar-content {}
     [sidebar/sidebar-menu {}
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {:is-active? true}
        [:> Kanban]
        "Dashboard"]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {}
        [:> Folder]
        "Projects"]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {}
        [:> Users]
        "Team"]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {}
        [:> Settings]
        "Settings"]]]]
    [sidebar/sidebar-footer {}
     [:div {:class "text-xs text-muted-foreground px-2"}
      "v1.0"]]]])
