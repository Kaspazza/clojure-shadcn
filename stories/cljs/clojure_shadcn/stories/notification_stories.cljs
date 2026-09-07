(ns clojure-shadcn.stories.notification-stories
  "Storybook stories for the Notification component. Ported from mateuszmazurczak.portfolio.ui-components.notification."
  (:require
   [clojure-shadcn.stories.helpers            :as helpers]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.notification :as sut]
   [reagent.core                              :as r])
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
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6 space-y-4"}
                 [sut/toaster]
                 (button/button {:on-click (fn [] (sut/show-toast "Event has been created"))}
                                "Show Toast")])))

(defstory ToastWithDescription
          "Toast with description text.
  Use :description to provide context."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6 space-y-4"}
                                                 [sut/toaster]
                                                 (button/button
                                                  {:on-click (fn []
                                                               (sut/show-toast
                                                                "Event created"
                                                                {:description
                                                                 "Sunday, December 3 at 9:00 AM"}))}
                                                  "Show Detailed Toast")])))

(defstory ToastWithAction
          "Toast with action button.
  Use :action for undo or follow-up steps."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "p-6 space-y-4"}
                          [sut/toaster]
                          (button/button
                           {:on-click (fn []
                                        (sut/show-toast
                                         "File deleted"
                                         {:action {:label "Undo"
                                                   :on-click (fn [] (js/console.log "undo"))}}))}
                           "Show Action Toast")])))

(defstory ToastDuration
          "Toast with custom duration. Use :duration for longer or shorter visibility."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "p-6 space-y-4"}
                          [sut/toaster]
                          (button/button {:on-click #(sut/show-toast "Auto closes in 10 seconds"
                                                                     {:duration 10000})}
                                         "Show Long Toast")])))

(defstory
 NotificationPositions
 "Trigger every supported Sonner viewport position."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "flex max-w-xl flex-wrap gap-2 p-6"}
    [sut/toaster]
    (for [position
          ["top-left" "top-center" "top-right" "bottom-left" "bottom-center" "bottom-right"]]
      ^{:key position}
      [button/button {:variant :outline
                      :on-click #(sut/show-toast position {:position position})}
       position])])))

(defstory NotificationAllTypes
          "Generic, success, info, warning, error, and loading notifications."
          []
          (r/as-element (helpers/wrap-component [:div {:class "flex flex-wrap gap-2 p-6"}
                                                 [sut/toaster {:rich-colors true}]
                                                 (for [[label notify] [["Default" sut/show-toast]
                                                                       ["Success" sut/show-success]
                                                                       ["Info" sut/show-info]
                                                                       ["Warning" sut/show-warning]
                                                                       ["Error" sut/show-error]
                                                                       ["Loading"
                                                                        sut/show-loading]]]
                                                   ^{:key label}
                                                   [button/button {:variant :outline
                                                                   :on-click #(notify label)}
                                                    label])])))

(defstory
 NotificationPromise
 "Promise notifications transition from loading to success or error using the current show-promise API."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "flex gap-2 p-6"}
    [sut/toaster]
    [button/button {:on-click (fn []
                                (sut/show-promise (js/Promise. (fn [resolve _]
                                                                 (js/setTimeout #(resolve "done")
                                                                                1200)))
                                                  {:loading "Saving…"
                                                   :success "Saved"
                                                   :error "Save failed"}))}
     "Resolve promise"]
    [button/button {:variant :destructive
                    :on-click (fn []
                                (sut/show-promise (js/Promise. (fn [_ reject]
                                                                 (js/setTimeout #(reject (js/Error.
                                                                                          "failed"))
                                                                                1200)))
                                                  {:loading "Saving…"
                                                   :success "Saved"
                                                   :error "Save failed"}))}
     "Reject promise"]])))

(defstory NotificationLoadingUpdateDismiss
          "A stable id updates a loading toast in place, while another control dismisses it."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "flex gap-2 p-6"}
                          [sut/toaster]
                          [button/button {:on-click
                                          (fn []
                                            (sut/show-loading "Uploading…" {:id "upload-demo"})
                                            (js/setTimeout #(sut/show-success "Upload complete"
                                                                              {:id "upload-demo"})
                                                           1400))}
                           "Load then update"]
                          [button/button {:variant :outline
                                          :on-click #(sut/dismiss-toast "upload-demo")}
                           "Dismiss"]])))

(defstory
 NotificationCancelCustomCallbacksAndConfig
 "Cancel/action callbacks, custom Hiccup content, lifecycle callbacks, and Toaster configuration."
 []
 (r/as-element
  (r/with-let [event (r/atom "No callback yet")]
              (helpers/wrap-component
               [:div {:class "space-y-3 p-6"}
                [sut/toaster {:position "bottom-center"
                              :close-button true
                              :visible-toasts 4}]
                [:output {:class "block text-sm text-muted-foreground"}
                 @event]
                [:div {:class "flex flex-wrap gap-2"}
                 [button/button {:on-click (fn []
                                             (sut/show-toast
                                              "Delete draft?"
                                              {:action {:label "Delete"
                                                        :on-click #(reset! event "Action clicked")}
                                               :cancel {:label "Cancel"
                                                        :on-click #(reset! event "Cancelled")}
                                               :on-dismiss #(reset! event "Dismissed")
                                               :on-auto-close #(reset! event "Auto-closed")}))}
                  "Callbacks"]
                 [button/button {:variant :outline
                                 :on-click #(sut/custom-toast
                                             [:div {:class "flex items-center gap-2 font-medium"}
                                              [:span "🚀"]
                                              [:span "Custom release content"]]
                                             {:duration 4000})}
                  "Custom content"]
                 [button/button {:variant :ghost
                                 :on-click sut/dismiss-toast}
                  "Dismiss all"]]]))))


(defstory NotificationPlayground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:message "Changes saved"}
           :arg-types {:message {:control {:type "text"}}}
           :parameters {:controls {:exclude ["options" "on-dismiss" "on-auto-close" "on-click"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/toaster {}]
                                                 [button/button {:on-click #(sut/show-success
                                                                             (:message args))}
                                                  "Show success toast"]])))
