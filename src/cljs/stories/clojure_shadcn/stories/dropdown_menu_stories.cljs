(ns clojure-shadcn.stories.dropdown-menu-stories
  "Storybook stories for the Dropdown Menu component. Ported from mateuszmazurczak.portfolio.ui-components.dropdown_menu."
  (:require
   [clojure-shadcn.stories.dropdown-menu.dropdown-menu-avatar       :as dropdown-menu-avatar]
   [clojure-shadcn.stories.dropdown-menu.dropdown-menu-checkboxes   :as dropdown-menu-checkboxes]
   [clojure-shadcn.stories.dropdown-menu.dropdown-menu-demo         :as dropdown-menu-demo]
   [clojure-shadcn.stories.dropdown-menu.dropdown-menu-dialog       :as dropdown-menu-dialog]
   [clojure-shadcn.stories.dropdown-menu.dropdown-menu-icons        :as dropdown-menu-icons]
   [clojure-shadcn.stories.dropdown-menu.dropdown-menu-radio-group  :as dropdown-menu-radio-group]
   [clojure-shadcn.stories.dropdown-menu.dropdown-menu-rtl          :as dropdown-menu-rtl]
   [clojure-shadcn.stories.dropdown-menu.dropdown-menu-team-actions :as dropdown-menu-team-actions]
   [clojure-shadcn.stories.dropdown-menu.playground                 :as playground]
   [clojure-shadcn.stories.helpers                                  :as helpers]
   [reagent.core                                                    :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Dropdown Menu"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Dropdown menu component for displaying a menu of actions."
                        :npm-install "npm install @radix-ui/react-dropdown-menu lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.dropdown_menu")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/dropdown_menu.cljs"
                        :filename "dropdown_menu.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "dropdown-menu"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/dropdown-menu"
              :label "Radix Dropdown Menu Docs"}
       :description "Radix DropdownMenu root controlling menu open state and modality."
       :props [{:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled open state."}
               {:name ":default-open"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial open state."}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Callback: (fn [open?] ...)."}
               {:name ":modal"
                :type "boolean"
                :default "true"
                :description "Whether menu behaves modally."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix DropdownMenu.Root."}]}]
     [helpers/api-component-card {:component-name "dropdown-menu-trigger"
                                  :description "Interactive trigger for opening/closing the menu."
                                  :props [{:name ":as-child"
                                           :type "boolean"
                                           :default nil
                                           :description
                                           "Compose trigger behavior into child element."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Trigger."}]}]
     [helpers/api-component-card
      {:component-name "dropdown-menu-content"
       :description
       "Portaled menu content with keyboard navigation and collision-aware positioning."
       :props [{:name ":side-offset"
                :type "number"
                :default "4"
                :description "Distance from trigger."}
               {:name ":align"
                :type "keyword | string"
                :default nil
                :description "Content alignment relative to trigger."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Content."}]}]
     [helpers/api-component-card
      {:component-name "dropdown-menu-item"
       :description "Standard actionable menu item."
       :props [{:name ":inset"
                :type "boolean"
                :default nil
                :description "Adds inset padding for alignment."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables selection."}
               {:name ":on-select"
                :type "function"
                :default nil
                :description "Item select callback."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Item."}]}]
     [helpers/api-component-card
      {:component-name "dropdown-menu-checkbox-item"
       :description "Toggleable checkbox item for multi-select preferences."
       :props [{:name ":checked"
                :type "boolean | \"indeterminate\""
                :default nil
                :description "Checked state."}
               {:name ":on-checked-change"
                :type "function"
                :default nil
                :description "Callback when checked state changes."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables item."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix CheckboxItem."}]}]
     [helpers/api-component-card
      {:component-name "dropdown-menu-radio-group"
       :description "Container for radio items with single-selection behavior."
       :props [{:name ":value"
                :type "string"
                :default nil
                :description "Controlled selected value."}
               {:name ":on-value-change"
                :type "function"
                :default nil
                :description "Callback: (fn [value] ...)."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix RadioGroup."}]}]
     [helpers/api-component-card
      {:component-name "dropdown-menu-radio-item"
       :description "Single option within dropdown-menu-radio-group."
       :props [{:name ":value"
                :type "string"
                :default nil
                :description "Value represented by this item."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables option."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix RadioItem."}]}]
     [helpers/api-component-card {:component-name "dropdown-menu-group"
                                  :description "Groups related menu items."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Group."}]}]
     [helpers/api-component-card {:component-name "dropdown-menu-label"
                                  :description "Non-interactive section label inside menu content."
                                  :props [{:name ":inset"
                                           :type "boolean"
                                           :default nil
                                           :description "Adds inset padding."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Label."}]}]
     [helpers/api-component-card {:component-name "dropdown-menu-separator"
                                  :description "Visual divider between menu groups."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix Separator."}]}]
     [helpers/api-component-card {:component-name "dropdown-menu-shortcut"
                                  :description "Right-aligned visual shortcut hint (display only)."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to underlying span."}]}]
     [helpers/api-component-card
      {:component-name "dropdown-menu-sub"
       :description "Root for nested submenu interactions."
       :props [{:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled submenu open state."}
               {:name ":default-open"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial open state."}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Callback when submenu state changes."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}]}]
     [helpers/api-component-card
      {:component-name "dropdown-menu-sub-trigger"
       :description "Menu item that opens a nested submenu."
       :props [{:name ":inset"
                :type "boolean"
                :default nil
                :description "Adds inset padding."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables submenu trigger."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix SubTrigger."}]}]
     [helpers/api-component-card {:component-name "dropdown-menu-sub-content"
                                  :description "Nested submenu content container."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to Radix SubContent."}]}]
     [helpers/api-component-card
      {:component-name "dropdown-menu-portal"
       :description "Portal wrapper for rendering menu outside local stacking context."
       :props [{:name ":container"
                :type "DOM node"
                :default nil
                :description "Custom portal target."}
               {:name ":force-mount"
                :type "boolean"
                :default nil
                :description "Force mounting for animation control."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Portal."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Many wrappers forward additional props directly to Radix primitives; avoid undocumented keys that may conflict with Radix internals."]
       [:li "For composition with existing buttons/links, use :as-child on dropdown-menu-trigger."]
       [:li
        "dropdown-menu-shortcut is visual only; keyboard handling must be implemented separately."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[dropdown-menu {}\n [dropdown-menu-trigger {:as-child true}\n  [button {:variant :outline} \"Open\"]]\n [dropdown-menu-content {:align \"end\"}\n  [dropdown-menu-label {} \"Actions\"]\n  [dropdown-menu-item {:on-select #(js/console.log \"edit\")} \"Edit\"]\n  [dropdown-menu-separator {}]\n  [dropdown-menu-sub {}\n   [dropdown-menu-sub-trigger {} \"More\"]\n   [dropdown-menu-sub-content {}\n    [dropdown-menu-item {} \"Duplicate\"]]]]]"]]]]])))

(defstory
 DropdownMenuDemo
 "Dropdown menu with grouped items and submenu.\n\n  Radix primitive: @radix-ui/react-dropdown-menu\n\n  Use groups, separators, and submenus for structured menus."
 {:source-code (embed-source "clojure-shadcn.stories.dropdown-menu.dropdown-menu-demo")}
 []
 (r/as-element (helpers/wrap-component [dropdown-menu-demo/example])))

(defstory
 DropdownMenuIcons
 "Ordinary dropdown actions with decorative Lucide icons.\n\n  Radix primitive: @radix-ui/react-dropdown-menu\n\n  Keep icon meaning paired with visible item text."
 {:source-code (embed-source "clojure-shadcn.stories.dropdown-menu.dropdown-menu-icons")}
 []
 (r/as-element (helpers/wrap-component [dropdown-menu-icons/example])))

(defstory
 DropdownMenuAvatar
 "Avatar-style trigger for an account menu.\n\n  Radix primitive: @radix-ui/react-dropdown-menu\n\n  Give icon-only or image triggers an accessible name."
 {:source-code (embed-source "clojure-shadcn.stories.dropdown-menu.dropdown-menu-avatar")}
 []
 (r/as-element (helpers/wrap-component [dropdown-menu-avatar/example])))

(defstory
 DropdownMenuTeamActions
 "Focused composition with labels, groups, icons, shortcuts, and a submenu.\n\n  Radix primitive: @radix-ui/react-dropdown-menu\n\n  Use this structure for a dense but coherent team action menu."
 {:source-code (embed-source "clojure-shadcn.stories.dropdown-menu.dropdown-menu-team-actions")}
 []
 (r/as-element (helpers/wrap-component [dropdown-menu-team-actions/example])))

(defstory
 DropdownMenuRtl
 "Right-to-left Radix Dropdown Menu.\n\n  Radix primitive: @radix-ui/react-dropdown-menu\n\n  Set :dir on Root so keyboard and submenu direction follow the content."
 {:source-code (embed-source "clojure-shadcn.stories.dropdown-menu.dropdown-menu-rtl")}
 []
 (r/as-element (helpers/wrap-component [dropdown-menu-rtl/example])))

(defstory
 DropdownMenuCheckboxes
 "Dropdown menu with checkbox items.\n\n  Radix primitive: @radix-ui/react-dropdown-menu\n\n  Checkbox items allow toggling view preferences."
 {:source-code (embed-source "clojure-shadcn.stories.dropdown-menu.dropdown-menu-checkboxes")}
 []
 (r/as-element (helpers/wrap-component [dropdown-menu-checkboxes/example])))

(defstory
 DropdownMenuRadioGroup
 "Dropdown menu with radio group selection.\n\n  Radix primitive: @radix-ui/react-dropdown-menu\n\n  Radio groups enforce a single selection within the menu."
 {:source-code (embed-source "clojure-shadcn.stories.dropdown-menu.dropdown-menu-radio-group")}
 []
 (r/as-element (helpers/wrap-component [dropdown-menu-radio-group/example])))

(defstory
 DropdownMenuDialog
 "Dropdown menu launching dialogs.\n\n  Radix primitives: @radix-ui/react-dropdown-menu, @radix-ui/react-dialog\n\n  Useful for contextual actions that open richer modals."
 {:source-code (embed-source "clojure-shadcn.stories.dropdown-menu.dropdown-menu-dialog")}
 []
 (r/as-element (helpers/wrap-component [dropdown-menu-dialog/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:modal true}
           :arg-types {:modal {:name "Modal"
                               :control {:type "boolean"}}}
           :parameters
           {:playground {:description "Explore dropdown menu modal with a live preview."}
            :controls {:expanded false
                       :exclude ["children" "open" "default-open" "on-open-change" "on-select"]}}
           :source-code (embed-source "clojure-shadcn.stories.dropdown-menu.playground")}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
