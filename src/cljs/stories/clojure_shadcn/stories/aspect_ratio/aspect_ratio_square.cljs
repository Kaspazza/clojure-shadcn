(ns clojure-shadcn.stories.aspect-ratio.aspect-ratio-square
  (:require [clojure-shadcn.ui.components.aspect-ratio :as aspect-ratio]))

(defn example []
  [:div {:class "w-64 p-6"}
   [aspect-ratio/aspect-ratio
    {:ratio 1 :class "grid place-items-center rounded-lg border bg-muted text-muted-foreground"}
    "1:1"]])
