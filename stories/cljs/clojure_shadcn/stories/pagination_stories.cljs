(ns clojure-shadcn.stories.pagination-stories
  (:require [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.pagination :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Pagination" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Semantic page navigation composed from button variants." :npm-install "npm install @radix-ui/react-slot lucide-react" :source-code (embed-source "clojure-shadcn.ui.components.pagination") :namespace-path "src/cljs/clojure_shadcn/ui/components/pagination.cljs" :filename "pagination.cljs"}]))
(defstory PaginationDemo []
  (r/as-element (helpers/wrap-component
    [sut/pagination {}
     [sut/pagination-content {}
      [sut/pagination-item {} [sut/pagination-previous {:href "#"}]]
      [sut/pagination-item {} [sut/pagination-link {:href "#"} "1"]]
      [sut/pagination-item {} [sut/pagination-link {:href "#" :is-active true} "2"]]
      [sut/pagination-item {} [sut/pagination-link {:href "#"} "3"]]
      [sut/pagination-item {} [sut/pagination-ellipsis {}]]
      [sut/pagination-item {} [sut/pagination-next {:href "#"}]]]])))
