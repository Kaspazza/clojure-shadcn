(ns clojure-shadcn.stories.empty-stories
  "Storybook stories for the Empty State component. Ported from mateuszmazurczak.portfolio.ui-components.empty."
  (:require
   ["lucide-react"                      :refer
                                        [ArrowUpRight Bell Bookmark Heart Inbox Plus RefreshCcw]]
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.avatar :as avatar]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.empty  :as sut]
   [reagent.core                        :as r])
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

(defstory EmptyDemo
          "Interactive empty-state playground."
          {:args {:media-variant "icon"
                  :title "No Projects Yet"
                  :description "You haven't created any projects yet."
                  :action-label "Create Project"}
           :arg-types {:media-variant {:control {:type "select"}
                                       :options ["default" "icon"]}
                       :title {:control {:type "text"}}
                       :description {:control {:type "text"}}
                       :action-label {:control {:type "text"}}}
           :parameters {:controls {:exclude ["class" "on-click"]}}
           :decode-args (fn [{:keys [mediaVariant actionLabel]
                              :as args}]
                          (cond-> (-> args
                                      (assoc :media-variant mediaVariant
                                             :action-label actionLabel)
                                      (dissoc :mediaVariant :actionLabel))
                            mediaVariant (update :media-variant keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/empty {}
                                                  [sut/empty-header {}
                                                   [sut/empty-media {:variant (:media-variant args)}
                                                    [:> Inbox]]
                                                   [sut/empty-title {}
                                                    (:title args)]
                                                   [sut/empty-description {}
                                                    (:description args)]]
                                                  [sut/empty-content {}
                                                   [button/button {}
                                                    (:action-label args)]]]])))

(defstory
 EmptyIcon
 "Grid of empty states with icons.

  Custom component built for empty or zero states.

  Useful for showcasing multiple empty modules."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 grid gap-8 md:grid-cols-2"}
    [sut/empty {}
     [sut/empty-header {}
      [sut/empty-media {:variant :icon}
       [:> Inbox]]
      [sut/empty-title {}
       "No messages"]
      [sut/empty-description {}
       "Your inbox is empty. New messages will appear here."]]]
    [sut/empty {}
     [sut/empty-header {}
      [sut/empty-media {:variant :icon}
       [:> Heart]]
      [sut/empty-title {}
       "No likes yet"]
      [sut/empty-description {}
       "Content you like will be saved here for easy access."]]]
    [sut/empty {}
     [sut/empty-header {}
      [sut/empty-media {:variant :icon}
       [:> Bookmark]]
      [sut/empty-title {}
       "No bookmarks"]
      [sut/empty-description {}
       "Save interesting content by bookmarking it."]]]
    [sut/empty {}
     [sut/empty-header {}
      [sut/empty-media {:variant :icon}
       [:> Bell]]
      [sut/empty-title {}
       "No notifications"]
      [sut/empty-description {}
       "You're all caught up. New notifications will appear here."]]]])))

(defstory
 EmptyOutline
 "Outlined empty state variant.

  Custom component built for empty or zero states.

  Use a dashed border to emphasize the empty container."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6"}
                 [sut/empty {:class "border border-dashed"}
                  [sut/empty-header {}
                   [sut/empty-media {:variant :icon}
                    [:> Bell]]
                   [sut/empty-title {}
                    "Cloud Storage Empty"]
                   [sut/empty-description {}
                    "Upload files to your cloud storage to access them anywhere."]]
                  [sut/empty-content {}
                   (button/button {:variant :outline
                                   :size :sm}
                                  "Upload Files")]]])))

(defstory
 EmptyAvatar
 "Empty state with an avatar media.

  Custom component built for empty or zero states.

  Use avatar media for user-centric empty states."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6"}
                 [sut/empty {}
                  [sut/empty-header {}
                   [sut/empty-media {:variant :default}
                    [avatar/avatar {:size :lg}
                     [avatar/avatar-image {:src "https://github.com/shadcn.png"
                                           :class "grayscale"}]
                     [avatar/avatar-fallback {}
                      "LR"]]]
                   [sut/empty-title {}
                    "User Offline"]
                   [sut/empty-description {}
                    "This user is currently offline. You can leave a message or try again later."]]
                  [sut/empty-content {}
                   (button/button {:size :sm} "Leave Message")]]])))

(defstory
 EmptyBackground
 "Empty state with muted background.

  Custom component built for empty or zero states.

  Gradient backgrounds help differentiate the empty section."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6"}
                 [sut/empty {:class "from-muted/50 to-background bg-gradient-to-b from-30%"}
                  [sut/empty-header {}
                   [sut/empty-media {:variant :icon}
                    [:> Bell]]
                   [sut/empty-title {}
                    "No Notifications"]
                   [sut/empty-description {}
                    "You're all caught up. New notifications will appear here."]]
                  [sut/empty-content {}
                   (button/button {:variant :outline
                                   :size :sm}
                                  [:> RefreshCcw]
                                  "Refresh")]]])))

(defstory
 EmptyAvatarGroup
 "Empty state with avatar group.

  Custom component built for empty or zero states.

  Use grouped avatars for team invites or collaboration prompts."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6"}
    [sut/empty {}
     [sut/empty-header {}
      [sut/empty-media {}
       [:div {:class "flex -space-x-2"}
        [avatar/avatar {:size :lg
                        :class "ring-2 ring-background grayscale"}
         [avatar/avatar-image {:src "https://github.com/shadcn.png"
                               :alt "@shadcn"}]
         [avatar/avatar-fallback {}
          "CN"]]
        [avatar/avatar {:size :lg
                        :class "ring-2 ring-background grayscale"}
         [avatar/avatar-image {:src "https://github.com/maxleiter.png"
                               :alt "@maxleiter"}]
         [avatar/avatar-fallback {}
          "LR"]]
        [avatar/avatar {:size :lg
                        :class "ring-2 ring-background grayscale"}
         [avatar/avatar-image {:src "https://github.com/evilrabbit.png"
                               :alt "@evilrabbit"}]
         [avatar/avatar-fallback {}
          "ER"]]]]
      [sut/empty-title {}
       "No Team Members"]
      [sut/empty-description {}
       "Invite your team to collaborate on this project."]]
     [sut/empty-content {}
      (button/button {:size :sm} [:> Plus] "Invite Members")]]])))
