(ns clojure-shadcn.stories.spinner.spinner-item
  (:require
   [clojure-shadcn.ui.components.item    :as item]
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [item/item {:variant :outline
              :class "max-w-md"
              :role "status"}
   [item/item-media {:variant :icon}
    [spinner/spinner {:class "size-5 text-primary"}]]
   [item/item-content {}
    [item/item-title {}
     "Importing workspace"]
    [item/item-description {}
     "Checking files and resolving dependencies."]]])
