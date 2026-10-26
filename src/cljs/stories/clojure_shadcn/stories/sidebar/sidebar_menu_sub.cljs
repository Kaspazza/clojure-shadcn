(ns clojure-shadcn.stories.sidebar.sidebar-menu-sub
  (:require
   [clojure-shadcn.ui.components.sidebar :as sidebar]))

(defn example
  []
  [:div {:class "p-6"}
   [sidebar/sidebar {:open? true
                     :is-mobile false
                     :collapsible "none"}
    [sidebar/sidebar-content {}
     [sidebar/sidebar-menu {}
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {:is-active? true}
        "Projects"]
       [sidebar/sidebar-menu-sub {}
        [sidebar/sidebar-menu-sub-item {}
         [sidebar/sidebar-menu-sub-button {}
          "Roadmap"]]
        [sidebar/sidebar-menu-sub-item {}
         [sidebar/sidebar-menu-sub-button {}
          "Backlog"]]
        [sidebar/sidebar-menu-sub-item {}
         [sidebar/sidebar-menu-sub-button {}
          "Archive"]]]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {}
        "Analytics"]]]]]])
