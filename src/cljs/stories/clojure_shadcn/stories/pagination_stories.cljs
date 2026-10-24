(ns clojure-shadcn.stories.pagination-stories
  (:require
   [clojure-shadcn.stories.helpers          :as helpers]
   [clojure-shadcn.ui.components.pagination :as sut]
   [reagent.core                            :as r])
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
          []
          (r/as-element
           (helpers/wrap-component
            [sut/pagination {}
             [sut/pagination-content {}
              [sut/pagination-item {}
               [sut/pagination-previous {:href "#"}]]
              [sut/pagination-item {}
               [sut/pagination-link {:href "#"}
                "1"]]
              [sut/pagination-item {}
               [sut/pagination-link {:href "#"
                                     :is-active true}
                "2"]]
              [sut/pagination-item {}
               [sut/pagination-link {:href "#"}
                "3"]]
              [sut/pagination-item {}
               [sut/pagination-ellipsis {}]]
              [sut/pagination-item {}
               [sut/pagination-next {:href "#"}]]]])))


(defstory
 PaginationTableControls
 "Compact pagination controls for a table footer.

  Pattern: shadcn/ui Data Table pagination

  Keep row-count controls beside page navigation without changing Pagination's built-in visible labels."
 []
 (r/as-element
  [(fn []
     (let [rows-per-page (r/atom "10")]
       (fn []
         (helpers/wrap-component
          [:div {:class "flex flex-wrap items-center justify-between gap-3 rounded-md border p-3"}
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
           [sut/pagination {:class "mx-0 w-auto"}
            [sut/pagination-content {}
             [sut/pagination-item {}
              [sut/pagination-previous {:href "#previous"
                                        :class "h-8"}]]
             [sut/pagination-item {}
              [sut/pagination-link {:href "#page-1"
                                    :is-active true
                                    :size :sm}
               "1"]]
             [sut/pagination-item {}
              [sut/pagination-link {:href "#page-2"
                                    :size :sm}
               "2"]]
             [sut/pagination-item {}
              [sut/pagination-next {:href "#next"
                                    :class "h-8"}]]]]]))))]))

(defstory
 PaginationVariants
 "Simple, icon-only, and RTL pagination. Icon-only links keep explicit accessible labels; RTL reverses both reading order and directional labels."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid gap-8"}
    [sut/pagination {:aria-label "Simple pagination"}
     [sut/pagination-content {}
      [sut/pagination-item {}
       [sut/pagination-previous {:href "#previous"}]]
      [sut/pagination-item {}
       [sut/pagination-link {:href "#page-2"
                             :is-active true}
        "2"]]
      [sut/pagination-item {}
       [sut/pagination-next {:href "#next"}]]]]
    [sut/pagination {:aria-label "Icon-only pagination"}
     [sut/pagination-content {}
      [sut/pagination-item {}
       [sut/pagination-link {:href "#first"
                             :aria-label "First page"}
        "«"]]
      [sut/pagination-item {}
       [sut/pagination-link {:href "#last"
                             :aria-label "Last page"}
        "»"]]]]
    [:div {:dir "rtl"}
     [sut/pagination {:aria-label "ترقيم الصفحات"}
      [sut/pagination-content {}
       [sut/pagination-item {}
        [sut/pagination-next {:href "#next"
                              :aria-label "الصفحة التالية"}]]
       [sut/pagination-item {}
        [sut/pagination-link {:href "#page-1"
                              :is-active true}
         "١"]]
       [sut/pagination-item {}
        [sut/pagination-previous {:href "#previous"
                                  :aria-label "الصفحة السابقة"}]]]]]])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:class ""}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description "Explore pagination css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "on-click"]}}}
          [args]
          (r/as-element (helpers/wrap-component [sut/pagination
                                                 (select-keys args [:class])
                                                 [sut/pagination-content {}
                                                  [sut/pagination-item {}
                                                   [sut/pagination-previous {:href "#"}]]
                                                  [sut/pagination-item {}
                                                   [sut/pagination-link {:href "#"
                                                                         :is-active true}
                                                    "1"]]
                                                  [sut/pagination-item {}
                                                   [sut/pagination-next {:href "#"}]]]])))
