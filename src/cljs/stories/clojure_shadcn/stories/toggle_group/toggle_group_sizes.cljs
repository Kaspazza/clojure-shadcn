(ns clojure-shadcn.stories.toggle-group.toggle-group-sizes
  (:require
   ["lucide-react"                            :refer [AlignCenter AlignLeft AlignRight]]
   [clojure-shadcn.ui.components.toggle-group :as toggle-group]))

(defn example
  []
  [:div {:class "flex flex-col items-start gap-4 p-6"}
   (for [[size label] [[:sm "Small"] [:default "Default"] [:lg "Large"]]]
     ^{:key (name size)}
     [toggle-group/toggle-group {:type "single"
                                 :default-value "center"
                                 :variant :outline
                                 :size size
                                 :aria-label (str label " alignment")}
      [toggle-group/toggle-group-item {:value "left"
                                       :aria-label "Align left"}
       [:> AlignLeft]]
      [toggle-group/toggle-group-item {:value "center"
                                       :aria-label "Align center"}
       [:> AlignCenter]]
      [toggle-group/toggle-group-item {:value "right"
                                       :aria-label "Align right"}
       [:> AlignRight]]])])
