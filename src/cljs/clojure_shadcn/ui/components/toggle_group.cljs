(ns clojure-shadcn.ui.components.toggle-group
  (:require
   ["@radix-ui/react-toggle-group"      :as primitive]
   ["react"                             :as react]
   [clojure-shadcn.ui.components.toggle :refer [toggle-classes]]
   [clojure-shadcn.utils.props          :refer [normalize-props]]
   [clojure-shadcn.utils.styles         :refer [merge-classes]]))

(def ^:private group-context
  (react/createContext #js {:variant "default"
                            :size "default"}))

(defn toggle-group
  [{:as raw-props} & children]
  (let [{:keys [class variant size]
         :or {variant :default
              size :default}
         :as props}
        (normalize-props raw-props)]
    [:>
     (.-Provider group-context)
     {:value #js {:variant (name variant)
                  :size (name size)}}
     (into
      [:>
       primitive/Root
       (->
         props
         (assoc
          :data-slot "toggle-group"
          :data-variant (name variant)
          :data-size (name size)
          :class
          (merge-classes
           "group/toggle-group flex w-fit items-center rounded-md data-[variant=outline]:shadow-xs"
           class))
         (dissoc :class-name :variant :size))]
      children)]))

(defn toggle-group-item
  [{:as raw-props} & children]
  (let [context (react/useContext group-context)
        {:keys [class variant size]
         :as props}
        (normalize-props raw-props)
        variant (or variant (keyword (.-variant context)))
        size (or size (keyword (.-size context)))]
    (into
     [:>
      primitive/Item
      (->
        props
        (assoc
         :data-slot "toggle-group-item"
         :data-variant (name variant)
         :data-size (name size)
         :class
         (merge-classes
          (toggle-classes {:variant variant
                           :size size})
          "min-w-0 flex-1 shrink-0 rounded-none shadow-none first:rounded-l-md last:rounded-r-md focus:z-10 focus-visible:z-10 data-[variant=outline]:border-l-0 data-[variant=outline]:first:border-l"
          class))
        (dissoc :class-name :variant :size))]
     children)))
