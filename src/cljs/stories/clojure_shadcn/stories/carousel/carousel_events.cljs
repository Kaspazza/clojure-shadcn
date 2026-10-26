(ns clojure-shadcn.stories.carousel.carousel-events
  (:require
   [clojure-shadcn.ui.components.carousel :as carousel]
   [reagent.core                          :as r]
   [reagent.hooks                         :as rhooks]))

(defn- slide-card
  [label]
  [:div {:class "p-1"}
   [:div {:class "flex aspect-square items-center justify-center rounded-lg border bg-card"}
    [:span {:class "text-3xl font-semibold"}
     label]]])

(r/defc example
 []
 (let [api (rhooks/use-memo #(r/atom nil) [])
       event-log (rhooks/use-memo #(r/atom []) [])
       on-select (rhooks/use-callback (fn []
                                        (when-let [^js current-api @api]
                                          (swap! event-log conj
                                            (str "select → snap "
                                                 (.selectedScrollSnap current-api)))))
                                      [])
       on-settle (rhooks/use-callback (fn [] (swap! event-log conj "settle → carousel stopped")) [])
       on-pointer-down
       (rhooks/use-callback (fn [] (swap! event-log conj "pointerDown → drag started")) [])]
   (rhooks/use-effect (fn []
                        (when-let [^js current-api @api]
                          (.on current-api "select" on-select)
                          (.on current-api "settle" on-settle)
                          (.on current-api "pointerDown" on-pointer-down)
                          (fn []
                            (.off current-api "select" on-select)
                            (.off current-api "settle" on-settle)
                            (.off current-api "pointerDown" on-pointer-down))))
                      [@api])
   [:div {:class "p-6"}
    [carousel/carousel {:set-api (fn [carousel-api] (reset! api carousel-api))
                        :class "w-full max-w-xs"}
     (into [carousel/carousel-content {}]
           (for [idx (range 1 6)]
             [carousel/carousel-item {:key idx}
              [slide-card idx]]))
     [carousel/carousel-previous {}]
     [carousel/carousel-next {}]]
    [:div {:class "mt-4 space-y-1"}
     [:p {:class "text-sm font-semibold"}
      "Event Log:"]
     [:div {:class "rounded-lg border bg-muted/50 p-3 max-h-32 overflow-y-auto"}
      (if (empty? @event-log)
        [:p {:class "text-xs text-muted-foreground"}
         "Interact with carousel..."]
        (into [:div {:class "space-y-1"}]
              (for [[idx event] (map-indexed vector (take-last 5 @event-log))]
                [:p {:key idx
                     :class "text-xs font-mono"}
                 event])))]]]))
