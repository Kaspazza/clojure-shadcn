(ns clojure-shadcn.stories.context-menu-stories
  (:require
   [clojure-shadcn.stories.context-menu.context-menu-choices          :as context-menu-choices]
   [clojure-shadcn.stories.context-menu.context-menu-demo             :as context-menu-demo]
   [clojure-shadcn.stories.context-menu.context-menu-groups-and-icons :as
                                                                      context-menu-groups-and-icons]
   [clojure-shadcn.stories.context-menu.context-menu-rtl              :as context-menu-rtl]
   [clojure-shadcn.stories.context-menu.playground                    :as playground]
   [clojure-shadcn.stories.helpers                                    :as helpers]
   [reagent.core                                                      :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Context Menu"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Right-click menu with items, submenus, checkbox, and radio choices."
                        :npm-install "npm install @radix-ui/react-context-menu lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.context_menu")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/context_menu.cljs"
                        :filename "context_menu.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "context-menu / context-menu-trigger / context-menu-content"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/context-menu"
             :label "Radix Context Menu Docs"}
      :description
      "Radix Root, right-click Trigger, and automatically portaled Content. Radix manages focus, keyboard navigation, collision positioning, and menu ARIA. Trigger supports :as-child when wrapping an existing element."
      :props [{:name "Radix primitive props"
               :type "map entries"
               :default nil
               :description
               "Normalized, class-merged, and forwarded. Content is always wrapped in a Portal."}]}]
    [helpers/api-component-card
     {:component-name "context-menu-item"
      :description "Action item with visual variants and optional inset alignment."
      :props [{:name ":variant"
               :type ":default | :destructive"
               :default ":default"
               :description "Sets data-variant styling."}
              {:name ":inset"
               :type "boolean"
               :default nil
               :description "Adds leading inset alignment."}
              {:name "Item props"
               :type "map entries"
               :default nil
               :description
               "Remaining props such as :disabled, :on-select, and :as-child are forwarded."}]}]
    [helpers/api-component-card
     {:component-name
      "context-menu-checkbox-item / context-menu-radio-group / context-menu-radio-item"
      :description
      "Selectable menu primitives with built-in check or radio indicators. Compose radio items under a radio group and manage selection using Radix controlled or uncontrolled props."
      :props [{:name ":checked / :on-checked-change"
               :type "boolean | indeterminate / function"
               :default nil
               :description "Checkbox state and callback."}
              {:name ":value / :on-value-change"
               :type "string / function"
               :default nil
               :description "Radio item value and group selection callback."}
              {:name "primitive props"
               :type "map entries"
               :default nil
               :description "Normalized and forwarded."}]}]
    [helpers/api-component-card
     {:component-name "context-menu-sub / context-menu-sub-trigger / context-menu-sub-content"
      :description
      "Nested menu composition. SubTrigger appends an aria-hidden chevron; place SubContent beside it under Sub."
      :props [{:name ":inset"
               :type "boolean"
               :default nil
               :description "SubTrigger-only alignment option."}
              {:name "primitive props"
               :type "map entries"
               :default nil
               :description "Normalized, class-merged, and forwarded."}]}]
    [helpers/api-component-card
     {:component-name
      "context-menu-group / context-menu-portal / context-menu-label / context-menu-separator / context-menu-shortcut"
      :description
      "Optional grouping, explicit portal, label, separator, and visual shortcut helpers. Shortcut text is presentational: consumers must implement the actual keyboard command."
      :props [{:name ":inset"
               :type "boolean"
               :default nil
               :description "Label-only alignment option."}
              {:name "primitive or span props"
               :type "map entries"
               :default nil
               :description "Normalized, class-merged, and forwarded."}]}]])))

(defstory ContextMenuDemo
          {:source-code (embed-source "clojure-shadcn.stories.context-menu.context-menu-demo")}
          []
          (r/as-element (helpers/wrap-component [context-menu-demo/example])))

(defstory
 ContextMenuGroupsAndIcons
 "Explicit Radix Context Menu groups with decorative action icons.\n\n  Radix primitive: @radix-ui/react-context-menu\n\n  Groups preserve menu semantics while separators communicate related action sets."
 {:source-code (embed-source "clojure-shadcn.stories.context-menu.context-menu-groups-and-icons")}
 []
 (r/as-element (helpers/wrap-component [context-menu-groups-and-icons/example])))

(defstory
 ContextMenuRtl
 "Right-to-left Radix Context Menu with grouped actions.\n\n  Radix primitive: @radix-ui/react-context-menu\n\n  Pass :dir to Root so keyboard navigation and submenu direction match the content."
 {:source-code (embed-source "clojure-shadcn.stories.context-menu.context-menu-rtl")}
 []
 (r/as-element (helpers/wrap-component [context-menu-rtl/example])))

(defstory ContextMenuChoices
          {:source-code (embed-source "clojure-shadcn.stories.context-menu.context-menu-choices")}
          []
          (r/as-element (helpers/wrap-component [context-menu-choices/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:class ""}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters
           {:playground {:description "Explore context menu css classes with a live preview."}
            :controls {:expanded false
                       :exclude ["children" "on-select" "on-value-change" "on-checked-change"]}}
           :source-code (embed-source "clojure-shadcn.stories.context-menu.playground")}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
