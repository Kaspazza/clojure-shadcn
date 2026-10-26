(ns clojure-shadcn.stories.avatar-stories
  (:require
   [clojure-shadcn.stories.avatar.avatar-badge-with-icon :as avatar-badge-with-icon]
   [clojure-shadcn.stories.avatar.avatar-custom-sizes    :as avatar-custom-sizes]
   [clojure-shadcn.stories.avatar.avatar-dropdown        :as avatar-dropdown]
   [clojure-shadcn.stories.avatar.avatar-group-rtl       :as avatar-group-rtl]
   [clojure-shadcn.stories.avatar.avatar-with-badge      :as avatar-with-badge]
   [clojure-shadcn.stories.avatar.empty-avatar           :as empty-avatar]
   [clojure-shadcn.stories.avatar.empty-avatar-group     :as empty-avatar-group]
   [clojure-shadcn.stories.avatar.playground             :as playground]
   [clojure-shadcn.stories.helpers                       :as helpers]
   [reagent.core                                         :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Avatar"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Avatar component based on Radix UI primitives."
                        :npm-install "npm install @radix-ui/react-avatar"
                        :source-code (embed-source "clojure-shadcn.ui.components.avatar")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/avatar.cljs"
                        :filename "avatar.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "avatar"
       :description "Root avatar container with size variants and optional status badge support."
       :props [{:name ":size"
                :type "keyword"
                :default nil
                :description "Size variant (:default, :sm, :lg)"}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to root container."}]}]
     [helpers/api-component-card {:component-name "avatar-image"
                                  :description "Displays the avatar image."
                                  :props [{:name ":src"
                                           :type "string"
                                           :default nil
                                           :description "Image source URL"}
                                          {:name ":alt"
                                           :type "string"
                                           :default nil
                                           :description "Alt text for accessibility"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "avatar-fallback"
                                  :description "Fallback content when image unavailable."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card
      {:component-name "avatar-group / avatar-group-count"
       :description
       "Compound layout pieces for overlapping avatars and a size-aware overflow count. They render div elements, set dedicated data slots, and forward all additional DOM props."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes merged with the group or count defaults."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to the underlying div."}]}]
     [helpers/api-component-card
      {:component-name "avatar-badge"
       :description
       "Badge indicator positioned at bottom right of avatar. Automatically sizes based on parent avatar size."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes"}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "Always provide meaningful :alt text for avatar-image when identity matters."]
       [:li "Use avatar-fallback for resilient UX in slow or failed image loading states."]
       [:li
        "avatar-badge is size-aware relative to parent avatar; avoid manual absolute positioning overrides."]
       [:li
        "Place avatar-group-count inside avatar-group so its size can follow :sm and :lg avatars via the group data selectors."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[avatar {:size :lg}\n"
        "  [avatar-image {:src \"https://github.com/user.png\"\n"
        "                 :alt \"@user\"}]\n"
        "  [avatar-fallback {}\n"
        "   \"UN\"]\n"
        "  [avatar-badge {:class \"bg-green-600\"}]]"]]]]])))

(defstory Playground
          "Interactive avatar playground."
          {:source-code (embed-source "clojure-shadcn.stories.avatar.playground")
           :args {:size "default"
                  :alt "Shadcn"
                  :fallback "CN"}
           :arg-types {:size {:name "Size"
                              :control {:type "select"}
                              :options ["sm" "default" "lg"]}
                       :alt {:name "Alternative text"
                             :control {:type "text"}}
                       :fallback {:name "Fallback text"
                                  :control {:type "text"}}}
           :parameters
           {:playground
            {:description
             "Explore avatar size, alternative text and fallback text with a live preview."}
            :controls {:expanded false
                       :exclude ["class" "src"]}}
           :decode-args (fn [{:keys [size]
                              :as args}]
                          (cond-> args
                            size (update :size keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory
 EmptyAvatar
 "Avatar-only empty state.

  Radix primitive: @radix-ui/react-avatar

  Use a grayscale avatar as the empty media."
 {:source-code (embed-source "clojure-shadcn.stories.avatar.empty-avatar")}
 []
 (r/as-element (helpers/wrap-component [empty-avatar/example])))

(defstory
 EmptyAvatarGroup
 "Compound avatar group with a size-aware overflow count.

  Based on shadcn/ui Avatar Group — current new-york-v4.

  Use the count when more members exist than the interface can display."
 {:source-code (embed-source "clojure-shadcn.stories.avatar.empty-avatar-group")}
 []
 (r/as-element (helpers/wrap-component [empty-avatar-group/example])))

(defstory
 AvatarGroupRtl
 "Avatar group in a right-to-left container. The DOM order and overlap remain observable independently of the localized count label."
 {:source-code (embed-source "clojure-shadcn.stories.avatar.avatar-group-rtl")}
 []
 (r/as-element (helpers/wrap-component [avatar-group-rtl/example])))

(defstory
 AvatarCustomSizes
 "Avatar size variants.

  Radix primitive: @radix-ui/react-avatar

  Use the :size prop for consistent sizing."
 {:source-code (embed-source "clojure-shadcn.stories.avatar.avatar-custom-sizes")}
 []
 (r/as-element (helpers/wrap-component [avatar-custom-sizes/example])))

(defstory
 AvatarWithBadge
 "Avatar with status badge.

  Radix primitive: @radix-ui/react-avatar

  Use badge to indicate online/offline status or other states."
 {:source-code (embed-source "clojure-shadcn.stories.avatar.avatar-with-badge")}
 []
 (r/as-element (helpers/wrap-component [avatar-with-badge/example])))

(defstory
 AvatarBadgeWithIcon
 "Avatar with badge containing an icon.

  Radix primitive: @radix-ui/react-avatar

  Use icon inside badge for actions or enhanced status indicators."
 {:source-code (embed-source "clojure-shadcn.stories.avatar.avatar-badge-with-icon")}
 []
 (r/as-element (helpers/wrap-component [avatar-badge-with-icon/example])))

(defstory
 AvatarDropdown
 "Dropdown — the upstream account-menu composition uses a single avatar trigger; no avatar-group API is implied."
 {:source-code (embed-source "clojure-shadcn.stories.avatar.avatar-dropdown")}
 []
 (r/as-element (helpers/wrap-component [avatar-dropdown/example])))
