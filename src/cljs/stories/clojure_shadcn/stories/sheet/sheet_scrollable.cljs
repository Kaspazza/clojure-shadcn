(ns clojure-shadcn.stories.sheet.sheet-scrollable
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.sheet  :as sheet]))

(defn example
  []
  [:div {:class "p-6"}
   [sheet/sheet {}
    [sheet/sheet-trigger {:as-child true}
     (button/button {:variant :outline} "Open Scrollable")]
    [sheet/sheet-content {:class "overflow-y-auto"}
     [sheet/sheet-header {}
      [sheet/sheet-title {}
       "Release Notes"]
      [sheet/sheet-description {}
       "Review recent changes before continuing."]]
     [:div {:class "space-y-3 px-4"}
      (for [idx (range 1 16)]
        ^{:key idx}
        [:p {:class "text-sm text-muted-foreground"}
         (str "Release item " idx ": Updated feature details and fixes.")])]
     [sheet/sheet-footer {}
      [sheet/sheet-close {:as-child true}
       (button/button {:variant :outline} "Close")]]]]])
