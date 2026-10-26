(ns clojure-shadcn.stories.dropdown-menu.dropdown-menu-radio-group
  (:require
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]
   [reagent.core                               :as r]))

(defn example
  []
  [(fn []
     (let [position (r/atom "bottom")]
       (fn [] [:div {:class "p-6"}
               [dropdown-menu/dropdown-menu {}
                [dropdown-menu/dropdown-menu-trigger {:as-child true}
                 (button/button {:variant :outline} "Panel Position")]
                [dropdown-menu/dropdown-menu-content {:class "w-56"}
                 [dropdown-menu/dropdown-menu-label {}
                  "Panel Position"]
                 [dropdown-menu/dropdown-menu-separator {}]
                 [dropdown-menu/dropdown-menu-radio-group {:value @position
                                                           :on-value-change (fn* [%1]
                                                                              (reset! position %1))}
                  [dropdown-menu/dropdown-menu-radio-item {:value "top"}
                   "Top"]
                  [dropdown-menu/dropdown-menu-radio-item {:value "bottom"}
                   "Bottom"]
                  [dropdown-menu/dropdown-menu-radio-item {:value "right"}
                   "Right"]]]]])))])
