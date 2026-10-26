(ns clojure-shadcn.stories.sheet.sheet-rtl
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.input  :as input]
   [clojure-shadcn.ui.components.label  :as label]
   [clojure-shadcn.ui.components.sheet  :as sheet]))

(defn example
  []
  [:div {:class "p-6"
         :dir "rtl"}
   [sheet/sheet {}
    [sheet/sheet-trigger {:as-child true}
     (button/button {:variant :outline} "فتح الملف الشخصي")]
    [sheet/sheet-content {:side :right
                          :dir "rtl"}
     [sheet/sheet-header {:class "text-right sm:text-right"}
      [sheet/sheet-title {}
       "تعديل الملف الشخصي"]
      [sheet/sheet-description {}
       "حدّث بياناتك ثم احفظ التغييرات."]]
     [:div {:class "grid flex-1 gap-3 px-4"}
      [label/label {:html-for "sheet-rtl-name"}
       "الاسم"]
      [input/input {:id "sheet-rtl-name"
                    :default-value "ليلى أحمد"}]]
     [sheet/sheet-footer {:class "sm:justify-start sm:space-x-0 sm:gap-2"}
      [sheet/sheet-close {:as-child true}
       (button/button {:variant :outline} "إلغاء")]
      (button/button {} "حفظ التغييرات")]]]])
