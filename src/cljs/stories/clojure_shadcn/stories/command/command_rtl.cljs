(ns clojure-shadcn.stories.command.command-rtl
  (:require
   ["lucide-react"                       :refer [Settings User]]
   [clojure-shadcn.ui.components.command :as command]))

(defn example
  []
  [:div {:class "max-w-md p-6"
         :dir "rtl"}
   [command/command {:class "rounded-lg border"}
    [command/command-input {:placeholder "ابحث عن أمر..."}]
    [command/command-list {}
     [command/command-group {:heading "الحساب"}
      [command/command-item {:value "profile"}
       [:> User {:aria-hidden true}]
       [:span "الملف الشخصي"]
       [command/command-shortcut {}
        "⌘P"]]
      [command/command-item {:value "settings"}
       [:> Settings {:aria-hidden true}]
       [:span "الإعدادات"]]]]]])
