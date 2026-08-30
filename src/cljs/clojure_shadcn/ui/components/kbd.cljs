(ns clojure-shadcn.ui.components.kbd
  (:require
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn kbd
  [{:as raw-props} & children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into
     [:kbd
      (->
        props
        (assoc
         :data-slot "kbd"
         :class
         (merge-classes
          "pointer-events-none inline-flex h-5 w-fit min-w-5 items-center justify-center gap-1 rounded-sm bg-muted px-1 font-sans text-xs font-medium text-muted-foreground select-none [&_svg:not([class*='size-'])]:size-3 [[data-slot=tooltip-content]_&]:bg-background/20 [[data-slot=tooltip-content]_&]:text-background dark:[[data-slot=tooltip-content]_&]:bg-background/10"
          class))
        (dissoc :class-name))]
     children)))

(defn kbd-group
  [{:as raw-props} & children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into [:kbd
           (-> props
               (assoc :data-slot "kbd-group"
                      :class (merge-classes "inline-flex items-center gap-1" class))
               (dissoc :class-name))]
          children)))
