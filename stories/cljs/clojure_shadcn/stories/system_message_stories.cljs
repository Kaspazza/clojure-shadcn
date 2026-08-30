(ns clojure-shadcn.stories.system-message-stories
  "Storybook stories for the System Message component. Ported from mateuszmazurczak.portfolio.ui-components.system_message."
  (:require
   [clojure-shadcn.stories.helpers              :as helpers]
   [clojure-shadcn.ui.components.system-message :as sut]
   [reagent.core                                :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/System Message"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "System message component for displaying notifications, alerts, and status messages."
           :npm-install "npm install lucide-react"
           :source-code (embed-source "clojure-shadcn.ui.components.system_message")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/system_message.cljs"
           :filename "system_message.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "system-message"
       :description "System message component"
       :link {:href "https://www.prompt-kit.com/docs/system-message"
              :label "Prompt Kit System Message Docs"}
       :props [{:name ":variant"
                :type "keyword"
                :default ":action"
                :description "One of: :action | :error | :warning"}
               {:name ":fill"
                :type "boolean"
                :default "false"
                :description "Filled background style"}
               {:name ":icon"
                :type "react-component | hiccup"
                :default nil
                :description "Custom icon component"}
               {:name ":icon-hidden?"
                :type "boolean"
                :default "false"
                :description "Hide icon"}
               {:name ":cta"
                :type "map"
                :default nil
                :description "CTA config {:label string :on-click fn}"}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name "children"
                :type "hiccup | string"
                :default nil
                :description "Message content/body text or hiccup."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Variants currently documented are :action, :error, :warning (no :info variant in public contract)."]
       [:li "Use :fill true for stronger visual emphasis in high-salience alerts."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[system-message {:variant :action\n                 :icon [:span \"ℹ️\"]\n                 :fill true}\n \"Your workspace sync is up to date.\"]"]]]]])))

(defstory
 SystemMessageDefault
 "Default system message.
  Used for informational or action messages.

  Supports optional CTA buttons via :cta prop."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/system-message {}
                                         "This is an informational system message."]])))

(defstory SystemMessageWarning
          "Warning system message.
  Use :variant :warning for cautionary messages."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/system-message {:variant :warning}
                                                  "Warning: please review your inputs."]])))

(defstory SystemMessageError
          "Error system message.
  Use :variant :error for failure states."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/system-message {:variant :error}
                                                  "Something went wrong while saving."]])))

(defstory SystemMessageCta
          "System message with CTA button.
  Use :cta to provide an inline action."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "p-6"}
                          [sut/system-message {:variant :error
                                               :cta {:label "Retry"
                                                     :on-click #(js/console.log "retry")}}
                           "A network error occurred."]])))

(defstory SystemMessageNoIcon
          "System message without icon.
  Set :icon-hidden? true for a cleaner layout."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/system-message {:icon-hidden? true}
                                                  "Minimal message without leading icon."]])))

(defstory SystemMessageFilled
          "Filled system message style.
  Use :fill true for stronger visual emphasis."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/system-message {:variant :warning
                                                                      :fill true}
                                                  "Maintenance window starts in 10 minutes."]])))

(defstory
 SystemMessageCustomIcon
 "System message with custom icon.
  Use :icon to override the default icon per message context."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/system-message {:variant :action
                                                             :icon [:span {:aria-hidden true}
                                                                    "🔔"]}
                                         "You have new workspace invitations."]])))
