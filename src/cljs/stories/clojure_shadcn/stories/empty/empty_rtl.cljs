(ns clojure-shadcn.stories.empty.empty-rtl
  (:require
   ["lucide-react"                      :refer [Inbox Plus]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.empty  :as empty]))

(defn example
  []
  [:div {:dir "rtl"
         :class "text-right"}
   [empty/empty {:class "border"}
    [empty/empty-header {}
     [empty/empty-media {:variant :icon}
      [:> Inbox]]
     [empty/empty-title {}
      "لا توجد مشاريع"]
     [empty/empty-description {}
      "أنشئ مشروعك الأول للبدء."]]
    [empty/empty-content {:class "flex-row"}
     (button/button {} [:> Plus] "إنشاء مشروع")
     (button/button {:variant :outline} "استيراد")]]])
