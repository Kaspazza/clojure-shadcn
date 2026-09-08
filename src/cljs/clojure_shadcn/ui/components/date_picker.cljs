(ns clojure-shadcn.ui.components.date-picker
  "Date-picker APIs.

  `date-picker` is the backward-compatible legacy native date input. Prefer its
  explicit `native-date-picker` name in new code that needs platform semantics.

  The composed API is `composed-date-picker`, `date-picker-trigger`,
  `date-picker-button`, `date-picker-content`, and `date-picker-calendar`. It is
  an upstream-style composition of the local Radix Popover, Button, and Calendar
  components; selection state stays at the call site instead of being hidden in
  one oversized widget."
  (:require
   ["lucide-react"                        :refer [CalendarIcon]]
   [clojure-shadcn.ui.components.button   :as button]
   [clojure-shadcn.ui.components.calendar :as calendar]
   [clojure-shadcn.ui.components.popover  :as popover]
   [clojure-shadcn.utils.props            :refer [normalize-props]]
   [clojure-shadcn.utils.styles           :refer [merge-classes]]))

(defn native-date-picker
  "Legacy platform date control under an explicit name.

  Accepts native input props. `:value` and `:default-value` use ISO yyyy-MM-dd.
  `:on-change` receives the browser event, preserving native form semantics."
  [{:as raw-props}]
  (let [{:keys [class wrapper-class icon-class]
         :as props}
        (normalize-props raw-props)]
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
        (dissoc :wrapper-class :icon-class :class-name))]]))

(def date-picker
  "Backward-compatible alias for `native-date-picker`. Existing callers keep the native input API."
  native-date-picker)

(defn composed-date-picker
  "Radix Popover root for the composed API.

  Popover props are forwarded, including controlled `:open`/`:on-open-change`
  and uncontrolled `:default-open`. Add trigger and content children explicitly."
  [props & children]
  (into [popover/popover (normalize-props props)] children))

(defn date-picker-trigger
  "Popover trigger for the composed API. Defaults to `:as-child true` so it can wrap `date-picker-button`."
  [props & children]
  (into [popover/popover-trigger (merge {:as-child true} (normalize-props props))] children))

(defn date-picker-button
  "Upstream-style outlined date trigger. Children provide the formatted value or placeholder."
  [{:as raw-props} & children]
  (let [{:keys [class]
         :as props}
        (normalize-props raw-props)]
    (into
     [button/button
      (->
        props
        (assoc
         :variant :outline
         :class
         (merge-classes
          "w-[280px] justify-start text-left font-normal data-[placeholder=true]:text-muted-foreground"
          class))
        (dissoc :class-name))
      [:>
       CalendarIcon
       {:aria-hidden true
        :data-icon "inline-start"}]]
     children)))

(defn date-picker-content
  "Portaled Popover content sized to the calendar. Positioning props are forwarded."
  [props & children]
  (into [popover/popover-content
         (merge {:align "start"
                 :class "w-auto p-0"}
                props)]
        children))

(defn date-picker-calendar
  "Local react-day-picker Calendar for the composed API.

  DayPicker props are forwarded. Use `:mode`, `:selected`, and `:on-select` for
  controlled single/range selection, or omit selection props for uncontrolled
  interaction where supported by React DayPicker."
  [props]
  [calendar/calendar props])
