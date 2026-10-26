(ns clojure-shadcn.stories.notification-stories
  "Storybook stories for the Notification component. Ported from mateuszmazurczak.portfolio.ui-components.notification."
  (:require
   [clojure-shadcn.stories.helpers                                                      :as helpers]
   [clojure-shadcn.stories.notification.notification-all-types
    :as notification-all-types]
   [clojure-shadcn.stories.notification.notification-cancel-custom-callbacks-and-config
    :as notification-cancel-custom-callbacks-and-config]
   [clojure-shadcn.stories.notification.notification-loading-update-dismiss
    :as notification-loading-update-dismiss]
   [clojure-shadcn.stories.notification.notification-positions
    :as notification-positions]
   [clojure-shadcn.stories.notification.notification-promise
    :as notification-promise]
   [clojure-shadcn.stories.notification.playground                                      :as
                                                                                        playground]
   [clojure-shadcn.stories.notification.toast-basic                                     :as
                                                                                        toast-basic]
   [clojure-shadcn.stories.notification.toast-duration
    :as toast-duration]
   [clojure-shadcn.stories.notification.toast-with-action
    :as toast-with-action]
   [clojure-shadcn.stories.notification.toast-with-description
    :as toast-with-description]
   [reagent.core                                                                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Notification"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Toast notification component using Sonner."
                        :npm-install "npm install lucide-react sonner"
                        :source-code (embed-source "clojure-shadcn.ui.components.notification")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/notification.cljs"
                        :filename "notification.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "toaster"
       :link {:href "https://sonner.emilkowal.ski"
              :label "Sonner Docs"}
       :description
       "Sonner provider component. Mount once near app root before calling show-* helpers."
       :props [{:name "arguments"
                :type "none"
                :default nil
                :description "(toaster) or (toaster {:position ... :richColors ...})"}
               {:name "supported options"
                :type "any"
                :default nil
                :description "All Sonner Toaster props are accepted and forwarded."}]}]
     [helpers/api-component-card
      {:component-name "show-toast"
       :description "Generic toast helper around sonner/toast."
       :props [{:name "arity"
                :type "none"
                :default nil
                :description "(show-toast message) or (show-toast message options)"}
               {:name "message"
                :type "string"
                :default nil
                :description "Primary toast text."}
               {:name "options/:description"
                :type "string"
                :default nil
                :description "Secondary text."}
               {:name "options/:action"
                :type "map"
                :default nil
                :description "{:label string :on-click fn}."}
               {:name "options/:cancel"
                :type "map"
                :default nil
                :description "{:label string :on-click fn}."}
               {:name "options/:duration"
                :type "number"
                :default nil
                :description "Duration in ms."}
               {:name "options/:position"
                :type "string"
                :default "\"top-right\""
                :description "Sonner position."}
               {:name "options/:id"
                :type "string | number"
                :default nil
                :description "Custom toast id."}
               {:name "options/:important"
                :type "boolean"
                :default nil
                :description "Prevent dismissal."}
               {:name "options/:on-dismiss"
                :type "function"
                :default nil
                :description "Callback (fn [])."}
               {:name "options/:on-auto-close"
                :type "function"
                :default nil
                :description "Callback (fn [])."}
               {:name "returns"
                :type "any"
                :default nil
                :description "toast id from Sonner."}]}]
     [helpers/api-component-card
      {:component-name "show-success"
       :description "Success-themed toast helper."
       :props [{:name "arity"
                :type "none"
                :default nil
                :description "(show-success message) or (show-success message options)"}
               {:name "message"
                :type "string"
                :default nil
                :description "Toast title text."}
               {:name "options"
                :type "map"
                :default nil
                :description "Sonner toast options."}]}]
     [helpers/api-component-card {:component-name "show-error"
                                  :description "Error-themed toast helper."
                                  :props [{:name "arity"
                                           :type "none"
                                           :default nil
                                           :description
                                           "(show-error message) or (show-error message options)"}
                                          {:name "message"
                                           :type "string"
                                           :default nil
                                           :description "Toast title text."}
                                          {:name "options"
                                           :type "map"
                                           :default nil
                                           :description "Sonner toast options."}]}]
     [helpers/api-component-card {:component-name "show-info"
                                  :description "Info-themed toast helper."
                                  :props [{:name "arity"
                                           :type "none"
                                           :default nil
                                           :description
                                           "(show-info message) or (show-info message options)"}
                                          {:name "message"
                                           :type "string"
                                           :default nil
                                           :description "Toast title text."}
                                          {:name "options"
                                           :type "map"
                                           :default nil
                                           :description "Sonner toast options."}]}]
     [helpers/api-component-card
      {:component-name "show-warning"
       :description "Warning-themed toast helper."
       :props [{:name "arity"
                :type "none"
                :default nil
                :description "(show-warning message) or (show-warning message options)"}
               {:name "message"
                :type "string"
                :default nil
                :description "Toast title text."}
               {:name "options"
                :type "map"
                :default nil
                :description "Sonner toast options."}]}]
     [helpers/api-component-card
      {:component-name "show-loading"
       :description "Loading toast helper (returns id for later dismissal/update)."
       :props [{:name "arity"
                :type "none"
                :default nil
                :description "(show-loading message) or (show-loading message options)"}
               {:name "message"
                :type "string"
                :default nil
                :description "Loading text."}
               {:name "options"
                :type "map"
                :default nil
                :description "Sonner toast options."}
               {:name "returns"
                :type "any"
                :default nil
                :description "toast id."}]}]
     [helpers/api-component-card
      {:component-name "show-promise"
       :description "Promise lifecycle toast helper (loading/success/error)."
       :props [{:name "arity"
                :type "none"
                :default nil
                :description "(show-promise promise messages options)"}
               {:name "promise"
                :type "js/Promise"
                :default nil
                :description "js/Promise or thenable."}
               {:name "messages"
                :type "map"
                :default nil
                :description "{:loading :success :error} labels/content."}
               {:name "options"
                :type "map"
                :default nil
                :description "Sonner options."}]}]
     [helpers/api-component-card
      {:component-name "dismiss-toast"
       :description "Dismiss one toast by id, or all toasts when called without args."
       :props [{:name "arity"
                :type "none"
                :default nil
                :description "(dismiss-toast) or (dismiss-toast toast-id)"}
               {:name "toast-id"
                :type "string | number"
                :default nil
                :description "Toast id to dismiss."}]}]
     [helpers/api-component-card {:component-name "custom-toast"
                                  :description "Render custom Reagent/React content in a toast."
                                  :props [{:name "arity"
                                           :type "none"
                                           :default nil
                                           :description "(custom-toast component options)"}
                                          {:name "component"
                                           :type "hiccup"
                                           :default nil
                                           :description "hiccup vector or React element."}
                                          {:name "options"
                                           :type "map"
                                           :default nil
                                           :description "Sonner options."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Mount [toaster] once in app root; helper functions won't render anything by themselves."]
       [:li "These APIs are function calls (show-toast, dismiss-toast, etc.), not components."]
       [:li "Action/cancel callbacks are wrapped for Sonner; keep side effects idempotent."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:<>\n [toaster {:position \"top-right\"}]\n [button {:on-click #(show-success \"Saved\" {:description \"Profile updated\"})}\n  \"Notify\"]\n [button {:on-click #(let [id (show-loading \"Uploading...\")]\n                       (js/setTimeout #(dismiss-toast id) 1500))}\n  \"Loading demo\"]]"]]]]])))

(defstory
 ToastBasic
 "Basic toast notification.
  Uses Sonner for toast rendering.

  Call show-toast with a simple message."
 {:source-code (embed-source "clojure-shadcn.stories.notification.toast-basic")}
 []
 (r/as-element (helpers/wrap-component [toast-basic/example])))

(defstory ToastWithDescription
          "Toast with description text.
  Use :description to provide context."
          {:source-code (embed-source "clojure-shadcn.stories.notification.toast-with-description")}
          []
          (r/as-element (helpers/wrap-component [toast-with-description/example])))

(defstory ToastWithAction
          "Toast with action button.
  Use :action for undo or follow-up steps."
          {:source-code (embed-source "clojure-shadcn.stories.notification.toast-with-action")}
          []
          (r/as-element (helpers/wrap-component [toast-with-action/example])))

(defstory ToastDuration
          "Toast with custom duration. Use :duration for longer or shorter visibility."
          {:source-code (embed-source "clojure-shadcn.stories.notification.toast-duration")}
          []
          (r/as-element (helpers/wrap-component [toast-duration/example])))

(defstory NotificationPositions
          "Trigger every supported Sonner viewport position."
          {:source-code (embed-source "clojure-shadcn.stories.notification.notification-positions")}
          []
          (r/as-element (helpers/wrap-component [notification-positions/example])))

(defstory NotificationAllTypes
          "Generic, success, info, warning, error, and loading notifications."
          {:source-code (embed-source "clojure-shadcn.stories.notification.notification-all-types")}
          []
          (r/as-element (helpers/wrap-component [notification-all-types/example])))

(defstory
 NotificationPromise
 "Promise notifications transition from loading to success or error using the current show-promise API."
 {:source-code (embed-source "clojure-shadcn.stories.notification.notification-promise")}
 []
 (r/as-element (helpers/wrap-component [notification-promise/example])))

(defstory NotificationLoadingUpdateDismiss
          "A stable id updates a loading toast in place, while another control dismisses it."
          {:source-code (embed-source
                         "clojure-shadcn.stories.notification.notification-loading-update-dismiss")}
          []
          (r/as-element (helpers/wrap-component [notification-loading-update-dismiss/example])))

(defstory
 NotificationCancelCustomCallbacksAndConfig
 "Cancel/action callbacks, custom Hiccup content, lifecycle callbacks, and Toaster configuration."
 {:source-code
  (embed-source
   "clojure-shadcn.stories.notification.notification-cancel-custom-callbacks-and-config")}
 []
 (r/as-element (helpers/wrap-component [notification-cancel-custom-callbacks-and-config/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:source-code (embed-source "clojure-shadcn.stories.notification.playground")
           :args {:message "Changes saved"}
           :arg-types {:message {:name "Message"
                                 :control {:type "text"}}}
           :parameters {:playground {:description
                                     "Explore notification message with a live preview."}
                        :controls {:expanded false
                                   :exclude ["options" "on-dismiss" "on-auto-close" "on-click"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
