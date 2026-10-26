(ns clojure-shadcn.stories.breadcrumb.breadcrumb-simple
  (:require
   [clojure-shadcn.ui.components.breadcrumb :as breadcrumb]))

(defn example
  []
  [:div {:class "p-6"}
   [breadcrumb/breadcrumb {}
    [breadcrumb/breadcrumb-list {}
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:href "#"}
       "Home"]]
     [breadcrumb/breadcrumb-separator {}]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:href "#"}
       "Components"]]
     [breadcrumb/breadcrumb-separator {}]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-page {}
       "Breadcrumb"]]]]])
