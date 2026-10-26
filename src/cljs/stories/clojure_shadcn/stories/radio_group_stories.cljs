(ns clojure-shadcn.stories.radio-group-stories
  "Storybook stories for the Radio Group component. Ported from mateuszmazurczak.portfolio.ui-components.radio_group."
  (:require
   [clojure-shadcn.stories.helpers                               :as helpers]
   [clojure-shadcn.stories.radio-group.field-radio               :as example-field-radio]
   [clojure-shadcn.stories.radio-group.playground                :as example-playground]
   [clojure-shadcn.stories.radio-group.radio-choice-cards        :as example-radio-choice-cards]
   [clojure-shadcn.stories.radio-group.radio-group-demo          :as example-radio-group-demo]
   [clojure-shadcn.stories.radio-group.radio-group-disabled      :as example-radio-group-disabled]
   [clojure-shadcn.stories.radio-group.radio-group-horizontal    :as example-radio-group-horizontal]
   [clojure-shadcn.stories.radio-group.radio-group-invalid       :as example-radio-group-invalid]
   [clojure-shadcn.stories.radio-group.radio-group-rtl           :as example-radio-group-rtl]
   [clojure-shadcn.stories.radio-group.radio-option-descriptions :as
                                                                 example-radio-option-descriptions]
   [reagent.core                                                 :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Radio Group"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Radio Group component built on Radix UI primitives."
                        :npm-install "npm install @radix-ui/react-radio-group lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.radio_group")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/radio_group.cljs"
                        :filename "radio_group.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "radio-group"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/radio-group"
              :label "Radix Radio Group Docs"}
       :description
       "Container for mutually-exclusive options. Handles keyboard navigation and selected value management. Additional props are forwarded to Radix RadioGroup.Root."
       :props [{:name ":value"
                :type "string"
                :default nil
                :description "Controlled selected value."}
               {:name ":default-value"
                :type "string"
                :default nil
                :description "Uncontrolled initial selected value."}
               {:name ":on-value-change"
                :type "function"
                :default nil
                :description "Callback when selected value changes: (fn [value] ...)."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables all items in the group."}
               {:name ":required"
                :type "boolean"
                :default nil
                :description "Marks group as required for forms."}
               {:name ":name"
                :type "string"
                :default nil
                :description "Form field name."}
               {:name ":orientation"
                :type "keyword"
                :default ":vertical"
                :description ":vertical or :horizontal."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix RadioGroup.Root."}]}]
     [helpers/api-component-card
      {:component-name "radio-group-item"
       :description "Single selectable option inside radio-group."
       :props [{:name ":value"
                :type "string"
                :default nil
                :description "Value represented by this option."}
               {:name ":id"
                :type "string"
                :default nil
                :description "ID for associated label :html-for."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables this option."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix RadioGroup.Item."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Each radio-group-item must have a unique :value; without it, selection logic cannot work."]
       [:li "Use matching item :id + label :html-for for accessible click targets."]
       [:li
        "Prefer a single radio-group per decision domain; avoid nesting groups with same :name."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "(let [billing (r/atom \"monthly\")]\n  [radio-group {:value @billing\n                :on-value-change #(reset! billing %)\n                :name \"billing-cycle\"}\n   [:div {:class \"flex items-center gap-2\"}\n    [radio-group-item {:id \"bill-monthly\" :value \"monthly\"}]\n    [label {:html-for \"bill-monthly\"} \"Monthly\"]]\n   [:div {:class \"flex items-center gap-2\"}\n    [radio-group-item {:id \"bill-yearly\" :value \"yearly\"}]\n    [label {:html-for \"bill-yearly\"} \"Yearly\"]]])"]]
      [:div {:class "flex flex-wrap gap-2 mt-3"}]]]])))

(defstory
 RadioGroupDemo
 "Radio group with labeled options.

  Radix primitive: @radix-ui/react-radio-group

  Use for exclusive choices like density or layout."
 {:source-code (embed-source "clojure-shadcn.stories.radio-group.radio-group-demo")}
 []
 (r/as-element (helpers/wrap-component [example-radio-group-demo/example])))

(defstory
 FieldRadio
 "Radio group embedded in Field layout.

  Radix primitive: @radix-ui/react-radio-group

  Ideal for pricing or plan selection with supporting copy."
 {:source-code (embed-source "clojure-shadcn.stories.radio-group.field-radio")}
 []
 (r/as-element (helpers/wrap-component [example-field-radio/example])))

(defstory
 RadioGroupDisabled
 "Disabled radio items in a group.

  Radix primitive: @radix-ui/react-radio-group

  Use disabled options for unavailable choices."
 {:source-code (embed-source "clojure-shadcn.stories.radio-group.radio-group-disabled")}
 []
 (r/as-element (helpers/wrap-component [example-radio-group-disabled/example])))

(defstory
 RadioGroupInvalid
 "Invalid radio group state with validation hint.

  Radix primitive: @radix-ui/react-radio-group

  Apply :aria-invalid on items and show an explicit error message."
 {:source-code (embed-source "clojure-shadcn.stories.radio-group.radio-group-invalid")}
 []
 (r/as-element (helpers/wrap-component [example-radio-group-invalid/example])))

(defstory
 RadioGroupHorizontal
 "Horizontal layout variant.

  Radix primitive: @radix-ui/react-radio-group

  Use :orientation :horizontal for inline radio groups."
 {:source-code (embed-source "clojure-shadcn.stories.radio-group.radio-group-horizontal")}
 []
 (r/as-element (helpers/wrap-component [example-radio-group-horizontal/example])))


(defstory
 Playground
 "Interactive radio-group playground."
 {:args {:default-value "comfortable"
         :orientation "vertical"
         :disabled false
         :required false}
  :arg-types {:default-value {:name "Initial value"
                              :control {:type "select"}
                              :options ["default" "comfortable" "compact"]}
              :orientation {:name "Orientation"
                            :control {:type "select"}
                            :options ["vertical" "horizontal"]}
              :disabled {:name "Disabled"
                         :control {:type "boolean"}}
              :required {:name "Required"
                         :control {:type "boolean"}}}
  :parameters
  {:playground
   {:description
    "Explore radio group initial value, orientation, disabled and required with a live preview."}
   :controls {:expanded false
              :exclude ["value" "on-value-change" "class"]}}
  :decode-args (fn [args] (update args :orientation keyword))
  :source-code (embed-source "clojure-shadcn.stories.radio-group.playground")}
 [args]
 (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory RadioOptionDescriptions
          "Each option has supporting copy. Based on shadcn/ui Radio Group — Description."
          {:source-code (embed-source
                         "clojure-shadcn.stories.radio-group.radio-option-descriptions")}
          []
          (r/as-element (helpers/wrap-component [example-radio-option-descriptions/example])))

(defstory RadioChoiceCards
          "Whole-row selectable choice cards. Based on shadcn/ui Radio Group — Cards."
          {:source-code (embed-source "clojure-shadcn.stories.radio-group.radio-choice-cards")}
          []
          (r/as-element (helpers/wrap-component [example-radio-choice-cards/example])))

(defstory RadioGroupRtl
          "Right-to-left radio choices. Based on shadcn/ui Radio Group — RTL."
          {:source-code (embed-source "clojure-shadcn.stories.radio-group.radio-group-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-radio-group-rtl/example])))
