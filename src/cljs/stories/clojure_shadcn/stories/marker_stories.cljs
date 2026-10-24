(ns clojure-shadcn.stories.marker-stories
  (:require
   ["lucide-react"                      :refer [CheckCircle Clock Info]]
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.loader :as loader]
   [clojure-shadcn.ui.components.marker :as sut]
   [reagent.core                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Marker"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Current shadcn/ui marker primitive."
                        :npm-install "npm install @radix-ui/react-slot"
                        :source-code (embed-source "clojure-shadcn.ui.components.marker")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/marker.cljs"
                        :filename "marker.cljs"}]))

(defstory ApiReference
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6 max-w-4xl"}
             [helpers/api-component-card
              {:component-name "marker / marker-icon / marker-content / marker-variants"
               :description
               "Status or timeline marker with optional separator/border and public class helper."
               :props [{:name ":variant"
                        :type ":default | :separator | :border"
                        :default ":default"}
                       {:name ":as-child?"
                        :type "boolean"
                        :default "false"}
                       {:name ":class"
                        :type "string"
                        :default nil}]}]])))

(defstory MarkerVariants
          "Interactive marker playground."
          {:args {:variant "default"
                  :content "Deployment completed"}
           :arg-types {:variant {:name "Variant"
                                 :control {:type "select"}
                                 :options ["default" "separator" "border"]}
                       :content {:name "Content"
                                 :control {:type "text"}}}
           :parameters {:playground {:description "Explore marker variant and content with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class" "as-child?"]}}
           :decode-args (fn [{:keys [variant]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "max-w-lg"}
                                                 [sut/marker
                                                  (select-keys args [:variant])
                                                  [sut/marker-icon {}
                                                   [:> CheckCircle]]
                                                  [sut/marker-content {}
                                                   (:content args)]]])))

(defstory MarkerAggregate
          "All marker primitives and variants in one comparison."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "w-full max-w-xl space-y-5"}
                          (for [[variant copy icon]
                                [[:default "Informational marker" [:> Info]]
                                 [:separator "Separated milestone" [:> CheckCircle]]
                                 [:border "Bordered status row" [:> Clock]]]]
                            ^{:key variant}
                            [sut/marker {:variant variant}
                             [sut/marker-icon {}
                              icon]
                             [sut/marker-content {}
                              copy]])])))

(defstory MarkerTimeline
          "Timeline composition using border markers and semantic status text."
          []
          (r/as-element (helpers/wrap-component [:ol {:class "max-w-lg"}
                                                 (for [[copy time] [["Build started" "09:30"]
                                                                    ["Tests passed" "09:32"]
                                                                    ["Deployed" "09:35"]]]
                                                   ^{:key copy}
                                                   [:li
                                                    [sut/marker {:variant :border
                                                                 :class "py-3"}
                                                     [sut/marker-icon {}
                                                      [:> CheckCircle]]
                                                     [sut/marker-content
                                                      {:class "flex flex-1 justify-between"}
                                                      [:span copy]
                                                      [:time time]]]])])))

(defstory
 MarkerAsChildLinksAndButtons
 "Slot-based polymorphism: the marker classes and data attributes are applied to one link or button child."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-lg space-y-3"}
    [sut/marker {:as-child? true}
     [:a {:href "https://example.com"
          :target "_blank"
          :rel "noreferrer"}
      [sut/marker-icon {}
       [:> Info]]
      [sut/marker-content {}
       "Open deployment details"]]]
    [sut/marker {:as-child? true
                 :variant :border}
     [:button {:type "button"
               :on-click #(js/alert "Retry requested")}
      [sut/marker-icon {}
       [:> Clock]]
      [sut/marker-content {}
       "Retry deployment"]]]])))

(defstory MarkerShimmerAndSpinnerStatus
          "Markers can compose loading visuals while the content supplies readable status text."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "max-w-lg space-y-4"}
                          [sut/marker {:variant :separator}
                           [sut/marker-icon {}
                            [loader/loader {:variant :circular
                                            :size :sm
                                            :label "Syncing"}]]
                           [sut/marker-content {}
                            "Syncing records"]]
                          [sut/marker {:variant :border}
                           [sut/marker-icon {}
                            [loader/loader {:variant :pulse-dot
                                            :size :sm
                                            :label "Indexing"}]]
                           [sut/marker-content {:class "animate-pulse motion-reduce:animate-none"}
                            "Indexing search data"]]])))
