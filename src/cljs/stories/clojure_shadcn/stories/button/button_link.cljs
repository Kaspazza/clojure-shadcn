(ns clojure-shadcn.stories.button.button-link
  (:require
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "p-6"}
   (button/button {:variant :link} "Learn more")])
