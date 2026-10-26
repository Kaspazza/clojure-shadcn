(ns clojure-shadcn.stories.pagination-stories
  (:require
   [clojure-shadcn.stories.helpers                              :as helpers]
   [clojure-shadcn.stories.pagination.pagination-demo           :as pagination-demo]
   [clojure-shadcn.stories.pagination.pagination-table-controls :as pagination-table-controls]
   [clojure-shadcn.stories.pagination.pagination-variants       :as pagination-variants]
   [clojure-shadcn.stories.pagination.playground                :as playground]
   [reagent.core                                                :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Pagination"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Semantic page navigation composed from button variants."
                        :npm-install "npm install @radix-ui/react-slot lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.pagination")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/pagination.cljs"
                        :filename "pagination.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "pagination / pagination-content / pagination-item"
      :description
      "Semantic nav, ul, and li composition. The root defaults role=navigation and aria-label=pagination; provide meaningful link destinations and current-page state."
      :props
      [{:name ":class / element props"
        :type "string / map entries"
        :default nil
        :description
        "Merged and normalized onto nav, ul, or li. Root props override its default role/label."}]}]
    [helpers/api-component-card
     {:component-name "pagination-link"
      :description
      "Anchor styled through Button's as-child composition. Active links use outline styling and aria-current=page; inactive links use ghost styling."
      :props [{:name ":is-active"
               :type "boolean"
               :default nil
               :description "Sets active visuals, data-active, and aria-current."}
              {:name ":size"
               :type "Button size"
               :default ":icon"
               :description "Forwarded to Button for sizing."}
              {:name ":href / additional props"
               :type "string / map entries"
               :default nil
               :description
               "Forwarded to the underlying anchor. :is-active and :size are consumed."}]}]
    [helpers/api-component-card
     {:component-name "pagination-previous / pagination-next"
      :description
      "Opinionated pagination-link shortcuts with icons, responsive labels, default size, and accessible labels."
      :props
      [{:name "anchor props"
        :type "map entries"
        :default nil
        :description
        "Merged over defaults and forwarded through pagination-link; normally supply :href."}]}]
    [helpers/api-component-card
     {:component-name "pagination-ellipsis"
      :description
      "Non-interactive, aria-hidden visual ellipsis with screen-reader text. It does not represent a link or button."
      :props
      [{:name ":class / span props"
        :type "string / map entries"
        :default nil
        :description
        "Merged over defaults; props can override aria-hidden if a different semantic is intentionally required."}]}]])))

(defstory PaginationDemo
          {:source-code (embed-source "clojure-shadcn.stories.pagination.pagination-demo")}
          []
          (r/as-element (helpers/wrap-component [pagination-demo/example])))


(defstory
 PaginationTableControls
 "Compact pagination controls for a table footer.

  Pattern: shadcn/ui Data Table pagination

  Keep row-count controls beside page navigation without changing Pagination's built-in visible labels."
 {:source-code (embed-source "clojure-shadcn.stories.pagination.pagination-table-controls")}
 []
 (r/as-element (helpers/wrap-component [pagination-table-controls/example])))

(defstory
 PaginationVariants
 "Simple, icon-only, and RTL pagination. Icon-only links keep explicit accessible labels; RTL reverses both reading order and directional labels."
 {:source-code (embed-source "clojure-shadcn.stories.pagination.pagination-variants")}
 []
 (r/as-element (helpers/wrap-component [pagination-variants/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:source-code (embed-source "clojure-shadcn.stories.pagination.playground")
           :args {:class ""}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description
                                     "Explore pagination css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "on-click"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
