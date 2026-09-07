(ns clojure-shadcn.stories.dialog-stories
  "Storybook stories for the Dialog component. Ported from mateuszmazurczak.portfolio.ui-components.dialog."
  (:require
   ["lucide-react"                       :refer [Calendar CreditCard Settings Smile User]]
   [clojure-shadcn.stories.helpers       :as helpers]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.command :as command]
   [clojure-shadcn.ui.components.dialog  :as sut]
   [clojure-shadcn.ui.components.drawer  :as drawer]
   [clojure-shadcn.ui.components.input   :as input]
   [clojure-shadcn.ui.components.label   :as label]
   [reagent.core                         :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Dialog"
       :parameters #js {:layout "padded"}})

(defn- profile-form
  [{:keys [class]}]
  [:div {:class (str "grid gap-4 " class)}
   [:div {:class "grid gap-2"}
    [label/label {:html-for "dialog-name"}
     "Name"]
    [input/input {:id "dialog-name"
                  :default-value "Pedro Duarte"}]]
   [:div {:class "grid gap-2"}
    [label/label {:html-for "dialog-username"}
     "Username"]
    [input/input {:id "dialog-username"
                  :default-value "@peduarte"}]]])

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
 "Dialog with form fields and footer actions.

  Radix primitive: @radix-ui/react-dialog

  Use dialogs for focused, interruptive workflows such as profile edits."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6"}
                 [sut/dialog {}
                  [sut/dialog-trigger {:as-child true}
                   (button/button {:variant :outline} "Open Dialog")]
                  [sut/dialog-content {:class "sm:max-w-[425px]"}
                   [sut/dialog-header {}
                    [sut/dialog-title {}
                     "Edit profile"]
                    [sut/dialog-description {}
                     "Make changes to your profile here. Click save when you're done."]]
                   [profile-form {}]
                   [sut/dialog-footer {}
                    [sut/dialog-close {:as-child true}
                     (button/button {:variant :outline} "Cancel")]
                    (button/button {:type "submit"} "Save changes")]]]])))

(defstory
 DialogCloseButton
 "Dialog with explicit close button in footer.

  Radix primitive: @radix-ui/react-dialog

  Useful for share sheets or info dialogs."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6"}
    [sut/dialog {}
     [sut/dialog-trigger {:as-child true}
      (button/button {:variant :outline} "Share")]
     [sut/dialog-content {:class "sm:max-w-md"}
      [sut/dialog-header {}
       [sut/dialog-title {}
        "Share link"]
       [sut/dialog-description {}
        "Anyone with this link will be able to view this."]]
      [:div {:class "flex items-center gap-2"}
       [:div {:class "grid flex-1 gap-2"}
        [label/label {:html-for "share-link"
                      :class "sr-only"}
         "Link"]
        [input/input {:id "share-link"
                      :read-only true
                      :default-value "https://ui.shadcn.com/docs/installation"}]]]
      [sut/dialog-footer {:class "sm:justify-start"}
       [sut/dialog-close {:as-child true}
        (button/button {:type "button"
                        :variant :secondary}
                       "Close")]]]]])))

(defstory
 CommandDialog
 "Command palette rendered inside a dialog.

  Radix primitive: @radix-ui/react-dialog

  Props are idiomatic kebab-case; Reagent converts to camelCase for Radix.
  Use this for global search or quick actions."
 []
 (r/as-element
  [(fn []
     (let [open? (r/atom false)]
       (fn []
         (helpers/wrap-component
          [:div {:class "p-6 space-y-3"}
           [:p {:class "text-muted-foreground text-sm"}
            "Press the button to open the command palette."]
           [sut/dialog {:open @open?
                        :on-open-change #(reset! open? %)}
            [sut/dialog-trigger {:as-child true}
             (button/button {:variant :outline} "Open Command Palette")]
            [sut/dialog-content {:class "p-0 overflow-hidden"}
             [command/command {:class "[&_[data-slot=command-input-wrapper]]:h-12"}
              [command/command-input {:placeholder "Type a command or search..."}]
              [command/command-list {}
               [command/command-empty {}
                "No results found."]
               [command/command-group {:heading "Suggestions"}
                [command/command-item {}
                 [:> Calendar]
                 [:span "Calendar"]]
                [command/command-item {}
                 [:> Smile]
                 [:span "Search Emoji"]]
                [command/command-item {:disabled true}
                 [:> CreditCard]
                 [:span "Calculator"]]]
               [command/command-separator {}]
               [command/command-group {:heading "Settings"}
                [command/command-item {}
                 [:> User]
                 [:span "Profile"]]
                [command/command-item {}
                 [:> Settings]
                 [:span "Settings"]]]]]]]]))))]))

(defstory
 ResponsiveDialogDrawer
 "One workflow rendered as a dialog on desktop and a bottom drawer on narrow screens.

  Radix primitive: @radix-ui/react-dialog; Vaul primitive: Drawer

  The local media-query adapter keeps one controlled open state while swapping presentation."
 []
 (r/as-element
  [(fn []
     (let [open? (r/atom false)
           desktop? (r/atom false)
           media-query (atom nil)
           update-layout! #(reset! desktop? (.-matches %))]
       (r/create-class
        {:display-name "responsive-dialog-drawer-story"
         :component-did-mount (fn []
                                (let [query (js/window.matchMedia "(min-width: 768px)")]
                                  (reset! media-query query)
                                  (reset! desktop? (.-matches query))
                                  (.addEventListener query "change" update-layout!)))
         :component-will-unmount (fn []
                                   (when-let [query @media-query]
                                     (.removeEventListener query "change" update-layout!)))
         :reagent-render (fn []
                           (helpers/wrap-component
                            [:div {:class "p-6"}
                             (if @desktop?
                               [sut/dialog {:open @open?
                                            :on-open-change #(reset! open? %)}
                                [sut/dialog-trigger {:as-child true}
                                 (button/button {:variant :outline} "Edit profile")]
                                [sut/dialog-content {:class "sm:max-w-[425px]"}
                                 [sut/dialog-header {}
                                  [sut/dialog-title {}
                                   "Edit profile"]
                                  [sut/dialog-description {}
                                   "Make changes to your profile and save when finished."]]
                                 [profile-form {}]
                                 [sut/dialog-footer {}
                                  [sut/dialog-close {:as-child true}
                                   (button/button {:variant :outline} "Cancel")]
                                  (button/button {:on-click #(reset! open? false)}
                                                 "Save changes")]]]
                               [drawer/drawer {:open @open?
                                               :on-open-change #(reset! open? %)
                                               :direction :bottom}
                                [drawer/drawer-trigger {:as-child true}
                                 (button/button {:variant :outline} "Edit profile")]
                                [drawer/drawer-content {}
                                 [drawer/drawer-header {:class "text-left"}
                                  [drawer/drawer-title {}
                                   "Edit profile"]
                                  [drawer/drawer-description {}
                                   "Make changes to your profile and save when finished."]]
                                 [profile-form {:class "px-4"}]
                                 [drawer/drawer-footer {}
                                  (button/button {:on-click #(reset! open? false)} "Save changes")
                                  [drawer/drawer-close {:as-child true}
                                   (button/button {:variant :outline} "Cancel")]]]])]))})))]))

(defstory
 DialogWithoutCornerClose
 "Dialog without the generated corner close control.

  Keep an explicit, clearly named close action in the footer so the modal remains easy to dismiss."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6"}
                 [sut/dialog {}
                  [sut/dialog-trigger {:as-child true}
                   (button/button {:variant :outline} "Review invitation")]
                  [sut/dialog-content {:show-close-button false}
                   [sut/dialog-header {}
                    [sut/dialog-title {}
                     "Team invitation"]
                    [sut/dialog-description {}
                     "Review the invitation before returning to your workspace."]]
                   [sut/dialog-footer {}
                    [sut/dialog-close {:as-child true}
                     (button/button {:variant :outline} "Close invitation")]]]]])))

(defstory DialogScrollableBody
          "Long dialog with an independently scrollable body and persistent header/footer actions."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6"}
             [sut/dialog {}
              [sut/dialog-trigger {:as-child true}
               (button/button {:variant :outline} "Review changelog")]
              [sut/dialog-content
               {:class "max-h-[80vh] grid-rows-[auto_minmax(0,1fr)_auto] overflow-hidden p-0"}
               [sut/dialog-header {:class "border-b p-6"}
                [sut/dialog-title {}
                 "Release changelog"]
                [sut/dialog-description {}
                 "Review all changes before publishing."]]
               [:div {:class "overflow-y-auto px-6"}
                (for [section (range 1 16)]
                  ^{:key section}
                  [:p {:class "mb-4 text-sm text-muted-foreground"}
                   (str "Change " section " improves reliability, accessibility, and feedback.")])]
               [sut/dialog-footer {:class "border-t bg-background p-6"}
                [sut/dialog-close {:as-child true}
                 (button/button {:variant :outline} "Cancel review")]
                (button/button {} "Publish release")]]]])))

(defstory DialogRtl
          "Dialog content and actions in a right-to-left document direction."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6"
                   :dir "rtl"}
             [sut/dialog {}
              [sut/dialog-trigger {:as-child true}
               (button/button {:variant :outline} "فتح مربع الحوار")]
              [sut/dialog-content {:class "text-right"
                                   :dir "rtl"
                                   :close-label "إغلاق"}
               [sut/dialog-header {:class "text-right sm:text-right"}
                [sut/dialog-title {}
                 "تعديل الملف الشخصي"]
                [sut/dialog-description {}
                 "حدّث معلوماتك ثم احفظ التغييرات."]]
               [sut/dialog-footer {:class "sm:justify-start"}
                [sut/dialog-close {:as-child true}
                 (button/button {:variant :outline} "إلغاء")]
                (button/button {} "حفظ التغييرات")]]]])))


(defstory DialogPlayground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:modal true}
           :arg-types {:modal {:control {:type "boolean"}}}
           :parameters {:controls {:exclude ["children" "open" "default-open" "on-open-change"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/dialog
                                                  (select-keys args [:modal])
                                                  [sut/dialog-trigger {:as-child true}
                                                   [button/button {:variant :outline}
                                                    "Open dialog"]]
                                                  [sut/dialog-content {}
                                                   [sut/dialog-header {}
                                                    [sut/dialog-title {}
                                                     "Edit profile"]
                                                    [sut/dialog-description {}
                                                     "Make changes to your profile here."]]]]])))
