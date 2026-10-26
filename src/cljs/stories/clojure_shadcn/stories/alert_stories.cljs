(ns clojure-shadcn.stories.alert-stories
  (:require
   [clojure-shadcn.stories.alert.playground :as playground]
   [clojure-shadcn.stories.alert.alert-destructive :as alert-destructive]
   [clojure-shadcn.stories.alert.alert-action :as alert-action]
   [clojure-shadcn.stories.alert.alert-colors :as alert-colors]
   [clojure-shadcn.stories.alert.alert-rtl :as alert-rtl]
   [clojure-shadcn.stories.helpers :as helpers]
   [reagent.core                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Alert"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Static status message with semantic alert role."
                        :npm-install "No additional npm dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.alert")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/alert.cljs"
                        :filename "alert.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [helpers/api-component-card
    {:component-name "alert / alert-title / alert-description / alert-action"
     :description
     "Semantic alert layout with an optional trailing action. Props are normalized and forwarded to the underlying elements."
     :props [{:name ":variant"
              :type ":default | :destructive"
              :default ":default"
              :description "Visual intent."}
             {:name ":class"
              :type "string"
              :default nil
              :description "Merged Tailwind classes."}]}])))

(defstory Playground
          "Interactive alert playground."
          {:source-code (embed-source "clojure-shadcn.stories.alert.playground")
           :args {:variant "default"
                  :title "Success"
                  :description "Your changes have been saved."}
           :arg-types {:variant {:name "Variant"
                                 :control {:type "select"}
                                 :options ["default" "destructive"]}
                       :title {:name "Title"
                               :control {:type "text"}}
                       :description {:name "Description"
                                     :control {:type "text"}}}
           :parameters {:playground {:description "Explore alert variant, title and description with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class" "role"]}}
           :decode-args (fn [{:keys [variant]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory AlertDestructive
          {:source-code (embed-source "clojure-shadcn.stories.alert.alert-destructive")}
          []
          (r/as-element (helpers/wrap-component [alert-destructive/example])))

(defstory AlertAction
          "Alert with an action control."
          {:source-code (embed-source "clojure-shadcn.stories.alert.alert-action")}
          []
          (r/as-element (helpers/wrap-component [alert-action/example])))

(defstory
 AlertColors
 "Colors — upstream class-based color treatments without inventing alert variants."
 {:source-code (embed-source "clojure-shadcn.stories.alert.alert-colors")}
 []
 (r/as-element (helpers/wrap-component [alert-colors/example])))

(defstory AlertRTL
          "RTL — alert icon and copy follow right-to-left document direction."
          {:source-code (embed-source "clojure-shadcn.stories.alert.alert-rtl")}
          []
          (r/as-element (helpers/wrap-component [alert-rtl/example])))
