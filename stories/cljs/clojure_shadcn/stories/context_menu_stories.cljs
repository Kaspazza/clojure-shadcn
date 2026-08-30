(ns clojure-shadcn.stories.context-menu-stories
  (:require [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.context-menu :as sut]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default #js {:title "Components/Context Menu" :parameters #js {:layout "padded"}})
(defdoc Installation []
  (r/as-element [helpers/installation-scene {:description "Right-click menu with items, submenus, checkbox, and radio choices." :npm-install "npm install @radix-ui/react-context-menu lucide-react" :source-code (embed-source "clojure-shadcn.ui.components.context_menu") :namespace-path "src/cljs/clojure_shadcn/ui/components/context_menu.cljs" :filename "context_menu.cljs"}]))

(defstory ApiReference []
  (r/as-element (helpers/wrap-component
    [:div {:class "space-y-4 p-6 max-w-4xl"}
     [helpers/api-component-card {:component-name "context-menu / context-menu-trigger / context-menu-content" :link {:href "https://www.radix-ui.com/primitives/docs/components/context-menu" :label "Radix Context Menu Docs"} :description "Radix Root, right-click Trigger, and automatically portaled Content. Radix manages focus, keyboard navigation, collision positioning, and menu ARIA. Trigger supports :as-child when wrapping an existing element." :props [{:name "Radix primitive props" :type "map entries" :default nil :description "Normalized, class-merged, and forwarded. Content is always wrapped in a Portal."}]}]
     [helpers/api-component-card {:component-name "context-menu-item" :description "Action item with visual variants and optional inset alignment." :props [{:name ":variant" :type ":default | :destructive" :default ":default" :description "Sets data-variant styling."} {:name ":inset" :type "boolean" :default nil :description "Adds leading inset alignment."} {:name "Item props" :type "map entries" :default nil :description "Remaining props such as :disabled, :on-select, and :as-child are forwarded."}]}]
     [helpers/api-component-card {:component-name "context-menu-checkbox-item / context-menu-radio-group / context-menu-radio-item" :description "Selectable menu primitives with built-in check or radio indicators. Compose radio items under a radio group and manage selection using Radix controlled or uncontrolled props." :props [{:name ":checked / :on-checked-change" :type "boolean | indeterminate / function" :default nil :description "Checkbox state and callback."} {:name ":value / :on-value-change" :type "string / function" :default nil :description "Radio item value and group selection callback."} {:name "primitive props" :type "map entries" :default nil :description "Normalized and forwarded."}]}]
     [helpers/api-component-card {:component-name "context-menu-sub / context-menu-sub-trigger / context-menu-sub-content" :description "Nested menu composition. SubTrigger appends an aria-hidden chevron; place SubContent beside it under Sub." :props [{:name ":inset" :type "boolean" :default nil :description "SubTrigger-only alignment option."} {:name "primitive props" :type "map entries" :default nil :description "Normalized, class-merged, and forwarded."}]}]
     [helpers/api-component-card {:component-name "context-menu-group / context-menu-portal / context-menu-label / context-menu-separator / context-menu-shortcut" :description "Optional grouping, explicit portal, label, separator, and visual shortcut helpers. Shortcut text is presentational: consumers must implement the actual keyboard command." :props [{:name ":inset" :type "boolean" :default nil :description "Label-only alignment option."} {:name "primitive or span props" :type "map entries" :default nil :description "Normalized, class-merged, and forwarded."}]}]])))

(defstory ContextMenuDemo []
  (r/as-element (helpers/wrap-component
    [sut/context-menu {}
     [sut/context-menu-trigger {:class "flex h-40 w-72 items-center justify-center rounded-md border border-dashed text-sm"} "Right click here"]
     [sut/context-menu-content {:class "w-56"}
      [sut/context-menu-item {} "Back" [sut/context-menu-shortcut {} "⌘["]]
      [sut/context-menu-item {:disabled true} "Forward"]
      [sut/context-menu-sub {}
       [sut/context-menu-sub-trigger {} "Share"]
       [sut/context-menu-sub-content {:class "w-44"} [sut/context-menu-item {} "Email"] [sut/context-menu-item {} "Messages"]]]
      [sut/context-menu-separator {}]
      [sut/context-menu-item {:variant :destructive} "Delete"]]])))

(defstory ContextMenuChoices []
  (r/as-element [(fn [] (let [checked? (r/atom true) position (r/atom "bottom")]
    (fn [] (helpers/wrap-component
      [sut/context-menu {}
       [sut/context-menu-trigger {:class "flex h-32 w-72 items-center justify-center rounded-md border"} "Right click for preferences"]
       [sut/context-menu-content {:class "w-56"}
        [sut/context-menu-checkbox-item {:checked @checked? :on-checked-change #(reset! checked? %)} "Show toolbar"]
        [sut/context-menu-separator {}]
        [sut/context-menu-label {} "Panel position"]
        [sut/context-menu-radio-group {:value @position :on-value-change #(reset! position %)}
         [sut/context-menu-radio-item {:value "top"} "Top"]
         [sut/context-menu-radio-item {:value "bottom"} "Bottom"]]]]))))]))
