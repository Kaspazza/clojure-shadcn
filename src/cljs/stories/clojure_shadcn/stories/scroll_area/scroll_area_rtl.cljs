(ns clojure-shadcn.stories.scroll-area.scroll-area-rtl
  (:require
   [clojure-shadcn.ui.components.scroll-area :as scroll-area]))

(defn example
  []
  [:div {:class "p-6"}
   [scroll-area/scroll-area {:dir "rtl"
                             :class "h-72 w-64 rounded-md border"}
    [:div {:class "space-y-3 p-4 text-right"}
     [:h4 {:class "font-medium"}
      "سجل النشاط"]
     (for [i (range 1 21)]
       ^{:key i}
       [:p {:class "border-b pb-2 text-sm"}
        (str "النشاط " i)])]]])
