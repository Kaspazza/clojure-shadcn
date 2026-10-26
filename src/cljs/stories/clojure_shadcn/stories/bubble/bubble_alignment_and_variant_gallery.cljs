(ns clojure-shadcn.stories.bubble.bubble-alignment-and-variant-gallery
  (:require [clojure-shadcn.ui.components.bubble :as bubble]))

(defn example []
  [bubble/bubble-group {:class "max-w-2xl"}
   (for [[variant align copy] [[:default :end "Primary user message"]
                              [:secondary :start "Secondary assistant response"]
                              [:muted :start "Muted supporting context"]
                              [:tinted :start "Tinted generated suggestion"]
                              [:outline :end "Outlined pending reply"]
                              [:ghost :start "Ghost long-form response"]
                              [:destructive :start "Destructive delivery failure"]]]
     ^{:key variant}
     [bubble/bubble {:variant variant :align align}
      [bubble/bubble-content {} copy]])])
