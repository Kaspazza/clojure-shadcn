(ns clojure-shadcn.stories.progress-stories
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.progress :as sut]
   [clojure-shadcn.ui.components.slider   :as slider]
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
                                   :exclude ["children"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "w-80"}
                                                 [sut/progress (select-keys args [:value])]])))

(defstory
 ProgressTimed
 "Timed — the upstream demonstration advances after mount to communicate asynchronous progress."
 []
 (let [timer-id (atom nil)]
   (r/as-element
    [(r/create-class
      {:display-name "timed-progress-story"
       :get-initial-state (fn [_] #js {:value 13})
       :component-did-mount (fn [^js component]
                              (reset! timer-id (js/setTimeout #(.setState component #js {:value 66})
                                                              500)))
       :component-will-unmount (fn [_] (when-let [id @timer-id] (js/clearTimeout id)))
       :reagent-render (fn []
                         (let [^js this (r/current-component)
                               value (.. this -state -value)]
                           (helpers/wrap-component [:div {:class "w-80 space-y-2"}
                                                    [:div {:class "flex justify-between text-sm"}
                                                     [:span "Installing update"]
                                                     [:span (str value "%")]]
                                                    [sut/progress {:value value
                                                                   :aria-label
                                                                   "Installing update"}]])))})])))

(defstory ProgressLabel
          "Label — upstream labeled progress recipe keeps value text adjacent to the indicator."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "w-80 space-y-2"}
                          [:div {:class "flex items-center justify-between text-sm"}
                           [:span {:id "storage-label"
                                   :class "font-medium"}
                            "Storage used"]
                           [:span {:class "text-muted-foreground tabular-nums"}
                            "32 of 50 GB"]]
                          [sut/progress {:value 64
                                         :aria-labelledby "storage-label"}]])))

(defstory ProgressSliderControlled
          "Slider controlled — the upstream interactive recipe uses one value for both controls."
          []
          (r/as-element [(fn []
                           (let [value (r/atom 40)]
                             (fn []
                               (helpers/wrap-component
                                [:div {:class "w-80 space-y-5"}
                                 [:div {:class "flex justify-between text-sm"}
                                  [:span "Completion"]
                                  [:output {:class "tabular-nums"}
                                   (str @value "%")]]
                                 [sut/progress {:value @value
                                                :aria-label "Completion"}]
                                 [slider/slider {:value [@value]
                                                 :on-value-change #(reset! value (first %))
                                                 :aria-label "Set completion"}]]))))]))

(defstory ProgressRTL
          "RTL — label and progress value follow right-to-left reading order."
          []
          (r/as-element (helpers/wrap-component [:div {:dir "rtl"
                                                       :class "w-80 space-y-2 text-right"}
                                                 [:div {:class "flex justify-between text-sm"}
                                                  [:span {:id "rtl-progress-label"}
                                                   "اكتمل التحميل"]
                                                  [:span "٧٥٪"]]
                                                 [sut/progress {:value 75
                                                                :aria-labelledby
                                                                "rtl-progress-label"}]])))
