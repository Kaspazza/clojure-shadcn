(ns clojure-shadcn.stories.pagination-stories
  (:require [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.pagination :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Pagination" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Semantic page navigation composed from button variants." :npm-install "npm install @radix-ui/react-slot lucide-react" :source-code (embed-source "clojure-shadcn.ui.components.pagination") :namespace-path "src/cljs/clojure_shadcn/ui/components/pagination.cljs" :filename "pagination.cljs"}]))
(defstory ApiReference []
  (r/as-element (helpers/wrap-component
    [:div {:class "space-y-4 p-6 max-w-4xl"}
     [helpers/api-component-card {:component-name "pagination / pagination-content / pagination-item" :description "Semantic nav, ul, and li composition. The root defaults role=navigation and aria-label=pagination; provide meaningful link destinations and current-page state." :props [{:name ":class / element props" :type "string / map entries" :default nil :description "Merged and normalized onto nav, ul, or li. Root props override its default role/label."}]}]
     [helpers/api-component-card {:component-name "pagination-link" :description "Anchor styled through Button's as-child composition. Active links use outline styling and aria-current=page; inactive links use ghost styling." :props [{:name ":is-active" :type "boolean" :default nil :description "Sets active visuals, data-active, and aria-current."} {:name ":size" :type "Button size" :default ":icon" :description "Forwarded to Button for sizing."} {:name ":href / additional props" :type "string / map entries" :default nil :description "Forwarded to the underlying anchor. :is-active and :size are consumed."}]}]
     [helpers/api-component-card {:component-name "pagination-previous / pagination-next" :description "Opinionated pagination-link shortcuts with icons, responsive labels, default size, and accessible labels." :props [{:name "anchor props" :type "map entries" :default nil :description "Merged over defaults and forwarded through pagination-link; normally supply :href."}]}]
     [helpers/api-component-card {:component-name "pagination-ellipsis" :description "Non-interactive, aria-hidden visual ellipsis with screen-reader text. It does not represent a link or button." :props [{:name ":class / span props" :type "string / map entries" :default nil :description "Merged over defaults; props can override aria-hidden if a different semantic is intentionally required."}]}]])))
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
