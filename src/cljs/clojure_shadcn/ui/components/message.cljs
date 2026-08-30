(ns clojure-shadcn.ui.components.message
  "Message component for chat interfaces.
  Provides structured message display with avatar, content, and actions.

Version: 1.0.0
Last updated: 2026-02-06

Custom component implementation."
  (:require
   [clojure-shadcn.ui.components.avatar   :as mateuszmazurczak-avatar]
   [clojure-shadcn.ui.components.markdown :as mateuszmazurczak-markdown]
   [clojure-shadcn.ui.components.tooltip  :as mateuszmazurczak-tooltip]
   [clojure-shadcn.utils.props            :refer [normalize-props]]
   [clojure-shadcn.utils.styles           :refer [merge-classes]]
   [reagent.core                          :as r]))

(defn message-group
  "Groups adjacent messages with canonical spacing."
  [{:keys [class]
    :as props}
   &
   children]
  (into [:div
         (-> props
             (assoc :data-slot "message-group"
                    :class (merge-classes "flex min-w-0 flex-col gap-2" class))
             (dissoc :class-name))]
        children))

(defn message
  "Root message row. `:align` accepts `:start` or `:end` (default `:start`).
  Existing class-based alignment remains supported and additional props are forwarded."
  [{:keys [class align]
    :or {align :start}
    :as props}
   &
   children]
  (into
   [:div
    (->
      props
      (assoc
       :data-slot "message"
       :data-align (name align)
       :class
       (merge-classes
        "group/message relative flex w-full min-w-0 gap-2 text-sm data-[align=end]:flex-row-reverse"
        class))
      (dissoc :class-name :align))]
   children))

(defn message-avatar
  "Message avatar component. Displays user avatar with optional fallback.
  
  Props:
  - `:src` - Avatar image source URL (required)
  - `:alt` - Alt text for accessibility (required)
  - `:fallback` - Fallback text (typically initials) when image fails to load
  - `:delay-ms` - Delay in milliseconds before showing fallback
  - `:class` - Additional Tailwind classes to merge with defaults
  Both kebab-case and camelCase prop spellings are accepted.
  
  Example:
  [message-avatar {:src \"https://github.com/user.png\" 
                   :alt \"John Doe\"
                   :fallback \"JD\"}]"
  [{:as raw-props} & children]
  (let
    [{:keys [src alt fallback delay-ms class]
      :as props}
     (normalize-props raw-props)
     wrapper-props
     (->
       props
       (assoc
        :data-slot "message-avatar"
        :class
        (merge-classes
         "flex w-fit min-w-8 shrink-0 items-center justify-center self-end overflow-hidden rounded-full bg-muted group-has-data-[slot=message-footer]/message:-translate-y-8"
         class))
       (dissoc :src :alt :fallback :delay-ms :class-name))]
    (if (seq children)
      (into [:div wrapper-props] children)
      [mateuszmazurczak-avatar/avatar
       wrapper-props
       [mateuszmazurczak-avatar/avatar-image {:src src
                                              :alt alt}]
       (when fallback
         [mateuszmazurczak-avatar/avatar-fallback {:delayMs delay-ms}
          fallback])])))

(defn message-content
  "Message content component. Displays message text with optional markdown rendering.
  
  Props:
  - `:markdown?` - When true, renders children as markdown (default: false)
  - `:class` - Additional Tailwind classes to merge with defaults
  - All other props are passed to the underlying div or Markdown component
  
  Example (plain text):
  [message-content {} \"This is a plain text message.\"]
  
  Example (markdown):
  [message-content {:markdown? true}
   \"# Hello\\n\\nThis is **bold** text.\"]"
  [{:keys [markdown? class]
    :or {markdown? false}
    :as props}
   &
   children]
  (let
    [base-classes
     "flex w-full min-w-0 flex-col gap-2.5 rounded-lg bg-secondary text-foreground prose break-words whitespace-normal group-data-[align=end]/message:*:data-slot:self-end"
     combined-classes (merge-classes base-classes class)
     content (first children)]
    (if markdown?
      [mateuszmazurczak-markdown/markdown
       (-> props
           (assoc :data-slot "message-content" :class combined-classes :children content)
           (dissoc :markdown? :class-name))]
      (into [:div
             (-> props
                 (assoc :data-slot "message-content" :class combined-classes)
                 (dissoc :markdown? :class-name))]
            children))))

(defn message-header
  "Metadata/header row for a message."
  [{:keys [class]
    :as props}
   &
   children]
  (into
   [:div
    (->
      props
      (assoc
       :data-slot "message-header"
       :class
       (merge-classes
        "flex max-w-full min-w-0 items-center px-3 text-xs font-medium text-muted-foreground group-has-data-[variant=ghost]/message:px-0"
        class))
      (dissoc :class-name))]
   children))

(defn message-footer
  "Metadata/footer row for a message."
  [{:keys [class]
    :as props}
   &
   children]
  (into
   [:div
    (->
      props
      (assoc
       :data-slot "message-footer"
       :class
       (merge-classes
        "flex max-w-full min-w-0 items-center px-3 text-xs font-medium text-muted-foreground group-has-data-[variant=ghost]/message:px-0 group-data-[align=end]/message:justify-end"
        class))
      (dissoc :class-name))]
   children))

(defn message-actions
  "Message actions container. Groups action buttons/links for a message.
  
  Props:
  - `:class` - Additional Tailwind classes to merge with defaults
  - All other props are passed to the underlying div element
  
  Example:
  [message-actions {}
    [message-action {:tooltip \"Copy\"}
      [button {:variant :ghost :size :icon} [:> copy-icon]]]
    [message-action {:tooltip \"Delete\"}
      [button {:variant :ghost :size :icon} [:> trash-icon]]]]"
  [{:keys [class]
    :as props}
   &
   children]
  (into [:div
         (-> props
             (assoc :class (merge-classes "text-muted-foreground flex items-center gap-2" class))
             (dissoc :class-name))]
        children))

(defn message-action
  "Message action component. Individual action button with tooltip.
  
  Props:
  - `:tooltip` - Tooltip content (required)
  - `:side` - Tooltip side: :top, :bottom, :left, :right (default: :top)
  - `:class` - Additional Tailwind classes for the tooltip content
  - All other props are passed to the tooltip component
  
  Children: Action button or interactive element
  
  Example:
  [message-action {:tooltip \"Copy message\"}
    [button {:variant :ghost :size :icon}
      [:> copy-icon {:class \"h-4 w-4\"}]]]
  
  Example with custom side:
  [message-action {:tooltip \"Delete\" :side :bottom}
    [button {:variant :ghost :size :icon :on-click delete-fn}
      [:> trash-icon {:class \"h-4 w-4\"}]]]"
  [{:keys [tooltip side class]
    :or {side :top}
    :as props}
   &
   children]
  [mateuszmazurczak-tooltip/tooltip
   (-> props
       (assoc :trigger (r/as-element (first children))
              :content tooltip
              :side side
              :content-class class
              :trigger-as-child? true)
       (dissoc :tooltip :class :class-name))])
