(ns clojure-shadcn.stories.badge-stories
  "Storybook stories for the Badge component. Ported from mateuszmazurczak.portfolio.ui-components.badge."
  (:require
   ["lucide-react"                         :refer [BadgeCheck Bookmark]]
   [clojure-shadcn.stories.helpers       :as helpers]
   [clojure-shadcn.ui.components.badge   :as sut]
   [clojure-shadcn.ui.components.spinner :as spinner]
   [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source]])

  (:require-macros [clojure-shadcn.stories.macros :refer [embed-body]]))

(def ^:export default
  #js {:title      "Components/Badge"
       :parameters #js {:layout "padded"}})

(defn ^:export Installation
  "Install dependencies and copy the component code into your project."
  []
  (r/as-element
  [helpers/installation-scene
             {:description "Badge component with support for multiple variants."
              :npm-install "npm install @radix-ui/react-slot"
              :source-code (embed-source "clojure-shadcn.ui.components.badge")
              :namespace-path "src/cljs/clojure_shadcn/ui/components/badge.cljs"
              :filename "badge.cljs"}]))

(defn ^:export ApiReference
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body ApiReference) :filename "badge_stories.cljs"}
    [:div {:class "p-6 max-w-4xl"}
     [:div {:class "space-y-4"}
       [helpers/api-component-card
        {:component-name "badge"
         :description "Compact status/metadata pill with variant styling and optional slot polymorphism."
         :props
         [{:name ":variant"      :type "keyword"      :default ":default" :description "One of: :default | :secondary | :destructive | :outline | :ghost | :link"}
          {:name ":class"        :type "string"       :default nil        :description "Additional Tailwind classes"}
          {:name ":as-child"     :type "boolean"      :default "false"    :description "Use Radix Slot polymorphism"}
          {:name ":on-click"     :type "fn"           :default nil        :description "Click handler"}
          {:name ":...dom-props" :type "map entries"  :default nil        :description "Forwarded to the rendered element"}]}]
       [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
        [:h4 {:class "text-sm font-semibold mb-2"} "⚠️ Important Notes"]
        [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
         [:li "Use semantic variants (:destructive, :secondary) for meaning—not only color differences."]
         [:li "For link-like badges, either use :variant :link or :as-child true with an anchor."]]]
       [:div {:class "border rounded-lg p-4 bg-muted/50"}
        [:h4 {:class "text-sm font-semibold mb-2"}
         "Usage Example"]
        [:pre {:class "text-xs overflow-x-auto"}
         [:code "[badge {:variant :outline} \"Outline\"]"]]]]])))

(defn ^:export BadgeDemo
  "Badge variants and numeric indicators.

  Radix primitive: @radix-ui/react-slot (for :as-child polymorphism)

  Use badges for statuses, labels, and small counters."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body BadgeDemo) :filename "badge_stories.cljs"}
    [:div {:class "p-6 space-y-3"}
     [:div {:class "flex flex-wrap gap-2"}
      [sut/badge {}
       "Badge"]
      [sut/badge {:variant :secondary}
       "Secondary"]
      [sut/badge {:variant :destructive}
       "Destructive"]
      [sut/badge {:variant :outline}
       "Outline"]
      [sut/badge {:variant :ghost}
       "Ghost"]
      [sut/badge {:variant :link}
       [:a {:href "#"}
        "Link"]]]
     [:div {:class "flex flex-wrap gap-2"}
      [sut/badge {:variant :secondary
                  :class "bg-blue-500 text-white"}
       [:> BadgeCheck {:data-icon "inline-start"}]
       "Verified"]
      [sut/badge {:variant :outline}
       "Bookmark"
       [:> Bookmark {:data-icon "inline-end"}]]
      [sut/badge {:class "h-5 min-w-5 rounded-full px-1 font-mono tabular-nums"}
       "8"]
      [sut/badge {:variant :destructive
                  :class "h-5 min-w-5 rounded-full px-1 font-mono tabular-nums"}
       "99"]
      [sut/badge {:variant :outline
                  :class "h-5 min-w-5 rounded-full px-1 font-mono tabular-nums"}
       "20+"]]])))

(defn ^:export BadgeOutline
  "Outlined badge for neutral tags.

  Radix primitive: @radix-ui/react-slot

  Outline badges work well for metadata or filters."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body BadgeOutline) :filename "badge_stories.cljs"} [:div {:class "p-6"}
                                       [sut/badge {:variant :outline}
                                        "Outline"]])))

(defn ^:export BadgeSecondary
  "Secondary badge for low-emphasis labels.

  Radix primitive: @radix-ui/react-slot

  Use :secondary for de-emphasized categories."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body BadgeSecondary) :filename "badge_stories.cljs"} [:div {:class "p-6"}
                                       [sut/badge {:variant :secondary}
                                        "Secondary"]])))

(defn ^:export BadgeDestructive
  "Destructive badge for error states.

  Radix primitive: @radix-ui/react-slot

  Use :destructive for failed or blocked statuses."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body BadgeDestructive) :filename "badge_stories.cljs"} [:div {:class "p-6"}
                                       [sut/badge {:variant :destructive}
                                        "Destructive"]])))

(defn ^:export BadgeGhost
  "Ghost badge with no background.

  Radix primitive: @radix-ui/react-slot

  Use :ghost for minimal emphasis badges."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body BadgeGhost) :filename "badge_stories.cljs"} [:div {:class "p-6"}
                                       [sut/badge {:variant :ghost}
                                        "Ghost"]])))

(defn ^:export BadgeLink
  "Link-styled badge with underline on hover.

  Radix primitive: @radix-ui/react-slot

  Use :link for clickable text-style badges."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body BadgeLink) :filename "badge_stories.cljs"} [:div {:class "p-6"}
                                       [sut/badge {:variant :link}
                                        [:a {:href "#"}
                                         "Link"]]])))

(defn ^:export SpinnerBadge
  "Badges paired with inline spinners.

  Radix primitive: @radix-ui/react-slot

  Combine spinners with badges to show background activity."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body SpinnerBadge) :filename "badge_stories.cljs"} [:div {:class "p-6 flex flex-wrap items-center gap-4"}
                                       [sut/badge {}
                                        [spinner/spinner {:class "size-4"
                                                          :data-icon "inline-start"}]
                                        "Syncing"]
                                       [sut/badge {:variant :secondary}
                                        [spinner/spinner {:class "size-4"
                                                          :data-icon "inline-start"}]
                                        "Updating"]
                                       [sut/badge {:variant :outline}
                                        "Processing"
                                        [spinner/spinner {:class "size-4"
                                                          :data-icon "inline-end"}]]])))

(defn ^:export BadgeAsChild
  "Badge rendered as a link via :as-child.

  Radix primitive: @radix-ui/react-slot

  Our wrapper supports :as-child to render anchors or buttons with badge styles."
  []
  (r/as-element
  (helpers/wrap-component {:source (embed-body BadgeAsChild) :filename "badge_stories.cljs"} [:div {:class "p-6"}
                                       [sut/badge {:as-child true}
                                        [:a {:href "#"
                                             :class "inline-flex items-center gap-1"}
                                         "View status"]]])))
