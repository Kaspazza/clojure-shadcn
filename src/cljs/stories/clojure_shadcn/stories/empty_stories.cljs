(ns clojure-shadcn.stories.empty-stories
  "Storybook stories for the Empty State component. Ported from mateuszmazurczak.portfolio.ui-components.empty."
  (:require
   [clojure-shadcn.stories.empty.empty-avatar            :as empty-avatar]
   [clojure-shadcn.stories.empty.empty-avatar-group      :as empty-avatar-group]
   [clojure-shadcn.stories.empty.empty-background        :as empty-background]
   [clojure-shadcn.stories.empty.empty-canonical-actions :as empty-canonical-actions]
   [clojure-shadcn.stories.empty.empty-card              :as empty-card]
   [clojure-shadcn.stories.empty.empty-icon              :as empty-icon]
   [clojure-shadcn.stories.empty.empty-input-group       :as empty-input-group]
   [clojure-shadcn.stories.empty.empty-outline           :as empty-outline]
   [clojure-shadcn.stories.empty.empty-rtl               :as empty-rtl]
   [clojure-shadcn.stories.empty.playground              :as playground]
   [clojure-shadcn.stories.helpers                       :as helpers]
   [reagent.core                                         :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Empty State"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "empty component."
                        :npm-install "No external dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.empty")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/empty.cljs"
                        :filename "empty.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card {:component-name "empty"
                                  :description "Root empty-state layout wrapper."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to wrapper element."}]}]
     [helpers/api-component-card {:component-name "empty-header"
                                  :description "Header section for media/title/description."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to wrapper element."}]}]
     [helpers/api-component-card {:component-name "empty-media"
                                  :description "Visual media slot for icon/avatar/illustration."
                                  :props [{:name ":variant"
                                           :type "keyword"
                                           :default ":default"
                                           :description ":default | :icon."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to media wrapper."}]}]
     [helpers/api-component-card {:component-name "empty-title"
                                  :description "Primary empty-state headline."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to title element."}]}]
     [helpers/api-component-card {:component-name "empty-description"
                                  :description "Supporting explanation text for empty state."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to description element."}]}]
     [helpers/api-component-card {:component-name "empty-content"
                                  :description "Action/content area (buttons, links, forms)."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to content wrapper."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "empty-media :variant :icon applies a bordered icon container; use :default for richer custom media."]
       [:li "Links rendered in empty-description receive built-in underline styling."]
       [:li "Compose empty-content for primary CTAs to keep visual hierarchy clear."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[empty {}\n [empty-header {}\n  [empty-media {:variant :icon} [:> Inbox]]\n  [empty-title {} \"No invoices yet\"]\n  [empty-description {} \"Create your first invoice to get started.\"]]\n [empty-content {}\n  [button {} \"Create invoice\"]]]"]]]]])))

(defstory
 Playground
 "Interactive empty-state playground."
 {:args {:media-variant "icon"
         :title "No Projects Yet"
         :description "You haven't created any projects yet."
         :action-label "Create Project"}
  :arg-types {:media-variant {:name "Media variant"
                              :control {:type "select"}
                              :options ["default" "icon"]}
              :title {:name "Title"
                      :control {:type "text"}}
              :description {:name "Description"
                            :control {:type "text"}}
              :action-label {:name "Action label"
                             :control {:type "text"}}}
  :parameters
  {:playground
   {:description
    "Explore empty media variant, title, description and action label with a live preview."}
   :controls {:expanded false
              :exclude ["class" "on-click"]}}
  :decode-args (fn [args]
                 (let [args (cond-> args
                              (and (not (contains? args :media-variant))
                                   (contains? args :mediaVariant))
                              (assoc :media-variant (:mediaVariant args))
                              (and (not (contains? args :action-label))
                                   (contains? args :actionLabel))
                              (assoc :action-label (:actionLabel args)))]
                   (cond-> (dissoc args :mediaVariant :actionLabel)
                     (some? (:media-variant args)) (update :media-variant keyword))))
  :source-code (embed-source "clojure-shadcn.stories.empty.playground")}
 [args]
 (r/as-element (helpers/wrap-component [playground/example args])))

(defstory
 EmptyIcon
 "Grid of empty states with icons.\n\n  Custom component built for empty or zero states.\n\n  Useful for showcasing multiple empty modules."
 {:source-code (embed-source "clojure-shadcn.stories.empty.empty-icon")}
 []
 (r/as-element (helpers/wrap-component [empty-icon/example])))

(defstory
 EmptyOutline
 "Outlined empty state variant.\n\n  Custom component built for empty or zero states.\n\n  Use a dashed border to emphasize the empty container."
 {:source-code (embed-source "clojure-shadcn.stories.empty.empty-outline")}
 []
 (r/as-element (helpers/wrap-component [empty-outline/example])))

(defstory
 EmptyAvatar
 "Empty state with an avatar media.\n\n  Custom component built for empty or zero states.\n\n  Use avatar media for user-centric empty states."
 {:source-code (embed-source "clojure-shadcn.stories.empty.empty-avatar")}
 []
 (r/as-element (helpers/wrap-component [empty-avatar/example])))

(defstory
 EmptyBackground
 "Empty state with muted background.\n\n  Custom component built for empty or zero states.\n\n  Gradient backgrounds help differentiate the empty section."
 {:source-code (embed-source "clojure-shadcn.stories.empty.empty-background")}
 []
 (r/as-element (helpers/wrap-component [empty-background/example])))

(defstory
 EmptyAvatarGroup
 "Empty state with avatar group.\n\n  Custom component built for empty or zero states.\n\n  Use grouped avatars for team invites or collaboration prompts."
 {:source-code (embed-source "clojure-shadcn.stories.empty.empty-avatar-group")}
 []
 (r/as-element (helpers/wrap-component [empty-avatar-group/example])))

(defstory
 EmptyCard
 "Empty state nested in a Card, keeping the outer surface responsible for border and elevation."
 {:source-code (embed-source "clojure-shadcn.stories.empty.empty-card")}
 []
 (r/as-element (helpers/wrap-component [empty-card/example])))

(defstory EmptyCanonicalActions
          "Canonical actions — upstream primary, secondary, and inline-link action arrangements."
          {:source-code (embed-source "clojure-shadcn.stories.empty.empty-canonical-actions")}
          []
          (r/as-element (helpers/wrap-component [empty-canonical-actions/example])))

(defstory EmptyInputGroup
          "Input Group — upstream empty-state recovery pattern for joining a workspace by code."
          {:source-code (embed-source "clojure-shadcn.stories.empty.empty-input-group")}
          []
          (r/as-element (helpers/wrap-component [empty-input-group/example])))

(defstory EmptyRTL
          "RTL — canonical empty state with logical action ordering."
          {:source-code (embed-source "clojure-shadcn.stories.empty.empty-rtl")}
          []
          (r/as-element (helpers/wrap-component [empty-rtl/example])))
