(ns clojure-shadcn.stories.aspect-ratio.aspect-ratio-portrait
  (:require [clojure-shadcn.ui.components.aspect-ratio :as aspect-ratio]))

(defn example []
  [:div {:class "w-64"}
   [aspect-ratio/aspect-ratio {:ratio 0.75 :class "overflow-hidden rounded-xl bg-muted"}
    [:img {:src "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=600&q=80"
           :alt "Mountain landscape in portrait format" :class "size-full object-cover"}]]])
