(ns clojure-shadcn.stories.button.button-loading
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [:div {:class "p-6"}
   (button/button {:variant :outline
                   :size :sm
                   :disabled true}
                  [spinner/spinner {:class "size-4"}]
                  "Submitting")])
