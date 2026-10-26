(ns clojure-shadcn.stories.message-stories
  "Storybook stories for the Message component. Ported from mateuszmazurczak.portfolio.ui-components.message."
  (:require
   [clojure-shadcn.stories.helpers                           :as helpers]
   [clojure-shadcn.stories.message.message-actions           :as message-actions]
   [clojure-shadcn.stories.message.message-avatar-fallback   :as message-avatar-fallback]
   [clojure-shadcn.stories.message.message-grouped-metadata  :as message-grouped-metadata]
   [clojure-shadcn.stories.message.message-markdown          :as message-markdown]
   [clojure-shadcn.stories.message.message-rich-markdown     :as message-rich-markdown]
   [clojure-shadcn.stories.message.message-stateful-feedback :as message-stateful-feedback]
   [clojure-shadcn.stories.message.message-user-vs-assistant :as message-user-vs-assistant]
   [clojure-shadcn.stories.message.message-with-attachment   :as message-with-attachment]
   [clojure-shadcn.stories.message.playground                :as playground]
   [reagent.core                                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Message"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Message component for chat interfaces."
                        :npm-install "No external dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.message")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/message.cljs"
                        :filename "message.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "message-group / message"
       :link {:href "https://ui.shadcn.com/docs/components/message"
              :label "shadcn/ui Message"}
       :description
       "Canonical group and message-row primitives. Message controls sender alignment; both merge :class and forward DOM props."
       :props [{:name ":align (message)"
                :type ":start | :end"
                :default ":start"
                :description "Places avatar/content at the start or end."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to the underlying div."}]}]
     [helpers/api-component-card
      {:component-name "message-header / message-footer"
       :description
       "Canonical metadata rows. Footer follows message alignment; both merge :class and forward DOM props."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to the underlying div."}]}]
     [helpers/api-component-card
      {:component-name "message-avatar"
       :description "Avatar renderer for message sender identity."
       :props [{:name ":src"
                :type "string"
                :default nil
                :description "Avatar image URL."}
               {:name ":alt"
                :type "string"
                :default nil
                :description "Avatar alt text."}
               {:name ":fallback"
                :type "string"
                :default nil
                :description "Fallback initials/text."}
               {:name ":delay-ms"
                :type "number"
                :default nil
                :description "Delay before fallback appears."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card {:component-name "message-content"
                                  :description
                                  "Message body container with optional markdown rendering mode."
                                  :props [{:name ":markdown?"
                                           :type "boolean"
                                           :default "false"
                                           :description "Render children/content as markdown."}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to content container."}]}]
     [helpers/api-component-card
      {:component-name "message-actions"
       :description "Action row container for per-message controls (copy/delete/etc.)."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to action row container."}]}]
     [helpers/api-component-card
      {:component-name "message-action"
       :description "Tooltip-wrapped message action slot."
       :props [{:name ":tooltip"
                :type "string | hiccup"
                :default nil
                :description "Tooltip content."}
               {:name ":side"
                :type "keyword"
                :default ":top"
                :description ":top | :right | :bottom | :left."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to tooltip wrapper."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "Compose message-avatar + message-content consistently for predictable row alignment."]
       [:li "Use message-content {:markdown? true} only for trusted/escaped content paths."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[message {:class \"items-start gap-3\"}\n [message-avatar {:src \"https://placehold.co/40x40\" :alt \"Assistant\" :fallback \"AI\"}]\n [:div {:class \"space-y-2\"}\n  [message-content {:markdown? true} \"**Hello** from the assistant\"]\n  [message-actions {}\n   [message-action {:tooltip \"Copy\"} [button {:size :icon} [:> Copy]]]]]]"]]]]])))

(defstory Playground
          "Interactive message playground."
          {:source-code (embed-source "clojure-shadcn.stories.message.playground")
           :args {:align "start"
                  :content "Hello! This is a basic message."
                  :fallback "JD"}
           :arg-types {:align {:name "Align"
                               :control {:type "select"}
                               :options ["start" "end"]}
                       :content {:name "Content"
                                 :control {:type "text"}}
                       :fallback {:name "Fallback text"
                                  :control {:type "text"}}}
           :parameters {:playground
                        {:description
                         "Explore message align, content and fallback text with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class" "src" "alt" "on-click"]}}
           :decode-args (fn [{:keys [align]
                              :as args}]
                          (cond-> args
                            align (update :align keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory MessageAvatarFallback
          "Message showing avatar fallback.
  Useful when image URLs fail or are missing."
          {:source-code (embed-source "clojure-shadcn.stories.message.message-avatar-fallback")}
          []
          (r/as-element (helpers/wrap-component [message-avatar-fallback/example])))

(defstory
 MessageMarkdown
 "Message with markdown rendering.
  Uses the Markdown component internally.

  Useful for rich assistant responses."
 {:source-code (embed-source "clojure-shadcn.stories.message.message-markdown")}
 []
 (r/as-element (helpers/wrap-component [message-markdown/example])))

(defstory
 MessageActions
 "Message with action buttons.
  Actions are wrapped with tooltips.

  Use for copy, delete, or feedback actions."
 {:source-code (embed-source "clojure-shadcn.stories.message.message-actions")}
 []
 (r/as-element (helpers/wrap-component [message-actions/example])))

(defstory
 MessageUserVsAssistant
 "User vs assistant message styling.
  Use classes to align and style different roles.

  Helpful for chat UIs with role-based presentation."
 {:source-code (embed-source "clojure-shadcn.stories.message.message-user-vs-assistant")}
 []
 (r/as-element (helpers/wrap-component [message-user-vs-assistant/example])))

(defstory
 MessageGroupedMetadata
 "Adjacent messages share a group while headers and footers retain per-message metadata and actions."
 {:source-code (embed-source "clojure-shadcn.stories.message.message-grouped-metadata")}
 []
 (r/as-element (helpers/wrap-component [message-grouped-metadata/example])))

(defstory MessageWithAttachment
          "Compose Attachment inside message-content without a message-specific attachment prop."
          {:source-code (embed-source "clojure-shadcn.stories.message.message-with-attachment")}
          []
          (r/as-element (helpers/wrap-component [message-with-attachment/example])))

(defstory MessageRichMarkdown
          "A richer assistant response with headings, links, code, and a task list."
          {:source-code (embed-source "clojure-shadcn.stories.message.message-rich-markdown")}
          []
          (r/as-element (helpers/wrap-component [message-rich-markdown/example])))

(defstory MessageStatefulFeedback
          "Feedback actions keep selected state in the consumer and remain accessible buttons."
          {:source-code (embed-source "clojure-shadcn.stories.message.message-stateful-feedback")}
          []
          (r/as-element (helpers/wrap-component [message-stateful-feedback/example])))
