(ns clojure-shadcn.stories.scroll-area-stories
  (:require
   [clojure-shadcn.stories.helpers                                        :as helpers]
   [clojure-shadcn.stories.scroll-area.playground                         :as example-playground]
   [clojure-shadcn.stories.scroll-area.scroll-area-basic                  :as
                                                                          example-scroll-area-basic]
   [clojure-shadcn.stories.scroll-area.scroll-area-composition-limitation
    :as example-scroll-area-composition-limitation]
   [clojure-shadcn.stories.scroll-area.scroll-area-horizontal
    :as example-scroll-area-horizontal]
   [clojure-shadcn.stories.scroll-area.scroll-area-rtl                    :as
                                                                          example-scroll-area-rtl]
   [reagent.core                                                          :as r])
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
          {:source-code (embed-source "clojure-shadcn.stories.scroll-area.scroll-area-basic")}
          []
          (r/as-element (helpers/wrap-component [example-scroll-area-basic/example])))

(defstory ScrollAreaHorizontal
          {:source-code (embed-source "clojure-shadcn.stories.scroll-area.scroll-area-horizontal")}
          []
          (r/as-element (helpers/wrap-component [example-scroll-area-horizontal/example])))


(defstory
 ScrollAreaRtl
 "RTL viewport using the public scroll-area API. Direction is forwarded to the Radix Root; the vertical scrollbar remains wrapper-managed."
 {:source-code (embed-source "clojure-shadcn.stories.scroll-area.scroll-area-rtl")}
 []
 (r/as-element (helpers/wrap-component [example-scroll-area-rtl/example])))

(defstory
 ScrollAreaCompositionLimitation
 "Architecture note: explicit sibling Scrollbar composition is not representable by scroll-area today; :orientation selects its one managed scrollbar."
 {:source-code (embed-source
                "clojure-shadcn.stories.scroll-area.scroll-area-composition-limitation")}
 []
 (r/as-element (helpers/wrap-component [example-scroll-area-composition-limitation/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:class "h-48 w-72 rounded-md border"}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description
                                     "Explore scroll area css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children"]}}
           :source-code (embed-source "clojure-shadcn.stories.scroll-area.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))
