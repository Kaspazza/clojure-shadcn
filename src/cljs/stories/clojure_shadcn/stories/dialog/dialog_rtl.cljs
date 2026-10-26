(ns clojure-shadcn.stories.dialog.dialog-rtl
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.dialog :as dialog]))

(defn example
  []
  [:div {:class "p-6"
         :dir "rtl"}
   [dialog/dialog {}
    [dialog/dialog-trigger {:as-child true}
     (button/button {:variant :outline} "فتح مربع الحوار")]
    [dialog/dialog-content {:class "text-right"
                            :dir "rtl"
                            :close-label "إغلاق"}
     [dialog/dialog-header {:class "text-right sm:text-right"}
      [dialog/dialog-title {}
       "تعديل الملف الشخصي"]
      [dialog/dialog-description {}
       "حدّث معلوماتك ثم احفظ التغييرات."]]
     [dialog/dialog-footer {:class "sm:justify-start"}
      [dialog/dialog-close {:as-child true}
       (button/button {:variant :outline} "إلغاء")]
      (button/button {} "حفظ التغييرات")]]]])
