(ns clojure-shadcn.stories.select-stories
  "Storybook stories for the Select component. Ported from mateuszmazurczak.portfolio.ui-components.select."
  (:require
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.field  :as field]
   [clojure-shadcn.ui.components.select :as sut]
   [reagent.core                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title      "Components/Select"
       :parameters #js {:layout "padded"}})

(defn ^:export Installation
  "Install dependencies and copy the component code into your project."
  []
  (r/as-element
  [helpers/installation-scene
             {:description "https://www.radix-ui.com/primitives/docs/components/select."
              :npm-install "npm install @radix-ui/react-select"
              :source-code (embed-source "clojure-shadcn.ui.components.select")
              :namespace-path "src/cljs/clojure_shadcn/ui/components/select.cljs"
              :filename "select.cljs"}]))

(defstory ApiReference
  []
  (r/as-element
  (helpers/wrap-component
    [:div {:class "p-6 max-w-4xl"}
     [:div {:class "space-y-4"}
      [helpers/api-component-card
       {:component-name "select"
        :description "Radix Select root for controlled/uncontrolled single-value selection."
        :link {:href "https://www.radix-ui.com/primitives/docs/components/select" :label "Radix Select Docs"}
        :props [{:name ":value"          :type "string"      :default nil :description "Controlled selected value."}
                 {:name ":default-value"  :type "string"      :default nil :description "Uncontrolled initial value."}
                 {:name ":on-value-change" :type "function"   :default nil :description "Callback when selection changes."}
                 {:name ":disabled"       :type "boolean"     :default nil :description "Disables the control."}
                 {:name ":name"           :type "string"      :default nil :description "Form field name."}
                 {:name ":required"       :type "boolean"     :default nil :description "Marks field as required."}
                 {:name "additional props" :type "map entries" :default nil :description "Forwarded to Radix Select.Root."}]}]
       [helpers/api-component-card
        {:component-name "select-group"
         :description "Groups related select items under a label."
         :props [{:name "additional props" :type "map entries" :default nil :description "Forwarded to Radix Select.Group."}]}]
       [helpers/api-component-card
        {:component-name "select-value"
         :description "Displays selected item text inside trigger."
         :props [{:name ":placeholder"    :type "string"      :default nil :description "Placeholder when no value is selected."}
                 {:name "additional props" :type "map entries" :default nil :description "Forwarded to Radix Select.Value."}]}]
       [helpers/api-component-card
        {:component-name "select-trigger"
         :description "Button-like trigger opening the select menu."
         :props [{:name ":size"          :type "string"      :default "\"default\"" :description "One of: \"default\" | \"sm\"."}
                 {:name ":class"         :type "string"      :default nil           :description "Additional Tailwind classes."}
                 {:name "additional props" :type "map entries" :default nil         :description "Forwarded to Radix Select.Trigger."}]}]
       [helpers/api-component-card
        {:component-name "select-content"
         :description "Portaled options container with popper positioning and scroll buttons."
         :props [{:name ":position" :type "string" :default "\"popper\""  :description "Radix positioning mode."}
                 {:name ":align"    :type "string" :default "\"center\""  :description "One of: \"start\" | \"center\" | \"end\"."}
                 {:name ":class"    :type "string" :default nil           :description "Additional Tailwind classes."}]}]
       [helpers/api-component-card
        {:component-name "select-label"
         :description "Section label inside select content."
         :props [{:name ":class" :type "string" :default nil :description "Additional Tailwind classes."}]}]
       [helpers/api-component-card
        {:component-name "select-item"
         :description "Selectable option item in select content."
         :props [{:name ":value"    :type "string"  :default nil :description "Option value."}
                 {:name ":disabled" :type "boolean" :default nil :description "Disables option."}
                 {:name ":class"    :type "string"  :default nil :description "Additional Tailwind classes."}]}]
       [helpers/api-component-card
        {:component-name "select-separator"
         :description "Visual separator between item groups."
         :props [{:name ":class" :type "string" :default nil :description "Additional Tailwind classes."}]}]
       [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
        [:h4 {:class "text-sm font-semibold mb-2"} "⚠️ Important Notes"]
        [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
         [:li "Use either controlled (:value + :on-value-change) or uncontrolled (:default-value) mode."]
         [:li "select-content is portaled, so stacking context/z-index should be managed at app layout level."]
         [:li "Use :aria-invalid on select-trigger via forwarded props when integrating validation styling."]]]
       [:div {:class "border rounded-lg p-4 bg-muted/50"}
        [:h4 {:class "text-sm font-semibold mb-2"}
         "Usage Example"]
        [:pre {:class "text-xs overflow-x-auto"}
         [:code "(let [value (r/atom \"banana\")]\n  [select {:value @value :on-value-change #(reset! value %)}\n   [select-trigger {:class \"w-[180px]\"}\n    [select-value {:placeholder \"Select a fruit\"}]]\n   [select-content {}\n    [select-item {:value \"apple\"} \"Apple\"]\n    [select-item {:value \"banana\"} \"Banana\"]]])"]]
  ]]])))

(defstory SelectDemo
  "Basic select with grouped items.

  Radix primitive: @radix-ui/react-select

  Our wrapper exposes select, trigger, content, and items as components."
  []
  (r/as-element [(fn [] (let [value (r/atom "banana")]
     (fn []
       (helpers/wrap-component
        [:div {:class "p-6"}
         [sut/select {:value @value
                      :on-value-change #(reset! value %)}
          [sut/select-trigger {:class "w-[180px]"}
           [sut/select-value {:placeholder "Select a fruit"}]]
          [sut/select-content {}
           [sut/select-group {}
            [sut/select-label {}
             "Fruits"]
            [sut/select-item {:value "apple"}
             "Apple"]
            [sut/select-item {:value "banana"}
             "Banana"]
            [sut/select-item {:value "blueberry"}
             "Blueberry"]
            [sut/select-item {:value "grapes"}
             "Grapes"]
            [sut/select-item {:value "pineapple"}
             "Pineapple"]]]]]))))]))

(defstory SelectScrollable
  "Scrollable select content with multiple groups.

  Radix primitive: @radix-ui/react-select

  Long lists automatically scroll within the content panel."
  []
  (r/as-element [(fn [] (let [value (r/atom "cet")]
     (fn []
       (helpers/wrap-component
        [:div {:class "p-6"}
         [sut/select {:value @value
                      :on-value-change #(reset! value %)}
          [sut/select-trigger {:class "w-[280px]"}
           [sut/select-value {:placeholder "Select a timezone"}]]
          [sut/select-content {}
           [sut/select-group {}
            [sut/select-label {}
             "North America"]
            [sut/select-item {:value "est"}
             "Eastern Standard Time (EST)"]
            [sut/select-item {:value "cst"}
             "Central Standard Time (CST)"]
            [sut/select-item {:value "mst"}
             "Mountain Standard Time (MST)"]
            [sut/select-item {:value "pst"}
             "Pacific Standard Time (PST)"]
            [sut/select-item {:value "akst"}
             "Alaska Standard Time (AKST)"]
            [sut/select-item {:value "hst"}
             "Hawaii Standard Time (HST)"]]
           [sut/select-group {}
            [sut/select-label {}
             "Europe & Africa"]
            [sut/select-item {:value "gmt"}
             "Greenwich Mean Time (GMT)"]
            [sut/select-item {:value "cet"}
             "Central European Time (CET)"]
            [sut/select-item {:value "eet"}
             "Eastern European Time (EET)"]
            [sut/select-item {:value "west"}
             "Western European Summer Time (WEST)"]
            [sut/select-item {:value "cat"}
             "Central Africa Time (CAT)"]
            [sut/select-item {:value "eat"}
             "East Africa Time (EAT)"]]]]]))))]))

(defstory FieldSelect
  "Select inside Field layout with helper copy.

  Radix primitive: @radix-ui/react-select

  Useful for richer forms with descriptions."
  []
  (r/as-element
  (helpers/wrap-component
    [:div {:class "p-6 max-w-md"}
     [field/field {}
      [field/field-label {}
       "Department"]
      [sut/select {:default-value "design"}
       [sut/select-trigger {}
        [sut/select-value {:placeholder "Choose department"}]]
       [sut/select-content {}
        [sut/select-item {:value "engineering"}
         "Engineering"]
        [sut/select-item {:value "design"}
         "Design"]
        [sut/select-item {:value "marketing"}
         "Marketing"]
        [sut/select-item {:value "sales"}
         "Sales"]
        [sut/select-item {:value "support"}
         "Customer Support"]
        [sut/select-item {:value "hr"}
         "Human Resources"]]]
      [field/field-description {}
       "Select your department or area of work."]]])))

(defstory SelectInvalid
  "Invalid select state with helper error text.

  Radix primitive: @radix-ui/react-select

  Pass :aria-invalid on select-trigger and show error copy beneath field."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6 max-w-sm space-y-2"}
                                       [field/field {}
                                        [field/field-label {}
                                         "Department"]
                                        [sut/select {:default-value nil}
                                         [sut/select-trigger {:aria-invalid true}
                                          [sut/select-value {:placeholder "Choose department"}]]
                                         [sut/select-content {}
                                          [sut/select-item {:value "engineering"}
                                           "Engineering"]
                                          [sut/select-item {:value "design"}
                                           "Design"]]]
                                        [field/field-description {:class "text-destructive"}
                                         "Department is required."]]])))

(defstory SelectDisabledItems
  "Select with disabled items.

  Radix primitive: @radix-ui/react-select

  Use :disabled on items that are not selectable."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6"}
                                       [sut/select {:default-value "pro"}
                                        [sut/select-trigger {:class "w-[200px]"}
                                         [sut/select-value {:placeholder "Choose a plan"}]]
                                        [sut/select-content {}
                                         [sut/select-item {:value "starter"}
                                          "Starter"]
                                         [sut/select-item {:value "pro"}
                                          "Pro"]
                                         [sut/select-item {:value "enterprise"
                                                           :disabled true}
                                          "Enterprise (contact us)"]]]])))
