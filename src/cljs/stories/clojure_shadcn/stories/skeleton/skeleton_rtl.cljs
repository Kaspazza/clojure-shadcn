(ns clojure-shadcn.stories.skeleton.skeleton-rtl
  (:require
   [clojure-shadcn.ui.components.skeleton :as skeleton]))

(defn example
  []
  [:div {:dir "rtl"
         :class "flex w-80 items-center gap-4"}
   [skeleton/skeleton {:class "size-12 shrink-0 rounded-full"}]
   [:div {:class "flex-1 space-y-2"}
    [skeleton/skeleton {:class "ms-auto h-4 w-32"}]
    [skeleton/skeleton {:class "ms-auto h-3 w-48"}]]])
