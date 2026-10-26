(ns clojure-shadcn.stories.input.playground
  (:require
   [clojure-shadcn.ui.components.input :as input]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:type "email"
                      :placeholder "you@example.com"
                      :default-value ""
                      :disabled false
                      :required false}
                     consumer-args)]
     [:div {:class "max-w-sm p-6"}
      [input/input args]])))
