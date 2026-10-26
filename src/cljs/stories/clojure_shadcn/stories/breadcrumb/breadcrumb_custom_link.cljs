(ns clojure-shadcn.stories.breadcrumb.breadcrumb-custom-link
  (:require
   [clojure-shadcn.ui.components.breadcrumb :as breadcrumb]))

(defn example
  []
  [:div {:class "p-6"}
   [breadcrumb/breadcrumb {}
    [breadcrumb/breadcrumb-list {}
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-link {:as-child true}
       [:a {:href "#guides"
            :data-router-link true}
        "Guides"]]]
     [breadcrumb/breadcrumb-separator {}]
     [breadcrumb/breadcrumb-item {}
      [breadcrumb/breadcrumb-page {}
       "Routing"]]]]])
