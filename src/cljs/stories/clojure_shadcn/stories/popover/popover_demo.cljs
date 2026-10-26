(ns clojure-shadcn.stories.popover.popover-demo
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.input   :as input]
   [clojure-shadcn.ui.components.label   :as label]
   [clojure-shadcn.ui.components.popover :as popover]))

(defn example
  []
  [:div {:class "p-6"}
   [popover/popover {}
    [popover/popover-trigger {:as-child true}
     (button/button {:variant :outline} "Open popover")]
    [popover/popover-content {:class "w-80"}
     [:div {:class "grid gap-4"}
      [popover/popover-header {}
       [popover/popover-title {}
        "Dimensions"]
       [popover/popover-description {}
        "Set the dimensions for the layer."]]
      [:div {:class "grid gap-2"}
       [:div {:class "grid grid-cols-3 items-center gap-4"}
        [label/label {:html-for "width"}
         "Width"]
        [input/input {:id "width"
                      :default-value "100%"
                      :class "col-span-2 h-8"}]]
       [:div {:class "grid grid-cols-3 items-center gap-4"}
        [label/label {:html-for "max-width"}
         "Max. width"]
        [input/input {:id "max-width"
                      :default-value "300px"
                      :class "col-span-2 h-8"}]]
       [:div {:class "grid grid-cols-3 items-center gap-4"}
        [label/label {:html-for "height"}
         "Height"]
        [input/input {:id "height"
                      :default-value "25px"
                      :class "col-span-2 h-8"}]]
       [:div {:class "grid grid-cols-3 items-center gap-4"}
        [label/label {:html-for "max-height"}
         "Max. height"]
        [input/input {:id "max-height"
                      :default-value "none"
                      :class "col-span-2 h-8"}]]]]]]])
