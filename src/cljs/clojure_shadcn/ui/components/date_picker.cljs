(ns clojure-shadcn.ui.components.date-picker
  "Reusable, dependency-free date-picker composition using the platform date control.
  This is a project extra, not a registry:ui module."
  (:require
   ["lucide-react"              :refer [CalendarIcon]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn date-picker
  "Accessible controlled/uncontrolled date input composition.

  Accepts native input props. `:value` and `:default-value` use ISO yyyy-MM-dd.
  `:on-change` receives the browser event, preserving native form semantics."
  [{:keys [class wrapper-class icon-class]
    :as props}]
  [:div {:data-slot "date-picker"
         :class (merge-classes "relative w-full" wrapper-class)}
   [:>
    CalendarIcon
    {:aria-hidden true
     :className
     (merge-classes
      "text-muted-foreground pointer-events-none absolute left-3 top-1/2 size-4 -translate-y-1/2"
      icon-class)}]
   [:input
    (->
      props
      (assoc
       :data-slot "date-picker-input"
       :type "date"
       :class
       (merge-classes
        "border-input bg-background ring-offset-background placeholder:text-muted-foreground focus-visible:ring-ring flex h-9 w-full rounded-md border px-3 py-1 pl-9 text-sm shadow-sm transition-colors focus-visible:outline-none focus-visible:ring-1 disabled:cursor-not-allowed disabled:opacity-50"
        class))
      (dissoc :wrapper-class :icon-class :class-name))]])
