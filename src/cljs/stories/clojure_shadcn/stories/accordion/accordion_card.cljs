(ns clojure-shadcn.stories.accordion.accordion-card
  (:require [clojure-shadcn.ui.components.accordion :as accordion]))

(defn example []
  [:div {:class "max-w-xl p-6"}
   [accordion/accordion {:type "single" :collapsible true :default-value "account"
                         :class "rounded-xl border bg-card px-4 shadow-sm"}
    [accordion/accordion-item {:value "account"}
     [accordion/accordion-trigger {} "Account"]
     [accordion/accordion-content {:class "text-muted-foreground"}
      "Manage your profile, email address, and account preferences."]]
    [accordion/accordion-item {:value "security"}
     [accordion/accordion-trigger {} "Security"]
     [accordion/accordion-content {:class "text-muted-foreground"}
      "Review passkeys, active sessions, and two-factor authentication."]]]])
