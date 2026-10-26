(ns clojure-shadcn.stories.drawer.snap-points-drawer
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]
   [reagent.core                        :as r]))

(defn example
  []
  [(fn []
     (let [snap-points ["148px" "355px" 1]
           active-snap-point (r/atom (first snap-points))]
       (fn [] [:div {:class "p-4"}
               [drawer/drawer {:snap-points snap-points
                               :active-snap-point @active-snap-point
                               :set-active-snap-point (fn* [%1] (reset! active-snap-point %1))
                               :fade-from-index 1}
                [drawer/drawer-trigger {:as-child true}
                 (button/button {:variant :outline} "Open snap-point drawer")]
                [drawer/drawer-content {:show-handle? false
                                        :class "h-full max-h-[97vh]"}
                 [drawer/drawer-handle {}]
                 [drawer/drawer-header {}
                  [drawer/drawer-title {}
                   "Three snap points"]
                  [drawer/drawer-description {}
                   (str "Active stop: " @active-snap-point)]]
                 [:div {:class "overflow-y-auto px-4 pb-8 text-sm text-muted-foreground"}
                  "Swipe to resize. Clicking the handle cycles through snap points."]]]])))])
