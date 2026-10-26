(ns clojure-shadcn.stories.typography.typography-rtl
  (:require
   [clojure-shadcn.ui.components.typography :as typography]))

(defn example
  []
  [:article {:dir "rtl"
             :class "mx-auto max-w-3xl text-right"}
   [typography/h1 {}
    "تصميم واجهات تدوم"]
   [typography/lead {}
    "تنجح مكتبة المكونات عندما تبقى عقودها واضحة مع تطور المنتج."]
   [typography/p {}
    "ليست الواجهة القابلة لإعادة الاستخدام مجرد مجموعة من العناصر المنسقة، بل هي لغة مشتركة بين المصممين والمطورين."]
   [typography/h2 {}
    "ابدأ بالدلالات"]
   [typography/p {}
    "استخدم العناصر الدلالية أولًا، ثم أضف الأسلوب البصري من خلال وصفات واضحة وقابلة للتركيب."]
   [typography/blockquote {:class "border-l-0 border-r-2 pl-0 pr-6"}
    "اجعل المسار الصحيح واضحًا وسهلًا."]
   [typography/list {:class "ml-0 mr-6"}
    [:li "عقود صغيرة وواضحة"]
    [:li "تركيب بدل الإعداد المفرط"]
    [:li "دعم اتجاه النص"]]
   [typography/muted {}
    "تمت المراجعة في مارس 2026"]])
