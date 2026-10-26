(ns clojure-shadcn.stories.empty.empty-input-group
  (:require
   ["lucide-react"                           :refer [Inbox]]
   [clojure-shadcn.ui.components.empty       :as empty]
   [clojure-shadcn.ui.components.input-group :as input-group]))

(defn example
  []
  [empty/empty {:class "max-w-xl border"}
   [empty/empty-header {}
    [empty/empty-media {:variant :icon}
     [:> Inbox]]
    [empty/empty-title {}
     "Join a workspace"]
    [empty/empty-description {}
     "Enter an invitation code to find your team."]]
   [empty/empty-content {:class "w-full max-w-sm"}
    [input-group/input-group {}
     [input-group/input-group-input {:aria-label "Invitation code"
                                     :placeholder "Invitation code"}]
     [input-group/input-group-addon {:align :inline-end}
      [input-group/input-group-button {}
       "Join"]]]]])
