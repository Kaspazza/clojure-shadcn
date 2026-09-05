(ns clojure-shadcn.ui.components.bubble
  "Conversation bubble primitives based on the current shadcn/ui Bubble module."
  (:require
   ["@radix-ui/react-slot"      :refer [Slot]]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- node
  [tag slot classes raw-props children]
  (let [props (normalize-props raw-props)]
    (into [tag
           (-> props
               (assoc :data-slot slot :class (merge-classes classes (:class props)))
               (dissoc :class-name))]
          children)))

(defn bubble-group
  [props & children]
  (node :div "bubble-group" "flex min-w-0 flex-col gap-2" props children))

(defn- variant-classes
  [variant]
  (case variant
    :secondary
    "*:data-[slot=bubble-content]:bg-secondary *:data-[slot=bubble-content]:text-secondary-foreground"
    :muted "*:data-[slot=bubble-content]:bg-muted"
    :tinted
    "*:data-[slot=bubble-content]:bg-primary/10 *:data-[slot=bubble-content]:text-foreground"
    :outline "*:data-[slot=bubble-content]:border-border *:data-[slot=bubble-content]:bg-background"
    :ghost
    "border-none *:data-[slot=bubble-content]:rounded-none *:data-[slot=bubble-content]:bg-transparent *:data-[slot=bubble-content]:p-0"
    :destructive
    "*:data-[slot=bubble-content]:bg-destructive/10 *:data-[slot=bubble-content]:text-destructive dark:*:data-[slot=bubble-content]:bg-destructive/20"
    "*:data-[slot=bubble-content]:bg-primary *:data-[slot=bubble-content]:text-primary-foreground"))

(defn bubble
  [{:keys [variant align]
    :or {variant :default
         align :start}
    :as props}
   &
   children]
  (node
   :div
   "bubble"
   (merge-classes
    "group/bubble relative flex w-fit max-w-[80%] min-w-0 flex-col gap-1 group-data-[align=end]/message:self-end data-[align=end]:self-end data-[variant=ghost]:max-w-full"
    (variant-classes variant))
   (-> props
       (assoc :data-variant (name variant) :data-align (name align))
       (dissoc :variant :align))
   children))

(defn bubble-content
  [{:keys [as-child? class]
    :as props}
   &
   children]
  (let
    [props (normalize-props props)
     as-child? (or as-child? (:as-child props))
     classes
     (merge-classes
      "w-fit max-w-full min-w-0 overflow-hidden rounded-xl border border-transparent px-3 py-2 text-sm leading-relaxed wrap-break-word group-data-[align=end]/bubble:self-end [button]:text-left [button,a]:transition-colors [button,a]:outline-none [button,a]:focus-visible:border-ring [button,a]:focus-visible:ring-3 [button,a]:focus-visible:ring-ring/50"
      class)
     p (-> props
           (assoc :data-slot "bubble-content")
           (dissoc :as-child? :as-child :class :class-name))]
    (if as-child?
      (into [:> Slot (assoc p :className classes)] children)
      (into [:div (assoc p :class classes)] children))))

(defn bubble-reactions
  [{:keys [side align]
    :or {side :bottom
         align :end}
    :as props}
   &
   children]
  (node
   :div
   "bubble-reactions"
   (merge-classes
    "absolute z-10 flex w-fit shrink-0 items-center justify-center gap-1 rounded-full bg-muted px-1.5 py-0.5 text-sm ring-3 ring-card has-[button]:p-0"
    (if (= side :top) "top-0 -translate-y-3/4" "bottom-0 translate-y-3/4")
    (if (= align :start) "left-3" "right-3"))
   (-> props
       (assoc :data-side (name side) :data-align (name align))
       (dissoc :side :align))
   children))
