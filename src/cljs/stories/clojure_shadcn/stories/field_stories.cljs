(ns clojure-shadcn.stories.field-stories
  "Storybook stories for the Field component. Ported from mateuszmazurczak.portfolio.ui-components.field."
  (:require
   [clojure-shadcn.stories.field.field-choices            :as field-choices]
   [clojure-shadcn.stories.field.field-patterns           :as field-patterns]
   [clojure-shadcn.stories.field.field-with-error         :as field-with-error]
   [clojure-shadcn.stories.field.field-with-input         :as field-with-input]
   [clojure-shadcn.stories.field.field-with-select        :as field-with-select]
   [clojure-shadcn.stories.field.field-with-textarea      :as field-with-textarea]
   [clojure-shadcn.stories.field.fieldset-multiple-fields :as fieldset-multiple-fields]
   [clojure-shadcn.stories.field.playground               :as playground]
   [clojure-shadcn.stories.helpers                        :as helpers]
   [reagent.core                                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Field"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Field component for building accessible form layouts."
                        :npm-install "No external dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.field")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/field.cljs"
                        :filename "field.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card {:component-name "field-set"
                                  :description
                                  "Semantic fieldset wrapper for grouping related controls."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to <fieldset>."}]}]
     [helpers/api-component-card {:component-name "field-legend"
                                  :description "Legend/title for a field-set section."
                                  :props [{:name ":variant"
                                           :type "keyword"
                                           :default ":legend"
                                           :description ":legend | :label."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to <legend>."}]}]
     [helpers/api-component-card
      {:component-name "field-group"
       :description "Container for vertically grouped fields with consistent spacing."
       :props [[":class" "string, optional - Additional Tailwind classes."]
               ["additional props" "map entries, optional - Forwarded to wrapper <div>."]]}]
     [helpers/api-component-card {:component-name "field"
                                  :description
                                  "Core field layout wrapper with orientation variants."
                                  :props [{:name ":orientation"
                                           :type "keyword"
                                           :default ":vertical"
                                           :description ":vertical | :horizontal | :responsive."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to wrapper <div>."}]}]
     [helpers/api-component-card {:component-name "field-content"
                                  :description "Groups input/control with description and errors."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to wrapper <div>."}]}]
     [helpers/api-component-card {:component-name "field-label"
                                  :description
                                  "Field-aware label wrapper around base label component."
                                  :props [{:name ":html-for"
                                           :type "string"
                                           :default nil
                                           :description "Associates label with input id."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to underlying label."}]}]
     [helpers/api-component-card {:component-name "field-title"
                                  :description "Title slot used inside complex field labels/cards."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to wrapper <div>."}]}]
     [helpers/api-component-card {:component-name "field-description"
                                  :description "Secondary helper text for context and guidance."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to <p>."}]}]
     [helpers/api-component-card
      {:component-name "field-separator"
       :description "Visual separator between field blocks; can render optional children label."
       :props [[":class" "string, optional - Additional Tailwind classes."]
               ["children" "optional - Label content displayed over separator line."]
               ["additional props" "map entries, optional - Forwarded to wrapper <div>."]]}]
     [helpers/api-component-card
      {:component-name "field-error"
       :description "Validation message renderer supporting one or multiple error messages."
       :props [{:name ":errors"
                :type "vector<map>"
                :default nil
                :description "Error maps with :message keys."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "children"
                :type nil
                :default nil
                :description "Custom error content; takes priority over :errors."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Use :html-for on field-label with matching input :id for accessible label click behavior."]
       [:li
        "field-separator can take children; without children it renders a plain separator line."]
       [:li "Most field helpers forward extra props to underlying DOM nodes for flexibility."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[field-set {}\n [field-legend {} \"Profile\"]\n [field {}\n  [field-label {:html-for \"display-name\"} \"Display name\"]\n  [field-content {}\n   [input {:id \"display-name\"}]\n   [field-description {} \"Shown publicly\"]]]]"]]]]])))

(defstory
 FieldWithInput
 "Field with label, input, and description.\n\n  Custom component for form layouts.\n\n  Use field-content to group controls and description text."
 {:source-code (embed-source "clojure-shadcn.stories.field.field-with-input")}
 []
 (r/as-element (helpers/wrap-component [field-with-input/example])))

(defstory
 FieldWithTextarea
 "Field with textarea.\n\n  Custom component for form layouts.\n\n  Use for multi-line inputs and richer descriptions."
 {:source-code (embed-source "clojure-shadcn.stories.field.field-with-textarea")}
 []
 (r/as-element (helpers/wrap-component [field-with-textarea/example])))

(defstory
 FieldWithSelect
 "Field with select control.\n\n  Custom component for form layouts.\n\n  Combine field + select for structured inputs."
 {:source-code (embed-source "clojure-shadcn.stories.field.field-with-select")}
 []
 (r/as-element (helpers/wrap-component [field-with-select/example])))

(defstory
 FieldWithError
 "Field displaying validation errors.\n\n  Custom component for form layouts.\n\n  Use field-error to show validation messages."
 {:source-code (embed-source "clojure-shadcn.stories.field.field-with-error")}
 []
 (r/as-element (helpers/wrap-component [field-with-error/example])))

(defstory
 FieldsetMultipleFields
 "Field set with multiple related fields.\n\n  Custom component for form layouts.\n\n  Use field-set + field-group for multi-field sections."
 {:source-code (embed-source "clojure-shadcn.stories.field.fieldset-multiple-fields")}
 []
 (r/as-element (helpers/wrap-component [fieldset-multiple-fields/example])))


(defstory Playground
          "Interactive field-layout playground."
          {:args {:orientation "vertical"
                  :placeholder "Jane Doe"
                  :disabled false}
           :arg-types {:orientation {:name "Orientation"
                                     :control {:type "select"}
                                     :options ["vertical" "horizontal" "responsive"]}
                       :placeholder {:name "Placeholder"
                                     :control {:type "text"}}
                       :disabled {:name "Disabled"
                                  :control {:type "boolean"}}}
           :parameters {:playground
                        {:description
                         "Explore field orientation, placeholder and disabled with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}
           :decode-args (fn [args] (update args :orientation keyword))
           :source-code (embed-source "clojure-shadcn.stories.field.playground")}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory FieldPatterns
          "Aggregate form, slider, choice-card, responsive, invalid, and RTL field compositions."
          {:source-code (embed-source "clojure-shadcn.stories.field.field-patterns")}
          []
          (r/as-element (helpers/wrap-component [field-patterns/example])))

(defstory
 FieldChoices
 "Radio, switch, and selectable choice-card field compositions. Based on current shadcn/ui Field examples."
 {:source-code (embed-source "clojure-shadcn.stories.field.field-choices")}
 []
 (r/as-element (helpers/wrap-component [field-choices/example])))
