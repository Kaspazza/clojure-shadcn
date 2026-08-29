(ns clojure-shadcn.stories.message-stories
  "Storybook stories for the Message component. Ported from mateuszmazurczak.portfolio.ui-components.message."
  (:require
   ["lucide-react"                         :refer [Copy Trash2]]
   [clojure-shadcn.stories.helpers       :as helpers]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.message :as sut]
   [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title      "Chat/Message"
       :parameters #js {:layout "padded"}})

(defn ^:export Installation
  "Install dependencies and copy the component code into your project."
  []
  (r/as-element
  [helpers/installation-scene
             {:description "Message component for chat interfaces."
              :npm-install "No external dependencies"
              :source-code (embed-source "clojure-shadcn.ui.components.message")
              :namespace-path "src/cljs/clojure_shadcn/ui/components/message.cljs"
              :filename "message.cljs"}]))

(defstory ApiReference
  []
  (r/as-element
  (helpers/wrap-component
    [:div {:class "p-6 max-w-4xl"}
     [:div {:class "space-y-4"}
      [helpers/api-component-card
       {:component-name "message-group / message"
        :link {:href "https://ui.shadcn.com/docs/components/message" :label "shadcn/ui Message"}
        :description "Canonical group and message-row primitives. Message controls sender alignment; both merge :class and forward DOM props."
        :props [{:name ":align (message)" :type ":start | :end" :default ":start" :description "Places avatar/content at the start or end."}
                {:name ":class" :type "string" :default nil :description "Additional Tailwind classes."}
                {:name "additional props" :type "map entries" :default nil :description "Forwarded to the underlying div."}]}]
      [helpers/api-component-card
       {:component-name "message-header / message-footer"
        :description "Canonical metadata rows. Footer follows message alignment; both merge :class and forward DOM props."
        :props [{:name ":class" :type "string" :default nil :description "Additional Tailwind classes."}
                {:name "additional props" :type "map entries" :default nil :description "Forwarded to the underlying div."}]}]
       [helpers/api-component-card
        {:component-name "message-avatar"
         :description "Avatar renderer for message sender identity."
         :props [{:name ":src"      :type "string" :default nil :description "Avatar image URL."}
                 {:name ":alt"      :type "string" :default nil :description "Avatar alt text."}
                 {:name ":fallback" :type "string" :default nil :description "Fallback initials/text."}
                 {:name ":delay-ms" :type "number" :default nil :description "Delay before fallback appears."}
                 {:name ":class"    :type "string" :default nil :description "Additional Tailwind classes."}]}]
       [helpers/api-component-card
        {:component-name "message-content"
         :description "Message body container with optional markdown rendering mode."
         :props [{:name ":markdown?"      :type "boolean"      :default "false" :description "Render children/content as markdown."}
                 {:name ":class"          :type "string"       :default nil     :description "Additional Tailwind classes."}
                 {:name "additional props" :type "map entries" :default nil     :description "Forwarded to content container."}]}]
       [helpers/api-component-card
        {:component-name "message-actions"
         :description "Action row container for per-message controls (copy/delete/etc.)."
         :props [{:name ":class"           :type "string"      :default nil :description "Additional Tailwind classes."}
                 {:name "additional props" :type "map entries" :default nil :description "Forwarded to action row container."}]}]
       [helpers/api-component-card
        {:component-name "message-action"
         :description "Tooltip-wrapped message action slot."
         :props [{:name ":tooltip"        :type "string | hiccup" :default nil   :description "Tooltip content."}
                 {:name ":side"           :type "keyword"         :default ":top" :description ":top | :right | :bottom | :left."}
                 {:name ":class"          :type "string"          :default nil   :description "Additional Tailwind classes."}
                 {:name "additional props" :type "map entries"    :default nil   :description "Forwarded to tooltip wrapper."}]}]
       [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
        [:h4 {:class "text-sm font-semibold mb-2"} "⚠️ Important Notes"]
        [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
         [:li "Compose message-avatar + message-content consistently for predictable row alignment."]
         [:li "Use message-content {:markdown? true} only for trusted/escaped content paths."]]]
       [:div {:class "border rounded-lg p-4 bg-muted/50"}
        [:h4 {:class "text-sm font-semibold mb-2"}
         "Usage Example"]
        [:pre {:class "text-xs overflow-x-auto"}
         [:code "[message {:class \"items-start gap-3\"}\n [message-avatar {:src \"https://placehold.co/40x40\" :alt \"Assistant\" :fallback \"AI\"}]\n [:div {:class \"space-y-2\"}\n  [message-content {:markdown? true} \"**Hello** from the assistant\"]\n  [message-actions {}\n   [message-action {:tooltip \"Copy\"} [button {:size :icon} [:> Copy]]]]]]" ]]]]])))

(defstory MessageBasic
  "Basic message with avatar and content.
  Uses avatar + markdown composition internally.

  Use for simple chat messages."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6"}
                                       [sut/message {}
                                        [sut/message-avatar {:src "https://placehold.co/40x40/png"
                                                             :alt "User"
                                                             :fallback "JD"}]
                                        [sut/message-content {}
                                         "Hello! This is a basic message."]]])))

(defstory MessageAvatarFallback
  "Message showing avatar fallback.
  Useful when image URLs fail or are missing."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6"}
                                       [sut/message {}
                                        [sut/message-avatar {:src ""
                                                             :alt "User"
                                                             :fallback "AL"}]
                                        [sut/message-content {}
                                         "Fallback initials are shown."]]])))

(defstory MessageMarkdown
  "Message with markdown rendering.
  Uses the Markdown component internally.

  Useful for rich assistant responses."
  []
  (r/as-element
  (helpers/wrap-component
    [:div {:class "p-6"}
     [sut/message {}
      [sut/message-avatar {:src "https://placehold.co/40x40/png"
                           :alt "Assistant"
                           :fallback "AI"}]
      [sut/message-content {:markdown? true}
       "**Markdown** supports lists:\n\n- First\n- Second\n- Third"]]])))

(defstory MessageActions
  "Message with action buttons.
  Actions are wrapped with tooltips.

  Use for copy, delete, or feedback actions."
  []
  (r/as-element
  (helpers/wrap-component
    [:div {:class "p-6"}
     [sut/message {}
      [sut/message-avatar {:src "https://placehold.co/40x40/png"
                           :alt "User"
                           :fallback "JD"}]
      [:div {:class "flex flex-col gap-2"}
       [sut/message-content {}
        "Here is a message with actions."]
       [sut/message-actions {}
        [sut/message-action {:tooltip "Copy"}
         (button/button {:variant :ghost
                         :size :icon}
                        [:> Copy {:class "size-4"}])]
        [sut/message-action {:tooltip "Delete"}
         (button/button {:variant :ghost
                         :size :icon}
                        [:> Trash2 {:class "size-4"}])]]]]])))

(defstory MessageUserVsAssistant
  "User vs assistant message styling.
  Use classes to align and style different roles.

  Helpful for chat UIs with role-based presentation."
  []
  (r/as-element
  (helpers/wrap-component [:div {:class "p-6 space-y-4"}
                                       [sut/message {:class "flex-row-reverse text-right"}
                                        [sut/message-avatar {:src "https://placehold.co/40x40/png"
                                                             :alt "User"
                                                             :fallback "ME"}]
                                        [sut/message-content {:class
                                                              "bg-primary text-primary-foreground"}
                                         "User message aligned right."]]
                                       [sut/message {}
                                        [sut/message-avatar {:src "https://placehold.co/40x40/png"
                                                             :alt "Assistant"
                                                             :fallback "AI"}]
                                        [sut/message-content {}
                                         "Assistant response aligned left."]]])))
