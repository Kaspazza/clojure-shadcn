(ns clojure-shadcn.stories.sheet.sheet-side
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.input  :as input]
   [clojure-shadcn.ui.components.label  :as label]
   [clojure-shadcn.ui.components.sheet  :as sheet]))

(defn example
  []
  [:div {:class "p-6 grid grid-cols-2 gap-2"}
   (for [side [:top :right :bottom :left]]
     ^{:key side}
     [sheet/sheet {}
      [sheet/sheet-trigger {:as-child true}
       (button/button {:variant :outline} (name side))]
      [sheet/sheet-content {:side side}
       [sheet/sheet-header {}
        [sheet/sheet-title {}
         "Edit profile"]
        [sheet/sheet-description {}
         "Make changes to your profile here. Click save when you're done."]]
       [:div {:class "grid gap-4 py-4"}
        [:div {:class "grid grid-cols-4 items-center gap-4"}
         [label/label {:html-for (str "sheet-name-" (name side))
                       :class "text-right"}
          "Name"]
         [input/input {:id (str "sheet-name-" (name side))
                       :default-value "Pedro Duarte"
                       :class "col-span-3"}]]
        [:div {:class "grid grid-cols-4 items-center gap-4"}
         [label/label {:html-for (str "sheet-username-" (name side))
                       :class "text-right"}
          "Username"]
         [input/input {:id (str "sheet-username-" (name side))
                       :default-value "@peduarte"
                       :class "col-span-3"}]]]
       [sheet/sheet-footer {}
        [sheet/sheet-close {:as-child true}
         (button/button {:type "submit"} "Save changes")]]]])])
