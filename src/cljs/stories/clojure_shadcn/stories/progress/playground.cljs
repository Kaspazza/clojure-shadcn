(ns clojure-shadcn.stories.progress.playground
  (:require
   [clojure-shadcn.ui.components.progress :as progress]))

(defn example
  ([] (example {:value 60}))
  ([args]
   [:div {:class "w-80"}
    [progress/progress (select-keys args [:value])]]))
