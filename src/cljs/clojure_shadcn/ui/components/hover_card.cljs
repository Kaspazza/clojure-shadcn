(ns clojure-shadcn.ui.components.hover-card
  (:require
   ["@radix-ui/react-hover-card" :as primitive]
   [clojure-shadcn.utils.props   :refer [normalize-props]]
   [clojure-shadcn.utils.styles  :refer [merge-classes]]))

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

(defn hover-card [props & children] (element primitive/Root "hover-card" nil props children))

(defn hover-card-trigger
  [props & children]
  (element primitive/Trigger "hover-card-trigger" nil props children))

(defn hover-card-content
  [props & children]
  (element
   primitive/Portal
   "hover-card-portal"
   nil
   {}
   [(element
     primitive/Content
     "hover-card-content"
     "bg-popover text-popover-foreground data-[state=open]:animate-in data-[state=closed]:animate-out data-[state=closed]:fade-out-0 data-[state=open]:fade-in-0 data-[state=closed]:zoom-out-95 data-[state=open]:zoom-in-95 data-[side=bottom]:slide-in-from-top-2 data-[side=left]:slide-in-from-right-2 data-[side=right]:slide-in-from-left-2 data-[side=top]:slide-in-from-bottom-2 z-50 w-64 origin-(--radix-hover-card-content-transform-origin) rounded-md border p-4 shadow-md outline-hidden"
     (merge {:side-offset 4} props)
     children)]))
