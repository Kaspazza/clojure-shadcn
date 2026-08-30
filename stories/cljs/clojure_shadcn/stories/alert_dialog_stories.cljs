(ns clojure-shadcn.stories.alert-dialog-stories
  (:require
   [clojure-shadcn.stories.helpers            :as helpers]
   [clojure-shadcn.ui.components.alert-dialog :as sut]
   [clojure-shadcn.ui.components.button       :as button]
   [reagent.core                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Alert Dialog"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Modal confirmation dialog that interrupts users before a consequential action."
           :npm-install "npm install @radix-ui/react-alert-dialog"
           :source-code (embed-source "clojure-shadcn.ui.components.alert-dialog")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/alert_dialog.cljs"
           :filename "alert_dialog.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "alert-dialog and composed parts"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/alert-dialog"
             :label "Radix Alert Dialog Docs"}
      :description
      "Data-oriented wrappers over Radix AlertDialog. The root, trigger, portal, overlay, content, title, description, action and cancel preserve modal focus management, keyboard behavior and accessible labeling. Additional props are normalized and forwarded."
      :props [{:name ":open / :default-open"
               :type "boolean"
               :default nil
               :description "Controlled or initial open state on alert-dialog."}
              {:name ":on-open-change"
               :type "function"
               :default nil
               :description "Receives the next open state."}
              {:name ":as-child"
               :type "boolean"
               :default nil
               :description "Compose a trigger with its single child."}
              {:name ":class"
               :type "string"
               :default nil
               :description "Tailwind classes merged with component defaults."}]}]
    [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4"}
     [:h4 {:class "mb-2 text-sm font-semibold"}
      "⚠️ Important Notes"]
     [:ul {:class "list-disc space-y-1 pl-4 text-xs text-muted-foreground"}
      [:li
       "Always provide both alert-dialog-title and alert-dialog-description so assistive technology can explain the decision."]
      [:li
       "Use alert-dialog-action only for the consequential action and alert-dialog-cancel for the safe escape path."]
      [:li
       "Alert dialogs intentionally cannot be dismissed by interacting outside the modal."]]]])))

(defstory AlertDialogBasic
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6"}
             [sut/alert-dialog {}
              [sut/alert-dialog-trigger {:as-child true}
               [button/button {:variant :outline}
                "Delete account"]]
              [sut/alert-dialog-content {}
               [sut/alert-dialog-header {}
                [sut/alert-dialog-title {}
                 "Are you absolutely sure?"]
                [sut/alert-dialog-description {}
                 "This action permanently deletes the account and its data."]]
               [sut/alert-dialog-footer {}
                [sut/alert-dialog-cancel {}
                 "Cancel"]
                [sut/alert-dialog-action {}
                 "Continue"]]]]])))

(defstory AlertDialogControlled
          []
          (r/as-element
           [(fn []
              (let [open? (r/atom true)]
                (fn []
                  (helpers/wrap-component
                   [:div {:class "p-6"}
                    [sut/alert-dialog {:open @open?
                                       :on-open-change #(reset! open? %)}
                     [sut/alert-dialog-trigger {:as-child true}
                      [button/button {}
                       "Open confirmation"]]
                     [sut/alert-dialog-content {}
                      [sut/alert-dialog-header {}
                       [sut/alert-dialog-title {}
                        "Publish this release?"]
                       [sut/alert-dialog-description {}
                        "Customers will immediately see the new version."]]
                      [sut/alert-dialog-footer {}
                       [sut/alert-dialog-cancel {}
                        "Not yet"]
                       [sut/alert-dialog-action {}
                        "Publish"]]]]]))))]))


(defstory
 AlertDialogPlayground
 "Controlled Storybook playground using only safe scalar component props."
 {:args {:default-open false}
  :arg-types {:default-open {:control {:type "boolean"}}}
  :parameters {:controls {:exclude ["children" "open" "on-open-change" "on-click"]}}
 }
 [args]
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6"}
    [sut/alert-dialog (select-keys args [:default-open])
     [sut/alert-dialog-trigger {:as-child true} [button/button {:variant :outline} "Delete account"]]
     [sut/alert-dialog-content {}
      [sut/alert-dialog-header {} [sut/alert-dialog-title {} "Delete account?"] [sut/alert-dialog-description {} "This action cannot be undone."]]
      [sut/alert-dialog-footer {} [sut/alert-dialog-cancel {} "Cancel"] [sut/alert-dialog-action {} "Continue"]]]]])))
