(ns clojure-shadcn.stories.pagination.pagination-variants
  (:require
   [clojure-shadcn.ui.components.pagination :as pagination]))

(defn example
  []
  [:div {:class "grid gap-8"}
   [pagination/pagination {:aria-label "Simple pagination"}
    [pagination/pagination-content {}
     [pagination/pagination-item {}
      [pagination/pagination-previous {:href "#previous"}]]
     [pagination/pagination-item {}
      [pagination/pagination-link {:href "#page-2"
                                   :is-active true}
       "2"]]
     [pagination/pagination-item {}
      [pagination/pagination-next {:href "#next"}]]]]
   [pagination/pagination {:aria-label "Icon-only pagination"}
    [pagination/pagination-content {}
     [pagination/pagination-item {}
      [pagination/pagination-link {:href "#first"
                                   :aria-label "First page"}
       "«"]]
     [pagination/pagination-item {}
      [pagination/pagination-link {:href "#last"
                                   :aria-label "Last page"}
       "»"]]]]
   [:div {:dir "rtl"}
    [pagination/pagination {:aria-label "ترقيم الصفحات"}
     [pagination/pagination-content {}
      [pagination/pagination-item {}
       [pagination/pagination-next {:href "#next"
                                    :aria-label "الصفحة التالية"}]]
      [pagination/pagination-item {}
       [pagination/pagination-link {:href "#page-1"
                                    :is-active true}
        "١"]]
      [pagination/pagination-item {}
       [pagination/pagination-previous {:href "#previous"
                                        :aria-label "الصفحة السابقة"}]]]]]])
