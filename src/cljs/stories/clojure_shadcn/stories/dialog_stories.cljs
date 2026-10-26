(ns clojure-shadcn.stories.dialog-stories
  "Storybook stories for the Dialog component. Ported from mateuszmazurczak.portfolio.ui-components.dialog."
  (:require
   [clojure-shadcn.stories.dialog.command-dialog              :as command-dialog]
   [clojure-shadcn.stories.dialog.dialog-close-button         :as dialog-close-button]
   [clojure-shadcn.stories.dialog.dialog-demo                 :as dialog-demo]
   [clojure-shadcn.stories.dialog.dialog-rtl                  :as dialog-rtl]
   [clojure-shadcn.stories.dialog.dialog-scrollable-body      :as dialog-scrollable-body]
   [clojure-shadcn.stories.dialog.dialog-without-corner-close :as dialog-without-corner-close]
   [clojure-shadcn.stories.dialog.playground                  :as playground]
   [clojure-shadcn.stories.dialog.responsive-dialog-drawer    :as responsive-dialog-drawer]
   [clojure-shadcn.stories.helpers                            :as helpers]
   [reagent.core                                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Dialog"
       :parameters #js {:layout "padded"}})



(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Dialog (modal) component with overlay and content area."
                        :npm-install "npm install @radix-ui/react-dialog lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.dialog")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/dialog.cljs"
                        :filename "dialog.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "dialog"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/dialog"
              :label "Radix Dialog Docs"}
       :description
       "Radix Dialog root that controls modal open/close state and accessibility semantics."
       :props [{:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled open state."}
               {:name ":defaultOpen"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial open state."}
               {:name ":onOpenChange"
                :type "function"
                :default nil
                :description "Callback when state changes: (fn [open?] ...)."}
               {:name ":modal"
                :type "boolean"
                :default "true"
                :description "Whether dialog behaves as modal."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Dialog.Root."}]}]
     [helpers/api-component-card {:component-name "dialog-trigger"
                                  :description "Interactive trigger that opens the dialog."
                                  :props [{:name ":asChild"
                                           :type "boolean"
                                           :default nil
                                           :description "Compose with child component."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Dialog.Trigger."}]}]
     [helpers/api-component-card {:component-name "dialog-portal"
                                  :description
                                  "Portal wrapper to render dialog outside normal DOM hierarchy."
                                  :props [{:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Dialog.Portal."}]}]
     [helpers/api-component-card {:component-name "dialog-close"
                                  :description "Control that closes the dialog when activated."
                                  :props [{:name ":asChild"
                                           :type "boolean"
                                           :default nil
                                           :description "Compose with child component."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Dialog.Close."}]}]
     [helpers/api-component-card {:component-name "dialog-overlay"
                                  :description "Backdrop layer behind dialog content."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Dialog.Overlay."}]}]
     [helpers/api-component-card
      {:component-name "dialog-content"
       :description "Main dialog panel with built-in overlay + optional corner close button."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name ":showCloseButton"
                :type "boolean"
                :default "true"
                :description "Show top-right close icon button."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Dialog.Content."}]}]
     [helpers/api-component-card {:component-name "dialog-header"
                                  :description
                                  "Layout wrapper for dialog-title and dialog-description."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to underlying div."}]}]
     [helpers/api-component-card
      {:component-name "dialog-footer"
       :description "Responsive action area (stacked on mobile, row-aligned on desktop)."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to underlying div."}]}]
     [helpers/api-component-card {:component-name "dialog-title"
                                  :description
                                  "Accessible title announced by screen readers (aria-labelledby)."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Dialog.Title."}]}]
     [helpers/api-component-card
      {:component-name "dialog-description"
       :description "Accessible supporting text announced by screen readers (aria-describedby)."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Dialog.Description."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "Use :asChild (camelCase) for trigger/close when wrapping existing button components."]
       [:li "For accessible dialogs, always include dialog-title and usually dialog-description."]
       [:li "Choose controlled mode (:open + :onOpenChange) OR uncontrolled mode (:defaultOpen)."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[dialog {:open @open?\n         :onOpenChange #(reset! open? %)}\n [dialog-trigger {:asChild true}\n  [button {:variant :outline} \"Open\"]]\n [dialog-content {:class \"sm:max-w-[425px]\"}\n  [dialog-header {}\n   [dialog-title {} \"Edit profile\"]\n   [dialog-description {} \"Update details and save.\"]]\n  [dialog-footer {}\n   [dialog-close {:asChild true} [button {:variant :outline} \"Cancel\"]]\n   [button {:type \"submit\"} \"Save\"]]]]"]]]]])))

(defstory
 DialogDemo
 "Dialog with form fields and footer actions.\n\n  Radix primitive: @radix-ui/react-dialog\n\n  Use dialogs for focused, interruptive workflows such as profile edits."
 {:source-code (embed-source "clojure-shadcn.stories.dialog.dialog-demo")}
 []
 (r/as-element (helpers/wrap-component [dialog-demo/example])))

(defstory
 DialogCloseButton
 "Dialog with explicit close button in footer.\n\n  Radix primitive: @radix-ui/react-dialog\n\n  Useful for share sheets or info dialogs."
 {:source-code (embed-source "clojure-shadcn.stories.dialog.dialog-close-button")}
 []
 (r/as-element (helpers/wrap-component [dialog-close-button/example])))

(defstory
 CommandDialog
 "Command palette rendered inside a dialog.\n\n  Radix primitive: @radix-ui/react-dialog\n\n  Props are idiomatic kebab-case; Reagent converts to camelCase for Radix.\n  Use this for global search or quick actions."
 {:source-code (embed-source "clojure-shadcn.stories.dialog.command-dialog")}
 []
 (r/as-element (helpers/wrap-component [command-dialog/example])))

(defstory
 ResponsiveDialogDrawer
 "One workflow rendered as a dialog on desktop and a bottom drawer on narrow screens.\n\n  Radix primitive: @radix-ui/react-dialog; Vaul primitive: Drawer\n\n  The local media-query adapter keeps one controlled open state while swapping presentation."
 {:source-code (embed-source "clojure-shadcn.stories.dialog.responsive-dialog-drawer")}
 []
 (r/as-element (helpers/wrap-component [responsive-dialog-drawer/example])))

(defstory
 DialogWithoutCornerClose
 "Dialog without the generated corner close control.\n\n  Keep an explicit, clearly named close action in the footer so the modal remains easy to dismiss."
 {:source-code (embed-source "clojure-shadcn.stories.dialog.dialog-without-corner-close")}
 []
 (r/as-element (helpers/wrap-component [dialog-without-corner-close/example])))

(defstory DialogScrollableBody
          "Long dialog with an independently scrollable body and persistent header/footer actions."
          {:source-code (embed-source "clojure-shadcn.stories.dialog.dialog-scrollable-body")}
          []
          (r/as-element (helpers/wrap-component [dialog-scrollable-body/example])))

(defstory DialogRtl
          "Dialog content and actions in a right-to-left document direction."
          {:source-code (embed-source "clojure-shadcn.stories.dialog.dialog-rtl")}
          []
          (r/as-element (helpers/wrap-component [dialog-rtl/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:modal true}
           :arg-types {:modal {:name "Modal"
                               :control {:type "boolean"}}}
           :parameters {:playground {:description "Explore dialog modal with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "open" "default-open" "on-open-change"]}}
           :source-code (embed-source "clojure-shadcn.stories.dialog.playground")}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
