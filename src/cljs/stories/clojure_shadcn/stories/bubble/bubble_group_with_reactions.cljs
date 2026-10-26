(ns clojure-shadcn.stories.bubble.bubble-group-with-reactions
  (:require [clojure-shadcn.ui.components.bubble :as bubble]))

(defn example []
  [bubble/bubble-group {:class "max-w-lg pb-3"}
   [bubble/bubble {:variant :muted}
    [bubble/bubble-content {} "The release notes are ready for review."]
    [bubble/bubble-reactions {:side :bottom :align :start} "👍 3"]]
   [bubble/bubble {:align :end}
    [bubble/bubble-content {} "Great — I’ll publish them after lunch."]
    [bubble/bubble-reactions {} "✅ 1"]]])
