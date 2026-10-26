(ns clojure-shadcn.stories.table.table-rtl
  (:require
   [clojure-shadcn.ui.components.table :as table]))

(defn example
  []
  [:div {:class "p-6"}
   [table/table {:dir "rtl"
                 :class "text-right"
                 :aria-label "الفواتير الأخيرة"}
    [table/table-caption {}
     "قائمة بأحدث الفواتير."]
    [table/table-header {}
     [table/table-row {}
      [table/table-head {:scope "col"
                         :class "text-right"}
       "الفاتورة"]
      [table/table-head {:scope "col"
                         :class "text-right"}
       "الحالة"]
      [table/table-head {:scope "col"
                         :class "text-left"}
       "المبلغ"]]]
    [table/table-body {}
     [table/table-row {}
      [table/table-cell {}
       "INV-101"]
      [table/table-cell {}
       "مدفوعة"]
      [table/table-cell {:class "text-left"}
       "٢٥٠٫٠٠ ر.س"]]
     [table/table-row {}
      [table/table-cell {}
       "INV-102"]
      [table/table-cell {}
       "قيد الانتظار"]
      [table/table-cell {:class "text-left"}
       "١٥٠٫٠٠ ر.س"]]]]])
