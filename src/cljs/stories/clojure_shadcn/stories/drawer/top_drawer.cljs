(ns clojure-shadcn.stories.drawer.top-drawer
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]
   [reagent.core                        :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)]
       (fn [] [:div {:class "p-4"}
               [drawer/drawer {:open @open?
                               :on-open-change (fn* [%1] (reset! open? %1))
                               :direction :top}
                [drawer/drawer-trigger {:as-child true}
                 (button/button {:variant :outline} "Open Top Drawer")]
                [drawer/drawer-content {}
                 [drawer/drawer-header {}
                  [drawer/drawer-title {}
                   "Quick Filters"]
                  [drawer/drawer-description {}
                   "Apply fast filters without leaving current context."]]
                 [drawer/drawer-footer {}
                  (button/button {:on-click (fn* [] (reset! open? false))} "Apply")]]]])))])
