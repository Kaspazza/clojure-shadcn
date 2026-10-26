(ns clojure-shadcn.stories.drawer.left-drawer-non-modal
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
                               :direction :left
                               :modal false}
                [drawer/drawer-trigger {:as-child true}
                 (button/button {:variant :outline} "Open Left Non-Modal")]
                [drawer/drawer-content {:class "w-80"}
                 [drawer/drawer-header {}
                  [drawer/drawer-title {}
                   "Inspector"]
                  [drawer/drawer-description {}
                   "Background stays interactive while this panel is open."]]
                 [drawer/drawer-footer {}
                  (button/button {:variant :outline
                                  :on-click (fn* [] (reset! open? false))}
                                 "Close")]]]])))])
