(ns clojure-shadcn.stories.badge.badge-colors
  (:require
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  []
  [:div {:class "flex flex-wrap gap-2"}
   [badge/badge {:class "bg-emerald-600 text-white hover:bg-emerald-600/90"}
    "Healthy"]
   [badge/badge {:class "bg-amber-500 text-black hover:bg-amber-500/90"}
    "Pending"]
   [badge/badge {:class "bg-sky-600 text-white hover:bg-sky-600/90"}
    "In review"]
   [badge/badge {:variant :outline
                 :class "border-violet-500 text-violet-700 dark:text-violet-300"}
    "Experimental"]])
