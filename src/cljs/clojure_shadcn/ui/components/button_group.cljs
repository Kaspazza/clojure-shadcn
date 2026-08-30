(ns clojure-shadcn.ui.components.button-group
  (:require
   ["@radix-ui/react-slot"                 :refer [Slot]]
   [clojure-shadcn.ui.components.separator :as separator]
   [clojure-shadcn.utils.props             :refer [normalize-props]]
   [clojure-shadcn.utils.styles            :refer [merge-classes]]))

(defn button-group
  [{:as raw-props} & children]
  (let
    [{:keys [class orientation]
      :or {orientation :horizontal}
      :as props}
     (normalize-props raw-props)
     orientation-class
     (case orientation
       :vertical
       "flex-col [&>*:not(:first-child)]:rounded-t-none [&>*:not(:first-child)]:border-t-0 [&>*:not(:last-child)]:rounded-b-none"
       "[&>*:not(:first-child)]:rounded-l-none [&>*:not(:first-child)]:border-l-0 [&>*:not(:last-child)]:rounded-r-none")]
    (into
     [:div
      (->
        props
        (assoc
         :role "group"
         :data-slot "button-group"
         :data-orientation (name orientation)
         :class
         (merge-classes
          "flex w-fit items-stretch has-[>[data-slot=button-group]]:gap-2 [&>*]:focus-visible:relative [&>*]:focus-visible:z-10 has-[select[aria-hidden=true]:last-child]:[&>[data-slot=select-trigger]:last-of-type]:rounded-r-md [&>[data-slot=select-trigger]:not([class*='w-'])]:w-fit [&>input]:flex-1"
          orientation-class
          class))
        (dissoc :class-name :orientation))]
     children)))

(defn button-group-text
  [{:as raw-props} & children]
  (let [{:keys [class as-child]
         :as props}
        (normalize-props raw-props)]
    (into
     [:>
      (if as-child Slot "div")
      (->
        props
        (assoc
         :data-slot "button-group-text"
         :class
         (merge-classes
          "flex items-center gap-2 rounded-md border bg-muted px-4 text-sm font-medium shadow-xs [&_svg]:pointer-events-none [&_svg:not([class*='size-'])]:size-4"
          class))
        (dissoc :class-name :as-child))]
     children)))

(defn button-group-separator
  [{:as raw-props}]
  (let [{:keys [class orientation]
         :or {orientation :vertical}
         :as props}
        (normalize-props raw-props)]
    [separator/separator
     (-> props
         (assoc :data-slot "button-group-separator"
                :orientation orientation
                :class (merge-classes
                        "relative !m-0 self-stretch bg-input data-[orientation=vertical]:h-auto"
                        class))
         (dissoc :class-name))]))
