(ns clojure-shadcn.stories.bubble.bubble-conversation
  (:require [clojure-shadcn.ui.components.bubble :as bubble]))

(defn example
  ([]
   (example {:variant :secondary
             :align :start
             :content "Can you send the latest report?"}))
  ([args]
  [bubble/bubble-group {:class "max-w-lg"}
   [bubble/bubble (select-keys args [:variant :align])
    [bubble/bubble-content {} (:content args)]]]))
