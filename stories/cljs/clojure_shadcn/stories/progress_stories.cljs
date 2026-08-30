(ns clojure-shadcn.stories.progress-stories
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.progress :as sut]
   [reagent.core                          :as r])
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
          []
          (r/as-element (helpers/wrap-component [:div {:class "w-[420px] p-6 space-y-2"}
                                                 [:div {:class "text-sm"}
                                                  "Uploading — 60%"]
                                                 [sut/progress {:value 60}]])))

(defstory ProgressComplete
          []
          (r/as-element (helpers/wrap-component [:div {:class "w-[420px] p-6"}
                                                 [sut/progress {:value 100
                                                                :aria-label "Upload complete"}]])))


(defstory ProgressPlayground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:value 60}
           :arg-types {:value {:control {:type "range"}
                               :min 0
                               :max 100
                               :step 1}}
           :parameters {:controls {:exclude ["children"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "w-80"}
                                                 [sut/progress (select-keys args [:value])]])))
