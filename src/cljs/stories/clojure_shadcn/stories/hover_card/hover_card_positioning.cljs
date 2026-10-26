(ns clojure-shadcn.stories.hover-card.hover-card-positioning
  (:require
   [clojure-shadcn.ui.components.hover-card :as hover-card]))

(defn- project-preview
  []
  [:div {:class "space-y-2"}
   [:h4 {:class "font-semibold"}
    "shadcn/ui"]
   [:p {:class "text-sm text-muted-foreground"}
    "Beautifully designed components you can copy and own."]])

(defn example
  []
  [:div {:class "grid min-h-[32rem] grid-cols-3 place-items-center gap-16 p-24"}
   (for [side [:top :right :bottom :left]
         align [:start :center :end]]
     ^{:key (str (name side) "-" (name align))}
     [hover-card/hover-card {}
      [hover-card/hover-card-trigger {:as-child true}
       [:button {:type "button"
                 :class "rounded-md border px-3 py-2 text-sm"
                 :aria-label (str "Preview " (name side) " " (name align) " placement")}
        (str (name side) " / " (name align))]]
      [hover-card/hover-card-content {:side side
                                      :align align}
       [project-preview]]])])
