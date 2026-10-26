(ns clojure-shadcn.stories.button.button-xs-size
  (:require
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "p-6"}
   (button/button {:size :xs
                   :variant :secondary}
                  "Compact action")])
