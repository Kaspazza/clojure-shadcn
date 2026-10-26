(ns clojure-shadcn.stories.item.item-polymorphic-links
  (:require
   ["lucide-react"                    :refer [FileText]]
   [clojure-shadcn.ui.components.item :as item]))

(defn example
  []
  [item/item-group {:class "max-w-xl"}
   [item/item {:as-child? true
               :variant :outline}
    [:a {:href "#documentation"}
     [item/item-media {:variant :icon}
      [:> FileText]]
     [item/item-content {}
      [item/item-title {}
       "Documentation"]
      [item/item-description {}
       "Read installation and component guides."]]]]
   [item/item {:as-child? true
               :variant :muted}
    [:a {:href "#examples"}
     [item/item-content {}
      [item/item-title {}
       "Examples"]
      [item/item-description {}
       "Browse complete composition recipes."]]]]])
