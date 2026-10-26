(ns clojure-shadcn.stories.alert-dialog-stories
  (:require
   [clojure-shadcn.stories.alert-dialog.alert-dialog-basic :as alert-dialog-basic]
   [clojure-shadcn.stories.alert-dialog.alert-dialog-destructive :as alert-dialog-destructive]
   [clojure-shadcn.stories.alert-dialog.alert-dialog-controlled :as alert-dialog-controlled]
   [clojure-shadcn.stories.alert-dialog.alert-dialog-with-media :as alert-dialog-with-media]
   [clojure-shadcn.stories.alert-dialog.alert-dialog-small :as alert-dialog-small]
   [clojure-shadcn.stories.alert-dialog.alert-dialog-rtl :as alert-dialog-rtl]
   [clojure-shadcn.stories.helpers :as helpers]
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

(defstory AlertDialogBasic
 "The canonical confirmation pattern from shadcn: a clear consequence and a safe escape path."
 {:source-code (embed-source "clojure-shadcn.stories.alert-dialog.alert-dialog-basic")}
 []
 (r/as-element (helpers/wrap-component [alert-dialog-basic/example])))

(defstory AlertDialogDestructive
 "A destructive confirmation tied to visible domain context. The action names the object being deleted instead of using a vague “Continue” label."
 {:source-code (embed-source "clojure-shadcn.stories.alert-dialog.alert-dialog-destructive")}
 []
 (r/as-element (helpers/wrap-component [alert-dialog-destructive/example])))

(defstory AlertDialogControlled
 "A controlled alert dialog for an application workflow. Confirming updates both dialog state and the surrounding interface."
 {:source-code (embed-source "clojure-shadcn.stories.alert-dialog.alert-dialog-controlled")}
 []
 (r/as-element (helpers/wrap-component [alert-dialog-controlled/example])))

(defstory AlertDialogWithMedia
 "Media-aware confirmation layout. The icon is structural context, while title and description retain the accessible decision text."
 {:source-code (embed-source "clojure-shadcn.stories.alert-dialog.alert-dialog-with-media")}
 []
 (r/as-element (helpers/wrap-component [alert-dialog-with-media/example])))

(defstory AlertDialogSmall
          "Compact small-size confirmation. Its footer becomes an equal two-column action grid."
          {:source-code (embed-source "clojure-shadcn.stories.alert-dialog.alert-dialog-small")}
          []
          (r/as-element (helpers/wrap-component [alert-dialog-small/example])))

(defstory AlertDialogRtl
          "Right-to-left alert dialog with localized action order and media-aware alignment."
          {:source-code (embed-source "clojure-shadcn.stories.alert-dialog.alert-dialog-rtl")}
          []
          (r/as-element (helpers/wrap-component [alert-dialog-rtl/example])))
