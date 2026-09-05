(ns clojure-shadcn.ui.components.item
  "Composable list item primitives based on the current shadcn/ui Item module."
  (:require
   ["@radix-ui/react-slot"                 :refer [Slot]]
   [clojure-shadcn.ui.components.separator :as separator]
   [clojure-shadcn.utils.styles            :refer [merge-classes]]))

(defn- element
  [tag slot classes props children]
  (into [tag
         (-> props
             (assoc :data-slot slot :class (merge-classes classes (:class props)))
             (dissoc :class-name))]
        children))

(defn item-group
  [props & children]
  (element :div "item-group" "group/item-group flex flex-col" (assoc props :role "list") children))

(defn item-separator
  [{:keys [class]
    :as props}]
  [separator/separator
   (-> props
       (assoc :data-slot "item-separator"
              :orientation :horizontal
              :class (merge-classes "my-0" class))
       (dissoc :class-name))])

(defn- item-variant
  [variant]
  (case variant
    :outline "border-border"
    :muted "bg-muted/50"
    "bg-transparent"))

(defn- item-size
  [size]
  (case size
    :sm "gap-2.5 px-4 py-3"
    "gap-4 p-4"))

(defn item
  [{:keys [class variant size as-child?]
    :or {variant :default
         size :default}
    :as props}
   &
   children]
  (let [tag (if as-child? :> :div)
        head (if as-child? [tag Slot] [tag])]
    (into
     (conj
      head
      (->
        props
        (assoc
         :data-slot "item"
         :data-variant (name variant)
         :data-size (name size)
         :role "listitem"
         :class
         (merge-classes
          "group/item flex flex-wrap items-center rounded-md border border-transparent text-sm transition-colors duration-100 outline-none focus-visible:border-ring focus-visible:ring-[3px] focus-visible:ring-ring/50 [a]:transition-colors [a]:hover:bg-accent/50"
          (item-variant variant)
          (item-size size)
          class))
        (dissoc :variant :size :as-child? :class-name)))
     children)))

(defn item-media
  [{:keys [variant]
    :or {variant :default}
    :as props}
   &
   children]
  (element
   :div
   "item-media"
   (merge-classes
    "flex shrink-0 items-center justify-center gap-2 group-has-[[data-slot=item-description]]/item:translate-y-0.5 group-has-[[data-slot=item-description]]/item:self-start [&_svg]:pointer-events-none"
    (case variant
      :icon "size-8 rounded-sm border bg-muted [&_svg:not([class*='size-'])]:size-4"
      :image "size-10 overflow-hidden rounded-sm [&_img]:size-full [&_img]:object-cover"
      "bg-transparent"))
   (-> props
       (assoc :data-variant (name variant))
       (dissoc :variant))
   children))

(defn item-content
  [props & children]
  (element :div
           "item-content"
           "flex flex-1 flex-col gap-1 [&+[data-slot=item-content]]:flex-none"
           props
           children))

(defn item-title
  [props & children]
  (element :div
           "item-title"
           "flex w-fit items-center gap-2 text-sm leading-snug font-medium"
           props
           children))

(defn item-description
  [props & children]
  (element
   :p
   "item-description"
   "line-clamp-2 text-sm leading-normal font-normal text-balance text-muted-foreground [&>a]:underline [&>a]:underline-offset-4 [&>a:hover]:text-primary"
   props
   children))

(defn item-actions
  [props & children]
  (element :div "item-actions" "flex items-center gap-2" props children))

(defn item-header
  [props & children]
  (element :div "item-header" "flex basis-full items-center justify-between gap-2" props children))

(defn item-footer
  [props & children]
  (element :div "item-footer" "flex basis-full items-center justify-between gap-2" props children))
