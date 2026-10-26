(ns clojure-shadcn.stories.loader.playground
  (:require
   [clojure-shadcn.ui.components.loader :as loader]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args ((fn [{:keys [variant size]
                     :as args}]
                 (cond-> args
                   variant (update :variant keyword)
                   size (update :size keyword)))
               (merge {:variant "circular"
                       :size "md"
                       :text "Thinking"}
                      consumer-args))]
     [:div {:class "p-6"}
      [loader/loader (select-keys args [:variant :size :text])]])))
