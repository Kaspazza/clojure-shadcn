(ns clojure-shadcn.stories.date-picker-stories
  (:require
   ["date-fns/locale"                        :refer [arSA]]
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.date-picker :as sut]
   [clojure-shadcn.ui.components.input       :as input]
   [reagent.core                             :as r])
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
          []
          (r/as-element (helpers/wrap-component [:div {:class "grid max-w-sm gap-5"}
                                                 [:label {:class "grid gap-2 text-sm font-medium"}
                                                  "Existing date-picker caller"
                                                  [sut/date-picker {:name "start-date"
                                                                    :default-value "2026-03-01"}]]
                                                 [:label {:class "grid gap-2 text-sm font-medium"}
                                                  "Explicit native-date-picker"
                                                  [sut/native-date-picker {:min "2026-03-01"
                                                                           :max "2026-12-31"
                                                                           :required true}]]])))

(defn- date-label
  [date]
  (if date (.toLocaleDateString date "en-US" #js {:dateStyle "long"}) "Pick a date"))

(defn- single-controlled-example
  []
  (r/with-let [selected (r/atom nil) open? (r/atom false)]
              [sut/composed-date-picker {:open @open?
                                         :on-open-change #(reset! open? %)}
               [sut/date-picker-trigger {}
                [sut/date-picker-button {:data-placeholder (nil? @selected)}
                 (date-label @selected)]]
               [sut/date-picker-content {}
                [sut/date-picker-calendar {:mode "single"
                                           :selected @selected
                                           :on-select
                                           #(when % (reset! selected %) (reset! open? false))}]]]))

(defstory ControlledSingle
          "Controlled single selection and controlled Radix Popover state."
          []
          (r/as-element (helpers/wrap-component [single-controlled-example])))

(defstory
 UncontrolledSingle
 "Uncontrolled Popover and DayPicker interaction: no open or selected value is retained by this composition layer."
 []
 (r/as-element (helpers/wrap-component [sut/composed-date-picker {:default-open false}
                                        [sut/date-picker-trigger {}
                                         [sut/date-picker-button {:data-placeholder true}
                                          "Pick a date"]]
                                        [sut/date-picker-content {}
                                         [sut/date-picker-calendar {:mode "single"}]]])))

(defn- range-example
  []
  (r/with-let [selected (r/atom nil)]
              [sut/composed-date-picker {}
               [sut/date-picker-trigger {}
                [sut/date-picker-button {:class "w-[320px]"}
                 (if-let [from (some-> @selected
                                       .-from)]
                   (str (date-label from)
                        (when-let [to (aget @selected "to")] (str " – " (date-label to))))
                   "Pick a date range")]]
               [sut/date-picker-content {}
                [sut/date-picker-calendar {:mode "range"
                                           :number-of-months 2
                                           :selected @selected
                                           :on-select #(reset! selected %)}]]]))

(defstory ControlledRange
          "Controlled range selection with two months."
          []
          (r/as-element (helpers/wrap-component [range-example])))

(defn- add-days [date days] (doto (js/Date. (.getTime date)) (.setDate (+ (.getDate date) days))))

(defn- presets-example
  []
  (r/with-let [selected (r/atom (js/Date.))]
              [:div {:class "flex flex-col gap-3"}
               [:div {:class "flex gap-2"}
                [button/button {:variant :outline
                                :size :sm
                                :on-click #(reset! selected (js/Date.))}
                 "Today"]
                [button/button {:variant :outline
                                :size :sm
                                :on-click #(reset! selected (add-days (js/Date.) 7))}
                 "In one week"]]
               [sut/composed-date-picker {}
                [sut/date-picker-trigger {}
                 [sut/date-picker-button {}
                  (date-label @selected)]]
                [sut/date-picker-content {}
                 [sut/date-picker-calendar {:mode "single"
                                            :selected @selected
                                            :on-select #(when % (reset! selected %))}]]]]))

(defstory Presets
          "Presets remain ordinary buttons that update the same controlled date value."
          []
          (r/as-element (helpers/wrap-component [presets-example])))

(defn- dob-example
  []
  (r/with-let [selected (r/atom nil)]
              [sut/composed-date-picker {}
               [sut/date-picker-trigger {}
                [sut/date-picker-button {:data-placeholder (nil? @selected)}
                 (if @selected (date-label @selected) "Date of birth")]]
               [sut/date-picker-content {}
                [sut/date-picker-calendar {:mode "single"
                                           :caption-layout :dropdown
                                           :start-month (js/Date. 1900 0)
                                           :end-month (js/Date.)
                                           :default-month (js/Date. 1990 0)
                                           :selected @selected
                                           :on-select #(reset! selected %)}]]]))

(defstory DateOfBirthDropdown
          "DOB selection with month/year dropdowns and an explicit 1900-to-current month interval."
          []
          (r/as-element (helpers/wrap-component [dob-example])))

(defn- date->iso
  [date]
  (when date
    (str (.getFullYear date)
         "-" (.padStart (str (inc (.getMonth date))) 2 "0")
         "-" (.padStart (str (.getDate date)) 2 "0"))))

(defn- iso->date
  [value]
  (when-let [[_ year month day] (re-matches #"(\d{4})-(\d{2})-(\d{2})" value)]
    (js/Date. (js/parseInt year 10) (dec (js/parseInt month 10)) (js/parseInt day 10))))

(defn- input-time-example
  []
  (r/with-let
   [selected (r/atom (js/Date. 2026 2 12)) date-input (r/atom "2026-03-12") time (r/atom "09:00")]
   [:div {:class "grid max-w-sm gap-3"}
    [input/input {:aria-label "Date"
                  :placeholder "yyyy-MM-dd"
                  :value @date-input
                  :on-change #(let [value (.. % -target -value)]
                                (reset! date-input value)
                                (when-let [date (iso->date value)] (reset! selected date)))}]
    [sut/composed-date-picker {}
     [sut/date-picker-trigger {}
      [sut/date-picker-button {}
       (date-label @selected)]]
     [sut/date-picker-content {}
      [sut/date-picker-calendar {:mode "single"
                                 :selected @selected
                                 :on-select
                                 #(when % (reset! selected %) (reset! date-input (date->iso %)))}]]]
    [input/input {:aria-label "Appointment time"
                  :type "time"
                  :value @time
                  :on-change #(reset! time (.. % -target -value))}]
    [:p {:class "text-sm text-muted-foreground"}
     (str "Form value: " @date-input "T" @time)]]))

(defstory InputAndTime
          "A controlled Calendar synchronized with a text input and a separate native time input."
          []
          (r/as-element (helpers/wrap-component [input-time-example])))

(defn- rtl-example
  []
  (r/with-let [selected (r/atom nil)]
              [:div {:dir "rtl"}
               [sut/composed-date-picker {}
                [sut/date-picker-trigger {}
                 [sut/date-picker-button {:data-placeholder (nil? @selected)}
                  (if @selected (.toLocaleDateString @selected "ar-SA") "اختر تاريخًا")]]
                [sut/date-picker-content {:align "start"}
                 [sut/date-picker-calendar {:mode "single"
                                            :dir "rtl"
                                            :locale arSA
                                            :selected @selected
                                            :on-select #(reset! selected %)}]]]]))

(defstory RightToLeft
          "Representative Arabic locale and RTL direction across trigger and calendar."
          []
          (r/as-element (helpers/wrap-component [rtl-example])))

(defstory DatePickerPlayground
          "Legacy native date-picker playground retained for existing consumers."
          {:args {:default-value "2026-03-01"
                  :disabled false
                  :required false
                  :min "2026-01-01"
                  :max "2026-12-31"}
           :arg-types {:default-value {:control {:type "text"}}
                       :disabled {:control {:type "boolean"}}
                       :required {:control {:type "boolean"}}
                       :min {:control {:type "text"}}
                       :max {:control {:type "text"}}}
           :parameters {:controls {:exclude
                                   ["value" "on-change" "class" "wrapper-class" "icon-class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "max-w-sm p-6"}
                                                 [sut/date-picker args]])))
