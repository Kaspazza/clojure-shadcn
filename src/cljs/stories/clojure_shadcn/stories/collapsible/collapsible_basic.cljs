(ns clojure-shadcn.stories.collapsible.collapsible-basic
  (:require
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.collapsible :as collapsible]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)]
       (fn [] [:div {:class "p-6"}
               [collapsible/collapsible {:open @open?
                                         :on-open-change (fn* [%1] (reset! open? %1))}
                [collapsible/collapsible-trigger {:as-child true}
                 (button/button {:variant :outline} (if @open? "Hide details" "Show details"))]
                [collapsible/collapsible-content {:class "mt-4"}
                 [:div {:class "rounded-md border bg-muted p-4 text-sm"}
                  "This content expands and collapses."]]]])))])
