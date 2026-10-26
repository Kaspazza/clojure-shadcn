(ns clojure-shadcn.stories.spinner.spinner-button
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [:div {:class "p-6 flex flex-col items-start gap-4"}
   (button/button {:disabled true
                   :size :sm}
                  [spinner/spinner {}]
                  "Loading...")
   (button/button {:variant :outline
                   :disabled true
                   :size :sm}
                  [spinner/spinner {}]
                  "Please wait")
   (button/button {:variant :secondary
                   :disabled true
                   :size :sm}
                  [spinner/spinner {}]
                  "Processing")])
