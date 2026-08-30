(ns clojure-shadcn.stories.alert-stories
  (:require
   ["lucide-react"                     :refer [CheckCircle2 CircleAlert]]
   [clojure-shadcn.stories.helpers     :as helpers]
   [clojure-shadcn.ui.components.alert :as sut]
   [reagent.core                       :as r])
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
    {:component-name "alert / alert-title / alert-description"
     :description
     "Semantic alert layout. Props are normalized and forwarded to the underlying elements."
     :props [{:name ":variant"
              :type ":default | :destructive"
              :default ":default"
              :description "Visual intent."}
             {:name ":class"
              :type "string"
              :default nil
              :description "Merged Tailwind classes."}]}])))

(defstory AlertBasic
 "Interactive alert playground."
 {:args {:variant "default" :title "Success" :description "Your changes have been saved."}
  :arg-types {:variant {:control {:type "select"} :options ["default" "destructive"]}
              :title {:control {:type "text"}}
              :description {:control {:type "text"}}}
  :parameters {:controls {:exclude ["class" "role"]}}
  :decode-args (fn [{:keys [variant] :as args}] (cond-> args variant (update :variant keyword)))}
 [args]
 (r/as-element (helpers/wrap-component [:div {:class "max-w-xl p-6"} [sut/alert (select-keys args [:variant]) [:> CheckCircle2] [sut/alert-title {} (:title args)] [sut/alert-description {} (:description args)]]])))

(defstory AlertDestructive
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "max-w-xl p-6"}
                          [sut/alert {:variant :destructive}
                           [:> CircleAlert]
                           [sut/alert-title {}
                            "Unable to continue"]
                           [sut/alert-description {}
                            "Resolve the validation errors and try again."]]])))
