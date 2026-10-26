(ns clojure-shadcn.stories.drawer.drawer-rtl
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.drawer :as drawer]))

(defn example
  []
  [:div {:class "p-4"
         :dir "rtl"}
   [drawer/drawer {:direction :right}
    [drawer/drawer-trigger {:as-child true}
     (button/button {:variant :outline} "فتح الإعدادات")]
    [drawer/drawer-content {:class "w-80"
                            :dir "rtl"}
     [drawer/drawer-header {:class "text-right"}
      [drawer/drawer-title {}
       "إعدادات الحساب"]
      [drawer/drawer-description {}
       "حدّث تفضيلات حسابك."]]
     [:div {:class "flex-1 px-4 text-right text-sm text-muted-foreground"}
      "تُطبّق التغييرات بعد الحفظ."]
     [drawer/drawer-footer {}
      [drawer/drawer-close {:as-child true}
       (button/button {:variant :outline} "إلغاء")]
      [drawer/drawer-close {:as-child true}
       (button/button {} "حفظ")]]]]])
