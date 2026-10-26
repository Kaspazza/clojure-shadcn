(ns clojure-shadcn.stories.button.button-with-icon
  (:require
   ["lucide-react"                      :refer [GitBranch]]
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "p-6"}
   (button/button {:variant :outline
                   :size :sm}
                  [:> GitBranch]
                  "New branch")])
