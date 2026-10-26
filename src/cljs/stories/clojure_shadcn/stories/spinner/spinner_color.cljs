(ns clojure-shadcn.stories.spinner.spinner-color
  (:require
   [clojure-shadcn.ui.components.spinner :as spinner]))

(defn example
  []
  [:div {:class "p-6 flex items-center gap-6"}
   [spinner/spinner {:class "size-6 text-red-500"}]
   [spinner/spinner {:class "size-6 text-green-500"}]
   [spinner/spinner {:class "size-6 text-blue-500"}]
   [spinner/spinner {:class "size-6 text-yellow-500"}]
   [spinner/spinner {:class "size-6 text-purple-500"}]])
