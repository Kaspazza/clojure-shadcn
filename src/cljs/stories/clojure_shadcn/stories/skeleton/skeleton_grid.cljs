(ns clojure-shadcn.stories.skeleton.skeleton-grid
  (:require
   [clojure-shadcn.ui.components.skeleton :as skeleton]))

(defn example
  []
  [:div {:class "p-6 grid gap-4 sm:grid-cols-3"}
   (for [idx (range 6)]
     ^{:key idx} [skeleton/skeleton {:class "h-24 w-full rounded-lg"}])])
