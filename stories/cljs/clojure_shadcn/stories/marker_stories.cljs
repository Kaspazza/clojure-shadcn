(ns clojure-shadcn.stories.marker-stories
  (:require
   ["lucide-react"                      :refer [CheckCircle Clock]]
   [clojure-shadcn.stories.helpers      :as helpers]
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

(defstory MarkerVariants "Interactive marker playground." {:args {:variant "default" :content "Deployment completed"} :arg-types {:variant {:control {:type "select"} :options ["default" "separator" "border"]} :content {:control {:type "text"}}} :parameters {:controls {:exclude ["class" "as-child?"]}} :decode-args (fn [{:keys [variant] :as args}] (cond-> args variant (update :variant keyword)))} [args] (r/as-element (helpers/wrap-component [:div {:class "max-w-lg"} [sut/marker (select-keys args [:variant]) [sut/marker-icon {} [:> CheckCircle]] [sut/marker-content {} (:content args)]]])))
