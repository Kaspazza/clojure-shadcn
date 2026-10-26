(ns clojure-shadcn.stories.breadcrumb-stories
  (:require
   [clojure-shadcn.stories.breadcrumb.breadcrumb-custom-link :as breadcrumb-custom-link]
   [clojure-shadcn.stories.breadcrumb.breadcrumb-demo        :as breadcrumb-demo]
   [clojure-shadcn.stories.breadcrumb.breadcrumb-dropdown    :as breadcrumb-dropdown]
   [clojure-shadcn.stories.breadcrumb.breadcrumb-ellipsis    :as breadcrumb-ellipsis]
   [clojure-shadcn.stories.breadcrumb.breadcrumb-responsive  :as breadcrumb-responsive]
   [clojure-shadcn.stories.breadcrumb.breadcrumb-rtl         :as breadcrumb-rtl]
   [clojure-shadcn.stories.breadcrumb.breadcrumb-separator   :as breadcrumb-separator]
   [clojure-shadcn.stories.breadcrumb.breadcrumb-simple      :as breadcrumb-simple]
   [clojure-shadcn.stories.breadcrumb.playground             :as playground]
   [clojure-shadcn.stories.helpers                           :as helpers]
   [reagent.core                                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Breadcrumb"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Breadcrumb navigation component for displaying hierarchical page location."
                        :npm-install "npm install @radix-ui/react-slot lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.breadcrumb")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/breadcrumb.cljs"
                        :filename "breadcrumb.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card {:component-name "breadcrumb"
                                  :description
                                  "Root nav wrapper for hierarchical page location trail."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "breadcrumb-list"
                                  :description "Ordered list container for breadcrumb items."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "breadcrumb-item"
                                  :description "List item wrapper for a single crumb segment."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "breadcrumb-link"
                                  :description "Navigable breadcrumb segment."
                                  :props [{:name ":href"
                                           :type "string"
                                           :default nil
                                           :description "Destination URL."}
                                          {:name ":as-child"
                                           :type "boolean"
                                           :default "false"
                                           :description "Render via Radix Slot."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card
      {:component-name "breadcrumb-page"
       :description "Current-page segment (non-clickable) with aria-current semantics."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card
      {:component-name "breadcrumb-separator"
       :description
       "Visual separator between segments; renders ChevronRight by default, or custom children when provided."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "children"
                :type nil
                :default nil
                :description "Custom separator content; overrides default icon."}]}]
     [helpers/api-component-card
      {:component-name "breadcrumb-ellipsis"
       :description "Collapsed-path indicator used when intermediate crumbs are hidden."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "breadcrumb-page sets aria-current=page and should represent the final/current segment."]
       [:li "Use children in breadcrumb-separator to override the default ChevronRight icon."]
       [:li
        "For long paths, pair breadcrumb-ellipsis with dropdown/drawer to reveal hidden ancestors."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[breadcrumb {}\n [breadcrumb-list {}\n  [breadcrumb-item {} [breadcrumb-link {:href \"/\"} \"Home\"]]\n  [breadcrumb-separator {}]\n  [breadcrumb-item {} [breadcrumb-page {} \"Settings\"]]]]"]]]]])))

(defstory
 BreadcrumbDemo
 "Breadcrumb with ellipsis dropdown in the middle.

  Radix primitives: @radix-ui/react-dropdown-menu, @radix-ui/react-separator

  Useful for long navigation paths."
 {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.breadcrumb-demo")}
 []
 (r/as-element (helpers/wrap-component [breadcrumb-demo/example])))

(defstory
 BreadcrumbCustomLink
 "Breadcrumb link composed into a consumer-owned anchor.

  Radix primitive: @radix-ui/react-slot

  Use :as-child when a router link or custom anchor must retain its own props."
 {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.breadcrumb-custom-link")}
 []
 (r/as-element (helpers/wrap-component [breadcrumb-custom-link/example])))

(defstory
 BreadcrumbRtl
 "Right-to-left breadcrumb with a custom directional separator.

  Pattern: WAI-ARIA Breadcrumb

  Set direction on the navigation region and choose a separator that follows reading order."
 {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.breadcrumb-rtl")}
 []
 (r/as-element (helpers/wrap-component [breadcrumb-rtl/example])))

(defstory
 BreadcrumbSimple
 "Simple breadcrumb link example.

  Radix primitive: @radix-ui/react-separator

  Use breadcrumb-link for navigable segments and breadcrumb-page for current."
 {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.breadcrumb-simple")}
 []
 (r/as-element (helpers/wrap-component [breadcrumb-simple/example])))

(defstory
 BreadcrumbEllipsis
 "Collapsed breadcrumb using ellipsis.

  Radix primitives: @radix-ui/react-separator

  Use the ellipsis when intermediate items are hidden."
 {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.breadcrumb-ellipsis")}
 []
 (r/as-element (helpers/wrap-component [breadcrumb-ellipsis/example])))

(defstory
 BreadcrumbSeparator
 "Breadcrumb with custom separator icon.

  Radix primitives: @radix-ui/react-separator

  Custom separators can be inserted per segment."
 {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.breadcrumb-separator")}
 []
 (r/as-element (helpers/wrap-component [breadcrumb-separator/example])))

(defstory
 BreadcrumbDropdown
 "Breadcrumb with dropdown menu item.

  Radix primitives: @radix-ui/react-dropdown-menu, @radix-ui/react-separator

  Dropdowns can replace intermediate links."
 {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.breadcrumb-dropdown")}
 []
 (r/as-element (helpers/wrap-component [breadcrumb-dropdown/example])))

(defstory
 BreadcrumbResponsive
 "Responsive breadcrumb using dropdown or drawer.

  Radix primitives: @radix-ui/react-dropdown-menu, @radix-ui/react-dialog

  This example shows both desktop (dropdown) and mobile (drawer) patterns."
 {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.breadcrumb-responsive")}
 []
 (r/as-element (helpers/wrap-component [breadcrumb-responsive/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:source-code (embed-source "clojure-shadcn.stories.breadcrumb.playground")
           :args {:class ""}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description
                                     "Explore breadcrumb css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
