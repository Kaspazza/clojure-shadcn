(ns clojure-shadcn.stories.sidebar.sidebar-controlled
  (:require
   [clojure-shadcn.ui.components.sidebar :as sidebar]
   [reagent.core                         :as r]))

(defn example
  []
  (let [open? (r/atom true)]
    (fn [] [:div {:class "p-6"}
            [:button {:class "mb-4 text-sm underline"
                      :on-click #(swap! open? not)}
             (if @open? "Collapse" "Expand")]
            [sidebar/sidebar {:open? @open?
                              :is-mobile false
                              :collapsible "offcanvas"}
             [sidebar/sidebar-content {}
              [sidebar/sidebar-menu {}
               [sidebar/sidebar-menu-item {}
                [sidebar/sidebar-menu-button {:is-active? true}
                 "Overview"]]
               [sidebar/sidebar-menu-item {}
                [sidebar/sidebar-menu-button {}
                 "Reports"]]]]]])))
