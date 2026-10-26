(ns clojure-shadcn.stories.spinner.playground
  (:require
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  ([] (example {:class "size-4"}))
  ([args]
   [:div {:class "p-6"}
    [spinner/spinner (select-keys args [:class])]]))
