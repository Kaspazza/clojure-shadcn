(ns clojure-shadcn.stories.checkbox.checkbox-rtl
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.field    :as field]))

(defn example
  []
  [:div {:dir "rtl"
         :class "max-w-md p-6"}
   [field/field {:orientation :horizontal}
    [checkbox/checkbox {:id "checkbox-rtl"
                        :default-checked true}]
    [field/field-content {}
     [field/field-label {:html-for "checkbox-rtl"}
      "تفعيل الإشعارات"]
     [field/field-description {}
      "يمكنك تغيير هذا الإعداد لاحقًا."]]]])
