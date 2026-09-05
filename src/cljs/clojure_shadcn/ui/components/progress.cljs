(ns clojure-shadcn.ui.components.progress
  (:require
   ["@radix-ui/react-progress"  :as primitive]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn- finite-number? [value]
  (and (number? value) (js/Number.isFinite value)))

(defn- clamp [value lower upper]
  (-> value (max lower) (min upper)))

(defn progress
  [raw-props]
  (let [{:keys [class value max indicator-class]
         :or {value 0
              max 100}
         :as props}
        (normalize-props raw-props)
        safe-max (if (and (finite-number? max) (pos? max)) max 100)
        safe-value (if (finite-number? value) (clamp value 0 safe-max) 0)
        percentage (* 100 (/ safe-value safe-max))]
    [:>
     primitive/Root
     (-> props
         (assoc :data-slot "progress"
                :value safe-value
                :max safe-max
                :class (merge-classes
                        "bg-primary/20 relative h-2 w-full overflow-hidden rounded-full"
                        class))
         (dissoc :class-name :indicator-class))
     [:>
      primitive/Indicator
      {:data-slot "progress-indicator"
       :class (merge-classes "bg-primary h-full w-full flex-1 transition-all" indicator-class)
       :style {:transform (str "translateX(-" (- 100 percentage) "%)")}}]]))
