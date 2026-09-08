(ns clojure-shadcn.ui.components.card
  (:require
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- element
  [tag slot base raw-props children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into [tag
           (-> props
               (assoc :data-slot slot :class (merge-classes base class))
               (dissoc :class-name))]
          children)))

(defn card
  [raw-props & children]
  (let [{:keys [size]
         :or {size :default}
         :as props}
        (normalize-props raw-props)]
    (element
     :div
     "card"
     "group/card flex flex-col gap-(--card-spacing) rounded-xl border bg-card py-(--card-spacing) text-card-foreground shadow-sm [--card-spacing:--spacing(6)] data-[size=sm]:[--card-spacing:--spacing(4)]"
     (-> props
         (assoc :data-size (name size))
         (dissoc :size))
     children)))

(defn card-header
  [props & children]
  (element
   :div
   "card-header"
   "group/card-header @container/card-header grid auto-rows-min items-start gap-2 px-(--card-spacing) has-data-[slot=card-action]:grid-cols-[1fr_auto] has-data-[slot=card-description]:grid-rows-[auto_auto] [.border-b]:pb-(--card-spacing)"
   props
   children))

(defn card-title
  [props & children]
  (element :div
           "card-title"
           "leading-none font-semibold group-data-[size=sm]/card:text-sm"
           props
           children))

(defn card-description
  [props & children]
  (element :div "card-description" "text-sm text-muted-foreground" props children))

(defn card-action
  [props & children]
  (element :div
           "card-action"
           "col-start-2 row-span-2 row-start-1 self-start justify-self-end"
           props
           children))

(defn card-content
  [props & children]
  (element :div "card-content" "px-(--card-spacing)" props children))

(defn card-footer
  [props & children]
  (element :div
           "card-footer"
           "flex items-center px-(--card-spacing) [.border-t]:pt-(--card-spacing)"
           props
           children))
