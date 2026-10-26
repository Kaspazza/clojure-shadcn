(ns clojure-shadcn.stories.input-group.input-group-in-card
  (:require
   ["lucide-react"                           :refer [AtSign]]
   [clojure-shadcn.ui.components.card        :as card]
   [clojure-shadcn.ui.components.input-group :as input-group]))

(defn example
  []
  [card/card {:class "w-full max-w-md"}
   [card/card-header {}
    [card/card-title {}
     "Invite a teammate"]
    [card/card-description {}
     "Send an invitation by email address."]]
   [card/card-content {}
    [input-group/input-group {}
     [input-group/input-group-addon {}
      [:> AtSign {:aria-hidden true}]]
     [input-group/input-group-input {:type "email"
                                     :aria-label "Email address"
                                     :placeholder "name@example.com"}]
     [input-group/input-group-addon {:align :inline-end}
      [input-group/input-group-button {}
       "Invite"]]]]])
