(ns clojure-shadcn.stories.drawer.stateful-goal-drawer
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]
   [reagent.core                        :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)
           goal (r/atom 350)]
       (fn [] [:div {:class "p-4"}
               [drawer/drawer {:open @open?
                               :on-open-change (fn* [%1] (reset! open? %1))
                               :direction :bottom}
                [drawer/drawer-trigger {:as-child true}
                 (button/button {:variant :outline} "Set daily goal")]
                [drawer/drawer-content {}
                 [drawer/drawer-header {:class "text-center"}
                  [drawer/drawer-title {}
                   "Daily activity goal"]
                  [drawer/drawer-description {}
                   "Choose how many calories you want to burn each day."]]
                 [:div {:class
                        "mx-auto flex w-full max-w-sm items-center justify-center gap-6 px-4 py-6"}
                  (button/button {:variant :outline
                                  :size :icon
                                  :aria-label "Decrease daily goal"
                                  :disabled (<= @goal 100)
                                  :on-click (fn* []
                                              (swap! goal (fn [value] (max 100 (- value 10)))))}
                                 "−")
                  [:div {:class "min-w-28 text-center"}
                   [:div {:class "text-5xl font-bold tabular-nums"}
                    @goal]
                   [:div {:class "text-sm text-muted-foreground"}
                    "calories per day"]]
                  (button/button {:variant :outline
                                  :size :icon
                                  :aria-label "Increase daily goal"
                                  :disabled (>= @goal 1000)
                                  :on-click (fn* []
                                              (swap! goal (fn [value] (min 1000 (+ value 10)))))}
                                 "+")]
                 [drawer/drawer-footer {:class "mx-auto w-full max-w-sm"}
                  (button/button {:on-click (fn* [] (reset! open? false))} "Save goal")
                  [drawer/drawer-close {:as-child true}
                   (button/button {:variant :outline} "Cancel")]]]]])))])
