(ns clojure-shadcn.stories.separator.separator-rtl
  (:require
   [clojure-shadcn.ui.components.separator :as separator]))

(defn example
  []
  [:nav {:dir "rtl"
         :aria-label "أقسام الحساب"
         :class "flex h-6 items-center gap-4 text-sm"}
   [:a {:href "#profile"}
    "الملف الشخصي"]
   [separator/separator {:orientation :vertical
                         :class "h-full"}]
   [:a {:href "#billing"}
    "الفواتير"]
   [separator/separator {:orientation :vertical
                         :class "h-full"}]
   [:a {:href "#team"}
    "الفريق"]])
