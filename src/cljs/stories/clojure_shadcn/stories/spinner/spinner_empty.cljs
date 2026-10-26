(ns clojure-shadcn.stories.spinner.spinner-empty
  (:require
   [clojure-shadcn.ui.components.empty   :as empty]
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [empty/empty {:role "status"
                :class "border"}
   [empty/empty-header {}
    [empty/empty-media {}
     [spinner/spinner {:class "size-8"}]]
    [empty/empty-title {}
     "Loading results"]
    [empty/empty-description {}
     "This may take a moment."]]])
