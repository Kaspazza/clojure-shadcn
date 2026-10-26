(ns clojure-shadcn.stories.bubble.bubble-as-child-links-and-buttons
  (:require [clojure-shadcn.ui.components.bubble :as bubble]))

(defn example []
  [bubble/bubble-group {:class "max-w-lg"}
   [bubble/bubble {:variant :outline}
    [bubble/bubble-content {:as-child? true}
     [:a {:href "https://ui.shadcn.com/docs/components/bubble"
          :target "_blank" :rel "noopener noreferrer"}
      "Open the Bubble documentation →"]]]
   [bubble/bubble {:variant :secondary}
    [bubble/bubble-content {:as-child? true}
     [:button {:type "button" :on-click #(js/console.log "retry")}
      "Retry this response"]]]])
