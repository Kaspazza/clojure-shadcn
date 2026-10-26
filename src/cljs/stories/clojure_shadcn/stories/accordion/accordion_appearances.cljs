(ns clojure-shadcn.stories.accordion.accordion-appearances
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [:div {:class "grid max-w-6xl gap-8 p-6 md:grid-cols-2"}
   [:section
    [:h3 {:class "mb-1 text-sm font-semibold"} "Vega — rounded and spacious"]
    [:p {:class "mb-4 text-sm text-muted-foreground"} "Separated rounded items with generous horizontal padding."]
    [accordion/accordion {:type "single" :collapsible true :default-value "vega-account" :class "grid gap-2"}
     [accordion/accordion-item {:value "vega-account" :class "rounded-lg border last:border-b"}
      [accordion/accordion-trigger {:class "px-4 text-sm hover:no-underline"} "Account settings"]
      [accordion/accordion-content {:class "px-4 text-muted-foreground"} "Update profile details, language, and regional preferences."]]
     [accordion/accordion-item {:value "vega-security" :class "rounded-lg border last:border-b"}
      [accordion/accordion-trigger {:class "px-4 text-sm hover:no-underline"} "Security"]
      [accordion/accordion-content {:class "px-4 text-muted-foreground"} "Configure passwords, passkeys, and two-factor authentication."]]]]
   [:section
    [:h3 {:class "mb-1 text-sm font-semibold"} "Nova — rounded and compact"]
    [:p {:class "mb-4 text-sm text-muted-foreground"} "The same card treatment with tighter horizontal rhythm."]
    [accordion/accordion {:type "single" :collapsible true :default-value "nova-plan" :class "grid gap-2"}
     [accordion/accordion-item {:value "nova-plan" :class "rounded-lg border last:border-b"}
      [accordion/accordion-trigger {:class "px-2.5 text-sm hover:no-underline"} "Current plan"]
      [accordion/accordion-content {:class "px-2.5 text-sm text-muted-foreground"} "Review included features and current workspace usage."]]
     [accordion/accordion-item {:value "nova-invoices" :class "rounded-lg border last:border-b"}
      [accordion/accordion-trigger {:class "px-2.5 text-sm hover:no-underline"} "Invoices"]
      [accordion/accordion-content {:class "px-2.5 text-sm text-muted-foreground"} "Download receipts and manage billing contact details."]]]]
   [:section
    [:h3 {:class "mb-1 text-sm font-semibold"} "Lyra — compact bordered rows"]
    [:p {:class "mb-4 text-sm text-muted-foreground"} "Square borders and smaller typography create a denser layout."]
    [accordion/accordion {:type "single" :collapsible true :default-value "lyra-builds" :class "grid gap-2"}
     [accordion/accordion-item {:value "lyra-builds" :class "border last:border-b"}
      [accordion/accordion-trigger {:class "px-2 py-3 text-xs hover:no-underline"} "Build history"]
      [accordion/accordion-content {:class "px-2 text-xs text-muted-foreground"} "Inspect recent deployments and their resulting artifacts."]]
     [accordion/accordion-item {:value "lyra-environment" :class "border last:border-b"}
      [accordion/accordion-trigger {:class "px-2 py-3 text-xs hover:no-underline"} "Environment variables"]
      [accordion/accordion-content {:class "px-2 text-xs text-muted-foreground"} "Manage encrypted values for each deployment environment."]]]]
   [:section
    [:h3 {:class "mb-1 text-sm font-semibold"} "Mira — minimal and dense"]
    [:p {:class "mb-4 text-sm text-muted-foreground"} "Flush divider rows keep the visual treatment lightweight."]
    [accordion/accordion {:type "single" :collapsible true :default-value "mira-shortcuts" :class "w-full"}
     [accordion/accordion-item {:value "mira-shortcuts"}
      [accordion/accordion-trigger {:class "py-3 text-xs"} "Keyboard shortcuts"]
      [accordion/accordion-content {:class "text-xs text-muted-foreground"} "View and customize commands used throughout the application."]]
     [accordion/accordion-item {:value "mira-accessibility"}
      [accordion/accordion-trigger {:class "py-3 text-xs"} "Accessibility"]
      [accordion/accordion-content {:class "text-xs text-muted-foreground"} "Adjust motion, contrast, and focus visibility preferences."]]]]])
