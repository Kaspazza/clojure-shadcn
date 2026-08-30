(ns clojure-shadcn.stories.avatar-stories
  "Storybook stories for the Avatar component. Ported from mateuszmazurczak.portfolio.ui-components.avatar."
  (:require
   ["lucide-react"                      :refer [Plus]]
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.avatar :as sut]
   [reagent.core                        :as r])
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
        "avatar-badge is size-aware relative to parent avatar; avoid manual absolute positioning overrides."]]]
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

(defstory AvatarDemo
          "Interactive avatar playground."
          {:args {:size "default"
                  :alt "Shadcn"
                  :fallback "CN"}
           :arg-types {:size {:control {:type "select"}
                              :options ["sm" "default" "lg"]}
                       :alt {:control {:type "text"}}
                       :fallback {:control {:type "text"}}}
           :parameters {:controls {:exclude ["class" "src"]}}
           :decode-args (fn [{:keys [size]
                              :as args}]
                          (cond-> args
                            size (update :size keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/avatar
                                                  (select-keys args [:size])
                                                  [sut/avatar-image {:src
                                                                     "https://github.com/shadcn.png"
                                                                     :alt (:alt args)}]
                                                  [sut/avatar-fallback {}
                                                   (:fallback args)]]])))

(defstory
 EmptyAvatar
 "Avatar-only empty state.

  Radix primitive: @radix-ui/react-avatar

  Use a grayscale avatar as the empty media."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/avatar {:size :lg}
                                         [sut/avatar-image {:src "https://github.com/shadcn.png"
                                                            :alt "@shadcn"
                                                            :class "grayscale"}]
                                         [sut/avatar-fallback {}
                                          "LR"]]])))

(defstory
 EmptyAvatarGroup
 "Stacked avatar group for empty states.

  Radix primitive: @radix-ui/react-avatar

  Use stacked avatars to represent teams or groups."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 flex -space-x-2"}
    [sut/avatar {:size :lg
                 :class "ring-2 ring-background grayscale"}
     [sut/avatar-image {:src "https://github.com/shadcn.png"
                        :alt "@shadcn"}]
     [sut/avatar-fallback {}
      "CN"]]
    [sut/avatar {:size :lg
                 :class "ring-2 ring-background grayscale"}
     [sut/avatar-image {:src "https://github.com/maxleiter.png"
                        :alt "@maxleiter"}]
     [sut/avatar-fallback {}
      "LR"]]
    [sut/avatar {:size :lg
                 :class "ring-2 ring-background grayscale"}
     [sut/avatar-image {:src "https://github.com/evilrabbit.png"
                        :alt "@evilrabbit"}]
     [sut/avatar-fallback {}
      "ER"]]])))

(defstory
 AvatarCustomSizes
 "Avatar size variants.

  Radix primitive: @radix-ui/react-avatar

  Use the :size prop for consistent sizing."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 flex items-center gap-4"}
                                        [sut/avatar {:size :sm}
                                         [sut/avatar-fallback {}
                                          "SM"]]
                                        [sut/avatar {:size :default}
                                         [sut/avatar-fallback {}
                                          "MD"]]
                                        [sut/avatar {:size :lg}
                                         [sut/avatar-fallback {}
                                          "LG"]]])))

(defstory
 AvatarWithBadge
 "Avatar with status badge.

  Radix primitive: @radix-ui/react-avatar

  Use badge to indicate online/offline status or other states."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/avatar {}
                                         [sut/avatar-image {:src "https://github.com/shadcn.png"
                                                            :alt "@shadcn"}]
                                         [sut/avatar-fallback {}
                                          "CN"]
                                         [sut/avatar-badge {:class
                                                            "bg-green-600 dark:bg-green-800"}]]])))

(defstory
 AvatarBadgeWithIcon
 "Avatar with badge containing an icon.

  Radix primitive: @radix-ui/react-avatar

  Use icon inside badge for actions or enhanced status indicators."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/avatar {:class "grayscale"}
                                         [sut/avatar-image {:src "https://github.com/pranathip.png"
                                                            :alt "@pranathip"}]
                                         [sut/avatar-fallback {}
                                          "PP"]
                                         [sut/avatar-badge {}
                                          [:> Plus]]]])))
