(ns clojure-shadcn.stories.spinner.spinner-badge
  (:require
   [clojure-shadcn.ui.components.badge   :as badge]
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [:div {:class "p-6 flex items-center gap-4"}
   [badge/badge {}
    [spinner/spinner {}]
    "Syncing"]
   [badge/badge {:variant :secondary}
    [spinner/spinner {}]
    "Updating"]
   [badge/badge {:variant :outline}
    [spinner/spinner {}]
    "Processing"]])
