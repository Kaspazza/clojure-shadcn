(ns clojure-shadcn.ui.components.aspect-ratio
  (:require
   ["@radix-ui/react-aspect-ratio" :as primitive]
   [clojure-shadcn.utils.props :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn aspect-ratio [{:as raw-props} & children]
  (let [{:keys [class] :as props} (normalize-props raw-props)]
    (into [:> primitive/Root (-> props
                                 (assoc :data-slot "aspect-ratio" :class (merge-classes class))
                                 (dissoc :class-name))]
          children)))
