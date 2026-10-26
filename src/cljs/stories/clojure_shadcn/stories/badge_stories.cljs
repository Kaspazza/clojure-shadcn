(ns clojure-shadcn.stories.badge-stories
  (:require
   [clojure-shadcn.stories.badge.badge-as-child    :as badge-as-child]
   [clojure-shadcn.stories.badge.badge-colors      :as badge-colors]
   [clojure-shadcn.stories.badge.badge-destructive :as badge-destructive]
   [clojure-shadcn.stories.badge.badge-ghost       :as badge-ghost]
   [clojure-shadcn.stories.badge.badge-icons       :as badge-icons]
   [clojure-shadcn.stories.badge.badge-link        :as badge-link]
   [clojure-shadcn.stories.badge.badge-outline     :as badge-outline]
   [clojure-shadcn.stories.badge.badge-rtl         :as badge-rtl]
   [clojure-shadcn.stories.badge.badge-secondary   :as badge-secondary]
   [clojure-shadcn.stories.badge.badge-variants    :as badge-variants]
   [clojure-shadcn.stories.badge.playground        :as playground]
   [clojure-shadcn.stories.badge.spinner-badge     :as spinner-badge]
   [clojure-shadcn.stories.helpers                 :as helpers]
   [reagent.core                                   :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Badge"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Badge component with support for multiple variants."
                        :npm-install "npm install @radix-ui/react-slot"
                        :source-code (embed-source "clojure-shadcn.ui.components.badge")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/badge.cljs"
                        :filename "badge.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "badge / badge-variants"
       :description
       "Compact status/metadata pill with variant styling and optional slot polymorphism."
       :props [{:name ":variant"
                :type "keyword"
                :default ":default"
                :description
                "One of: :default | :secondary | :destructive | :outline | :ghost | :link"}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name ":as-child"
                :type "boolean"
                :default "false"
                :description "Use Radix Slot polymorphism"}
               {:name ":on-click"
                :type "fn"
                :default nil
                :description "Click handler"}
               {:name ":...dom-props"
                :type "map entries"
                :default nil
                :description "Forwarded to the rendered element"}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Use semantic variants (:destructive, :secondary) for meaning—not only color differences."]
       [:li "For link-like badges, either use :variant :link or :as-child true with an anchor."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code "[badge {:variant :outline} \"Outline\"]"]]]]])))

(defstory
 Playground
 "Interactive badge playground."
 {:source-code (embed-source "clojure-shadcn.stories.badge.playground")
  :args {:variant "default"
         :label "Badge"}
  :arg-types {:variant {:name "Variant"
                        :control {:type "select"}
                        :options ["default" "secondary" "destructive" "outline" "ghost" "link"]}
              :label {:name "Label"
                      :control {:type "text"}}}
  :parameters {:playground {:description "Explore badge variant and label with a live preview."}
               :controls {:expanded false
                          :exclude ["class" "as-child" "on-click"]}}
  :decode-args (fn [{:keys [variant]
                     :as args}]
                 (cond-> args
                   variant (update :variant keyword)))}
 [args]
 (r/as-element (helpers/wrap-component [playground/example args])))

(defstory BadgeVariants
          "All badge variants displayed together for the component gallery."
          {:source-code (embed-source "clojure-shadcn.stories.badge.badge-variants")}
          []
          (r/as-element (helpers/wrap-component [badge-variants/example])))

(defstory
 BadgeOutline
 "Outlined badge for neutral tags.

  Radix primitive: @radix-ui/react-slot

  Outline badges work well for metadata or filters."
 {:source-code (embed-source "clojure-shadcn.stories.badge.badge-outline")}
 []
 (r/as-element (helpers/wrap-component [badge-outline/example])))

(defstory
 BadgeSecondary
 "Secondary badge for low-emphasis labels.

  Radix primitive: @radix-ui/react-slot

  Use :secondary for de-emphasized categories."
 {:source-code (embed-source "clojure-shadcn.stories.badge.badge-secondary")}
 []
 (r/as-element (helpers/wrap-component [badge-secondary/example])))

(defstory
 BadgeDestructive
 "Destructive badge for error states.

  Radix primitive: @radix-ui/react-slot

  Use :destructive for failed or blocked statuses."
 {:source-code (embed-source "clojure-shadcn.stories.badge.badge-destructive")}
 []
 (r/as-element (helpers/wrap-component [badge-destructive/example])))

(defstory
 BadgeGhost
 "Ghost badge with no background.

  Radix primitive: @radix-ui/react-slot

  Use :ghost for minimal emphasis badges."
 {:source-code (embed-source "clojure-shadcn.stories.badge.badge-ghost")}
 []
 (r/as-element (helpers/wrap-component [badge-ghost/example])))

(defstory
 BadgeLink
 "Link-styled badge with underline on hover.

  Radix primitive: @radix-ui/react-slot

  Use :link for clickable text-style badges."
 {:source-code (embed-source "clojure-shadcn.stories.badge.badge-link")}
 []
 (r/as-element (helpers/wrap-component [badge-link/example])))

(defstory
 SpinnerBadge
 "Badges paired with inline spinners.

  Radix primitive: @radix-ui/react-slot

  Combine spinners with badges to show background activity."
 {:source-code (embed-source "clojure-shadcn.stories.badge.spinner-badge")}
 []
 (r/as-element (helpers/wrap-component [spinner-badge/example])))

(defstory
 BadgeAsChild
 "Badge rendered as a link via :as-child.

  Radix primitive: @radix-ui/react-slot

  Our wrapper supports :as-child to render anchors or buttons with badge styles."
 {:source-code (embed-source "clojure-shadcn.stories.badge.badge-as-child")}
 []
 (r/as-element (helpers/wrap-component [badge-as-child/example])))

(defstory BadgeIcons
          "Icons — upstream leading and trailing icon placements across real badge variants."
          {:source-code (embed-source "clojure-shadcn.stories.badge.badge-icons")}
          []
          (r/as-element (helpers/wrap-component [badge-icons/example])))

(defstory
 BadgeColors
 "Colors — upstream class-based status colors; these are not additional component variants."
 {:source-code (embed-source "clojure-shadcn.stories.badge.badge-colors")}
 []
 (r/as-element (helpers/wrap-component [badge-colors/example])))

(defstory BadgeRTL
          "RTL — badges preserve logical icon placement and reading order."
          {:source-code (embed-source "clojure-shadcn.stories.badge.badge-rtl")}
          []
          (r/as-element (helpers/wrap-component [badge-rtl/example])))
