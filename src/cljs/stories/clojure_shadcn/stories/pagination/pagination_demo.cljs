(ns clojure-shadcn.stories.pagination.pagination-demo
  (:require
   [clojure-shadcn.ui.components.pagination :as pagination]))

(defn example
  []
  [pagination/pagination {}
   [pagination/pagination-content {}
    [pagination/pagination-item {}
     [pagination/pagination-previous {:href "#"}]]
    [pagination/pagination-item {}
     [pagination/pagination-link {:href "#"}
      "1"]]
    [pagination/pagination-item {}
     [pagination/pagination-link {:href "#"
                                  :is-active true}
      "2"]]
    [pagination/pagination-item {}
     [pagination/pagination-link {:href "#"}
      "3"]]
    [pagination/pagination-item {}
     [pagination/pagination-ellipsis {}]]
    [pagination/pagination-item {}
     [pagination/pagination-next {:href "#"}]]]])
