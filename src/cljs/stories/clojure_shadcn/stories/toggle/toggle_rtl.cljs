(ns clojure-shadcn.stories.toggle.toggle-rtl
  (:require
   ["lucide-react"                      :refer [Bold]]
   [clojure-shadcn.ui.components.toggle :as toggle]))

(defn example
  []
  [:div {:dir "rtl"
         :class "p-6"}
   [toggle/toggle {:variant :outline
                   :aria-label "تبديل الخط العريض"}
    [:> Bold]
    "عريض"]])
