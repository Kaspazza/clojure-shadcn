(ns clojure-shadcn.stories.badge.spinner-badge
  (:require
   [clojure-shadcn.ui.components.badge   :as badge]
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [:div {:class "p-6 flex flex-wrap items-center gap-4"}
   [badge/badge {}
    [spinner/spinner {:class "size-4"
                      :data-icon "inline-start"}]
    "Syncing"]
   [badge/badge {:variant :secondary}
    [spinner/spinner {:class "size-4"
                      :data-icon "inline-start"}]
    "Updating"]
   [badge/badge {:variant :outline}
    "Processing"
    [spinner/spinner {:class "size-4"
                      :data-icon "inline-end"}]]])
