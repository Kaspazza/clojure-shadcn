(ns clojure-shadcn.stories.sidebar.sidebar-menu
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
        "Inbox"]
       [sidebar/sidebar-menu-badge {}
        "12"]
       [sidebar/sidebar-menu-action {:show-on-hover? true}
        "✎"]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {}
        "Notifications"]
       [sidebar/sidebar-menu-badge {}
        "3"]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {}
        "Archives"]]]]]])
