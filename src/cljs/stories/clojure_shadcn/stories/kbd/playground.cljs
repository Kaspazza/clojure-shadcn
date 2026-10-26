(ns clojure-shadcn.stories.kbd.playground
  (:require
   [clojure-shadcn.ui.components.kbd :as kbd]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:prefix "Press"
                      :key "K"
                      :suffix "to search"}
                     consumer-args)]
     [:div {:class "flex items-center gap-2 text-sm"}
      (:prefix args)
      [kbd/kbd {}
       "⌘"]
      [kbd/kbd {}
       (:key args)]
      (:suffix args)])))
