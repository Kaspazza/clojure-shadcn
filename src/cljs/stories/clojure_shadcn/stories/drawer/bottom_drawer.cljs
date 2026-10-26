(ns clojure-shadcn.stories.drawer.bottom-drawer
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]
   [clojure-shadcn.ui.components.input  :as input]
   [reagent.core                        :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)]
       (fn [] [:div {:class "p-4"}
               [drawer/drawer {:open @open?
                               :on-open-change (fn* [%1] (reset! open? %1))
                               :direction :bottom}
                [drawer/drawer-trigger {:as-child true}
                 (button/button {:variant :outline} "Open Bottom Drawer")]
                [drawer/drawer-content {}
                 [drawer/drawer-header {}
                  [drawer/drawer-title {}
                   "Edit Profile"]
                  [drawer/drawer-description {}
                   "Make changes to your profile here. Click save when you're done."]]
                 [:div {:class "p-4 space-y-4"}
                  [:div {:class "space-y-2"}
                   [:label {:class "text-sm font-medium"}
                    "Name"]
                   [input/input {:placeholder "Enter your name"}]]
                  [:div {:class "space-y-2"}
                   [:label {:class "text-sm font-medium"}
                    "Email"]
                   [input/input {:type "email"
                                 :placeholder "Enter your email"}]]]
                 [drawer/drawer-footer {}
                  (button/button {:on-click (fn* [] (reset! open? false))} "Save Changes")
                  (button/button {:variant :outline
                                  :on-click (fn* [] (reset! open? false))}
                                 "Cancel")]]]])))])
