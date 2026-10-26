(ns clojure-shadcn.stories.drawer.swipe-handle-only
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]
   [clojure-shadcn.ui.components.input  :as input]))

(defn example
  []
  [:div {:class "p-4"}
   [drawer/drawer {:handle-only true}
    [drawer/drawer-trigger {:as-child true}
     (button/button {:variant :outline} "Open handle-only drawer")]
    [drawer/drawer-content {:show-handle? false}
     [drawer/drawer-handle {:prevent-cycle true}]
     [drawer/drawer-header {}
      [drawer/drawer-title {}
       "Swipe the handle"]
      [drawer/drawer-description {}
       "Dragging is restricted to the visible handle; click cycling is disabled."]]
     [:div {:class "p-4"}
      [input/input {:placeholder "Interacting here will not drag the drawer"}]]]]])
