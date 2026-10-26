(ns clojure-shadcn.stories.context-menu.context-menu-choices
  (:require
   [clojure-shadcn.ui.components.context-menu :as context-menu]
   [reagent.core                              :as r]))

(defn example
  []
  [(fn []
     (let [checked? (r/atom true)
           position (r/atom "bottom")]
       (fn [] [context-menu/context-menu {}
               [context-menu/context-menu-trigger
                {:class "flex h-32 w-72 items-center justify-center rounded-md border"}
                "Right click for preferences"]
               [context-menu/context-menu-content {:class "w-56"}
                [context-menu/context-menu-checkbox-item {:checked @checked?
                                                          :on-checked-change
                                                          (fn* [%1] (reset! checked? %1))}
                 "Show toolbar"]
                [context-menu/context-menu-separator {}]
                [context-menu/context-menu-label {}
                 "Panel position"]
                [context-menu/context-menu-radio-group {:value @position
                                                        :on-value-change (fn* [%1]
                                                                           (reset! position %1))}
                 [context-menu/context-menu-radio-item {:value "top"}
                  "Top"]
                 [context-menu/context-menu-radio-item {:value "bottom"}
                  "Bottom"]]]])))])
