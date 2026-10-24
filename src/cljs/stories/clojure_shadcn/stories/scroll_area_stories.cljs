(ns clojure-shadcn.stories.scroll-area-stories
  (:require
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.scroll-area :as sut]
   [reagent.core                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Scroll Area"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Cross-browser styled scroll viewport."
                        :npm-install "npm install @radix-ui/react-scroll-area"
                        :source-code (embed-source "clojure-shadcn.ui.components.scroll-area")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/scroll_area.cljs"
                        :filename "scroll_area.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [helpers/api-component-card
    {:component-name "scroll-area / scroll-bar"
     :link {:href "https://www.radix-ui.com/primitives/docs/components/scroll-area"
            :label "Radix Scroll Area Docs"}
     :description
     "scroll-area owns the Radix Root, Viewport, optional scroll-bar, and Corner composition. scroll-bar is public, but scroll-area places every child inside Viewport, so consumers cannot compose a sibling scrollbar at the root with the current API."
     :props [{:name ":type / :scroll-hide-delay"
              :type "string|number"
              :default nil
              :description "Scrollbar visibility behavior."}
             {:name ":viewport-class"
              :type "string"
              :default nil
              :description "Viewport classes."}
             {:name ":scrollbar?"
              :type "boolean"
              :default "true"
              :description "Include the default vertical scrollbar."}]}])))

(defstory ScrollAreaBasic
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/scroll-area {:class
                                                                   "h-72 w-48 rounded-md border"}
                                                  [:div {:class "p-4"}
                                                   [:h4 {:class "mb-4 text-sm font-medium"}
                                                    "Tags"]
                                                   (for [i (range 30)]
                                                     ^{:key i}
                                                     [:div {:class "border-b py-2 text-sm"}
                                                      (str "v1.2." i)])]]])))

(defstory ScrollAreaHorizontal
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "p-6"}
                          [sut/scroll-area {:class "w-96 whitespace-nowrap rounded-md border"
                                            :orientation :horizontal}
                           [:div {:class "flex w-max gap-4 p-4"}
                            (for [i (range 8)]
                              ^{:key i}
                              [:div {:class "h-24 w-32 rounded bg-muted grid place-items-center"}
                               i])]]])))


(defstory
 ScrollAreaRtl
 "RTL viewport using the public scroll-area API. Direction is forwarded to the Radix Root; the vertical scrollbar remains wrapper-managed."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/scroll-area {:dir "rtl"
                                                          :class "h-72 w-64 rounded-md border"}
                                         [:div {:class "space-y-3 p-4 text-right"}
                                          [:h4 {:class "font-medium"}
                                           "سجل النشاط"]
                                          (for [i (range 1 21)]
                                            ^{:key i}
                                            [:p {:class "border-b pb-2 text-sm"}
                                             (str "النشاط " i)])]]])))

(defstory
 ScrollAreaCompositionLimitation
 "Architecture note: explicit sibling Scrollbar composition is not representable by scroll-area today; :orientation selects its one managed scrollbar."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-lg rounded-lg border border-amber-500/30 bg-amber-500/10 p-4 text-sm"}
    "The public scroll-bar can render only under a Radix Root, while scroll-area nests all children in its Viewport. Use :orientation for the managed vertical or horizontal bar; dual-axis or custom sibling composition requires a root/viewport API change."])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:class "h-48 w-72 rounded-md border"}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description "Explore scroll area css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children"]}}}
          [args]
          (r/as-element (helpers/wrap-component [sut/scroll-area
                                                 (select-keys args [:class])
                                                 [:div {:class "p-4"}
                                                  (for [n (range 1 21)]
                                                    ^{:key n}
                                                    [:p {:class "text-sm"}
                                                     (str "Item " n)])]])))
