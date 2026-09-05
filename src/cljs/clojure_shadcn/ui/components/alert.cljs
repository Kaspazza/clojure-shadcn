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
          "relative w-full rounded-lg border px-4 py-3 text-sm grid has-[>svg]:grid-cols-[calc(var(--spacing)*4)_1fr] grid-cols-[0_1fr] has-[>svg]:gap-x-3 gap-y-0.5 items-start [&>svg]:size-4 [&>svg]:translate-y-0.5 [&>svg]:text-current"
          (variant-class variant)
          class))
        (dissoc :class-name :variant))]
     children)))

(defn alert-title
  [{:as raw-props} & children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into [:div
           (-> props
               (assoc :data-slot "alert-title"
                      :class (merge-classes
                              "col-start-2 line-clamp-1 min-h-4 font-medium tracking-tight"
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
          "text-muted-foreground col-start-2 grid justify-items-start gap-1 text-sm [&_p]:leading-relaxed"
          class))
        (dissoc :class-name))]
     children)))
