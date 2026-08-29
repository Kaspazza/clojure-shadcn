(ns clojure-shadcn.ui.components.progress
  (:require
   ["@radix-ui/react-progress" :as primitive]
   [clojure-shadcn.utils.props :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn progress [{:as raw-props}]
  (let [{:keys [class value indicator-class] :or {value 0} :as props} (normalize-props raw-props)]
    [:> primitive/Root
     (-> props
         (assoc :data-slot "progress" :value value
                :class (merge-classes "bg-primary/20 relative h-2 w-full overflow-hidden rounded-full" class))
         (dissoc :class-name :indicator-class))
     [:> primitive/Indicator
      {:data-slot "progress-indicator"
       :class (merge-classes "bg-primary h-full w-full flex-1 transition-all" indicator-class)
       :style {:transform (str "translateX(-" (- 100 (or value 0)) "%)")}}]]))
