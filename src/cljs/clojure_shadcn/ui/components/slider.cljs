(ns clojure-shadcn.ui.components.slider
  (:require
   ["@radix-ui/react-slider"    :as primitive]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn slider
  [{:as raw-props}]
  (let [{:keys [class value default-value min max thumb-class]
         :or {min 0
              max 100}
         :as props}
        (normalize-props raw-props)
        values (or value default-value [min])]
    (into
     [:>
      primitive/Root
      (->
        props
        (assoc
         :data-slot "slider"
         :min min
         :max max
         :class
         (merge-classes
          "relative flex w-full touch-none items-center select-none data-[disabled]:opacity-50 data-[orientation=vertical]:h-full data-[orientation=vertical]:min-h-44 data-[orientation=vertical]:w-auto data-[orientation=vertical]:flex-col"
          class))
        (dissoc :class-name :thumb-class))
      [:>
       primitive/Track
       {:data-slot "slider-track"
        :class
        "bg-muted relative grow overflow-hidden rounded-full data-[orientation=horizontal]:h-1.5 data-[orientation=horizontal]:w-full data-[orientation=vertical]:h-full data-[orientation=vertical]:w-1.5"}
       [:>
        primitive/Range
        {:data-slot "slider-range"
         :class
         "bg-primary absolute data-[orientation=horizontal]:h-full data-[orientation=vertical]:w-full"}]]]
     (map-indexed
      (fn [index _]
        ^{:key index}
        [:>
         primitive/Thumb
         {:data-slot "slider-thumb"
          :class
          (merge-classes
           "border-primary bg-background ring-ring/50 block size-4 shrink-0 rounded-full border shadow-sm transition-[color,box-shadow] hover:ring-4 focus-visible:ring-4 focus-visible:outline-hidden disabled:pointer-events-none disabled:opacity-50"
           thumb-class)}])
      values))))
