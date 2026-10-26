(ns clojure-shadcn.stories.menubar.menubar-rtl
  (:require
   ["lucide-react"                       :refer [Copy Mail]]
   [clojure-shadcn.ui.components.menubar :as menubar]
   [reagent.core                         :as r]))

(defn example
  []
  [(fn []
     (let [mail? (r/atom true)]
       (fn [] [menubar/menubar {:dir "rtl"}
               [menubar/menubar-menu {}
                [menubar/menubar-trigger {}
                 "تحرير"]
                [menubar/menubar-content {:dir "rtl"}
                 [menubar/menubar-group {}
                  [menubar/menubar-item {}
                   [:> Copy {:aria-hidden true}]
                   "نسخ"]
                  [menubar/menubar-item {:inset true}
                   "لصق"]]
                 [menubar/menubar-separator {}]
                 [menubar/menubar-checkbox-item {:checked @mail?
                                                 :on-checked-change #(reset! mail? %)}
                  [:> Mail {:aria-hidden true}]
                  "إشعارات البريد"]]]])))])
