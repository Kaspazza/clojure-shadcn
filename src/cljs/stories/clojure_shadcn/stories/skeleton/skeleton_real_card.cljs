(ns clojure-shadcn.stories.skeleton.skeleton-real-card
  (:require
   [clojure-shadcn.ui.components.card     :as card]
   [clojure-shadcn.ui.components.skeleton :as skeleton]))

(defn example
  []
  [card/card {:class "w-full max-w-sm"
              :aria-busy true
              :aria-label "Loading project"}
   [card/card-header {}
    [skeleton/skeleton {:class "h-6 w-40"}]
    [skeleton/skeleton {:class "h-4 w-64"}]]
   [card/card-content {:class "space-y-3"}
    [skeleton/skeleton {:class "aspect-video w-full rounded-lg"}]
    [skeleton/skeleton {:class "h-4 w-full"}]]
   [card/card-footer {}
    [skeleton/skeleton {:class "h-9 w-full"}]]])
