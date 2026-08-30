(ns clojure-shadcn.stories.date-picker-stories
  (:require
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.date-picker :as sut]
   [reagent.core                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Date Picker"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Reusable native date-picker composition; a project extra rather than a registry module."
           :npm-install "npm install lucide-react"
           :source-code (embed-source "clojure-shadcn.ui.components.date_picker")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/date_picker.cljs"
           :filename "date_picker.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-4xl space-y-4 p-6"}
    [helpers/api-component-card
     {:component-name "date-picker"
      :description
      "Accessible composition over the platform date input. It preserves native form, validation, locale, and keyboard behavior."
      :props [{:name ":value / :default-value"
               :type "ISO date string"
               :default nil
               :description "Controlled or initial yyyy-MM-dd value."}
              {:name ":on-change"
               :type "event -> any"
               :default nil
               :description "Receives the native input event."}
              {:name ":wrapper-class"
               :type "string"
               :default nil
               :description "Classes merged onto the wrapper."}
              {:name ":icon-class"
               :type "string"
               :default nil
               :description "Classes merged onto the decorative icon."}
              {:name ":class"
               :type "string"
               :default nil
               :description "Classes merged onto the input."}
              {:name "native input props"
               :type "map entries"
               :default nil
               :description "Includes :name, :min, :max, :required, :disabled and ARIA props."}]}]
    [:div {:class "rounded-lg border bg-muted/50 p-4"}
     [:pre {:class "overflow-x-auto text-xs"}
      [:code
       "[date-picker {:name \"starts-on\"\n              :default-value \"2026-03-01\"\n              :min \"2026-01-01\"\n              :required true}] "]]]])))

(defstory DatePickerStates
          "Native date controls in default, constrained, and disabled states."
          []
          (r/as-element (helpers/wrap-component [:div {:class "grid max-w-sm gap-5"}
                                                 [:label {:class "grid gap-2 text-sm font-medium"}
                                                  "Start date"
                                                  [sut/date-picker {:name "start-date"
                                                                    :default-value "2026-03-01"}]]
                                                 [:label {:class "grid gap-2 text-sm font-medium"}
                                                  "Constrained date"
                                                  [sut/date-picker {:min "2026-03-01"
                                                                    :max "2026-12-31"
                                                                    :required true}]]
                                                 [:label {:class "grid gap-2 text-sm font-medium"}
                                                  "Unavailable"
                                                  [sut/date-picker {:default-value "2026-03-01"
                                                                    :disabled true}]]])))


(defstory DatePickerPlayground
  "Interactive native date-picker playground."
  {:args {:default-value "2026-03-01" :disabled false :required false :min "2026-01-01" :max "2026-12-31"}
   :arg-types {:default-value {:control {:type "text"}} :disabled {:control {:type "boolean"}} :required {:control {:type "boolean"}} :min {:control {:type "text"}} :max {:control {:type "text"}}}
   :parameters {:controls {:exclude ["value" "on-change" "class" "wrapper-class" "icon-class"]}}}
  [args]
  (r/as-element (helpers/wrap-component [:div {:class "max-w-sm p-6"} [sut/date-picker args]])))
