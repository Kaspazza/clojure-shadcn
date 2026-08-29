(ns clojure-shadcn.stories.card-stories
  (:require [clojure-shadcn.stories.helpers :as helpers]
            [clojure-shadcn.ui.components.button :as button]
            [clojure-shadcn.ui.components.card :as sut]
            [clojure-shadcn.ui.components.input :as input]
            [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))
(def ^:export default #js {:title "Components/Card" :parameters #js {:layout "padded"}})
(defn ^:export Installation [] (r/as-element [helpers/installation-scene {:description "Composable content container with header, action, content, and footer regions." :npm-install nil :source-code (embed-source "clojure-shadcn.ui.components.card") :namespace-path "src/cljs/clojure_shadcn/ui/components/card.cljs" :filename "card.cljs"}]))
(defstory CardDemo []
  (r/as-element (helpers/wrap-component
    [sut/card {:class "w-full max-w-sm"}
     [sut/card-header {} [sut/card-title {} "Create project"] [sut/card-description {} "Deploy a new project in one click."] [sut/card-action {} [button/button {:variant :ghost :size :sm} "Help"]]]
     [sut/card-content {:class "space-y-2"} [:label {:for "project-name" :class "text-sm font-medium"} "Name"] [input/input {:id "project-name" :placeholder "My project"}]]
     [sut/card-footer {:class "justify-between border-t"} [button/button {:variant :outline} "Cancel"] [button/button {} "Deploy"]]])))
(defstory CardSimple []
  (r/as-element (helpers/wrap-component [sut/card {:class "max-w-md"} [sut/card-header {} [sut/card-title {} "Notifications"] [sut/card-description {} "You have three unread messages."]] [sut/card-content {} "Your latest activity appears here."]])))
