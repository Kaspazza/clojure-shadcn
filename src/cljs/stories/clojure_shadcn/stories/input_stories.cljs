(ns clojure-shadcn.stories.input-stories
  "Storybook stories for the Input component. Ported from mateuszmazurczak.portfolio.ui-components.input."
  (:require
   [clojure-shadcn.stories.helpers                         :as helpers]
   [clojure-shadcn.stories.input.input-badge-label         :as input-badge-label]
   [clojure-shadcn.stories.input.input-controlled          :as input-controlled]
   [clojure-shadcn.stories.input.input-demo                :as input-demo]
   [clojure-shadcn.stories.input.input-disabled            :as input-disabled]
   [clojure-shadcn.stories.input.input-field-disabled      :as input-field-disabled]
   [clojure-shadcn.stories.input.input-field-group-actions :as input-field-group-actions]
   [clojure-shadcn.stories.input.input-file                :as input-file]
   [clojure-shadcn.stories.input.input-file-guidance       :as input-file-guidance]
   [clojure-shadcn.stories.input.input-invalid             :as input-invalid]
   [clojure-shadcn.stories.input.input-joined-button-group :as input-joined-button-group]
   [clojure-shadcn.stories.input.input-required            :as input-required]
   [clojure-shadcn.stories.input.input-rtl                 :as input-rtl]
   [clojure-shadcn.stories.input.input-two-column-grid     :as input-two-column-grid]
   [clojure-shadcn.stories.input.input-with-button         :as input-with-button]
   [clojure-shadcn.stories.input.input-with-label          :as input-with-label]
   [clojure-shadcn.stories.input.input-with-text           :as input-with-text]
   [clojure-shadcn.stories.input.playground                :as playground]
   [reagent.core                                           :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Input"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Input component for forms."
                        :npm-install "No external dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.input")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/input.cljs"
                        :filename "input.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "input"
       :description
       "Styled native input element. Supports controlled/uncontrolled form patterns and forwards additional props to underlying <input>."
       :props [{:name ":type"
                :type "string"
                :default "\"text\""
                :description "Native input type."}
               {:name ":value"
                :type "string"
                :default nil
                :description "Controlled value."}
               {:name ":default-value"
                :type "string"
                :default nil
                :description "Uncontrolled initial value."}
               {:name ":placeholder"
                :type "string"
                :default nil
                :description "Placeholder text."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables input."}
               {:name ":required"
                :type "boolean"
                :default nil
                :description "Marks input as required."}
               {:name ":on-change"
                :type "function"
                :default nil
                :description "Change handler."}
               {:name ":on-blur"
                :type "function"
                :default nil
                :description "Blur handler."}
               {:name ":on-focus"
                :type "function"
                :default nil
                :description "Focus handler."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to native <input>."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Prefer either controlled (:value + :on-change) or uncontrolled (:default-value), not both."]
       [:li "Use :aria-invalid true to trigger built-in invalid styling for validation states."]
       [:li "All native input attributes (autocomplete, min, max, accept, etc.) are forwarded."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"space-y-2 max-w-sm\"}\n [input {:type \"email\"\n         :placeholder \"you@example.com\"\n         :required true}]\n [input {:type \"text\"\n         :aria-invalid true\n         :default-value \"bad value\"}]]"]]]]])))

(defstory
 InputDemo
 "Basic input for standard text or email entry.

  Native element: <input>

  Our wrapper accepts all standard input props with Tailwind defaults."
 {:source-code (embed-source "clojure-shadcn.stories.input.input-demo")}
 []
 (r/as-element (helpers/wrap-component [input-demo/example])))

(defstory
 InputFile
 "File input with a label for upload workflows.

  Native element: <input type='file'>

  File inputs retain the same base styling for consistency."
 {:source-code (embed-source "clojure-shadcn.stories.input.input-file")}
 []
 (r/as-element (helpers/wrap-component [input-file/example])))

(defstory
 InputDisabled
 "Disabled input state for locked fields.

  Native element: <input>

  Disabled inputs are visually muted and non-interactive."
 {:source-code (embed-source "clojure-shadcn.stories.input.input-disabled")}
 []
 (r/as-element (helpers/wrap-component [input-disabled/example])))

(defstory
 InputInvalid
 "Invalid input state with validation message.

  Native element: <input>

  Use :aria-invalid true and helper/error text for validation feedback."
 {:source-code (embed-source "clojure-shadcn.stories.input.input-invalid")}
 []
 (r/as-element (helpers/wrap-component [input-invalid/example])))

(defstory
 InputWithLabel
 "Input paired with a label.

  Native element: <input>

  Use labels for accessible form controls."
 {:source-code (embed-source "clojure-shadcn.stories.input.input-with-label")}
 []
 (r/as-element (helpers/wrap-component [input-with-label/example])))

(defstory
 InputWithButton
 "Input combined with an inline action button.

  Native element: <input>

  Useful for newsletter signups or quick actions."
 {:source-code (embed-source "clojure-shadcn.stories.input.input-with-button")}
 []
 (r/as-element (helpers/wrap-component [input-with-button/example])))

(defstory
 InputWithText
 "Input with helper text for guidance.

  Native element: <input>

  Helper text clarifies validation or intent."
 {:source-code (embed-source "clojure-shadcn.stories.input.input-with-text")}
 []
 (r/as-element (helpers/wrap-component [input-with-text/example])))

(defstory
 InputControlled
 "Controlled input state managed via r/atom.

  Demonstrates how to keep input value in app state.

  Use controlled inputs when you need validation or formatting."
 {:source-code (embed-source "clojure-shadcn.stories.input.input-controlled")}
 []
 (r/as-element (helpers/wrap-component [input-controlled/example])))


(defstory
 Playground
 "Interactive native input playground."
 {:source-code (embed-source "clojure-shadcn.stories.input.playground")
  :args {:type "email"
         :placeholder "you@example.com"
         :default-value ""
         :disabled false
         :required false}
  :arg-types {:type {:name "Type"
                     :control {:type "select"}
                     :options ["text" "email" "password" "search"]}
              :placeholder {:name "Placeholder"
                            :control {:type "text"}}
              :default-value {:name "Initial value"
                              :control {:type "text"}}
              :disabled {:name "Disabled"
                         :control {:type "boolean"}}
              :required {:name "Required"
                         :control {:type "boolean"}}}
  :parameters
  {:playground
   {:description
    "Explore input type, placeholder, initial value, disabled and required with a live preview."}
   :controls {:expanded false
              :exclude ["value" "on-change" "class"]}}}
 [args]
 (r/as-element (helpers/wrap-component [playground/example args])))

(defstory InputFieldGroupActions
          "FieldGroup form with native reset and submit actions. Based on shadcn/ui Input — Field."
          {:source-code (embed-source "clojure-shadcn.stories.input.input-field-group-actions")}
          []
          (r/as-element (helpers/wrap-component [input-field-group-actions/example])))

(defstory InputFieldDisabled
          "Disabled control inside a disabled Field. Based on shadcn/ui Input — Field Disabled."
          {:source-code (embed-source "clojure-shadcn.stories.input.input-field-disabled")}
          []
          (r/as-element (helpers/wrap-component [input-field-disabled/example])))

(defstory InputFileGuidance
          "File input with format guidance. Based on shadcn/ui Input — File."
          {:source-code (embed-source "clojure-shadcn.stories.input.input-file-guidance")}
          []
          (r/as-element (helpers/wrap-component [input-file-guidance/example])))

(defstory InputTwoColumnGrid
          "Responsive two-column form grid. Based on shadcn/ui Input — Grid."
          {:source-code (embed-source "clojure-shadcn.stories.input.input-two-column-grid")}
          []
          (r/as-element (helpers/wrap-component [input-two-column-grid/example])))

(defstory InputRequired
          "Required marker with native required semantics. Based on shadcn/ui Input — Required."
          {:source-code (embed-source "clojure-shadcn.stories.input.input-required")}
          []
          (r/as-element (helpers/wrap-component [input-required/example])))

(defstory InputBadgeLabel
          "Label metadata with Badge. Based on shadcn/ui Input — Badge."
          {:source-code (embed-source "clojure-shadcn.stories.input.input-badge-label")}
          []
          (r/as-element (helpers/wrap-component [input-badge-label/example])))

(defstory InputJoinedButtonGroup
          "Joined input and action. Based on shadcn/ui Input — Button Group."
          {:source-code (embed-source "clojure-shadcn.stories.input.input-joined-button-group")}
          []
          (r/as-element (helpers/wrap-component [input-joined-button-group/example])))

(defstory InputRtl
          "Right-to-left labeled input. Based on shadcn/ui Input — RTL."
          {:source-code (embed-source "clojure-shadcn.stories.input.input-rtl")}
          []
          (r/as-element (helpers/wrap-component [input-rtl/example])))
