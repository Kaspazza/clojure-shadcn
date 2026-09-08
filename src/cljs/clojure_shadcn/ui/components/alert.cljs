(ns clojure-shadcn.ui.components.alert
  (:require
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- variant-class
  [variant]
  (case variant
    :destructive
    "text-destructive bg-card [&>svg]:text-current *:data-[slot=alert-description]:text-destructive/90"
    "bg-card text-card-foreground"))

(defn alert
  [{:as raw-props} & children]
  (let [{:keys [class variant]
         :or {variant :default}
         :as props}
        (normalize-props raw-props)]
    (into
     [:div
      (->
        props
        (assoc
         :role (or (:role props) "alert")
         :data-slot "alert"
         :data-variant (name variant)
         :class
         (merge-classes
          "group/alert relative grid w-full gap-0.5 rounded-lg border px-2.5 py-2 text-left text-sm has-data-[slot=alert-action]:pr-18 has-[>svg]:grid-cols-[auto_1fr] has-[>svg]:gap-x-2 *:[svg]:row-span-2 *:[svg]:translate-y-0.5 *:[svg]:text-current *:[svg:not([class*='size-'])]:size-4"
          (variant-class variant)
          class))
        (dissoc :class-name :variant))]
     children)))

(defn alert-title
  [{:as raw-props} & children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into
     [:div
      (->
        props
        (assoc
         :data-slot "alert-title"
         :class
         (merge-classes
          "font-medium group-has-[>svg]/alert:col-start-2 [&_a]:underline [&_a]:underline-offset-3 [&_a]:hover:text-foreground"
          class))
        (dissoc :class-name))]
     children)))

(defn alert-description
  [{:as raw-props} & children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into
     [:div
      (->
        props
        (assoc
         :data-slot "alert-description"
         :class
         (merge-classes
          "text-muted-foreground text-sm text-balance md:text-pretty group-has-[>svg]/alert:col-start-2 [&_a]:underline [&_a]:underline-offset-3 [&_a]:hover:text-foreground [&_p:not(:last-child)]:mb-4"
          class))
        (dissoc :class-name))]
     children)))

(defn alert-action
  [{:as raw-props} & children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into [:div
           (-> props
               (assoc :data-slot "alert-action"
                      :class (merge-classes "absolute top-2 right-2" class))
               (dissoc :class-name))]
          children)))
