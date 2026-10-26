(ns clojure-shadcn.stories.sidebar.playground
  (:require
   [clojure-shadcn.ui.components.sidebar :as sidebar]))

(defn example
  ([] (example {:variant "sidebar"}))
  ([args]
   [sidebar/sidebar
    (assoc (select-keys args [:variant]) :open? true :is-mobile false :collapsible "none")
    [sidebar/sidebar-header {}
     [:div {:class "px-2 text-sm font-semibold"}
      "Workspace"]]
    [sidebar/sidebar-content {}
     [sidebar/sidebar-menu {}
      [sidebar/sidebar-menu-item {}
       [sidebar/sidebar-menu-button {:is-active? true}
        "Dashboard"]]]]]))
