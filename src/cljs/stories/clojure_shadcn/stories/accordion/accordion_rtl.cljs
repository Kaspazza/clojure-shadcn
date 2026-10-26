(ns clojure-shadcn.stories.accordion.accordion-rtl
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [:div {:dir "rtl" :class "max-w-xl text-right"}
   [accordion/accordion {:type "single" :collapsible true :default-value "shipping"}
    [accordion/accordion-item {:value "shipping"}
     [accordion/accordion-trigger {} "ما مدة الشحن؟"]
     [accordion/accordion-content {} "يصل الطلب عادة خلال ثلاثة إلى خمسة أيام عمل."]]
    [accordion/accordion-item {:value "returns"}
     [accordion/accordion-trigger {} "هل يمكن إرجاع الطلب؟"]
     [accordion/accordion-content {} "يمكن إرجاع المنتجات خلال ثلاثين يومًا."]]]])
