(ns clojure-shadcn.stories.drawer.right-drawer
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]
   [reagent.core                        :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)
           sections ["Overview"
                     "Activity"
                     "Members"
                     "Integrations"
                     "Billing"
                     "Security"
                     "Notifications"
                     "Automation"
                     "Exports"
                     "Advanced"]]
       (fn [] [:div {:class "p-4"}
               [drawer/drawer {:open @open?
                               :on-open-change (fn* [%1] (reset! open? %1))
                               :direction :right}
                [drawer/drawer-trigger {:as-child true}
                 (button/button {:variant :outline} "Open project settings")]
                [drawer/drawer-content {:class "w-80 overflow-hidden"}
                 [drawer/drawer-header {:class "shrink-0 border-b"}
                  [drawer/drawer-title {}
                   "Project settings"]
                  [drawer/drawer-description {}
                   "Choose a section to configure."]]
                 [:nav {:class "min-h-0 flex-1 space-y-2 overflow-y-auto p-4"
                        :aria-label "Project settings sections"}
                  (for [item sections]
                    ^{:key item}
                    [:button {:type "button"
                              :class "w-full rounded-md px-3 py-3 text-left hover:bg-accent"}
                     item])]
                 [drawer/drawer-footer {:class "shrink-0 border-t bg-background"}
                  [drawer/drawer-close {:as-child true}
                   (button/button {:variant :outline
                                   :class "w-full"}
                                  "Close settings")]]]]])))])
