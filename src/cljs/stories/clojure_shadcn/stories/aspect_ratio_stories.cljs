(ns clojure-shadcn.stories.aspect-ratio-stories
  (:require
   [clojure-shadcn.stories.helpers            :as helpers]
   [clojure-shadcn.stories.aspect-ratio.playground :as playground]
   [clojure-shadcn.stories.aspect-ratio.aspect-ratio-square :as aspect-ratio-square]
   [clojure-shadcn.stories.aspect-ratio.aspect-ratio-portrait :as aspect-ratio-portrait]
   [clojure-shadcn.stories.aspect-ratio.aspect-ratio-rtl :as aspect-ratio-rtl]
   [reagent.core                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Aspect Ratio"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keeps media at a requested width-to-height ratio."
                        :npm-install "npm install @radix-ui/react-aspect-ratio"
                        :source-code (embed-source "clojure-shadcn.ui.components.aspect-ratio")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/aspect_ratio.cljs"
                        :filename "aspect_ratio.cljs"}]))

(defstory ApiReference
          []
          (r/as-element (helpers/wrap-component
                         [helpers/api-component-card
                          {:component-name "aspect-ratio"
                           :link {:href
                                  "https://www.radix-ui.com/primitives/docs/components/aspect-ratio"
                                  :label "Radix Aspect Ratio Docs"}
                           :description "Radix AspectRatio.Root with normalized, forwarded props."
                           :props [{:name ":ratio"
                                    :type "number"
                                    :default "1"
                                    :description "Width divided by height."}
                                   {:name ":class"
                                    :type "string"
                                    :default nil
                                    :description "Merged classes."}]}])))

(defstory
 Playground
 "Interactive aspect-ratio playground."
 {:source-code (embed-source "clojure-shadcn.stories.aspect-ratio.playground")
  :args {:ratio 1.7778}
  :arg-types {:ratio {:name "Ratio"
                               :control {:type "number"}
                      :min 0.5
                      :max 3
                      :step 0.1}}
  :parameters {:playground {:description "Explore aspect ratio ratio with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}}
 [args]
 (r/as-element (helpers/wrap-component [playground/example args])))

(defstory AspectRatioSquare
          {:source-code (embed-source "clojure-shadcn.stories.aspect-ratio.aspect-ratio-square")}
          []
          (r/as-element (helpers/wrap-component [aspect-ratio-square/example])))

(defstory
 AspectRatioPortrait
 "Portrait — upstream 3:4 media treatment for posters and profile photography."
 {:source-code (embed-source "clojure-shadcn.stories.aspect-ratio.aspect-ratio-portrait")}
 []
 (r/as-element (helpers/wrap-component [aspect-ratio-portrait/example])))

(defstory
 AspectRatioRTL
 "RTL — aspect-ratio media embedded in a right-to-left content card."
 {:source-code (embed-source "clojure-shadcn.stories.aspect-ratio.aspect-ratio-rtl")}
 []
 (r/as-element (helpers/wrap-component [aspect-ratio-rtl/example])))
