(ns clojure-shadcn.stories.badge.badge-icons
  (:require
   ["lucide-react"                     :refer [BadgeCheck Bookmark]]
   [clojure-shadcn.ui.components.badge :as badge]))

(defn example
  []
  [:div {:class "flex flex-wrap items-center gap-2"}
   [badge/badge {}
    [:> BadgeCheck {:data-icon "inline-start"}]
    "Verified"]
   [badge/badge {:variant :secondary}
    "Saved"
    [:> Bookmark {:data-icon "inline-end"}]]
   [badge/badge {:variant :outline}
    [:> Bookmark {:data-icon "inline-start"}]
    "Bookmark"]])
