(ns clojure-shadcn.stories.sidebar.sidebar-mobile
  (:require
   [clojure-shadcn.ui.components.sidebar :as sidebar]))

(defn example
  []
  [:div {:class "p-6"}
   [sidebar/sidebar {:open? true
                     :is-mobile true
                     :collapsible "offcanvas"}
    [sidebar/sidebar-header {}
     [:div {:class "text-sm font-semibold px-2"}
      "Mobile Nav"]]
    [sidebar/sidebar-content {}
     [sidebar/sidebar-menu {}
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {:is-active? true}
        "Home"]]
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {}
        "Settings"]]]]]])
