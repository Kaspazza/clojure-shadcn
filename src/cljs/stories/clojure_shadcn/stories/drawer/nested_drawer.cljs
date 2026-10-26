(ns clojure-shadcn.stories.drawer.nested-drawer
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]))

(defn example
  []
  [:div {:class "p-4"}
   [drawer/drawer {}
    [drawer/drawer-trigger {:as-child true}
     (button/button {:variant :outline} "Open parent drawer")]
    [drawer/drawer-content {}
     [drawer/drawer-header {}
      [drawer/drawer-title {}
       "Parent drawer"]
      [drawer/drawer-description {}
       "The nested root must remain inside this content."]]
     [:div {:class "p-4"}
      [drawer/drawer-nested-root {}
       [drawer/drawer-trigger {:as-child true}
        (button/button {} "Open nested drawer")]
       [drawer/drawer-content {:class "min-h-[45vh]"}
        [drawer/drawer-header {}
         [drawer/drawer-title {}
          "Nested drawer"]
         [drawer/drawer-description {}
          "Vaul coordinates this drawer with its parent."]]
        [drawer/drawer-footer {}
         [drawer/drawer-close {:as-child true}
          (button/button {:variant :outline} "Close nested drawer")]]]]]
     [drawer/drawer-footer {}
      [drawer/drawer-close {:as-child true}
       (button/button {:variant :outline} "Close parent drawer")]]]]])
