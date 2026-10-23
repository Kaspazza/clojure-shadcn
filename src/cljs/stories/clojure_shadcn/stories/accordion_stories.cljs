(ns clojure-shadcn.stories.accordion-stories
  (:require
   [clojure-shadcn.stories.helpers         :as helpers]
   [clojure-shadcn.ui.components.accordion :as sut]
   [reagent.core                           :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Accordion"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Accessible vertically stacked disclosure sections."
                        :npm-install "npm install @radix-ui/react-accordion lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.accordion")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/accordion.cljs"
                        :filename "accordion.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [helpers/api-component-card
    {:component-name "accordion / accordion-item / accordion-trigger / accordion-content"
     :link {:href "https://www.radix-ui.com/primitives/docs/components/accordion"
            :label "Radix Accordion Docs"}
     :description
     "Data-oriented wrappers over Radix Accordion. Root accepts :type, :value, :default-value, :on-value-change, :collapsible and :orientation; all props are normalized and forwarded."
     :props [{:name ":type"
              :type ":single | :multiple"
              :default nil
              :description "Selection mode."}
             {:name ":value / :default-value"
              :type "string | vector"
              :default nil
              :description "Controlled or initial item values."}
             {:name ":collapsible"
              :type "boolean"
              :default false
              :description
              "For :type single, allow the open item to close so that no items remain open."}
             {:name ":disabled"
              :type "boolean"
              :default nil
              :description "Disable an item."}]}])))

(defstory
 AccordionBasic
 "The canonical shadcn product-information example: a full-width, collapsible accordion with the first item initially open."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-xl p-6"}
    [sut/accordion {:type "single"
                    :collapsible true
                    :default-value "item-1"
                    :class "w-full"}
     [sut/accordion-item {:value "item-1"}
      [sut/accordion-trigger {}
       "Product Information"]
      [sut/accordion-content {:class "text-balance [&>div]:flex [&>div]:flex-col [&>div]:gap-4"}
       [:p
        "Our flagship product combines cutting-edge technology with sleek design. Built with premium materials, it offers reliable performance for everyday use."]
       [:p
        "Key features include advanced processing capabilities and an intuitive interface designed for beginners and experts."]]]
     [sut/accordion-item {:value "item-2"}
      [sut/accordion-trigger {}
       "Shipping Details"]
      [sut/accordion-content {:class "text-balance [&>div]:flex [&>div]:flex-col [&>div]:gap-4"}
       [:p
        "We offer worldwide shipping through trusted courier partners. Standard delivery takes 3–5 business days."]
       [:p "Every order is carefully packaged, insured, and available for real-time tracking."]]]
     [sut/accordion-item {:value "item-3"}
      [sut/accordion-trigger {}
       "Return Policy"]
      [sut/accordion-content {:class "text-balance [&>div]:flex [&>div]:flex-col [&>div]:gap-4"}
       [:p "Return products in their original condition within 30 days for a full refund."]
       [:p
        "The return process includes free shipping and refunds processed after the returned item is received."]]]]])))

(defstory
 AccordionNonCollapsible
 "A single accordion where one item must remain open. Clicking another item moves the open state, but clicking the open item does not close it."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-xl p-6"}
    [:p {:class "mb-3 text-sm text-muted-foreground"}
     "One section always remains open because :collapsible is false."]
    [sut/accordion {:type "single"
                    :collapsible false
                    :default-value "one"
                    :class "w-full"}
     [sut/accordion-item {:value "one"}
      [sut/accordion-trigger {}
       "Can I close the active section?"]
      [sut/accordion-content {}
       "No. With :collapsible false, a single accordion keeps one item open."]]
     [sut/accordion-item {:value "two"}
      [sut/accordion-trigger {}
       "Can I open a different section?"]
      [sut/accordion-content {}
       "Yes. Opening this section closes the previously active section."]]]])))

(defstory
 AccordionMultiple
 "Allow several sections to remain open by using :type multiple and a vector of initial values."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-xl p-6"}
    [sut/accordion {:type "multiple"
                    :default-value ["notifications" "billing"]
                    :class "w-full"}
     [sut/accordion-item {:value "notifications"}
      [sut/accordion-trigger {}
       "Notification settings"]
      [sut/accordion-content {:class "text-muted-foreground"}
       "Choose email alerts, mobile push notifications, and activity summaries independently."]]
     [sut/accordion-item {:value "privacy"}
      [sut/accordion-trigger {}
       "Privacy and security"]
      [sut/accordion-content {:class "text-muted-foreground"}
       "Manage two-factor authentication, connected devices, active sessions, and data sharing."]]
     [sut/accordion-item {:value "billing"}
      [sut/accordion-trigger {}
       "Billing and subscription"]
      [sut/accordion-content {:class "text-muted-foreground"}
       "Review invoices, update payment details, or change the workspace subscription."]]]])))

(defstory
 AccordionAppearances
 "Four class-based treatments adapted from shadcn v4 presets. They demonstrate composition without adding a variant prop to the component."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid max-w-6xl gap-8 p-6 md:grid-cols-2"}
    [:section
     [:h3 {:class "mb-1 text-sm font-semibold"}
      "Vega — rounded and spacious"]
     [:p {:class "mb-4 text-sm text-muted-foreground"}
      "Separated rounded items with generous horizontal padding."]
     [sut/accordion {:type "single"
                     :collapsible true
                     :default-value "vega-account"
                     :class "grid gap-2"}
      [sut/accordion-item {:value "vega-account"
                           :class "rounded-lg border last:border-b"}
       [sut/accordion-trigger {:class "px-4 text-sm hover:no-underline"}
        "Account settings"]
       [sut/accordion-content {:class "px-4 text-muted-foreground"}
        "Update profile details, language, and regional preferences."]]
      [sut/accordion-item {:value "vega-security"
                           :class "rounded-lg border last:border-b"}
       [sut/accordion-trigger {:class "px-4 text-sm hover:no-underline"}
        "Security"]
       [sut/accordion-content {:class "px-4 text-muted-foreground"}
        "Configure passwords, passkeys, and two-factor authentication."]]]]
    [:section
     [:h3 {:class "mb-1 text-sm font-semibold"}
      "Nova — rounded and compact"]
     [:p {:class "mb-4 text-sm text-muted-foreground"}
      "The same card treatment with tighter horizontal rhythm."]
     [sut/accordion {:type "single"
                     :collapsible true
                     :default-value "nova-plan"
                     :class "grid gap-2"}
      [sut/accordion-item {:value "nova-plan"
                           :class "rounded-lg border last:border-b"}
       [sut/accordion-trigger {:class "px-2.5 text-sm hover:no-underline"}
        "Current plan"]
       [sut/accordion-content {:class "px-2.5 text-sm text-muted-foreground"}
        "Review included features and current workspace usage."]]
      [sut/accordion-item {:value "nova-invoices"
                           :class "rounded-lg border last:border-b"}
       [sut/accordion-trigger {:class "px-2.5 text-sm hover:no-underline"}
        "Invoices"]
       [sut/accordion-content {:class "px-2.5 text-sm text-muted-foreground"}
        "Download receipts and manage billing contact details."]]]]
    [:section
     [:h3 {:class "mb-1 text-sm font-semibold"}
      "Lyra — compact bordered rows"]
     [:p {:class "mb-4 text-sm text-muted-foreground"}
      "Square borders and smaller typography create a denser layout."]
     [sut/accordion {:type "single"
                     :collapsible true
                     :default-value "lyra-builds"
                     :class "grid gap-2"}
      [sut/accordion-item {:value "lyra-builds"
                           :class "border last:border-b"}
       [sut/accordion-trigger {:class "px-2 py-3 text-xs hover:no-underline"}
        "Build history"]
       [sut/accordion-content {:class "px-2 text-xs text-muted-foreground"}
        "Inspect recent deployments and their resulting artifacts."]]
      [sut/accordion-item {:value "lyra-environment"
                           :class "border last:border-b"}
       [sut/accordion-trigger {:class "px-2 py-3 text-xs hover:no-underline"}
        "Environment variables"]
       [sut/accordion-content {:class "px-2 text-xs text-muted-foreground"}
        "Manage encrypted values for each deployment environment."]]]]
    [:section
     [:h3 {:class "mb-1 text-sm font-semibold"}
      "Mira — minimal and dense"]
     [:p {:class "mb-4 text-sm text-muted-foreground"}
      "Flush divider rows keep the visual treatment lightweight."]
     [sut/accordion {:type "single"
                     :collapsible true
                     :default-value "mira-shortcuts"
                     :class "w-full"}
      [sut/accordion-item {:value "mira-shortcuts"}
       [sut/accordion-trigger {:class "py-3 text-xs"}
        "Keyboard shortcuts"]
       [sut/accordion-content {:class "text-xs text-muted-foreground"}
        "View and customize commands used throughout the application."]]
      [sut/accordion-item {:value "mira-accessibility"}
       [sut/accordion-trigger {:class "py-3 text-xs"}
        "Accessibility"]
       [sut/accordion-content {:class "text-xs text-muted-foreground"}
        "Adjust motion, contrast, and focus visibility preferences."]]]]])))

(defstory AccordionDisabled
          []
          (r/as-element (helpers/wrap-component [:div {:class "max-w-xl p-6"}
                                                 [sut/accordion {:type "single"}
                                                  [sut/accordion-item {:value "locked"
                                                                       :disabled true}
                                                   [sut/accordion-trigger {}
                                                    "Unavailable section"]
                                                   [sut/accordion-content {}
                                                    "Cannot be opened."]]]])))

(defstory
 AccordionPlayground
 "Toggle whether the open item may be closed. When disabled, opening an item means one item must remain open."
 {:args {:collapsible true}
  :arg-types {:collapsible {:control {:type "boolean"}
                            :description
                            "Allow the open item to be closed, leaving every item closed."}}
  :parameters {:controls {:exclude ["children" "on-value-change" "value" "default-value"]}}}
 [args]
 (r/as-element (helpers/wrap-component
                [sut/accordion {:type "single"
                                :collapsible (:collapsible args)}
                 [sut/accordion-item {:value "item-1"}
                  [sut/accordion-trigger {}
                   "Can I close this section after opening it?"]
                  [sut/accordion-content {}
                   "Yes when collapsible is enabled; otherwise this section remains open."]]])))

(defstory
 AccordionCard
 "Accordion Card — the upstream card composition places disclosure rows inside one rounded surface."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "max-w-xl p-6"}
                 [sut/accordion {:type "single"
                                 :collapsible true
                                 :default-value "account"
                                 :class "rounded-xl border bg-card px-4 shadow-sm"}
                  [sut/accordion-item {:value "account"}
                   [sut/accordion-trigger {}
                    "Account"]
                   [sut/accordion-content {:class "text-muted-foreground"}
                    "Manage your profile, email address, and account preferences."]]
                  [sut/accordion-item {:value "security"}
                   [sut/accordion-trigger {}
                    "Security"]
                   [sut/accordion-content {:class "text-muted-foreground"}
                    "Review passkeys, active sessions, and two-factor authentication."]]]])))

(defstory
 AccordionEnclosingBorder
 "Enclosing border — the upstream bordered example keeps a single outline around flush rows."
 []
 (r/as-element (helpers/wrap-component [sut/accordion
                                        {:type "single"
                                         :collapsible true
                                         :class "max-w-xl overflow-hidden rounded-lg border px-4"}
                                        [sut/accordion-item {:value "one"}
                                         [sut/accordion-trigger {}
                                          "Is the border a component variant?"]
                                         [sut/accordion-content {}
                                          "No. It is composed with classes on the root."]]
                                        [sut/accordion-item {:value "two"
                                                             :class "border-b-0"}
                                         [sut/accordion-trigger {}
                                          "Do items remain separated?"]
                                         [sut/accordion-content {}
                                          "Yes. Item dividers preserve the row structure."]]])))

(defstory AccordionDisabledComparison
          "Disabled — comparative enabled and disabled items make the unavailable state explicit."
          []
          (r/as-element (helpers/wrap-component [sut/accordion {:type "single"
                                                                :collapsible true
                                                                :class "max-w-xl"}
                                                 [sut/accordion-item {:value "available"}
                                                  [sut/accordion-trigger {}
                                                   "Available section"]
                                                  [sut/accordion-content {}
                                                   "This section can be opened and closed."]]
                                                 [sut/accordion-item {:value "unavailable"
                                                                      :disabled true}
                                                  [sut/accordion-trigger {}
                                                   "Unavailable section"]
                                                  [sut/accordion-content {}
                                                   "This content cannot be opened."]]])))

(defstory AccordionRTL
          "RTL — direction-aware disclosure layout with Arabic content."
          []
          (r/as-element (helpers/wrap-component [:div {:dir "rtl"
                                                       :class "max-w-xl text-right"}
                                                 [sut/accordion {:type "single"
                                                                 :collapsible true
                                                                 :default-value "shipping"}
                                                  [sut/accordion-item {:value "shipping"}
                                                   [sut/accordion-trigger {}
                                                    "ما مدة الشحن؟"]
                                                   [sut/accordion-content {}
                                                    "يصل الطلب عادة خلال ثلاثة إلى خمسة أيام عمل."]]
                                                  [sut/accordion-item {:value "returns"}
                                                   [sut/accordion-trigger {}
                                                    "هل يمكن إرجاع الطلب؟"]
                                                   [sut/accordion-content {}
                                                    "يمكن إرجاع المنتجات خلال ثلاثين يومًا."]]]])))
