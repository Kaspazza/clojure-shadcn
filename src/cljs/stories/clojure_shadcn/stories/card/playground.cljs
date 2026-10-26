(ns clojure-shadcn.stories.card.playground
  (:require
   [clojure-shadcn.ui.components.card :as card]
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  ([]
   (example {:title "Create project"
             :description "Deploy a new project in one click."
             :action-label "Deploy"}))
  ([args]
  [card/card {:class "w-full max-w-sm"}
   [card/card-header {}
    [card/card-title {} (:title args)]
    [card/card-description {} (:description args)]]
   [card/card-content {} "Configure your project settings."]
   [card/card-footer {:class "justify-end border-t"}
    [button/button {} (or (:action-label args) (:actionLabel args))]]]))
