(ns clojure-shadcn.stories.skeleton.playground
  (:require
   [clojure-shadcn.ui.components.skeleton :as skeleton]))

(defn example
  ([] (example {:class "h-4 w-[250px] rounded-md"}))
  ([args]
   [:div {:class "p-6"}
    [skeleton/skeleton (select-keys args [:class])]]))
