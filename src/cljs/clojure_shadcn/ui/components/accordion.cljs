(ns clojure-shadcn.ui.components.accordion
  (:require
   ["@radix-ui/react-accordion" :as primitive]
   ["lucide-react"              :refer [ChevronDownIcon]]
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

(defn accordion [props & children] (element primitive/Root "accordion" nil props children))

(defn accordion-item
  [props & children]
  (element primitive/Item "accordion-item" "border-b last:border-b-0" props children))

(defn accordion-trigger
  [props & children]
  (element
   primitive/Header
   "accordion-header"
   "flex"
   {}
   [(element
     primitive/Trigger
     "accordion-trigger"
     "focus-visible:border-ring focus-visible:ring-ring/50 flex flex-1 items-start justify-between gap-4 rounded-md py-4 text-left text-sm font-medium outline-none transition-all hover:underline focus-visible:ring-[3px] disabled:pointer-events-none disabled:opacity-50 [&[data-state=open]>svg]:rotate-180"
     props
     (concat
      children
      [[:>
        ChevronDownIcon
        {:class
         "text-muted-foreground pointer-events-none size-4 shrink-0 translate-y-0.5 transition-transform duration-200"}]]))]))

(defn accordion-content
  [props & children]
  (element
   primitive/Content
   "accordion-content"
   "data-[state=closed]:animate-accordion-up data-[state=open]:animate-accordion-down overflow-hidden text-sm"
   props
   [(into [:div {:class "pb-4 pt-0"}]
          children)]))
