(ns clojure-shadcn.stories.pagination.playground
  (:require
   [clojure-shadcn.ui.components.pagination :as pagination]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:class ""} consumer-args)]
     [pagination/pagination
      (select-keys args [:class])
      [pagination/pagination-content {}
       [pagination/pagination-item {}
        [pagination/pagination-previous {:href "#"}]]
       [pagination/pagination-item {}
        [pagination/pagination-link {:href "#"
                                     :is-active true}
         "1"]]
       [pagination/pagination-item {}
        [pagination/pagination-next {:href "#"}]]]])))
