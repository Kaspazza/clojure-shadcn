(ns clojure-shadcn.stories.carousel.carousel-orientation
  (:require
   [clojure-shadcn.ui.components.carousel :as carousel]))

(defn- slide-card
  [label]
  [:div {:class "p-1"}
   [:div {:class "flex aspect-square items-center justify-center rounded-lg border bg-card"}
    [:span {:class "text-3xl font-semibold"}
     label]]])

(defn example
  []
  [:div {:class "p-6"}
   [carousel/carousel {:opts {:align "start"}
                       :orientation :vertical
                       :class "w-full max-w-xs"}
    (into [carousel/carousel-content {:class "h-[340px]"}]
          (for [idx (range 1 6)]
            [carousel/carousel-item {:key idx}
             [slide-card idx]]))
    [carousel/carousel-previous {}]
    [carousel/carousel-next {}]]])
