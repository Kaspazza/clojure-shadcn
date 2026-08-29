(ns clojure-shadcn.stories.resizable-stories
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.resizable :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Components/Resizable"
       :parameters #js {:layout "centered"}})

(defn ^:export Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Keyboard-accessible resizable panel layouts."
     :npm-install "npm install react-resizable-panels lucide-react"
     :source-code (embed-source "clojure-shadcn.ui.components.resizable")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/resizable.cljs"
     :filename "resizable.cljs"}]))

(defstory Horizontal
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "h-48 w-[600px] overflow-hidden rounded-lg border"}
     [sut/panel-group {:orientation :horizontal}
      [sut/panel {:defaultSize 35}
       [:div {:class "grid h-full place-items-center"} "Navigation"]]
      [sut/handle {:with-handle? true}]
      [sut/panel {:defaultSize 65}
       [:div {:class "grid h-full place-items-center"} "Content"]]]])))
