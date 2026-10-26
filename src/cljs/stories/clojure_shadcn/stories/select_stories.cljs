(ns clojure-shadcn.stories.select-stories
  "Storybook stories for the Select component. Ported from mateuszmazurczak.portfolio.ui-components.select."
  (:require
   [clojure-shadcn.stories.helpers                      :as helpers]
   [clojure-shadcn.stories.select.field-select          :as example-field-select]
   [clojure-shadcn.stories.select.playground            :as example-playground]
   [clojure-shadcn.stories.select.select-demo           :as example-select-demo]
   [clojure-shadcn.stories.select.select-disabled-items :as example-select-disabled-items]
   [clojure-shadcn.stories.select.select-invalid        :as example-select-invalid]
   [clojure-shadcn.stories.select.select-patterns       :as example-select-patterns]
   [clojure-shadcn.stories.select.select-scrollable     :as example-select-scrollable]
   [reagent.core                                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Select"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "https://www.radix-ui.com/primitives/docs/components/select."
                        :npm-install "npm install @radix-ui/react-select"
                        :source-code (embed-source "clojure-shadcn.ui.components.select")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/select.cljs"
                        :filename "select.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "select"
       :description "Radix Select root for controlled/uncontrolled single-value selection."
       :link {:href "https://www.radix-ui.com/primitives/docs/components/select"
              :label "Radix Select Docs"}
       :props [{:name ":value"
                :type "string"
                :default nil
                :description "Controlled selected value."}
               {:name ":default-value"
                :type "string"
                :default nil
                :description "Uncontrolled initial value."}
               {:name ":on-value-change"
                :type "function"
                :default nil
                :description "Callback when selection changes."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables the control."}
               {:name ":name"
                :type "string"
                :default nil
                :description "Form field name."}
               {:name ":required"
                :type "boolean"
                :default nil
                :description "Marks field as required."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Select.Root."}]}]
     [helpers/api-component-card {:component-name "select-group"
                                  :description "Groups related select items under a label."
                                  :props [{:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Select.Group."}]}]
     [helpers/api-component-card {:component-name "select-value"
                                  :description "Displays selected item text inside trigger."
                                  :props [{:name ":placeholder"
                                           :type "string"
                                           :default nil
                                           :description "Placeholder when no value is selected."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Select.Value."}]}]
     [helpers/api-component-card {:component-name "select-trigger"
                                  :description "Button-like trigger opening the select menu."
                                  :props [{:name ":size"
                                           :type "string"
                                           :default "\"default\""
                                           :description "One of: \"default\" | \"sm\"."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Select.Trigger."}]}]
     [helpers/api-component-card
      {:component-name "select-content"
       :description "Portaled options container with popper positioning and scroll buttons."
       :props [{:name ":position"
                :type "string"
                :default "\"popper\""
                :description "Radix positioning mode."}
               {:name ":align"
                :type "string"
                :default "\"center\""
                :description "One of: \"start\" | \"center\" | \"end\"."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "select-label"
                                  :description "Section label inside select content."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "select-item"
                                  :description "Selectable option item in select content."
                                  :props [{:name ":value"
                                           :type "string"
                                           :default nil
                                           :description "Option value."}
                                          {:name ":disabled"
                                           :type "boolean"
                                           :default nil
                                           :description "Disables option."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "select-separator"
                                  :description "Visual separator between item groups."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Use either controlled (:value + :on-value-change) or uncontrolled (:default-value) mode."]
       [:li
        "select-content is portaled, so stacking context/z-index should be managed at app layout level."]
       [:li
        "Use :aria-invalid on select-trigger via forwarded props when integrating validation styling."]
       [:li
        "Radix Select is not an autocomplete: it has typeahead navigation but no editable query input or arbitrary values. Use Combobox when users must filter options by typing."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "(let [value (r/atom \"banana\")]\n  [select {:value @value :on-value-change #(reset! value %)}\n   [select-trigger {:class \"w-[180px]\"}\n    [select-value {:placeholder \"Select a fruit\"}]]\n   [select-content {}\n    [select-item {:value \"apple\"} \"Apple\"]\n    [select-item {:value \"banana\"} \"Banana\"]]])"]]]]])))

(defstory
 SelectDemo
 "Basic select with grouped items.

  Radix primitive: @radix-ui/react-select

  Our wrapper exposes select, trigger, content, and items as components."
 {:source-code (embed-source "clojure-shadcn.stories.select.select-demo")}
 []
 (r/as-element (helpers/wrap-component [example-select-demo/example])))

(defstory
 SelectScrollable
 "Scrollable select content with multiple groups.

  Radix primitive: @radix-ui/react-select

  Long lists automatically scroll within the content panel."
 {:source-code (embed-source "clojure-shadcn.stories.select.select-scrollable")}
 []
 (r/as-element (helpers/wrap-component [example-select-scrollable/example])))

(defstory
 FieldSelect
 "Select inside Field layout with helper copy.

  Radix primitive: @radix-ui/react-select

  Useful for richer forms with descriptions."
 {:source-code (embed-source "clojure-shadcn.stories.select.field-select")}
 []
 (r/as-element (helpers/wrap-component [example-field-select/example])))

(defstory
 SelectInvalid
 "Invalid select state with helper error text.

  Radix primitive: @radix-ui/react-select

  Pass :aria-invalid on select-trigger and show error copy beneath field."
 {:source-code (embed-source "clojure-shadcn.stories.select.select-invalid")}
 []
 (r/as-element (helpers/wrap-component [example-select-invalid/example])))

(defstory
 SelectDisabledItems
 "Select with disabled items.

  Radix primitive: @radix-ui/react-select

  Use :disabled on items that are not selectable."
 {:source-code (embed-source "clojure-shadcn.stories.select.select-disabled-items")}
 []
 (r/as-element (helpers/wrap-component [example-select-disabled-items/example])))


(defstory Playground
          "Interactive select playground."
          {:args {:default-value "banana"
                  :disabled false
                  :trigger-size "default"}
           :arg-types {:default-value {:name "Initial value"
                                       :control {:type "select"}
                                       :options ["apple" "banana" "blueberry"]}
                       :disabled {:name "Disabled"
                                  :control {:type "boolean"}}
                       :trigger-size {:name "Trigger size"
                                      :control {:type "select"}
                                      :options ["default" "sm"]}}
           :parameters
           {:playground
            {:description
             "Explore select initial value, disabled and trigger size with a live preview."}
            :controls {:expanded false
                       :exclude ["value" "on-value-change" "class"]}}
           :source-code (embed-source "clojure-shadcn.stories.select.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))



(defstory
 SelectPatterns
 "Content alignment, labeled groups and separator, canonical Field error composition, and RTL context."
 {:source-code (embed-source "clojure-shadcn.stories.select.select-patterns")}
 []
 (r/as-element (helpers/wrap-component [example-select-patterns/example])))
