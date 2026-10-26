(ns clojure-shadcn.stories.button.button-loading-placements
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [:div {:class "flex flex-wrap items-center gap-3 p-6"}
   (button/button {:disabled true}
                  [spinner/spinner {}]
                  "Saving")
   (button/button {:variant :secondary
                   :disabled true}
                  "Publishing"
                  [spinner/spinner {}])
   (button/button {:variant :outline
                   :disabled true}
                  [spinner/spinner {}]
                  "Loading")
   (button/button {:variant :ghost
                   :size :icon
                   :disabled true
                   :aria-label "Loading"}
                  [spinner/spinner {}])])
