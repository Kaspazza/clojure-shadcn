(ns clojure-shadcn.stories.tabs.tabs-rtl
  (:require
   ["lucide-react"                    :refer [Bell User]]
   [clojure-shadcn.ui.components.tabs :as tabs]))

(defn example
  []
  [:div {:class "w-[420px] p-6"
         :dir "rtl"}
   [tabs/tabs {:default-value "account"
               :dir "rtl"}
    [tabs/tabs-list {}
     [tabs/tabs-trigger {:value "account"}
      [:> User {:aria-hidden true}]
      "الحساب"]
     [tabs/tabs-trigger {:value "alerts"}
      [:> Bell {:aria-hidden true}]
      "التنبيهات"]]
    [tabs/tabs-content {:value "account"
                        :class "rounded-md border p-4"}
     "إدارة معلومات الحساب."]
    [tabs/tabs-content {:value "alerts"
                        :class "rounded-md border p-4"}
     "اختيار إعدادات التنبيهات."]]])
