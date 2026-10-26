(ns clojure-shadcn.stories.sidebar-stories
  "Storybook stories for the Sidebar component. Ported from mateuszmazurczak.portfolio.ui-components.sidebar."
  (:require
   [clojure-shadcn.stories.helpers                          :as helpers]
   [clojure-shadcn.stories.sidebar.application-layout       :as example-application-layout]
   [clojure-shadcn.stories.sidebar.playground               :as example-playground]
   [clojure-shadcn.stories.sidebar.provider-adapter         :as example-provider-adapter]
   [clojure-shadcn.stories.sidebar.provider-controlled      :as example-provider-controlled]
   [clojure-shadcn.stories.sidebar.provider-mobile-rtl      :as example-provider-mobile-rtl]
   [clojure-shadcn.stories.sidebar.sidebar-controlled       :as example-sidebar-controlled]
   [clojure-shadcn.stories.sidebar.sidebar-demo             :as example-sidebar-demo]
   [clojure-shadcn.stories.sidebar.sidebar-menu             :as example-sidebar-menu]
   [clojure-shadcn.stories.sidebar.sidebar-menu-collapsible :as example-sidebar-menu-collapsible]
   [clojure-shadcn.stories.sidebar.sidebar-menu-sub         :as example-sidebar-menu-sub]
   [clojure-shadcn.stories.sidebar.sidebar-mobile           :as example-sidebar-mobile]
   [reagent.core                                            :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Sidebar"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Pure presentational sidebar components."
                        :npm-install "npm install @radix-ui/react-slot lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.sidebar")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/sidebar.cljs"
                        :filename "sidebar.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "sidebar-provider / use-sidebar"
       :description "Optional context state adapter; presentational components remain explicit"
       :props [{:name ":open? / :default-open?"
                :type "boolean"
                :default "controlled / true"
                :description "Desktop controlled value or uncontrolled initial value"}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Desktop state callback"}
               {:name ":open-mobile? / :default-open-mobile?"
                :type "boolean"
                :default "controlled / false"
                :description "Independent mobile controlled value or initial value"}
               {:name ":on-open-mobile-change"
                :type "function"
                :default nil
                :description "Mobile state callback"}
               {:name ":is-mobile"
                :type "boolean"
                :default "matchMedia"
                :description "Explicit responsive mode; omit for max-width 767px detection"}
               {:name ":shortcut"
                :type "string | nil"
                :default "\"b\""
                :description "Ctrl/Cmd shortcut key; nil disables"}
               {:name ":persist-cookie?"
                :type "boolean"
                :default "false"
                :description "Opt-in desktop open-state cookie persistence"}
               {:name ":cookie-name / :cookie-max-age"
                :type "string / seconds"
                :default "sidebar_state / 604800"
                :description "Cookie configuration"}]}]
     [helpers/api-component-card
      {:component-name "sidebar"
       :description "Sidebar component"
       :props [{:name ":open?"
                :type "boolean"
                :default nil
                :description "Expanded/open state"}
               {:name ":is-mobile"
                :type "boolean"
                :default nil
                :description "Render mobile variant"}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Callback (fn [open?])"}
               {:name ":side"
                :type "string"
                :default "\"left\""
                :description "One of: 'left' | 'right'"}
               {:name ":variant"
                :type "string"
                :default "\"sidebar\""
                :description "One of: 'sidebar' | 'floating' | 'inset'"}
               {:name ":collapsible"
                :type "string"
                :default "\"offcanvas\""
                :description "One of: 'offcanvas' | 'icon' | 'none'"}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name ":style"
                :type "map"
                :default nil
                :description "Inline style map"}]}]
     [helpers/api-component-card {:component-name "sidebar-trigger"
                                  :description "Sidebar trigger component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}
                                          {:name ":on-click"
                                           :type "function"
                                           :default nil
                                           :description "Click handler (fn [event])"}]}]
     [helpers/api-component-card {:component-name "sidebar-rail"
                                  :description "Sidebar rail component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}
                                          {:name ":on-click"
                                           :type "function"
                                           :default nil
                                           :description "Click handler (fn [event])"}]}]
     [helpers/api-component-card {:component-name "sidebar-inset"
                                  :description "Sidebar inset component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-input"
                                  :description "Sidebar input component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-header"
                                  :description "Sidebar header component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-footer"
                                  :description "Sidebar footer component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-separator"
                                  :description "Sidebar separator component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-content"
                                  :description "Sidebar content component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-group"
                                  :description "Sidebar group component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-group-label"
                                  :description "Sidebar group label component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}
                                          {:name ":as-child"
                                           :type "boolean"
                                           :default "false"
                                           :description "Render via Radix Slot"}]}]
     [helpers/api-component-card {:component-name "sidebar-group-action"
                                  :description "Sidebar group action component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}
                                          {:name ":as-child"
                                           :type "boolean"
                                           :default "false"
                                           :description "Render via Radix Slot"}]}]
     [helpers/api-component-card {:component-name "sidebar-group-content"
                                  :description "Sidebar group content component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-menu"
                                  :description "Sidebar menu component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-menu-item"
                                  :description "Sidebar menu item component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card
      {:component-name "sidebar-menu-button"
       :description "Sidebar menu button component"
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name ":as-child"
                :type "boolean"
                :default "false"
                :description "Render via Radix Slot"}
               {:name ":is-active?"
                :type "boolean"
                :default nil
                :description "Active state"}
               {:name ":tooltip"
                :type "string | hiccup"
                :default nil
                :description "Tooltip content"}
               {:name ":variant"
                :type "keyword"
                :default ":default"
                :description "One of: :default | :outline"}
               {:name ":size"
                :type "keyword"
                :default ":default"
                :description "One of: :default | :sm | :lg"}
               {:name ":collapsed?"
                :type "boolean"
                :default nil
                :description "Whether sidebar is collapsed"}
               {:name ":is-mobile"
                :type "boolean"
                :default nil
                :description "Render mobile variant"}]}]
     [helpers/api-component-card {:component-name "sidebar-menu-action"
                                  :description "Sidebar menu action component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}
                                          {:name ":as-child"
                                           :type "boolean"
                                           :default "false"
                                           :description "Render via Radix Slot"}
                                          {:name ":show-on-hover?"
                                           :type "boolean"
                                           :default "false"
                                           :description "Show action on hover"}]}]
     [helpers/api-component-card {:component-name "sidebar-menu-badge"
                                  :description "Sidebar menu badge component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-menu-skeleton"
                                  :description "Sidebar menu skeleton component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}
                                          {:name ":show-icon?"
                                           :type "boolean"
                                           :default "false"
                                           :description "Show icon skeleton"}]}]
     [helpers/api-component-card {:component-name "sidebar-menu-sub"
                                  :description "Sidebar menu sub component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "sidebar-menu-sub-item"
                                  :description "Sidebar menu sub item component"
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card
      {:component-name "sidebar-menu-sub-button"
       :description "Sidebar menu sub button component"
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name ":as-child"
                :type "boolean"
                :default "false"
                :description "Render via Radix Slot"}
               {:name ":size"
                :type "keyword"
                :default ":md"
                :description "One of: :sm | :md"}
               {:name ":is-active?"
                :type "boolean"
                :default nil
                :description "Active state"}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "The original components remain explicit-state presentation functions. They never discover or mutate provider state implicitly."]
       [:li
        "sidebar-provider is an optional controlled/uncontrolled adapter. A :f> React/Reagent consumer calls use-sidebar, then explicitly passes its values and callbacks to sidebar, trigger, rail, and tooltips."]
       [:li
        "The context exposes desktop and mobile open state independently. Omit :is-mobile for matchMedia detection; Ctrl/Cmd+B toggles the active viewport state by default."]
       [:li
        "Cookie persistence is off by default and requires :persist-cookie? true. All sidebar DOM parts expose current data-slot metadata while retaining data-sidebar selectors for compatibility."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"flex min-h-svh\"}\n [sidebar {:open? open? :is-mobile mobile? :on-open-change set-open! :variant \"inset\"}\n  [sidebar-header {} ...]\n  [sidebar-content {} ...]\n  [sidebar-rail {:on-click toggle!}]]\n [sidebar-inset {}\n  [sidebar-trigger {:on-click toggle!}]\n  [:section {:class \"p-4\"} \"Content\"]]]"]]]]])))



(defstory
 ProviderAdapter
 "Uncontrolled provider adapter with keyboard toggle and automatic responsive matchMedia coordination. Cookie persistence remains disabled."
 {:source-code (embed-source "clojure-shadcn.stories.sidebar.provider-adapter")}
 []
 (r/as-element (helpers/wrap-component [example-provider-adapter/example])))

(defstory ProviderControlled
          "Controlled provider: the owner receives changes and remains the source of truth."
          {:source-code (embed-source "clojure-shadcn.stories.sidebar.provider-controlled")}
          []
          (r/as-element (helpers/wrap-component [example-provider-controlled/example])))

(defstory ProviderMobileRtl
          "Forced mobile provider with independent open-mobile state and RTL/right-side rendering."
          {:source-code (embed-source "clojure-shadcn.stories.sidebar.provider-mobile-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-provider-mobile-rtl/example])))


(defstory
 ApplicationLayout
 "Application-scale, providerless controlled layout covering inset, trigger, rail, groups, actions, Slot links, collapse tooltips, skeletons, header/footer, and an explicit mobile-sheet preview."
 {:source-code (embed-source "clojure-shadcn.stories.sidebar.application-layout")}
 []
 (r/as-element (helpers/wrap-component [example-application-layout/example])))

(defstory
 SidebarDemo
 "Basic sidebar layout with header, menu, and footer.

  Custom component with composable slots.

  Use sidebar-content and sidebar-menu to structure navigation."
 {:source-code (embed-source "clojure-shadcn.stories.sidebar.sidebar-demo")}
 []
 (r/as-element (helpers/wrap-component [example-sidebar-demo/example])))

(defstory
 SidebarMenu
 "Menu items with actions and badges.

  Custom component with optional menu actions.

  Use menu-action and menu-badge for per-item utilities."
 {:source-code (embed-source "clojure-shadcn.stories.sidebar.sidebar-menu")}
 []
 (r/as-element (helpers/wrap-component [example-sidebar-menu/example])))

(defstory
 SidebarMenuSub
 "Sidebar submenu with nested links.

  Custom component with nested menu-sub elements.

  Use menu-sub for secondary navigation."
 {:source-code (embed-source "clojure-shadcn.stories.sidebar.sidebar-menu-sub")}
 []
 (r/as-element (helpers/wrap-component [example-sidebar-menu-sub/example])))

(defstory
 SidebarMenuCollapsible
 "Collapsible sidebar (icon mode).

  Custom component supports :collapsible 'icon'.

  When collapsed, icons remain visible while labels hide."
 {:source-code (embed-source "clojure-shadcn.stories.sidebar.sidebar-menu-collapsible")}
 []
 (r/as-element (helpers/wrap-component [example-sidebar-menu-collapsible/example])))

(defstory
 SidebarControlled
 "Controlled open/close state.

  Custom component expects open? and callbacks from the consumer.

  Use controlled state for responsive layouts."
 {:source-code (embed-source "clojure-shadcn.stories.sidebar.sidebar-controlled")}
 []
 (r/as-element (helpers/wrap-component [example-sidebar-controlled/example])))

(defstory
 SidebarMobile
 "Mobile sidebar rendered as a sheet.

  Custom component toggles to a sheet when :is-mobile is true.

  Use for off-canvas mobile navigation."
 {:source-code (embed-source "clojure-shadcn.stories.sidebar.sidebar-mobile")}
 []
 (r/as-element (helpers/wrap-component [example-sidebar-mobile/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:variant "sidebar"}
           :arg-types {:variant {:name "Variant"
                                 :control {:type "select"}
                                 :options ["sidebar" "floating" "inset"]}}
           :parameters {:playground {:description "Explore sidebar variant with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "open?" "on-open-change" "is-mobile"]}}
           :source-code (embed-source "clojure-shadcn.stories.sidebar.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))
