(ns clojure-shadcn.stories.breadcrumb.playground
  (:require
   [clojure-shadcn.ui.components.breadcrumb :as breadcrumb]))

(defn example
  ([]
   (example {:class ""}))
  ([args]
  [breadcrumb/breadcrumb
   (select-keys args [:class])
   [breadcrumb/breadcrumb-list {}
    [breadcrumb/breadcrumb-item {}
     [breadcrumb/breadcrumb-link {:href "#"}
      "Home"]]
    [breadcrumb/breadcrumb-separator {}]
    [breadcrumb/breadcrumb-item {}
     [breadcrumb/breadcrumb-page {}
      "Components"]]]]))
