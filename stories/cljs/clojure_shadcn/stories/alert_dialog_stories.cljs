(ns clojure-shadcn.stories.alert-dialog-stories
  (:require
   ["lucide-react"                            :refer [TriangleAlert]]
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
      :props
      [{:name ":open / :default-open"
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
       {:name ":size"
        :type "keyword"
        :default ":default"
        :description
        "Content size: :default or :sm. The small layout uses a compact width and two-column footer."}
       {:name ":class"
        :type "string"
        :default nil
        :description "Tailwind classes merged with component defaults."}
       {:name "additional props"
        :type "map entries"
        :default nil
        :description "Normalized and forwarded to the wrapped Radix primitive or DOM element."}]}]
    [helpers/api-component-card
     {:component-name "alert-dialog-media"
      :description
      "Optional visual context rendered as a div inside alert-dialog-header. Its data slot activates the responsive media-aware header grid."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Additional Tailwind classes merged with the media container defaults."}
              {:name "additional props"
               :type "map entries"
               :default nil
               :description "Forwarded to the underlying div."}]}]
    [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4"}
     [:h4 {:class "mb-2 text-sm font-semibold"}
      "⚠️ Important Notes"]
     [:ul {:class "list-disc space-y-1 pl-4 text-xs text-muted-foreground"}
      [:li
       "Always provide both alert-dialog-title and alert-dialog-description so assistive technology can explain the decision."]
      [:li
       "Use alert-dialog-action only for the consequential action and alert-dialog-cancel for the safe escape path."]
      [:li "Alert dialogs intentionally cannot be dismissed by interacting outside the modal."]
      [:li
       "Action and cancel compose the shared button component, so button props such as :variant, :size, :disabled and :on-click are supported. Cancel defaults to :outline."]
      [:li
       "Place alert-dialog-media inside alert-dialog-header so the header's data-slot selectors activate the media-aware layout."]]]])))

(defstory
 AlertDialogBasic
 "The canonical confirmation pattern from shadcn: a clear consequence and a safe escape path."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6"}
    [sut/alert-dialog {}
     [sut/alert-dialog-trigger {:as-child true}
      (button/button {:variant :outline} "Show dialog")]
     [sut/alert-dialog-content {}
      [sut/alert-dialog-header {}
       [sut/alert-dialog-title {}
        "Are you absolutely sure?"]
       [sut/alert-dialog-description {}
        "This action cannot be undone. This will permanently delete your account and remove your data from our servers."]]
      [sut/alert-dialog-footer {}
       [sut/alert-dialog-cancel {}
        "Cancel"]
       [sut/alert-dialog-action {}
        "Continue"]]]]])))

(defstory
 AlertDialogDestructive
 "A destructive confirmation tied to visible domain context. The action names the object being deleted instead of using a vague “Continue” label."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-md space-y-4 p-6"}
    [:div {:class "rounded-lg border p-4"}
     [:div {:class "flex items-start justify-between gap-4"}
      [:div {:class "space-y-1"}
       [:h3 {:class "font-medium"}
        "website-redesign"]
       [:p {:class "text-sm text-muted-foreground"}
        "Production project · 12 collaborators"]]
      [sut/alert-dialog {}
       [sut/alert-dialog-trigger {:as-child true}
        (button/button {:variant :destructive
                        :size :sm}
                       "Delete project")]
       [sut/alert-dialog-content {}
        [sut/alert-dialog-header {}
         [sut/alert-dialog-title {}
          "Delete website-redesign?"]
         [sut/alert-dialog-description {}
          "The project, its deployments, environment variables, and activity history will be permanently deleted. This cannot be undone."]]
        [sut/alert-dialog-footer {}
         [sut/alert-dialog-cancel {}
          "Keep project"]
         [sut/alert-dialog-action {:variant :destructive}
          "Delete project"]]]]]]])))

(defstory
 AlertDialogControlled
 "A controlled alert dialog for an application workflow. Confirming updates both dialog state and the surrounding interface."
 []
 (r/as-element
  [(fn []
     (let [open? (r/atom false)
           published? (r/atom false)]
       (fn []
         (helpers/wrap-component
          [:div {:class "max-w-md space-y-4 p-6"}
           [:div {:class "rounded-lg border p-4"}
            [:div {:class "flex items-center justify-between gap-4"}
             [:div
              [:h3 {:class "font-medium"}
               "Version 2.4.0"]
              [:p {:class "text-sm text-muted-foreground"}
               (if @published? "Published to all customers" "Draft · ready to publish")]]
             [:span {:class (if @published?
                              "text-sm font-medium text-emerald-600"
                              "text-sm text-muted-foreground")}
              (if @published? "Published" "Draft")]]]
           (if @published?
             (button/button {:variant :outline
                             :disabled true}
                            "Release published")
             [sut/alert-dialog {:open @open?
                                :on-open-change #(reset! open? %)}
              [sut/alert-dialog-trigger {:as-child true}
               (button/button {} "Publish release")]
              [sut/alert-dialog-content {}
               [sut/alert-dialog-header {}
                [sut/alert-dialog-title {}
                 "Publish version 2.4.0?"]
                [sut/alert-dialog-description {}
                 "All customers will immediately receive this release. Rollbacks require a new deployment."]]
               [sut/alert-dialog-footer {}
                [sut/alert-dialog-cancel {}
                 "Review again"]
                [sut/alert-dialog-action {:on-click #(reset! published? true)}
                 "Publish now"]]]])]))))]))

(defstory
 AlertDialogWithMedia
 "Media-aware confirmation layout. The icon is structural context, while title and description retain the accessible decision text."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6"}
    [sut/alert-dialog {}
     [sut/alert-dialog-trigger {:as-child true}
      (button/button {:variant :destructive} "Revoke access")]
     [sut/alert-dialog-content {}
      [sut/alert-dialog-header {}
       [sut/alert-dialog-media {}
        [:> TriangleAlert {:aria-hidden true}]]
       [sut/alert-dialog-title {}
        "Revoke API access?"]
       [sut/alert-dialog-description {}
        "All active credentials for this integration will stop working immediately."]]
      [sut/alert-dialog-footer {}
       [sut/alert-dialog-cancel {}
        "Keep access"]
       [sut/alert-dialog-action {:variant :destructive}
        "Revoke access"]]]]])))

(defstory AlertDialogSmall
          "Compact small-size confirmation. Its footer becomes an equal two-column action grid."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/alert-dialog {}
                                                  [sut/alert-dialog-trigger {:as-child true}
                                                   (button/button {:variant :outline}
                                                                  "Discard draft")]
                                                  [sut/alert-dialog-content {:size :sm}
                                                   [sut/alert-dialog-header {}
                                                    [sut/alert-dialog-title {}
                                                     "Discard draft?"]
                                                    [sut/alert-dialog-description {}
                                                     "Your unsaved edits will be lost."]]
                                                   [sut/alert-dialog-footer {}
                                                    [sut/alert-dialog-cancel {}
                                                     "Cancel"]
                                                    [sut/alert-dialog-action {}
                                                     "Discard"]]]]])))

(defstory AlertDialogRtl
          "Right-to-left alert dialog with localized action order and media-aware alignment."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6"
                   :dir "rtl"}
             [sut/alert-dialog {}
              [sut/alert-dialog-trigger {:as-child true}
               (button/button {:variant :outline} "حذف المشروع")]
              [sut/alert-dialog-content {:dir "rtl"}
               [sut/alert-dialog-header {}
                [sut/alert-dialog-media {}
                 [:> TriangleAlert {:aria-hidden true}]]
                [sut/alert-dialog-title {}
                 "هل تريد حذف المشروع؟"]
                [sut/alert-dialog-description {}
                 "لا يمكن التراجع عن هذا الإجراء بعد التأكيد."]]
               [sut/alert-dialog-footer {}
                [sut/alert-dialog-cancel {}
                 "إلغاء"]
                [sut/alert-dialog-action {:variant :destructive}
                 "حذف"]]]]])))
