(ns clojure-shadcn.stories.carousel.carousel-position
  (:require
   [clojure-shadcn.ui.components.carousel :as carousel]
   [reagent.core                          :as r]
   [reagent.hooks                         :as rhooks]))

(r/defc example
 []
 (let [slide-card (fn [label]
                    [:div {:class "p-1"}
                     [:div
                      {:class
                       "flex aspect-square items-center justify-center rounded-lg border bg-card"}
                      [:span {:class "text-3xl font-semibold"}
                       label]]])
       api (rhooks/use-memo #(r/atom nil) [])
       current (rhooks/use-memo #(r/atom 1) [])
       total (rhooks/use-memo #(r/atom 0) [])
       sync-position (rhooks/use-callback
                      (fn []
                        (when-let [^js carousel-api @api]
                          (reset! current (inc (.selectedScrollSnap carousel-api)))
                          (reset! total (.-length (.scrollSnapList carousel-api)))))
                      [])]
   (rhooks/use-effect (fn []
                        (when-let [^js carousel-api @api]
                          (sync-position)
                          (.on carousel-api "select" sync-position)
                          (.on carousel-api "reInit" sync-position)
                          (fn []
                            (.off carousel-api "select" sync-position)
                            (.off carousel-api "reInit" sync-position))))
                      [@api sync-position])
   [:div {:class "p-6"}
    [carousel/carousel {:set-api #(reset! api %)
                        :class "w-full max-w-xs"}
     (into [carousel/carousel-content {}]
           (for [idx (range 1 6)]
             [carousel/carousel-item {:key idx}
              [slide-card idx]]))
     [carousel/carousel-previous {}]
     [carousel/carousel-next {}]]
    [:p {:class "mt-4 text-center text-sm text-muted-foreground"
         :aria-live "polite"}
     (str "Slide " @current " of " @total)]]))
