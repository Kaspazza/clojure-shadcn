(ns clojure-shadcn.stories.skeleton.skeleton-card
  (:require
   [clojure-shadcn.ui.components.skeleton :as skeleton]))

(defn example
  []
  [:div {:class "p-6"}
   [:div {:class "flex flex-col space-y-3"}
    [skeleton/skeleton {:class "h-[125px] w-[250px] rounded-xl"}]
    [:div {:class "space-y-2"}
     [skeleton/skeleton {:class "h-4 w-[250px]"}]
     [skeleton/skeleton {:class "h-4 w-[200px]"}]]]])
