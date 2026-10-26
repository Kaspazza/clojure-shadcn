(ns clojure-shadcn.stories.drawer.drawer-with-scroll
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
                               :direction :bottom}
                [drawer/drawer-trigger {:as-child true}
                 (button/button {:variant :outline} "Open Scrollable Drawer")]
                [drawer/drawer-content {}
                 [drawer/drawer-header {}
                  [drawer/drawer-title {}
                   "Terms and Conditions"]
                  [drawer/drawer-description {}
                   "Please read through the terms before continuing"]]
                 [:div {:class "p-4 overflow-y-auto"}
                  (for [i (range 20)]
                    ^{:key i}
                    [:p {:class "mb-4 text-sm text-muted-foreground"}
                     (str "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                          "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                          "Section "
                          (inc i)
                          " of the terms.")])]
                 [drawer/drawer-footer {}
                  (button/button {:on-click (fn* [] (reset! open? false))} "I Accept")
                  (button/button {:variant :outline
                                  :on-click (fn* [] (reset! open? false))}
                                 "Decline")]]]])))])
