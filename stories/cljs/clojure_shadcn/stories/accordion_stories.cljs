(ns clojure-shadcn.stories.accordion-stories
  (:require
   [clojure-shadcn.stories.helpers         :as helpers]
   [clojure-shadcn.ui.components.accordion :as sut]
   [reagent.core                           :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Accordion"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Accessible vertically stacked disclosure sections."
                        :npm-install "npm install @radix-ui/react-accordion lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.accordion")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/accordion.cljs"
                        :filename "accordion.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [helpers/api-component-card
    {:component-name "accordion / accordion-item / accordion-trigger / accordion-content"
     :link {:href "https://www.radix-ui.com/primitives/docs/components/accordion"
            :label "Radix Accordion Docs"}
     :description
     "Data-oriented wrappers over Radix Accordion. Root accepts :type, :value, :default-value, :on-value-change, :collapsible and :orientation; all props are normalized and forwarded."
     :props [{:name ":type"
              :type ":single | :multiple"
              :default nil
              :description "Selection mode."}
             {:name ":value / :default-value"
              :type "string | vector"
              :default nil
              :description "Controlled or initial item values."}
             {:name ":disabled"
              :type "boolean"
              :default nil
              :description "Disable an item."}]}])))

(defstory AccordionBasic
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "max-w-xl p-6"}
                          [sut/accordion {:type "single"
                                          :collapsible true
                                          :class "w-full"}
                           [sut/accordion-item {:value "one"}
                            [sut/accordion-trigger {}
                             "Is it accessible?"]
                            [sut/accordion-content {}
                             "Yes. It follows the WAI-ARIA accordion pattern."]]
                           [sut/accordion-item {:value "two"}
                            [sut/accordion-trigger {}
                             "Is it styled?"]
                            [sut/accordion-content {}
                             "Yes. Classes remain fully overridable."]]]])))

(defstory AccordionDisabled
          []
          (r/as-element (helpers/wrap-component [:div {:class "max-w-xl p-6"}
                                                 [sut/accordion {:type "single"}
                                                  [sut/accordion-item {:value "locked"
                                                                       :disabled true}
                                                   [sut/accordion-trigger {}
                                                    "Unavailable section"]
                                                   [sut/accordion-content {}
                                                    "Cannot be opened."]]]])))
