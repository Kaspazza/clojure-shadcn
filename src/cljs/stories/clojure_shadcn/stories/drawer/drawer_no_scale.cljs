(ns clojure-shadcn.stories.drawer.drawer-no-scale
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
                               :direction :bottom
                               :should-scale-background false}
                [drawer/drawer-trigger {:as-child true}
                 (button/button {:variant :outline} "Open (No Scale)")]
                [drawer/drawer-content {}
                 [drawer/drawer-header {}
                  [drawer/drawer-title {}
                   "No Background Scaling"]
                  [drawer/drawer-description {}
                   "Notice the background doesn't scale when this opens"]]
                 [:div {:class "p-4"}
                  [:p {:class "text-sm text-muted-foreground"}
                   "This drawer is configured with :should-scale-background false"]]
                 [drawer/drawer-footer {}
                  (button/button {:on-click (fn* [] (reset! open? false))} "Close")]]]])))])
