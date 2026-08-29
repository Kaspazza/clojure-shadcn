(ns clojure-shadcn.ui.components.marker
  "Timeline and status marker primitives based on current shadcn/ui Marker."
  (:require
   ["@radix-ui/react-slot" :refer [Slot]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn marker-variants
  "Returns canonical marker classes for a variant."
  [variant class]
  (merge-classes
   "group/marker relative flex min-h-4 w-full items-center gap-2 text-left text-sm text-muted-foreground [&_svg:not([class*='size-'])]:size-4 [a]:underline [a]:underline-offset-3 [a]:hover:text-foreground"
   (case variant
     :separator "before:mr-1 before:h-px before:min-w-0 before:flex-1 before:bg-border after:ml-1 after:h-px after:min-w-0 after:flex-1 after:bg-border"
     :border "border-b border-border pb-2"
     "") class))

(defn marker [{:keys [variant as-child? class] :or {variant :default} :as props} & children]
  (let [p (-> props (assoc :data-slot "marker" :data-variant (name variant))
              (dissoc :variant :as-child? :class :class-name))
        classes (marker-variants variant class)]
    (if as-child? (into [:> Slot (assoc p :className classes)] children)
        (into [:div (assoc p :class classes)] children))))

(defn marker-icon [{:keys [class] :as props} & children]
  (into [:span (-> props (assoc :data-slot "marker-icon" :aria-hidden true
                                :class (merge-classes "size-4 shrink-0 [&_svg:not([class*='size-'])]:size-4" class))
                    (dissoc :class-name))] children))

(defn marker-content [{:keys [class] :as props} & children]
  (into [:span (-> props (assoc :data-slot "marker-content"
                                :class (merge-classes "min-w-0 wrap-break-word group-data-[variant=separator]/marker:flex-none group-data-[variant=separator]/marker:text-center *:[a]:underline *:[a]:underline-offset-3 *:[a]:hover:text-foreground" class))
                    (dissoc :class-name))] children))
