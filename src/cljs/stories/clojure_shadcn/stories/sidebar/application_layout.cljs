(ns clojure-shadcn.stories.sidebar.application-layout
  (:require
   ["lucide-react"                       :refer [Folder Kanban Settings Users]]
   [clojure-shadcn.ui.components.sidebar :as sidebar]
   [reagent.core                         :as r]))

(defn example
  []
  (let [open? (r/atom true)
        mobile? (r/atom false)
        toggle! #(swap! open? not)]
    (fn []
      [:div {:class "flex min-h-[680px] w-full overflow-hidden rounded-xl border"}
       [sidebar/sidebar {:open? @open?
                         :is-mobile @mobile?
                         :on-open-change #(reset! open? %)
                         :variant "inset"
                         :collapsible "icon"}
        [sidebar/sidebar-header {}
         [sidebar/sidebar-menu {}
          [sidebar/sidebar-menu-item {}
           [sidebar/sidebar-menu-button {:as-child true
                                         :size :lg}
            [:a {:href "#workspace"}
             [:> Kanban]
             [:span "Northstar"]]]]]
         [sidebar/sidebar-input {:placeholder "Search projects"
                                 :aria-label "Search projects"}]]
        [sidebar/sidebar-separator {}]
        [sidebar/sidebar-content {}
         [sidebar/sidebar-group {}
          [sidebar/sidebar-group-label {}
           "Platform"]
          [sidebar/sidebar-group-action {:aria-label "Add project"}
           "+"]
          [sidebar/sidebar-group-content {}
           [sidebar/sidebar-menu {}
            [sidebar/sidebar-menu-item {}
             [sidebar/sidebar-menu-button {:as-child true
                                           :is-active? true
                                           :tooltip "Dashboard"
                                           :collapsed? (not @open?)
                                           :is-mobile @mobile?}
              [:a {:href "#dashboard"}
               [:> Kanban]
               [:span "Dashboard"]]]]
            [sidebar/sidebar-menu-item {}
             [sidebar/sidebar-menu-button {:as-child true
                                           :tooltip "Projects"
                                           :collapsed? (not @open?)
                                           :is-mobile @mobile?}
              [:a {:href "#projects"}
               [:> Folder]
               [:span "Projects"]]]
             [sidebar/sidebar-menu-action {:aria-label "Project actions"
                                           :show-on-hover? true}
              "⋯"]
             [sidebar/sidebar-menu-badge {}
              "8"]]
            [sidebar/sidebar-menu-item {}
             [sidebar/sidebar-menu-button {:as-child true
                                           :tooltip "Team"
                                           :collapsed? (not @open?)
                                           :is-mobile @mobile?}
              [:a {:href "#team"}
               [:> Users]
               [:span "Team"]]]]]]]
         [sidebar/sidebar-group {}
          [sidebar/sidebar-group-label {}
           "Loading example"]
          [sidebar/sidebar-menu {}
           [sidebar/sidebar-menu-skeleton {:show-icon? true
                                           :width "62%"}]
           [sidebar/sidebar-menu-skeleton {:show-icon? true
                                           :width "78%"}]]]]
        [sidebar/sidebar-footer {}
         [sidebar/sidebar-menu {}
          [sidebar/sidebar-menu-item {}
           [sidebar/sidebar-menu-button {:as-child true
                                         :tooltip "Settings"
                                         :collapsed? (not @open?)
                                         :is-mobile @mobile?}
            [:a {:href "#settings"}
             [:> Settings]
             [:span "Settings"]]]]]]
        [sidebar/sidebar-rail {:on-click toggle!}]]])))
