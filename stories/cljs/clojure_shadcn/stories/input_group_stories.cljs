(ns clojure-shadcn.stories.input-group-stories
  (:require
   ["lucide-react" :refer [Search]]
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.input-group :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Components/Input Group"
       :parameters #js {:layout "centered"}})

(defn ^:export Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Compound text inputs with semantic addons."
     :npm-install "npm install lucide-react"
     :source-code (embed-source "clojure-shadcn.ui.components.input_group")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/input_group.cljs"
     :filename "input_group.cljs"}]))

(defstory WithAddon
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "w-80"}
     [sut/input-group {}
      [sut/input-group-addon {}
       [:> Search {:aria-hidden true}]]
      [sut/input-group-input {:aria-label "Search" :placeholder "Search…"}]
      [sut/input-group-addon {:align :inline-end}
       [sut/input-group-text {} "⌘K"]]]])))
