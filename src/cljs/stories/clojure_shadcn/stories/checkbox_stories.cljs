(ns clojure-shadcn.stories.checkbox-stories
  (:require
   [clojure-shadcn.stories.checkbox.checkbox-demo             :as checkbox-demo]
   [clojure-shadcn.stories.checkbox.checkbox-disabled         :as checkbox-disabled]
   [clojure-shadcn.stories.checkbox.checkbox-field-disabled   :as checkbox-field-disabled]
   [clojure-shadcn.stories.checkbox.checkbox-form-integration :as checkbox-form-integration]
   [clojure-shadcn.stories.checkbox.checkbox-group            :as checkbox-group]
   [clojure-shadcn.stories.checkbox.checkbox-indeterminate    :as checkbox-indeterminate]
   [clojure-shadcn.stories.checkbox.checkbox-invalid          :as checkbox-invalid]
   [clojure-shadcn.stories.checkbox.checkbox-rtl              :as checkbox-rtl]
   [clojure-shadcn.stories.checkbox.checkbox-table            :as checkbox-table]
   [clojure-shadcn.stories.checkbox.checkbox-with-field-label :as checkbox-with-field-label]
   [clojure-shadcn.stories.checkbox.checkbox-with-text        :as checkbox-with-text]
   [clojure-shadcn.stories.checkbox.field-checkbox            :as field-checkbox]
   [clojure-shadcn.stories.checkbox.playground                :as playground]
   [clojure-shadcn.stories.helpers                            :as helpers]
   [reagent.core                                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Checkbox"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Checkbox component with built-in check indicator."
                        :npm-install "npm install @radix-ui/react-checkbox lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.checkbox")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/checkbox.cljs"
                        :filename "checkbox.cljs"}]))

(defn ^:export ApiReference
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "p-6 max-w-4xl"}
     [:div {:class "space-y-4"}
      [helpers/api-component-card
       {:component-name "checkbox"
        :link {:href "https://www.radix-ui.com/primitives/docs/components/checkbox"
               :label "Radix Checkbox Docs"}
        :description
        "Checkbox component built on top of Radix UI checkbox. Supports controlled and uncontrolled modes, indeterminate state, and aria-invalid styling for form validation."
        :props
        [{:name ":checked"
          :type "boolean | :indeterminate | \"indeterminate\""
          :default nil
          :description
          "Controlled checked state. Use true/false for checked/unchecked, or :indeterminate keyword (string also accepted) for a partial selection state."}
         {:name ":default-checked"
          :type "boolean"
          :default nil
          :description
          "Uncontrolled default checked state. Use when you don't need to control the state externally."}
         {:name ":on-checked-change"
          :type "function"
          :default nil
          :description
          "Callback when checked state changes: (fn [checked?] ...). Receives true, false, or :indeterminate."}
         {:name ":disabled"
          :type "boolean"
          :default nil
          :description
          "Disables the checkbox. Renders with reduced opacity and cursor-not-allowed."}
         {:name ":required"
          :type "boolean"
          :default nil
          :description "Marks the checkbox as required for form validation."}
         {:name ":name"
          :type "string"
          :default nil
          :description
          "Form field name. Used when checkbox is inside a <form> for native form submission."}
         {:name ":value"
          :type "string"
          :default nil
          :description "Form field value submitted with the name when checked."}
         {:name ":aria-invalid"
          :type "boolean"
          :default nil
          :description
          "Marks the checkbox as invalid. Applies destructive ring and border styling for form validation errors."}
         {:name ":class"
          :type "string"
          :default nil
          :description "Additional Tailwind classes merged with default styling."}]}]
      [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
       [:h4 {:class "text-sm font-semibold mb-2"}
        "⚠️ Important Notes"]
       [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
        [:li "The props map {} is required even when empty."]
        [:li
         "For controlled usage, always pair :checked with :on-checked-change to avoid a read-only checkbox."]
        [:li
         "Use :indeterminate keyword for the indeterminate state. The component handles Radix JS interop in both directions — callbacks also return :indeterminate, never the raw string."]
        [:li
         "Use :aria-invalid true to show error styling — pair with Field and field-error for full validation UX."]]]]])))

(defstory
 CheckboxDemo
 "Checkbox variants: uncontrolled and controlled usage.

  Radix primitive: @radix-ui/react-checkbox

  Use labels and helper text for clarity. Always pair :checked with :on-checked-change for controlled usage."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-demo")}
 []
 (r/as-element (helpers/wrap-component [checkbox-demo/example])))

(defstory
 CheckboxWithFieldLabel
 "Checkbox inside Field wrapped by FieldLabel with title and description.

  Radix primitive: @radix-ui/react-checkbox

  Wrapping a horizontal Field inside field-label makes the entire row clickable.
  Use field-title and field-description for structured labelling."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-with-field-label")}
 []
 (r/as-element (helpers/wrap-component [checkbox-with-field-label/example])))

(defstory
 CheckboxDisabled
 "Disabled checkbox state.

  Radix primitive: @radix-ui/react-checkbox

  Disabled checkboxes are muted and non-interactive."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-disabled")}
 []
 (r/as-element (helpers/wrap-component [checkbox-disabled/example])))

(defstory
 CheckboxWithText
 "Checkbox with supporting text.

  Radix primitive: @radix-ui/react-checkbox

  Use descriptive text for legal agreements or onboarding flows."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-with-text")}
 []
 (r/as-element (helpers/wrap-component [checkbox-with-text/example])))

(defstory
 FieldCheckbox
 "Checkboxes inside Field layout.

  Radix primitive: @radix-ui/react-checkbox

  The Field components provide structured form layouts."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.field-checkbox")}
 []
 (r/as-element (helpers/wrap-component [field-checkbox/example])))

(defstory CheckboxGroup
          "Controlled checkbox group with independent values. Based on shadcn/ui Checkbox — Group."
          {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-group")}
          []
          (r/as-element (helpers/wrap-component [checkbox-group/example])))

(defstory
 CheckboxIndeterminate
 "Indeterminate checkbox for partial selections.

  Radix primitive: @radix-ui/react-checkbox

  Use :checked :indeterminate for partial selection states."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-indeterminate")}
 []
 (r/as-element (helpers/wrap-component [checkbox-indeterminate/example])))

(defstory
 CheckboxInvalid
 "Checkbox with invalid/error state for form validation.

  Radix primitive: @radix-ui/react-checkbox

  Use :aria-invalid true to show destructive ring and border styling.
  Pair with Field and field-error for a complete validation UX."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-invalid")}
 []
 (r/as-element (helpers/wrap-component [checkbox-invalid/example])))

(defstory
 CheckboxFormIntegration
 "Checkbox used inside a native HTML form with :name, :value, and :required.

  Radix primitive: @radix-ui/react-checkbox

  Use :name and :value for native form submission. Use :required to enforce the field before submit."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-form-integration")}
 []
 (r/as-element (helpers/wrap-component [checkbox-form-integration/example])))

(defstory
 CheckboxTable
 "Checkboxes inside a data table for row selection.

  Combines: Checkbox + Table components

  Common pattern for bulk actions in admin dashboards and data management UIs."
 {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-table")}
 []
 (r/as-element (helpers/wrap-component [checkbox-table/example])))

(defstory Playground
          "Interactive checkbox playground."
          {:source-code (embed-source "clojure-shadcn.stories.checkbox.playground")
           :args {:default-checked false
                  :disabled false
                  :required false}
           :arg-types {:default-checked {:name "Initially checked"
                                         :control {:type "boolean"}}
                       :disabled {:name "Disabled"
                                  :control {:type "boolean"}}
                       :required {:name "Required"
                                  :control {:type "boolean"}}}
           :parameters
           {:playground
            {:description
             "Explore checkbox initially checked, disabled and required with a live preview."}
            :controls {:expanded false
                       :exclude ["checked" "on-checked-change" "class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory CheckboxFieldDisabled
          "Disabled Field row. Based on shadcn/ui Checkbox — Field Disabled."
          {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-field-disabled")}
          []
          (r/as-element (helpers/wrap-component [checkbox-field-disabled/example])))

(defstory CheckboxRtl
          "Right-to-left checkbox field. Based on shadcn/ui Checkbox — RTL."
          {:source-code (embed-source "clojure-shadcn.stories.checkbox.checkbox-rtl")}
          []
          (r/as-element (helpers/wrap-component [checkbox-rtl/example])))
