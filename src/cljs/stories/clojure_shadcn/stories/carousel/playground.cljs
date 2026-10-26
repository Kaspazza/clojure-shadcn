(ns clojure-shadcn.stories.carousel.playground
  (:require
   [clojure-shadcn.ui.components.carousel :as carousel]))

(defn- slide-card
  [label]
  [:div {:class "p-1"}
   [:div {:class "flex aspect-square items-center justify-center rounded-lg border bg-card"}
    [:span {:class "text-3xl font-semibold"}
     label]]])

(defn example
  ([]
   (example {:orientation :horizontal}))
  ([args]
  [carousel/carousel
   (assoc (select-keys args [:orientation]) :class "w-full max-w-xs")
   [carousel/carousel-content {}
    [carousel/carousel-item {}
     [slide-card "1"]]
    [carousel/carousel-item {}
     [slide-card "2"]]]
   [carousel/carousel-previous {}]
   [carousel/carousel-next {}]]))
