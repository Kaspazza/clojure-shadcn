(ns clojure-shadcn.stories.card.card-image
  (:require
   [clojure-shadcn.ui.components.card :as card]
   [clojure-shadcn.ui.components.button :as button]))

(defn example []
  [card/card {:class "w-full max-w-sm overflow-hidden pt-0"}
   [:img {:src "https://images.unsplash.com/photo-1522202176988-66273c2fd55f?auto=format&fit=crop&w=800&q=80"
          :alt "Team collaborating around a table" :class "aspect-video w-full object-cover"}]
   [card/card-header {}
    [card/card-title {} [:h2 "Design systems workshop"]]
    [card/card-description {} "A practical session for building a shared component language."]]
   [card/card-footer {} (button/button {:class "w-full"} "Reserve a seat")]])
