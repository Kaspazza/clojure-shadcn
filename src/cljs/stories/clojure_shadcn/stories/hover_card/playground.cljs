(ns clojure-shadcn.stories.hover-card.playground
  (:require
   [clojure-shadcn.ui.components.hover-card :as hover-card]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:open-delay 700} consumer-args)]
     [hover-card/hover-card
      (select-keys args [:open-delay])
      [hover-card/hover-card-trigger {:as-child true}
       [:a {:href "#"
            :class "underline"}
        "@shadcn"]]
      [hover-card/hover-card-content {}
       "A reusable component library."]])))
