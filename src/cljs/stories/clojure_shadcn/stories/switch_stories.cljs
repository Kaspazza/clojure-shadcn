(ns clojure-shadcn.stories.switch-stories
  "Storybook stories for the Switch component. Ported from mateuszmazurczak.portfolio.ui-components.switch."
  (:require
   [clojure-shadcn.stories.helpers                      :as helpers]
   [clojure-shadcn.stories.switch.field-switch          :as example-field-switch]
   [clojure-shadcn.stories.switch.playground            :as example-playground]
   [clojure-shadcn.stories.switch.switch-choice-cards   :as example-switch-choice-cards]
   [clojure-shadcn.stories.switch.switch-controlled     :as example-switch-controlled]
   [clojure-shadcn.stories.switch.switch-demo           :as example-switch-demo]
   [clojure-shadcn.stories.switch.switch-disabled       :as example-switch-disabled]
   [clojure-shadcn.stories.switch.switch-field-disabled :as example-switch-field-disabled]
   [clojure-shadcn.stories.switch.switch-invalid        :as example-switch-invalid]
   [clojure-shadcn.stories.switch.switch-rtl            :as example-switch-rtl]
   [reagent.core                                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Switch"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Switch component for toggle controls."
                        :npm-install "npm install @radix-ui/react-switch"
                        :source-code (embed-source "clojure-shadcn.ui.components.switch")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/switch.cljs"
                        :filename "switch.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "switch"
       :description
       "Radix-based boolean toggle control with accessible switch semantics. Additional props are forwarded to the underlying Radix Switch.Root."
       :link {:href "https://www.radix-ui.com/primitives/docs/components/switch"
              :label "Radix Switch Docs"}
       :props [{:name ":checked"
                :type "boolean"
                :default nil
                :description "Controlled checked state."}
               {:name ":default-checked"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial checked state."}
               {:name ":on-checked-change"
                :type "function"
                :default nil
                :description "Callback when state changes: (fn [checked?] ...)."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables interaction."}
               {:name ":required"
                :type "boolean"
                :default nil
                :description "Marks field as required for forms."}
               {:name ":name"
                :type "string"
                :default nil
                :description "Form field name."}
               {:name ":value"
                :type "string"
                :default nil
                :description "Form field value."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Switch.Root."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Use controlled mode (:checked + :on-checked-change) when external state drives related UI."]
       [:li
        "Switch includes built-in disabled styling; avoid layering custom pointer-events overrides unless needed."]
       [:li "Associate with a label using matching :id and :html-for for better accessibility."]
       [:li
        "The local Switch has one fixed visual size and does not consume a :size prop. Upstream size examples are unsupported until the component exposes size variants; use :class only for intentional one-off scaling."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "(let [enabled? (r/atom false)]\n  [:div {:class \"flex items-center gap-2\"}\n   [switch {:id \"notifications\"\n            :checked @enabled?\n            :on-checked-change #(reset! enabled? %)\n            :name \"notifications\"\n            :value \"enabled\"}]\n   [label {:html-for \"notifications\"} \"Enable notifications\"]])"]]]]])))

(defstory
 SwitchDemo
 "Switch paired with a label.

  Radix primitive: @radix-ui/react-switch

  Useful for single boolean preferences."
 {:source-code (embed-source "clojure-shadcn.stories.switch.switch-demo")}
 []
 (r/as-element (helpers/wrap-component [example-switch-demo/example])))

(defstory
 FieldSwitch
 "Switch inside Field layout with description.

  Radix primitive: @radix-ui/react-switch

  Use for richer settings forms with copy."
 {:source-code (embed-source "clojure-shadcn.stories.switch.field-switch")}
 []
 (r/as-element (helpers/wrap-component [example-field-switch/example])))

(defstory
 SwitchDisabled
 "Disabled switch state.

  Radix primitive: @radix-ui/react-switch

  Use disabled state when the preference is locked."
 {:source-code (embed-source "clojure-shadcn.stories.switch.switch-disabled")}
 []
 (r/as-element (helpers/wrap-component [example-switch-disabled/example])))

(defstory
 SwitchInvalid
 "Invalid switch state for form validation.

  Radix primitive: @radix-ui/react-switch

  Use :aria-invalid true and helper text for validation feedback."
 {:source-code (embed-source "clojure-shadcn.stories.switch.switch-invalid")}
 []
 (r/as-element (helpers/wrap-component [example-switch-invalid/example])))

(defstory
 SwitchControlled
 "Controlled switch with live state.

  Radix primitive: @radix-ui/react-switch

  Use controlled switches when state drives other UI."
 {:source-code (embed-source "clojure-shadcn.stories.switch.switch-controlled")}
 []
 (r/as-element (helpers/wrap-component [example-switch-controlled/example])))


(defstory Playground
          "Interactive switch playground."
          {:args {:default-checked false
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
             "Explore switch initially checked, disabled and required with a live preview."}
            :controls {:expanded false
                       :exclude ["checked" "on-checked-change" "class"]}}
           :source-code (embed-source "clojure-shadcn.stories.switch.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory SwitchChoiceCards
          "Whole-card switch choices. Based on shadcn/ui Switch — Cards."
          {:source-code (embed-source "clojure-shadcn.stories.switch.switch-choice-cards")}
          []
          (r/as-element (helpers/wrap-component [example-switch-choice-cards/example])))

(defstory
 SwitchFieldDisabled
 "Disabled Field row without unsupported size props. Based on shadcn/ui Switch — Field Disabled."
 {:source-code (embed-source "clojure-shadcn.stories.switch.switch-field-disabled")}
 []
 (r/as-element (helpers/wrap-component [example-switch-field-disabled/example])))

(defstory SwitchRtl
          "Right-to-left switch field. Based on shadcn/ui Switch — RTL."
          {:source-code (embed-source "clojure-shadcn.stories.switch.switch-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-switch-rtl/example])))
