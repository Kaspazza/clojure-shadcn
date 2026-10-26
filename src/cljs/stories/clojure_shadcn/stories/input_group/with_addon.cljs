(ns clojure-shadcn.stories.input-group.with-addon
  (:require
   ["lucide-react"                           :refer [Search]]
   [clojure-shadcn.ui.components.input-group :as input-group]))

(defn example
  []
  [:div {:class "w-80"}
   [input-group/input-group {}
    [input-group/input-group-addon {}
     [:> Search {:aria-hidden true}]]
    [input-group/input-group-input {:aria-label "Search"
                                    :placeholder "Search…"}]
    [input-group/input-group-addon {:align :inline-end}
     [input-group/input-group-text {}
      "⌘K"]]]])
