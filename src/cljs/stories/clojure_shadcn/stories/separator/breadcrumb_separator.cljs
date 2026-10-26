(ns clojure-shadcn.stories.separator.breadcrumb-separator
  (:require
   ["lucide-react"                          :refer [Slash]]
   [clojure-shadcn.ui.components.breadcrumb :as breadcrumb]))

(defn example
  []
  [:div {:class "p-6"}
   [breadcrumb/breadcrumb {}
    [breadcrumb/breadcrumb-list {}
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:href "#"}
       "Home"]]
     [breadcrumb/breadcrumb-separator {}
      [:> Slash]]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:href "#"}
       "Components"]]
     [breadcrumb/breadcrumb-separator {}
      [:> Slash]]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-page {}
       "Separator"]]]]])
