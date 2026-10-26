(ns clojure-shadcn.stories.item.item-header
  (:require
   [clojure-shadcn.ui.components.item :as item]))

(defn example
  []
  [item/item {:variant :outline
              :class "max-w-xl"}
   [item/item-header {:class "text-xs text-muted-foreground"}
    [:span "Release notes"]
    [:time {:date-time "2026-03-01"}
     "March 1"]]
   [item/item-content {}
    [item/item-title {}
     "Version 2.4"]
    [item/item-description {}
     "Improved keyboard navigation and logical RTL spacing."]]
   [item/item-footer {:class "text-xs text-muted-foreground"}
    "5 minute read"]])
