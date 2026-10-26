(ns clojure-shadcn.stories.button.playground
  (:require
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  ([]
   (example {:label "Continue"
             :variant :default
             :size :default
             :disabled false}))
  ([args]
  [:div {:class "p-6"}
   (button/button (select-keys args [:variant :size :disabled]) (:label args))]))
