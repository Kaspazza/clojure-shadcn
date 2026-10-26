(ns clojure-shadcn.stories.button.button-as-child
  (:require
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "p-6"}
   (button/button {:as-child true}
                  [:a {:href "#"}
                   "Go to login"])])
