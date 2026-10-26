(ns clojure-shadcn.stories.dropdown-menu.dropdown-menu-rtl
  (:require
   ["lucide-react"                             :refer [CircleUser LogOut Settings]]
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]))

(defn example
  []
  [dropdown-menu/dropdown-menu {:dir "rtl"}
   [dropdown-menu/dropdown-menu-trigger {:as-child true}
    [button/button {:variant :outline}
     "فتح القائمة"]]
   [dropdown-menu/dropdown-menu-content {:class "w-52"
                                         :align "start"
                                         :dir "rtl"}
    [dropdown-menu/dropdown-menu-label {}
     "الحساب"]
    [dropdown-menu/dropdown-menu-group {}
     [dropdown-menu/dropdown-menu-item {}
      [:> CircleUser {:aria-hidden true}]
      "الملف الشخصي"]
     [dropdown-menu/dropdown-menu-item {}
      [:> Settings {:aria-hidden true}]
      "الإعدادات"]]
    [dropdown-menu/dropdown-menu-separator {}]
    [dropdown-menu/dropdown-menu-item {}
     [:> LogOut {:aria-hidden true}]
     "تسجيل الخروج"]]])
