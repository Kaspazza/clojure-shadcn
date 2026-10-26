(ns clojure-shadcn.stories.sheet.sheet-demo
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.input  :as input]
   [clojure-shadcn.ui.components.label  :as label]
   [clojure-shadcn.ui.components.sheet  :as sheet]))

(defn example
  []
  [:div {:class "p-6"}
   [sheet/sheet {}
    [sheet/sheet-trigger {:as-child true}
     (button/button {:variant :outline} "Open")]
    [sheet/sheet-content {}
     [sheet/sheet-header {}
      [sheet/sheet-title {}
       "Edit profile"]
      [sheet/sheet-description {}
       "Make changes to your profile here. Click save when you're done."]]
     [:div {:class "grid flex-1 auto-rows-min gap-6 px-4"}
      [:div {:class "grid gap-3"}
       [label/label {:html-for "sheet-demo-name"}
        "Name"]
       [input/input {:id "sheet-demo-name"
                     :default-value "Pedro Duarte"}]]
      [:div {:class "grid gap-3"}
       [label/label {:html-for "sheet-demo-username"}
        "Username"]
       [input/input {:id "sheet-demo-username"
                     :default-value "@peduarte"}]]]
     [sheet/sheet-footer {}
      (button/button {:type "submit"} "Save changes")
      [sheet/sheet-close {:as-child true}
       (button/button {:variant :outline} "Close")]]]]])
