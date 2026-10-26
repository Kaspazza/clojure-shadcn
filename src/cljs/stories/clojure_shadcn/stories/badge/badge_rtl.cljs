(ns clojure-shadcn.stories.badge.badge-rtl
  (:require
   ["lucide-react"                     :refer [BadgeCheck Bookmark]]
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  []
  [:div {:dir "rtl"
         :class "flex flex-wrap gap-2"}
   [badge/badge {}
    [:> BadgeCheck {:data-icon "inline-start"}]
    "موثّق"]
   [badge/badge {:variant :secondary}
    "محفوظ"
    [:> Bookmark {:data-icon "inline-end"}]]])
