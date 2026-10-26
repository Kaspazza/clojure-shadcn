(ns clojure-shadcn.stories.button.button-ghost
  (:require
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "p-6"}
   (button/button {:variant :ghost} "Dismiss")])
