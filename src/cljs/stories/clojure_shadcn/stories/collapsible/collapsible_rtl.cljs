(ns clojure-shadcn.stories.collapsible.collapsible-rtl
  (:require
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.collapsible :as collapsible]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)]
       (fn [] [:div {:dir "rtl"
                     :class "max-w-md text-right"}
               [collapsible/collapsible {:open @open?
                                         :on-open-change (fn* [%1] (reset! open? %1))}
                [collapsible/collapsible-trigger {:as-child true}
                 (button/button {:variant :outline
                                 :class "w-full justify-between"}
                                "تفاصيل إضافية"
                                (if @open? "إخفاء" "عرض"))]
                [collapsible/collapsible-content {:class "pt-3 text-sm text-muted-foreground"}
                 "يمكن إظهار هذا المحتوى أو إخفاؤه دون مغادرة الصفحة."]]])))])
