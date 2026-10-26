(ns clojure-shadcn.stories.system-message-stories
  "Storybook stories for the System Message component. Ported from mateuszmazurczak.portfolio.ui-components.system_message."
  (:require
   [clojure-shadcn.stories.helpers                                              :as helpers]
   [clojure-shadcn.stories.system-message.playground                            :as
                                                                                example-playground]
   [clojure-shadcn.stories.system-message.system-message-cta
    :as example-system-message-cta]
   [clojure-shadcn.stories.system-message.system-message-error
    :as example-system-message-error]
   [clojure-shadcn.stories.system-message.system-message-filled
    :as example-system-message-filled]
   [clojure-shadcn.stories.system-message.system-message-filled-cta-custom-icon
    :as example-system-message-filled-cta-custom-icon]
   [clojure-shadcn.stories.system-message.system-message-warning
    :as example-system-message-warning]
   [reagent.core                                                                :as r])
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
 Playground
 "Interactive system-message playground."
 {:args {:variant "action"
         :fill false
         :icon-hidden? false
         :message "This is an informational system message."}
  :arg-types {:variant {:name "Variant"
                        :control {:type "select"}
                        :options ["action" "error" "warning"]}
              :fill {:name "Fill background"
                     :control {:type "boolean"}}
              :icon-hidden? {:name "Hide icon"
                             :control {:type "boolean"}}
              :message {:name "Message"
                        :control {:type "text"}}}
  :parameters
  {:playground
   {:description
    "Explore system message variant, fill background, hide icon and message with a live preview."}
   :controls {:expanded false
              :exclude ["class" "icon" "cta" "on-click"]}}
  :decode-args (fn [{:keys [variant]
                     :as args}]
                 (cond-> args
                   variant (update :variant keyword)))
  :source-code (embed-source "clojure-shadcn.stories.system-message.playground")}
 [args]
 (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory SystemMessageWarning
          "Warning system message.
  Use :variant :warning for cautionary messages."
          {:source-code (embed-source
                         "clojure-shadcn.stories.system-message.system-message-warning")}
          []
          (r/as-element (helpers/wrap-component [example-system-message-warning/example])))

(defstory SystemMessageError
          "Error system message.
  Use :variant :error for failure states."
          {:source-code (embed-source "clojure-shadcn.stories.system-message.system-message-error")}
          []
          (r/as-element (helpers/wrap-component [example-system-message-error/example])))

(defstory SystemMessageCta
          "System message with CTA button.
  Use :cta to provide an inline action."
          {:source-code (embed-source "clojure-shadcn.stories.system-message.system-message-cta")}
          []
          (r/as-element (helpers/wrap-component [example-system-message-cta/example])))

(defstory SystemMessageFilled
          "Filled system message style.
  Use :fill true for stronger visual emphasis."
          {:source-code (embed-source
                         "clojure-shadcn.stories.system-message.system-message-filled")}
          []
          (r/as-element (helpers/wrap-component [example-system-message-filled/example])))

(defstory
 SystemMessageFilledCtaCustomIcon
 "Combine filled emphasis, an inline CTA, and a custom React icon through documented props."
 {:source-code (embed-source
                "clojure-shadcn.stories.system-message.system-message-filled-cta-custom-icon")}
 []
 (r/as-element (helpers/wrap-component [example-system-message-filled-cta-custom-icon/example])))
