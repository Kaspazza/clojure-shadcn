(ns clojure-shadcn.stories.alert-stories
  (:require
   ["lucide-react"                      :refer [CheckCircle2 CircleAlert]]
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.alert  :as sut]
   [clojure-shadcn.ui.components.button :as button]
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

(defstory AlertBasic
          "Interactive alert playground."
          {:args {:variant "default"
                  :title "Success"
                  :description "Your changes have been saved."}
           :arg-types {:variant {:control {:type "select"}
                                 :options ["default" "destructive"]}
                       :title {:control {:type "text"}}
                       :description {:control {:type "text"}}}
           :parameters {:controls {:exclude ["class" "role"]}}
           :decode-args (fn [{:keys [variant]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "max-w-xl p-6"}
                                                 [sut/alert
                                                  (select-keys args [:variant])
                                                  [:> CheckCircle2]
                                                  [sut/alert-title {}
                                                   (:title args)]
                                                  [sut/alert-description {}
                                                   (:description args)]]])))

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

(defstory AlertAction
          "Alert with an action control."
          []
          (r/as-element (helpers/wrap-component
                         [sut/alert {:class "max-w-md"}
                          [sut/alert-title {}
                           "Dark mode is now available"]
                          [sut/alert-description {}
                           "Enable it under your profile settings to get started."]
                          [sut/alert-action {}
                           [button/button {:variant :default
                                           :size :xs}
                            "Enable"]]])))

(defstory
 AlertColors
 "Colors — upstream class-based color treatments without inventing alert variants."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid max-w-2xl gap-3"}
    [sut/alert
     {:class
      "border-emerald-600/40 text-emerald-700 dark:text-emerald-400 [&>svg]:text-emerald-600"}
     [:> CheckCircle2]
     [sut/alert-title {}
      "Deployment complete"]
     [sut/alert-description {}
      "The production release is now available."]]
    [sut/alert {:class
                "border-amber-600/40 text-amber-800 dark:text-amber-300 [&>svg]:text-amber-600"}
     [:> CircleAlert]
     [sut/alert-title {}
      "Maintenance scheduled"]
     [sut/alert-description {}
      "Editing will be unavailable for approximately five minutes."]]
    [sut/alert {:class "border-sky-600/40 text-sky-800 dark:text-sky-300 [&>svg]:text-sky-600"}
     [:> CircleAlert]
     [sut/alert-title {}
      "New workspace policy"]
     [sut/alert-description {}
      "Review the updated access requirements before Friday."]]])))

(defstory AlertRTL
          "RTL — alert icon and copy follow right-to-left document direction."
          []
          (r/as-element (helpers/wrap-component [:div {:dir "rtl"
                                                       :class "max-w-xl text-right"}
                                                 [sut/alert {}
                                                  [:> CheckCircle2]
                                                  [sut/alert-title {}
                                                   "تم حفظ التغييرات"]
                                                  [sut/alert-description {}
                                                   "تم تحديث إعدادات حسابك بنجاح."]]])))
