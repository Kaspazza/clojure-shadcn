(ns clojure-shadcn.stories.button.button-outline
  (:require
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "p-6"}
   (button/button {:variant :outline} "View details")])
