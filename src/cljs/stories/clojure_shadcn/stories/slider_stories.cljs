(ns clojure-shadcn.stories.slider-stories
  (:require
   [clojure-shadcn.stories.helpers                   :as helpers]
   [clojure-shadcn.stories.slider.playground         :as example-playground]
   [clojure-shadcn.stories.slider.slider-basic       :as example-slider-basic]
   [clojure-shadcn.stories.slider.slider-disabled    :as example-slider-disabled]
   [clojure-shadcn.stories.slider.slider-range       :as example-slider-range]
   [clojure-shadcn.stories.slider.slider-rtl         :as example-slider-rtl]
   [clojure-shadcn.stories.slider.slider-three-thumb :as example-slider-three-thumb]
   [clojure-shadcn.stories.slider.slider-vertical    :as example-slider-vertical]
   [reagent.core                                     :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Slider"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keyboard and pointer accessible numeric slider."
                        :npm-install "npm install @radix-ui/react-slider"
                        :source-code (embed-source "clojure-shadcn.ui.components.slider")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/slider.cljs"
                        :filename "slider.cljs"}]))

(defstory ApiReference
          []
          (r/as-element
           (helpers/wrap-component
            [helpers/api-component-card
             {:component-name "slider"
              :link {:href "https://www.radix-ui.com/primitives/docs/components/slider"
                     :label "Radix Slider Docs"}
              :description "Radix Slider with one thumb per controlled/default value."
              :props [{:name ":value / :default-value"
                       :type "vector<number>"
                       :default "[min]"
                       :description "Controlled or initial values."}
                      {:name ":on-value-change / :on-value-commit"
                       :type "function"
                       :default nil
                       :description "Value callbacks."}
                      {:name ":min / :max / :step"
                       :type "number"
                       :default "0 / 100 / 1"
                       :description "Numeric bounds."}]}])))

(defstory SliderBasic
          {:source-code (embed-source "clojure-shadcn.stories.slider.slider-basic")}
          []
          (r/as-element (helpers/wrap-component [example-slider-basic/example])))

(defstory SliderRange
          {:source-code (embed-source "clojure-shadcn.stories.slider.slider-range")}
          []
          (r/as-element (helpers/wrap-component [example-slider-range/example])))


(defstory
 Playground
 "Interactive slider playground."
 {:args {:default-value 40
         :min 0
         :max 100
         :step 1
         :disabled false}
  :arg-types {:default-value {:name "Initial value"
                              :control {:type "number"}}
              :min {:name "Min"
                    :control {:type "number"}}
              :max {:name "Max"
                    :control {:type "number"}}
              :step {:name "Step"
                     :control {:type "number"}}
              :disabled {:name "Disabled"
                         :control {:type "boolean"}}}
  :parameters
  {:playground {:description
                "Explore slider initial value, min, max, step and disabled with a live preview."}
   :controls {:expanded false
              :exclude ["value" "on-value-change" "on-value-commit" "class" "thumb-class"]}}
  :decode-args (fn [{:keys [default-value]
                     :as args}]
                 (cond-> args
                   (number? default-value) (assoc :default-value [default-value])))
  :source-code (embed-source "clojure-shadcn.stories.slider.playground")}
 [args]
 (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory SliderThreeThumb
          "Three-point range. Based on shadcn/ui Slider — Multiple Thumbs."
          {:source-code (embed-source "clojure-shadcn.stories.slider.slider-three-thumb")}
          []
          (r/as-element (helpers/wrap-component [example-slider-three-thumb/example])))

(defstory SliderVertical
          "Vertical orientation with an explicit height. Based on shadcn/ui Slider — Vertical."
          {:source-code (embed-source "clojure-shadcn.stories.slider.slider-vertical")}
          []
          (r/as-element (helpers/wrap-component [example-slider-vertical/example])))

(defstory
 SliderDisabled
 "Disabled single-value and range sliders retain their values while preventing pointer and keyboard changes."
 {:source-code (embed-source "clojure-shadcn.stories.slider.slider-disabled")}
 []
 (r/as-element (helpers/wrap-component [example-slider-disabled/example])))

(defstory SliderRtl
          "Right-to-left range direction. Based on shadcn/ui Slider — RTL."
          {:source-code (embed-source "clojure-shadcn.stories.slider.slider-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-slider-rtl/example])))
