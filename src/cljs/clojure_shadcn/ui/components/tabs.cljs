(ns clojure-shadcn.ui.components.tabs
  (:require
   ["@radix-ui/react-tabs"      :as primitive]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- element
  [component slot base raw-props children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into [:>
           component
           (-> props
               (assoc :data-slot slot :class (merge-classes base class))
               (dissoc :class-name))]
          children)))

(defn tabs
  [raw-props & children]
  (let [{:keys [class orientation]
         :or {orientation "horizontal"}
         :as props}
        (normalize-props raw-props)
        orientation (if (keyword? orientation) (name orientation) orientation)]
    (into [:>
           primitive/Root
           (-> props
               (assoc :data-slot "tabs"
                      :data-orientation orientation
                      :orientation orientation
                      :class (merge-classes
                              "group/tabs flex gap-2 data-[orientation=horizontal]:flex-col"
                              class))
               (dissoc :class-name))]
          children)))

(defn tabs-list
  [raw-props & children]
  (let [{:keys [class variant]
         :or {variant :default}
         :as props}
        (normalize-props raw-props)
        variant (if (keyword? variant) (name variant) variant)]
    (into
     [:>
      primitive/List
      (->
        props
        (assoc
         :data-slot "tabs-list"
         :data-variant variant
         :class
         (merge-classes
          (str
           "group/tabs-list text-muted-foreground inline-flex w-fit items-center justify-center rounded-lg p-[3px] "
           "group-data-[orientation=horizontal]/tabs:h-9 "
           "group-data-[orientation=vertical]/tabs:h-fit group-data-[orientation=vertical]/tabs:flex-col "
           "data-[variant=default]:bg-muted data-[variant=line]:gap-1 data-[variant=line]:rounded-none data-[variant=line]:bg-transparent")
          class))
        (dissoc :class-name :variant))]
     children)))

(defn tabs-trigger
  [props & children]
  (element
   primitive/Trigger
   "tabs-trigger"
   (str
    "relative data-[state=active]:bg-background dark:data-[state=active]:text-foreground "
    "focus-visible:border-ring focus-visible:ring-ring/50 focus-visible:outline-ring "
    "dark:data-[state=active]:border-input dark:data-[state=active]:bg-input/30 text-foreground dark:text-muted-foreground "
    "inline-flex h-[calc(100%-1px)] flex-1 items-center justify-center gap-1.5 rounded-md border border-transparent "
    "px-2 py-1 text-sm font-medium whitespace-nowrap transition-all focus-visible:ring-[3px] focus-visible:outline-1 "
    "disabled:pointer-events-none disabled:opacity-50 data-[state=active]:shadow-sm "
    "group-data-[orientation=vertical]/tabs:w-full group-data-[orientation=vertical]/tabs:justify-start "
    "group-data-[variant=line]/tabs-list:bg-transparent group-data-[variant=line]/tabs-list:data-[state=active]:bg-transparent "
    "group-data-[variant=line]/tabs-list:data-[state=active]:shadow-none "
    "dark:group-data-[variant=line]/tabs-list:data-[state=active]:border-transparent "
    "dark:group-data-[variant=line]/tabs-list:data-[state=active]:bg-transparent "
    "after:absolute after:bg-foreground after:opacity-0 after:transition-opacity "
    "group-data-[orientation=horizontal]/tabs:after:inset-x-0 "
    "group-data-[orientation=horizontal]/tabs:after:bottom-[-5px] group-data-[orientation=horizontal]/tabs:after:h-0.5 "
    "group-data-[orientation=vertical]/tabs:after:inset-y-0 group-data-[orientation=vertical]/tabs:after:-right-1 "
    "group-data-[orientation=vertical]/tabs:after:w-0.5 "
    "group-data-[variant=line]/tabs-list:data-[state=active]:after:opacity-100 "
    "[&_svg]:pointer-events-none [&_svg]:shrink-0 [&_svg:not([class*='size-'])]:size-4")
   props
   children))

(defn tabs-content
  [props & children]
  (element primitive/Content "tabs-content" "flex-1 outline-none" props children))
