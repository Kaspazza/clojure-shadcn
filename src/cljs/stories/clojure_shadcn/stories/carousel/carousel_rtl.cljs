(ns clojure-shadcn.stories.carousel.carousel-rtl
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
  [:div {:dir "rtl"
         :class "p-6"}
   [carousel/carousel {:opts {:direction "rtl"
                              :align "start"}
                       :class "w-full max-w-sm"}
    (into [carousel/carousel-content {}]
          (for [idx (range 1 7)]
            [carousel/carousel-item {:key idx
                                     :class "basis-1/2"}
             [slide-card idx]]))
    [carousel/carousel-previous {:aria-label "الشريحة السابقة"}]
    [carousel/carousel-next {:aria-label "الشريحة التالية"}]]])
