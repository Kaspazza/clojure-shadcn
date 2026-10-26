(ns clojure-shadcn.stories.date-picker-stories
  (:require
   [clojure-shadcn.stories.date-picker.controlled-range       :as controlled-range]
   [clojure-shadcn.stories.date-picker.controlled-single      :as controlled-single]
   [clojure-shadcn.stories.date-picker.date-of-birth-dropdown :as date-of-birth-dropdown]
   [clojure-shadcn.stories.date-picker.date-picker-states     :as date-picker-states]
   [clojure-shadcn.stories.date-picker.input-and-time         :as input-and-time]
   [clojure-shadcn.stories.date-picker.playground             :as playground]
   [clojure-shadcn.stories.date-picker.presets                :as presets]
   [clojure-shadcn.stories.date-picker.right-to-left          :as right-to-left]
   [clojure-shadcn.stories.date-picker.uncontrolled-single    :as uncontrolled-single]
   [clojure-shadcn.stories.helpers                            :as helpers]
   [reagent.core                                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Date Picker"
       :parameters #js {:layout "padded"}})

(defdoc
 Installation
 []
 (r/as-element
  [helpers/installation-scene
   {:description
    "Two intentionally distinct APIs: date-picker/native-date-picker is the legacy platform input; composed-date-picker combines the existing Radix Popover, local Calendar, and Button components. No Base UI dependency is used."
    :npm-install "npm install @radix-ui/react-popover react-day-picker date-fns lucide-react"
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
     {:component-name "native-date-picker (legacy: date-picker)"
      :description
      "Explicit name for the platform <input type=date>. date-picker remains an exact backward-compatible alias, so existing callers retain native values, events, forms, validation, locale UI, and keyboard behavior."
      :props [{:name ":value / :default-value"
               :type "ISO yyyy-MM-dd string"
               :default nil
               :description "Controlled value or uncontrolled initial value."}
              {:name ":on-change"
               :type "event -> any"
               :default nil
               :description "Receives the native input event."}
              {:name ":wrapper-class / :icon-class / :class"
               :type "string"
               :default nil
               :description "Classes for the wrapper, icon, and input."}
              {:name "native input props"
               :type "map entries"
               :default nil
               :description "Includes :name, :min, :max, :required, :disabled, and ARIA props."}]}]
    [helpers/api-component-card
     {:component-name "composed-date-picker"
      :description
      "Composed API root backed by Radix Popover. This API does not own date state: compose its trigger, button, content, and calendar parts and keep selection at the call site."
      :props [{:name ":open / :on-open-change"
               :type "boolean / boolean -> any"
               :default nil
               :description "Controlled popover state."}
              {:name ":default-open"
               :type "boolean"
               :default nil
               :description "Initial state for an uncontrolled popover."}
              {:name "Radix Popover.Root props"
               :type "map entries"
               :default nil
               :description "Remaining props are forwarded to the local Popover root."}]}]
    [helpers/api-component-card
     {:component-name "date-picker-trigger / date-picker-button / date-picker-content"
      :description
      "Small presentation pieces for upstream-style assembly. The trigger defaults to as-child, the button defaults to outline styling, and content defaults to start alignment with calendar-sized padding."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Merged into the button or content defaults."}
              {:name "component props"
               :type "map entries"
               :default nil
               :description "Forwarded to the corresponding local Popover or Button component."}]}]
    [helpers/api-component-card
     {:component-name "date-picker-calendar"
      :description
      "Thin composition boundary over the local Calendar. React DayPicker owns calendar semantics; callers own selected values."
      :props
      [{:name ":mode"
        :type "\"single\" | \"range\""
        :default nil
        :description "Selects the DayPicker selection model."}
       {:name ":selected / :on-select"
        :type "Date or DateRange / function"
        :default nil
        :description "Controlled single or range selection."}
       {:name "Calendar / DayPicker props"
        :type "map entries"
        :default nil
        :description
        "Includes caption layout, locale, direction, month bounds, disabled dates, and number of months."}]}]
    [:div {:class "rounded-lg border bg-muted/50 p-4 text-sm"}
     [:strong "Naming contract: "]
     "date-picker is legacy and remains native. native-date-picker makes that choice explicit. composed-date-picker and its date-picker-* parts are the Popover + Calendar API."]])))

(defstory DatePickerStates
          "Backward-compatible native date input under both its legacy and explicit names."
          {:source-code (embed-source "clojure-shadcn.stories.date-picker.date-picker-states")}
          []
          (r/as-element (helpers/wrap-component [date-picker-states/example])))


(defstory ControlledSingle
          "Controlled single selection and controlled Radix Popover state."
          {:source-code (embed-source "clojure-shadcn.stories.date-picker.controlled-single")}
          []
          (r/as-element (helpers/wrap-component [controlled-single/example])))

(defstory
 UncontrolledSingle
 "Uncontrolled Popover and DayPicker interaction: no open or selected value is retained by this composition layer."
 {:source-code (embed-source "clojure-shadcn.stories.date-picker.uncontrolled-single")}
 []
 (r/as-element (helpers/wrap-component [uncontrolled-single/example])))


(defstory ControlledRange
          "Controlled range selection with two months."
          {:source-code (embed-source "clojure-shadcn.stories.date-picker.controlled-range")}
          []
          (r/as-element (helpers/wrap-component [controlled-range/example])))


(defstory Presets
          "Presets remain ordinary buttons that update the same controlled date value."
          {:source-code (embed-source "clojure-shadcn.stories.date-picker.presets")}
          []
          (r/as-element (helpers/wrap-component [presets/example])))


(defstory DateOfBirthDropdown
          "DOB selection with month/year dropdowns and an explicit 1900-to-current month interval."
          {:source-code (embed-source "clojure-shadcn.stories.date-picker.date-of-birth-dropdown")}
          []
          (r/as-element (helpers/wrap-component [date-of-birth-dropdown/example])))


(defstory InputAndTime
          "A controlled Calendar synchronized with a text input and a separate native time input."
          {:source-code (embed-source "clojure-shadcn.stories.date-picker.input-and-time")}
          []
          (r/as-element (helpers/wrap-component [input-and-time/example])))


(defstory RightToLeft
          "Representative Arabic locale and RTL direction across trigger and calendar."
          {:source-code (embed-source "clojure-shadcn.stories.date-picker.right-to-left")}
          []
          (r/as-element (helpers/wrap-component [right-to-left/example])))

(defstory
 Playground
 "Legacy native date-picker playground retained for existing consumers."
 {:args {:default-value "2026-03-01"
         :disabled false
         :required false
         :min "2026-01-01"
         :max "2026-12-31"}
  :arg-types {:default-value {:name "Initial value"
                              :control {:type "text"}}
              :disabled {:name "Disabled"
                         :control {:type "boolean"}}
              :required {:name "Required"
                         :control {:type "boolean"}}
              :min {:name "Min"
                    :control {:type "text"}}
              :max {:name "Max"
                    :control {:type "text"}}}
  :parameters
  {:playground
   {:description
    "Explore date picker initial value, disabled, required, min and max with a live preview."}
   :controls {:expanded false
              :exclude ["value" "on-change" "class" "wrapper-class" "icon-class"]}}
  :source-code (embed-source "clojure-shadcn.stories.date-picker.playground")}
 [args]
 (r/as-element (helpers/wrap-component [playground/example args])))
