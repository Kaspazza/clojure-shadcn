(ns clojure-shadcn.stories.card.card-form
  (:require
   [clojure-shadcn.ui.components.card :as card]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.input :as input]))

(defn example []
  [card/card {:class "w-full max-w-md"}
   [card/card-header {}
    [card/card-title {} [:h2 "Create project"]]
    [card/card-description {} "Deploy a new project to your workspace."]
    [card/card-action {} (button/button {:variant :ghost :size :sm} "Import")]]
   [card/card-content {:class "space-y-4"}
    [:div {:class "grid gap-2"}
     [:label {:for "project-name" :class "text-sm font-medium"} "Project name"]
     [input/input {:id "project-name" :placeholder "Acme website"}]]
    [:div {:class "grid gap-2"}
     [:label {:for "project-domain" :class "text-sm font-medium"} "Domain"]
     [input/input {:id "project-domain" :placeholder "acme.example.com"}]]]
   [card/card-footer {:class "justify-between border-t"}
    (button/button {:variant :outline} "Cancel")
    (button/button {} "Create project")]])
