(ns clojure-shadcn.stories.switch-stories
  "Storybook stories for the Switch component. Ported from mateuszmazurczak.portfolio.ui-components.switch."
  (:require
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.field  :as field]
   [clojure-shadcn.ui.components.label  :as label]
   [clojure-shadcn.ui.components.switch :as sut]
   [reagent.core                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title      "Components/Switch"
       :parameters #js {:layout "padded"}})

(defn ^:export Installation
  "Install dependencies and copy the component code into your project."
  []
  (r/as-element
  [helpers/installation-scene
             {:description "Switch component for toggle controls."
              :npm-install "npm install @radix-ui/react-switch"
              :source-code (embed-source "clojure-shadcn.ui.components.switch")
              :namespace-path "src/cljs/clojure_shadcn/ui/components/switch.cljs"
              :filename "switch.cljs"}]))

(defstory ApiReference
  []
  (r/as-element
  (helpers/wrap-component
             [:div {:class "p-6 max-w-4xl"}
              [:div {:class "space-y-4"}
               [helpers/api-component-card
                {:component-name "switch"
                 :description "Radix-based boolean toggle control with accessible switch semantics. Additional props are forwarded to the underlying Radix Switch.Root."
                 :link {:href "https://www.radix-ui.com/primitives/docs/components/switch" :label "Radix Switch Docs"}
                 :props [{:name ":checked"           :type "boolean"      :default nil :description "Controlled checked state."}
                          {:name ":default-checked"   :type "boolean"      :default nil :description "Uncontrolled initial checked state."}
                          {:name ":on-checked-change" :type "function"     :default nil :description "Callback when state changes: (fn [checked?] ...)."}
                          {:name ":disabled"          :type "boolean"      :default nil :description "Disables interaction."}
                          {:name ":required"          :type "boolean"      :default nil :description "Marks field as required for forms."}
                          {:name ":name"              :type "string"       :default nil :description "Form field name."}
                          {:name ":value"             :type "string"       :default nil :description "Form field value."}
                          {:name ":class"             :type "string"       :default nil :description "Additional Tailwind classes."}
                          {:name "additional props"   :type "map entries"  :default nil :description "Forwarded to Radix Switch.Root."}]}]
                [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
                 [:h4 {:class "text-sm font-semibold mb-2"} "⚠️ Important Notes"]
                 [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
                  [:li "Use controlled mode (:checked + :on-checked-change) when external state drives related UI."]
                  [:li "Switch includes built-in disabled styling; avoid layering custom pointer-events overrides unless needed."]
                  [:li "Associate with a label using matching :id and :html-for for better accessibility."]]]
                [:div {:class "border rounded-lg p-4 bg-muted/50"}
                 [:h4 {:class "text-sm font-semibold mb-2"}
                  "Usage Example"]
                 [:pre {:class "text-xs overflow-x-auto"}
                  [:code "(let [enabled? (r/atom false)]\n  [:div {:class \"flex items-center gap-2\"}\n   [switch {:id \"notifications\"\n            :checked @enabled?\n            :on-checked-change #(reset! enabled? %)\n            :name \"notifications\"\n            :value \"enabled\"}]\n   [label {:html-for \"notifications\"} \"Enable notifications\"]])"]]
            ]]])))

(defstory SwitchDemo
  "Switch paired with a label.

  Radix primitive: @radix-ui/react-switch

  Useful for single boolean preferences."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6"}
                                       [:div {:class "flex items-center gap-2"}
                                        [sut/switch {:id "airplane-mode"}]
                                        [label/label {:html-for "airplane-mode"}
                                         "Airplane Mode"]]])))

(defstory FieldSwitch
  "Switch inside Field layout with description.

  Radix primitive: @radix-ui/react-switch

  Use for richer settings forms with copy."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6 max-w-md"}
                                       [field/field {:orientation :horizontal}
                                        [field/field-content {}
                                         [field/field-label {:html-for "mfa"}
                                          "Multi-factor authentication"]
                                         [field/field-description {}
                                          "Enable MFA for additional account security."]]
                                        [sut/switch {:id "mfa"}]]])))

(defstory SwitchDisabled
  "Disabled switch state.

  Radix primitive: @radix-ui/react-switch

  Use disabled state when the preference is locked."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6"}
                                       [:div {:class "flex items-center gap-2"}
                                        [sut/switch {:id "locked"
                                                     :disabled true
                                                     :checked true}]
                                        [:span {:class "text-sm text-muted-foreground"}
                                         "Locked setting"]]])))

(defstory SwitchInvalid
  "Invalid switch state for form validation.

  Radix primitive: @radix-ui/react-switch

  Use :aria-invalid true and helper text for validation feedback."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6 space-y-2"}
                                       [:div {:class "flex items-center gap-2"}
                                        [sut/switch {:id "terms-invalid"
                                                     :aria-invalid true
                                                     :checked false}]
                                        [label/label {:html-for "terms-invalid"}
                                         "Accept terms"]]
                                       [:p {:class "text-destructive text-sm"}
                                        "You must accept terms to continue."]])))

(defstory SwitchControlled
  "Controlled switch with live state.

  Radix primitive: @radix-ui/react-switch

  Use controlled switches when state drives other UI."
  []
  (r/as-element [(fn [] (let [enabled? (r/atom true)]
     (fn []
       (helpers/wrap-component [:div {:class "p-6 flex items-center gap-3"}
                                           [sut/switch {:checked @enabled?
                                                        :on-checked-change #(reset! enabled? %)}]
                                           [:span {:class "text-sm"}
                                            (if @enabled? "Enabled" "Disabled")]]))))]))
