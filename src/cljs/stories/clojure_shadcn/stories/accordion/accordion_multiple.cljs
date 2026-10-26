(ns clojure-shadcn.stories.accordion.accordion-multiple
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [:div {:class "max-w-xl p-6"}
   [accordion/accordion {:type "multiple" :default-value ["notifications" "billing"] :class "w-full"}
    [accordion/accordion-item {:value "notifications"}
     [accordion/accordion-trigger {} "Notification settings"]
     [accordion/accordion-content {:class "text-muted-foreground"}
      "Choose email alerts, mobile push notifications, and activity summaries independently."]]
    [accordion/accordion-item {:value "privacy"}
     [accordion/accordion-trigger {} "Privacy and security"]
     [accordion/accordion-content {:class "text-muted-foreground"}
      "Manage two-factor authentication, connected devices, active sessions, and data sharing."]]
    [accordion/accordion-item {:value "billing"}
     [accordion/accordion-trigger {} "Billing and subscription"]
     [accordion/accordion-content {:class "text-muted-foreground"}
      "Review invoices, update payment details, or change the workspace subscription."]]]])
