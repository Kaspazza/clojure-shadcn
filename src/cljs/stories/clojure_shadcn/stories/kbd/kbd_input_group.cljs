(ns clojure-shadcn.stories.kbd.kbd-input-group
  (:require
   ["lucide-react"                           :refer [Command]]
   [clojure-shadcn.ui.components.input-group :as input-group]
   [clojure-shadcn.ui.components.kbd         :as kbd]))

(defn example
  []
  [input-group/input-group {:class "max-w-sm"}
   [input-group/input-group-input {:type "search"
                                   :aria-label "Search"
                                   :placeholder "Search documentation..."}]
   [input-group/input-group-addon {:align :inline-end}
    [kbd/kbd-group {}
     [kbd/kbd {}
      [:> Command]]
     [kbd/kbd {}
      "K"]]]])
