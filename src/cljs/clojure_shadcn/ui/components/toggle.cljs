(ns clojure-shadcn.ui.components.toggle
  (:require
   ["@radix-ui/react-toggle" :as primitive]
   [clojure-shadcn.utils.props :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn toggle-classes [{:keys [variant size]}]
  (merge-classes
   "inline-flex items-center justify-center gap-2 rounded-md text-sm font-medium hover:bg-muted hover:text-muted-foreground disabled:pointer-events-none disabled:opacity-50 data-[state=on]:bg-accent data-[state=on]:text-accent-foreground [&_svg]:pointer-events-none [&_svg]:shrink-0 focus-visible:border-ring focus-visible:ring-ring/50 outline-none transition-[color,box-shadow] focus-visible:ring-[3px]"
   (when (= variant :outline) "border border-input bg-transparent shadow-xs hover:bg-accent hover:text-accent-foreground")
   (case size :sm "h-8 px-1.5 min-w-8" :lg "h-10 px-2.5 min-w-10" "h-9 px-2 min-w-9")))

(defn toggle [{:as raw-props} & children]
  (let [{:keys [class variant size] :or {variant :default size :default} :as props} (normalize-props raw-props)]
    (into [:> primitive/Root
           (-> props
               (assoc :data-slot "toggle" :data-variant (name variant) :data-size (name size)
                      :class (merge-classes (toggle-classes {:variant variant :size size}) class))
               (dissoc :class-name :variant :size))]
          children)))
