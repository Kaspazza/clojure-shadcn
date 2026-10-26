(ns clojure-shadcn.stories.skeleton.skeleton-table
  (:require
   [clojure-shadcn.ui.components.skeleton :as skeleton]))

(defn example
  []
  [:div {:class "w-full max-w-2xl overflow-hidden rounded-lg border"
         :aria-busy true
         :aria-label "Loading invoices"}
   [:div {:class "grid grid-cols-[1fr_8rem_6rem] gap-4 border-b bg-muted/50 p-3"}
    [skeleton/skeleton {:class "h-4 w-24"}]
    [skeleton/skeleton {:class "h-4 w-16"}]
    [skeleton/skeleton {:class "h-4 w-12"}]]
   (for [row (range 4)]
     ^{:key row}
     [:div {:class "grid grid-cols-[1fr_8rem_6rem] gap-4 border-b p-3 last:border-0"}
      [skeleton/skeleton {:class "h-4 w-40"}]
      [skeleton/skeleton {:class "h-4 w-20"}]
      [skeleton/skeleton {:class "h-4 w-14"}]])])
