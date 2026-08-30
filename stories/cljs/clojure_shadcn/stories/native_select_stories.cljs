(ns clojure-shadcn.stories.native-select-stories
  (:require
   [clojure-shadcn.stories.helpers             :as helpers]
   [clojure-shadcn.ui.components.native-select :as sut]
   [reagent.core                               :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Native Select"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Styled native select preserving platform semantics and accessibility."
                        :npm-install "npm install lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.native_select")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/native_select.cljs"
                        :filename "native_select.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "native-select"
      :description
      "Native HTML select wrapped for styling with an aria-hidden chevron. Browser semantics, keyboard interaction, form submission, and option constraints remain native."
      :props
      [{:name ":size"
        :type ":default | :sm"
        :default ":default"
        :description "Controls select height and padding; consumed as a styling data attribute."}
       {:name ":class"
        :type "string"
        :default nil
        :description "Classes merged onto the select, not its wrapper."}
       {:name "select props"
        :type "map entries"
        :default nil
        :description
        "Normalized and forwarded to select, including :name, :value, :default-value, :on-change, :disabled, :required, and ARIA props."}]}]
    [helpers/api-component-card
     {:component-name "native-select-option / native-select-optgroup"
      :description
      "Styled native option and optgroup elements. Nest both beneath native-select; optgroup should contain options and receive a :label."
      :props
      [{:name ":class / native props"
        :type "string / map entries"
        :default nil
        :description
        "Classes are merged and remaining normalized props forwarded to option or optgroup."}]}]])))

(defstory NativeSelectDemo
          []
          (r/as-element (helpers/wrap-component [sut/native-select {:aria-label "Choose a framework"
                                                                    :default-value ""}
                                                 [sut/native-select-option {:value ""
                                                                            :disabled true}
                                                  "Select framework"]
                                                 [sut/native-select-optgroup {:label "Frontend"}
                                                  [sut/native-select-option {:value "reagent"}
                                                   "Reagent"]
                                                  [sut/native-select-option {:value "react"}
                                                   "React"]]
                                                 [sut/native-select-optgroup {:label "Backend"}
                                                  [sut/native-select-option {:value "clojure"}
                                                   "Clojure"]]])))

(defstory NativeSelectSizes
          []
          (r/as-element (helpers/wrap-component [:div {:class "flex items-center gap-3"}
                                                 [sut/native-select {:size :default
                                                                     :default-value "one"}
                                                  [sut/native-select-option {:value "one"}
                                                   "Default"]]
                                                 [sut/native-select {:size :sm
                                                                     :default-value "two"}
                                                  [sut/native-select-option {:value "two"}
                                                   "Small"]]
                                                 [sut/native-select {:disabled true}
                                                  [sut/native-select-option {}
                                                   "Disabled"]]])))


(defstory NativeSelectPlayground
  "Interactive native-select playground."
  {:args {:default-value "reagent" :size "default" :disabled false :required false}
   :arg-types {:default-value {:control {:type "select"} :options ["reagent" "react" "clojure"]} :size {:control {:type "select"} :options ["default" "sm"]} :disabled {:control {:type "boolean"}} :required {:control {:type "boolean"}}}
   :parameters {:controls {:exclude ["value" "on-change" "class"]}}
   :decode-args (fn [{:keys [size] :as args}] (update args :size keyword))}
  [args]
  (r/as-element (helpers/wrap-component [sut/native-select (assoc args :aria-label "Framework") [sut/native-select-option {:value "reagent"} "Reagent"] [sut/native-select-option {:value "react"} "React"] [sut/native-select-option {:value "clojure"} "Clojure"]])))
