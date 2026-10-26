(ns clojure-shadcn.stories.sheet.sheet-without-close-button
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.sheet  :as sheet]))

(defn example
  []
  [:div {:class "p-6"}
   [sheet/sheet {}
    [sheet/sheet-trigger {:as-child true}
     (button/button {:variant :outline} "Open without corner close")]
    [sheet/sheet-content {:show-close-button false}
     [sheet/sheet-header {}
      [sheet/sheet-title {}
       "Review changes"]
      [sheet/sheet-description {}
       "This sheet uses only its explicit dismissal action."]]
     [:div {:class "grid flex-1 gap-4 px-4 text-sm text-muted-foreground"}
      "Confirm the details before returning to the page."]
     [sheet/sheet-footer {}
      [sheet/sheet-close {:as-child true}
       (button/button {:variant :outline} "Done")]]]]])
