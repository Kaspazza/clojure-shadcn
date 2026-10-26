(ns clojure-shadcn.stories.popover.popover-rtl
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.input   :as input]
   [clojure-shadcn.ui.components.label   :as label]
   [clojure-shadcn.ui.components.popover :as popover]))

(defn example
  []
  [:div {:class "p-6"
         :dir "rtl"}
   [popover/popover {}
    [popover/popover-trigger {:as-child true}
     (button/button {:variant :outline} "تعديل الملف")]
    [popover/popover-content {:align "start"
                              :class "w-80"
                              :dir "rtl"}
     [popover/popover-header {}
      [popover/popover-title {}
       "بيانات الحساب"]
      [popover/popover-description {}
       "حدّث الاسم الظاهر في ملفك الشخصي."]]
     [:div {:class "mt-4 grid gap-2"}
      [label/label {:html-for "rtl-display-name"}
       "الاسم"]
      [input/input {:id "rtl-display-name"
                    :default-value "مريم"}]]]]])
