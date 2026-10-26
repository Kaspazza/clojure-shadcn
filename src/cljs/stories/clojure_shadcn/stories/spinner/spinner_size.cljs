(ns clojure-shadcn.stories.spinner.spinner-size
  (:require
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [:div {:class "p-6 flex items-center gap-6"}
   [spinner/spinner {:class "size-3"}]
   [spinner/spinner {:class "size-4"}]
   [spinner/spinner {:class "size-6"}]
   [spinner/spinner {:class "size-8"}]])
