(ns clojure-shadcn.stories.radio-group-stories
  "Storybook stories for the Radio Group component. Ported from mateuszmazurczak.portfolio.ui-components.radio_group."
  (:require
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.field       :as field]
   [clojure-shadcn.ui.components.label       :as label]
   [clojure-shadcn.ui.components.radio-group :as sut]
   [reagent.core                               :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source]])

  (:require-macros [clojure-shadcn.stories.macros :refer [embed-body]]))

(def ^:export default
  #js {:title      "Components/Radio Group"
       :parameters #js {:layout "padded"}})

(defn ^:export Installation
  "Install dependencies and copy the component code into your project."
  []
  (r/as-element
  [helpers/installation-scene
             {:description "Radio Group component built on Radix UI primitives."
              :npm-install "npm install @radix-ui/react-radio-group lucide-react"
              :source-code (embed-source "clojure-shadcn.ui.components.radio_group")
              :namespace-path "src/cljs/clojure_shadcn/ui/components/radio_group.cljs"
              :filename "radio_group.cljs"}]))

(defn ^:export ApiReference
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body ApiReference) :filename "radio_group_stories.cljs"}
             [:div {:class "p-6 max-w-4xl"}
              [:div {:class "space-y-4"}
               [helpers/api-component-card
                {:component-name "radio-group"
                 :link {:href "https://www.radix-ui.com/primitives/docs/components/radio-group" :label "Radix Radio Group Docs"}
                 :description "Container for mutually-exclusive options. Handles keyboard navigation and selected value management. Additional props are forwarded to Radix RadioGroup.Root."
                  :props [{:name ":value"          :type "string"      :default nil        :description "Controlled selected value."}
                          {:name ":default-value"  :type "string"      :default nil        :description "Uncontrolled initial selected value."}
                          {:name ":on-value-change" :type "function"   :default nil        :description "Callback when selected value changes: (fn [value] ...)."}
                          {:name ":disabled"       :type "boolean"     :default nil        :description "Disables all items in the group."}
                          {:name ":required"       :type "boolean"     :default nil        :description "Marks group as required for forms."}
                          {:name ":name"           :type "string"      :default nil        :description "Form field name."}
                          {:name ":orientation"    :type "keyword"     :default ":vertical" :description ":vertical or :horizontal."}
                          {:name ":class"          :type "string"      :default nil        :description "Additional Tailwind classes."}
                          {:name "additional props" :type "map entries" :default nil       :description "Forwarded to Radix RadioGroup.Root."}]}]
                [helpers/api-component-card
                 {:component-name "radio-group-item"
                  :description "Single selectable option inside radio-group."
                  :props [{:name ":value"         :type "string"      :default nil :description "Value represented by this option."}
                          {:name ":id"            :type "string"      :default nil :description "ID for associated label :html-for."}
                          {:name ":disabled"      :type "boolean"     :default nil :description "Disables this option."}
                          {:name ":class"         :type "string"      :default nil :description "Additional Tailwind classes."}
                          {:name "additional props" :type "map entries" :default nil :description "Forwarded to Radix RadioGroup.Item."}]}]
                [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
                 [:h4 {:class "text-sm font-semibold mb-2"} "⚠️ Important Notes"]
                 [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
                  [:li "Each radio-group-item must have a unique :value; without it, selection logic cannot work."]
                  [:li "Use matching item :id + label :html-for for accessible click targets."]
                  [:li "Prefer a single radio-group per decision domain; avoid nesting groups with same :name."]]]
                [:div {:class "border rounded-lg p-4 bg-muted/50"}
                 [:h4 {:class "text-sm font-semibold mb-2"}
                  "Usage Example"]
                 [:pre {:class "text-xs overflow-x-auto"}
                  [:code "(let [billing (r/atom \"monthly\")]\n  [radio-group {:value @billing\n                :on-value-change #(reset! billing %)\n                :name \"billing-cycle\"}\n   [:div {:class \"flex items-center gap-2\"}\n    [radio-group-item {:id \"bill-monthly\" :value \"monthly\"}]\n    [label {:html-for \"bill-monthly\"} \"Monthly\"]]\n   [:div {:class \"flex items-center gap-2\"}\n    [radio-group-item {:id \"bill-yearly\" :value \"yearly\"}]\n    [label {:html-for \"bill-yearly\"} \"Yearly\"]]])"]]
                 [:div {:class "flex flex-wrap gap-2 mt-3"}
                  ]]]])))

(defn ^:export RadioGroupDemo
  "Radio group with labeled options.

  Radix primitive: @radix-ui/react-radio-group

  Use for exclusive choices like density or layout."
  []
  (r/as-element [(fn [] (let [value (r/atom "comfortable")]
     (fn []
       (helpers/wrap-component {:source (embed-body RadioGroupDemo) :filename "radio_group_stories.cljs"}
        [:div {:class "p-6"}
         [sut/radio-group {:value @value
                           :on-value-change #(reset! value %)}
          [:div {:class "flex items-center gap-3"}
           [sut/radio-group-item {:value "default"
                                  :id "density-default"}]
           [label/label {:html-for "density-default"}
            "Default"]]
          [:div {:class "flex items-center gap-3"}
           [sut/radio-group-item {:value "comfortable"
                                  :id "density-comfortable"}]
           [label/label {:html-for "density-comfortable"}
            "Comfortable"]]
          [:div {:class "flex items-center gap-3"}
           [sut/radio-group-item {:value "compact"
                                  :id "density-compact"}]
           [label/label {:html-for "density-compact"}
            "Compact"]]]]))))]))

(defn ^:export FieldRadio
  "Radio group embedded in Field layout.

  Radix primitive: @radix-ui/react-radio-group

  Ideal for pricing or plan selection with supporting copy."
  []
  (r/as-element [(fn [] (let [value (r/atom "monthly")]
     (fn []
       (helpers/wrap-component {:source (embed-body FieldRadio) :filename "radio_group_stories.cljs"}
        [:div {:class "p-6 max-w-md"}
         [field/field-set {}
          [field/field-label {}
           "Subscription Plan"]
          [field/field-description {}
           "Yearly and lifetime plans offer significant savings."]
          [sut/radio-group {:value @value
                            :on-value-change #(reset! value %)}
           [field/field {:orientation :horizontal}
            [sut/radio-group-item {:value "monthly"
                                   :id "plan-monthly"}]
            [field/field-label {:html-for "plan-monthly"
                                :class "font-normal"}
             "Monthly ($9.99/month)"]]
           [field/field {:orientation :horizontal}
            [sut/radio-group-item {:value "yearly"
                                   :id "plan-yearly"}]
            [field/field-label {:html-for "plan-yearly"
                                :class "font-normal"}
             "Yearly ($99.99/year)"]]
           [field/field {:orientation :horizontal}
            [sut/radio-group-item {:value "lifetime"
                                   :id "plan-lifetime"}]
            [field/field-label {:html-for "plan-lifetime"
                                :class "font-normal"}
             "Lifetime ($299.99)"]]]]]))))]))

(defn ^:export RadioGroupDisabled
  "Disabled radio items in a group.

  Radix primitive: @radix-ui/react-radio-group

  Use disabled options for unavailable choices."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body RadioGroupDisabled) :filename "radio_group_stories.cljs"} [:div {:class "p-6"}
                                       [sut/radio-group {:default-value "standard"}
                                        [:div {:class "flex items-center gap-3"}
                                         [sut/radio-group-item {:value "standard"
                                                                :id "plan-standard"}]
                                         [label/label {:html-for "plan-standard"}
                                          "Standard"]]
                                        [:div {:class "flex items-center gap-3"}
                                         [sut/radio-group-item {:value "premium"
                                                                :id "plan-premium"
                                                                :disabled true}]
                                         [label/label {:html-for "plan-premium"
                                                       :class "text-muted-foreground"}
                                          "Premium (coming soon)"]]]])))

(defn ^:export RadioGroupInvalid
  "Invalid radio group state with validation hint.

  Radix primitive: @radix-ui/react-radio-group

  Apply :aria-invalid on items and show an explicit error message."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body RadioGroupInvalid) :filename "radio_group_stories.cljs"} [:div {:class "p-6 space-y-2"}
                                       [sut/radio-group {:default-value nil}
                                        [:div {:class "flex items-center gap-3"}
                                         [sut/radio-group-item {:value "free"
                                                                :id "plan-free"
                                                                :aria-invalid true}]
                                         [label/label {:html-for "plan-free"}
                                          "Free"]]
                                        [:div {:class "flex items-center gap-3"}
                                         [sut/radio-group-item {:value "pro"
                                                                :id "plan-pro"
                                                                :aria-invalid true}]
                                         [label/label {:html-for "plan-pro"}
                                          "Pro"]]]
                                       [:p {:class "text-destructive text-sm"}
                                        "Please select a billing plan."]])))

(defn ^:export RadioGroupHorizontal
  "Horizontal layout variant.

  Radix primitive: @radix-ui/react-radio-group

  Use :orientation :horizontal for inline radio groups."
  []
  (r/as-element [(fn [] (let [value (r/atom "monthly")]
     (fn []
       (helpers/wrap-component {:source (embed-body RadioGroupHorizontal) :filename "radio_group_stories.cljs"} [:div {:class "p-6"}
                                           [sut/radio-group {:value @value
                                                             :orientation :horizontal
                                                             :class "flex items-center gap-6"
                                                             :on-value-change #(reset! value %)}
                                            [:div {:class "flex items-center gap-2"}
                                             [sut/radio-group-item {:value "monthly"
                                                                    :id "cycle-monthly"}]
                                             [label/label {:html-for "cycle-monthly"}
                                              "Monthly"]]
                                            [:div {:class "flex items-center gap-2"}
                                             [sut/radio-group-item {:value "yearly"
                                                                    :id "cycle-yearly"}]
                                             [label/label {:html-for "cycle-yearly"}
                                              "Yearly"]]]]))))]))
