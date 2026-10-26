(ns clojure-shadcn.stories.breadcrumb.breadcrumb-rtl
  (:require
   [clojure-shadcn.ui.components.breadcrumb :as breadcrumb]))

(defn example
  []
  [:div {:class "p-6"
         :dir "rtl"}
   [breadcrumb/breadcrumb {}
    [breadcrumb/breadcrumb-list {}
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:href "#"}
       "الرئيسية"]]
     [breadcrumb/breadcrumb-separator {}
      [:span "‹"]]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:href "#"}
       "المكونات"]]
     [breadcrumb/breadcrumb-separator {}
      [:span "‹"]]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-page {}
       "مسار التنقل"]]]]])
