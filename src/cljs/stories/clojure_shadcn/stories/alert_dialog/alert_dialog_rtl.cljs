(ns clojure-shadcn.stories.alert-dialog.alert-dialog-rtl
  (:require
   ["lucide-react" :refer [TriangleAlert]]
   [clojure-shadcn.ui.components.alert-dialog :as alert-dialog]
   [clojure-shadcn.ui.components.button :as button]))

(defn example []
  [:div {:class "p-6" :dir "rtl"}
   [alert-dialog/alert-dialog {}
    [alert-dialog/alert-dialog-trigger {:as-child true}
     (button/button {:variant :outline} "حذف المشروع")]
    [alert-dialog/alert-dialog-content {:dir "rtl"}
     [alert-dialog/alert-dialog-header {}
      [alert-dialog/alert-dialog-media {} [:> TriangleAlert {:aria-hidden true}]]
      [alert-dialog/alert-dialog-title {} "هل تريد حذف المشروع؟"]
      [alert-dialog/alert-dialog-description {} "لا يمكن التراجع عن هذا الإجراء بعد التأكيد."]]
     [alert-dialog/alert-dialog-footer {}
      [alert-dialog/alert-dialog-cancel {} "إلغاء"]
      [alert-dialog/alert-dialog-action {:variant :destructive} "حذف"]]]]])
