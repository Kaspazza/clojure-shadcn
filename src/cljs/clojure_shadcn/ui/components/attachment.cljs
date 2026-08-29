(ns clojure-shadcn.ui.components.attachment
  "File attachment primitives based on the current shadcn/ui Attachment module."
  (:require
   ["@radix-ui/react-slot" :refer [Slot]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- node [tag slot classes props children]
  (into [tag (-> props (assoc :data-slot slot :class (merge-classes classes (:class props)))
                 (dissoc :class-name))] children))

(defn- size-classes [size]
  (case size
    :sm "gap-2.5 text-xs has-data-[slot=attachment-content]:px-2 has-data-[slot=attachment-content]:py-1.5 has-data-[slot=attachment-media]:p-1.5"
    :xs "gap-1.5 rounded-lg text-xs has-data-[slot=attachment-content]:px-1.5 has-data-[slot=attachment-content]:py-1 has-data-[slot=attachment-media]:p-1"
    "gap-2 text-sm has-data-[slot=attachment-content]:px-2.5 has-data-[slot=attachment-content]:py-2 has-data-[slot=attachment-media]:p-2"))

(defn attachment [{:keys [state size orientation] :or {state :done size :default orientation :horizontal} :as props} & children]
  (node :div "attachment"
        (merge-classes "group/attachment relative flex w-fit max-w-full min-w-0 shrink-0 flex-wrap rounded-xl border bg-card text-card-foreground transition-colors focus-within:ring-1 focus-within:ring-ring/50 has-[>a,>button]:hover:bg-muted/50 data-[state=error]:border-destructive/30 data-[state=idle]:border-dashed"
                       (size-classes size) (if (= orientation :vertical) "w-24 flex-col has-data-[slot=attachment-content]:w-30" "min-w-40 items-center"))
        (-> props (assoc :data-state (name state) :data-size (name size) :data-orientation (name orientation))
            (dissoc :state :size :orientation)) children))

(defn attachment-media [{:keys [variant] :or {variant :icon} :as props} & children]
  (node :div "attachment-media"
        (merge-classes "relative flex aspect-square w-10 shrink-0 items-center justify-center overflow-hidden rounded-lg bg-muted text-foreground group-data-[orientation=vertical]/attachment:w-full group-data-[size=sm]/attachment:w-8 group-data-[size=xs]/attachment:w-7 group-data-[state=error]/attachment:bg-destructive/10 group-data-[state=error]/attachment:text-destructive [&_svg:not([class*='size-'])]:size-4"
                       (when (= variant :image) "opacity-60 group-data-[state=done]/attachment:opacity-100 group-data-[state=idle]/attachment:opacity-100 *:[img]:aspect-square *:[img]:w-full *:[img]:object-cover"))
        (-> props (assoc :data-variant (name variant)) (dissoc :variant)) children))

(defn attachment-content [props & children] (node :div "attachment-content" "max-w-full min-w-0 flex-1 leading-tight group-data-[orientation=vertical]/attachment:px-1" props children))
(defn attachment-title [props & children] (node :span "attachment-title" "block max-w-full min-w-0 truncate font-medium group-data-[state=processing]/attachment:shimmer group-data-[state=uploading]/attachment:shimmer" props children))
(defn attachment-description [props & children] (node :span "attachment-description" "mt-0.5 block min-w-0 truncate text-xs text-muted-foreground group-data-[state=error]/attachment:text-destructive/80 max-w-full" props children))
(defn attachment-actions [props & children] (node :div "attachment-actions" "relative z-20 flex shrink-0 items-center group-data-[orientation=vertical]/attachment:absolute group-data-[orientation=vertical]/attachment:top-3 group-data-[orientation=vertical]/attachment:right-3 group-data-[orientation=vertical]/attachment:gap-1" props children))

(defn attachment-action [{:keys [variant size class] :or {variant :ghost size :icon-xs} :as props} & children]
  (apply button/button (-> props (assoc :data-slot "attachment-action" :variant variant :size size :class class)
                           (dissoc :class-name)) children))

(defn attachment-trigger [{:keys [as-child? type class] :as props} & children]
  (let [p (-> props (assoc :data-slot "attachment-trigger") (dissoc :as-child? :class :class-name))
        classes (merge-classes "absolute inset-0 z-10 outline-none" class)]
    (if as-child? (into [:> Slot (assoc p :className classes)] children)
        (into [:button (assoc p :type (or type "button") :class classes)] children))))

(defn attachment-group [props & children]
  (node :div "attachment-group" "flex min-w-0 scroll-fade-x snap-x snap-mandatory scroll-px-1 scrollbar-none gap-3 overflow-x-auto overscroll-x-contain py-1 *:data-[slot=attachment]:flex-none *:data-[slot=attachment]:snap-start" props children))
