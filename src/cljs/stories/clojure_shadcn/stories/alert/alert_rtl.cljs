(ns clojure-shadcn.stories.alert.alert-rtl
  (:require
   ["lucide-react" :refer [CheckCircle2]]
   [clojure-shadcn.ui.components.alert :as alert]))

(defn example []
  [:div {:dir "rtl" :class "max-w-xl text-right"}
   [alert/alert {}
    [:> CheckCircle2]
    [alert/alert-title {} "تم حفظ التغييرات"]
    [alert/alert-description {} "تم تحديث إعدادات حسابك بنجاح."]]])
