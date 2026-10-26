(ns clojure-shadcn.stories.message-scroller-composite-stories
  (:require
   [clojure-shadcn.stories.helpers                         :as helpers]
   [clojure-shadcn.stories.message-scroller-composite.demo :as demo]
   [reagent.core                                           :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Composites/Message Scroller"
       :parameters #js {:layout "centered"}})


(defstory Demo
          "Read-only queued chat demonstrating anchored, intent-aware scrolling."
          {:source-code (embed-source "clojure-shadcn.stories.message-scroller-composite.demo")}
          []
          (r/as-element (helpers/wrap-component [demo/example])))
