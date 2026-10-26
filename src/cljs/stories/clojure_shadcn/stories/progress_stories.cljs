(ns clojure-shadcn.stories.progress-stories
  (:require
   [clojure-shadcn.stories.helpers                             :as helpers]
   [clojure-shadcn.stories.progress.playground                 :as example-playground]
   [clojure-shadcn.stories.progress.progress-basic             :as example-progress-basic]
   [clojure-shadcn.stories.progress.progress-complete          :as example-progress-complete]
   [clojure-shadcn.stories.progress.progress-label             :as example-progress-label]
   [clojure-shadcn.stories.progress.progress-rtl               :as example-progress-rtl]
   [clojure-shadcn.stories.progress.progress-slider-controlled :as
                                                               example-progress-slider-controlled]
   [clojure-shadcn.stories.progress.progress-timed             :as example-progress-timed]
   [reagent.core                                               :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Progress"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Accessible task progress indicator."
                        :npm-install "npm install @radix-ui/react-progress"
                        :source-code (embed-source "clojure-shadcn.ui.components.progress")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/progress.cljs"
                        :filename "progress.cljs"}]))

(defstory ApiReference
          []
          (r/as-element
           (helpers/wrap-component
            [helpers/api-component-card
             {:component-name "progress"
              :link {:href "https://www.radix-ui.com/primitives/docs/components/progress"
                     :label "Radix Progress Docs"}
              :description "Radix progress semantics with a translated indicator."
              :props [{:name ":value"
                       :type "number | nil"
                       :default "0"
                       :description "Current value; nil is indeterminate."}
                      {:name ":max"
                       :type "number"
                       :default "100"
                       :description "Maximum value."}
                      {:name ":indicator-class"
                       :type "string"
                       :default nil
                       :description "Indicator override classes."}]}])))

(defstory ProgressBasic
          {:source-code (embed-source "clojure-shadcn.stories.progress.progress-basic")}
          []
          (r/as-element (helpers/wrap-component [example-progress-basic/example])))

(defstory ProgressComplete
          {:source-code (embed-source "clojure-shadcn.stories.progress.progress-complete")}
          []
          (r/as-element (helpers/wrap-component [example-progress-complete/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:value 60}
           :arg-types {:value {:name "Value"
                               :control {:type "range"}
                               :min 0
                               :max 100
                               :step 1}}
           :parameters {:playground {:description "Explore progress value with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children"]}}
           :source-code (embed-source "clojure-shadcn.stories.progress.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory
 ProgressTimed
 "Timed — the upstream demonstration advances after mount to communicate asynchronous progress."
 {:source-code (embed-source "clojure-shadcn.stories.progress.progress-timed")}
 []
 (r/as-element (helpers/wrap-component [example-progress-timed/example])))

(defstory ProgressLabel
          "Label — upstream labeled progress recipe keeps value text adjacent to the indicator."
          {:source-code (embed-source "clojure-shadcn.stories.progress.progress-label")}
          []
          (r/as-element (helpers/wrap-component [example-progress-label/example])))

(defstory ProgressSliderControlled
          "Slider controlled — the upstream interactive recipe uses one value for both controls."
          {:source-code (embed-source "clojure-shadcn.stories.progress.progress-slider-controlled")}
          []
          (r/as-element (helpers/wrap-component [example-progress-slider-controlled/example])))

(defstory ProgressRTL
          "RTL — label and progress value follow right-to-left reading order."
          {:source-code (embed-source "clojure-shadcn.stories.progress.progress-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-progress-rtl/example])))
