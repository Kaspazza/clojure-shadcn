(ns clojure-shadcn.stories.popover.popover-alignments
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.popover :as popover]))

(defn example
  []
  [:div {:class "flex flex-wrap items-start justify-center gap-16 p-16"}
   (for [align ["start" "center" "end"]]
     ^{:key align}
     [popover/popover {}
      [popover/popover-trigger {:as-child true}
       (button/button {:variant :outline
                       :aria-label (str "Open " align " aligned popover")}
                      align)]
      [popover/popover-content {:align align
                                :class "w-56"}
       [:p {:class "text-sm"}
        (str "Content aligned to the " align " edge.")]]])])
