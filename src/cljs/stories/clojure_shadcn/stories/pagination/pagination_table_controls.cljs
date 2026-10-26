(ns clojure-shadcn.stories.pagination.pagination-table-controls
  (:require
   [clojure-shadcn.ui.components.pagination :as pagination]
   [reagent.core                            :as r]))

(defn example
  []
  [(fn []
     (let [rows-per-page (r/atom "10")]
       (fn [] [:div {:class
                     "flex flex-wrap items-center justify-between gap-3 rounded-md border p-3"}
               [:div {:class "flex items-center gap-2 text-sm"}
                [:label {:for "rows-per-page"
                         :class "font-medium"}
                 "Rows per page"]
                [:select {:id "rows-per-page"
                          :class "h-8 rounded-md border bg-background px-2"
                          :value @rows-per-page
                          :on-change #(reset! rows-per-page (.. % -target -value))}
                 (for [size ["10" "20" "50"]]
                   ^{:key size}
                   [:option {:value size}
                    size])]]
               [:span {:class "text-sm text-muted-foreground"}
                (str "1–" @rows-per-page " of 96 rows")]
               [pagination/pagination {:class "mx-0 w-auto"}
                [pagination/pagination-content {}
                 [pagination/pagination-item {}
                  [pagination/pagination-previous {:href "#previous"
                                                   :class "h-8"}]]
                 [pagination/pagination-item {}
                  [pagination/pagination-link {:href "#page-1"
                                               :is-active true
                                               :size :sm}
                   "1"]]
                 [pagination/pagination-item {}
                  [pagination/pagination-link {:href "#page-2"
                                               :size :sm}
                   "2"]]
                 [pagination/pagination-item {}
                  [pagination/pagination-next {:href "#next"
                                               :class "h-8"}]]]]])))])
