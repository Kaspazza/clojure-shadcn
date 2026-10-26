(ns clojure-shadcn.stories.card.card-edge-spacing
  (:require
   [clojure-shadcn.ui.components.card :as card]
   [clojure-shadcn.ui.components.button :as button]))

(defn example []
  [:div {:class "grid gap-6 md:grid-cols-2"}
   [card/card {:class "overflow-hidden pt-0"}
    [:div {:class "grid aspect-video place-items-center bg-muted text-sm text-muted-foreground"}
     "Flush edge media"]
    [card/card-header {:class "border-b"}
     [card/card-title {} "Edge treatment"]
     [card/card-description {} "Media reaches the card boundary while the header retains its inset."]]
    [card/card-content {} "Content keeps the standard six-unit inset."]]
   [card/card {:size :sm}
    [card/card-header {:class "border-b"}
     [card/card-title {} "Compact card"]
     [card/card-description {} "The small size is declared once on the root."]]
    [card/card-content {} "Shared spacing keeps every region aligned."]
    [card/card-footer {:class "border-t"} (button/button {:size :sm} "Continue")]]])
