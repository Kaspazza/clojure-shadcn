(ns clojure-shadcn.ui.components.scroll-area
  (:require
   ["@radix-ui/react-scroll-area" :as primitive]
   [clojure-shadcn.utils.props    :refer [normalize-props]]
   [clojure-shadcn.utils.styles   :refer [merge-classes]]))

(defn scroll-bar
  [{:as raw-props}]
  (let [{:keys [class orientation]
         :or {orientation :vertical}
         :as props}
        (normalize-props raw-props)
        orientation-name (name orientation)]
    [:>
     primitive/Scrollbar
     (-> props
         (assoc :data-slot "scroll-area-scrollbar"
                :orientation orientation-name
                :class (merge-classes "flex touch-none p-px transition-colors select-none"
                                      (if (= orientation :horizontal)
                                        "h-2.5 flex-col border-t border-t-transparent"
                                        "h-full w-2.5 border-l border-l-transparent")
                                      class))
         (dissoc :class-name))
     [:>
      primitive/Thumb
      {:data-slot "scroll-area-thumb"
       :class "bg-border relative flex-1 rounded-full"}]]))

(defn scroll-area
  [{:as raw-props} & children]
  (let [{:keys [class viewport-class scrollbar? orientation]
         :or {scrollbar? true
              orientation :vertical}
         :as props}
        (normalize-props raw-props)]
    (into
     [:>
      primitive/Root
      (-> props
          (assoc :data-slot "scroll-area" :class (merge-classes "relative" class))
          (dissoc :class-name :viewport-class :scrollbar? :orientation))
      (into
       [:>
        primitive/Viewport
        {:data-slot "scroll-area-viewport"
         :class
         (merge-classes
          "focus-visible:ring-ring/50 size-full rounded-[inherit] transition-[color,box-shadow] outline-none focus-visible:ring-[3px]"
          viewport-class)}]
       children)]
     (when scrollbar?
       [[scroll-bar {:orientation orientation}]
        [:> primitive/Corner {:data-slot "scroll-area-corner"}]]))))
