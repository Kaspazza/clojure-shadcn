(ns clojure-shadcn.stories.loader.loader-all-variants
  (:require
   [clojure-shadcn.ui.components.loader :as loader]))

(defn example
  []
  [:div {:class "p-6 flex flex-wrap items-center gap-6"}
   [loader/loader {:variant :circular}]
   [loader/loader {:variant :classic}]
   [loader/loader {:variant :pulse}]
   [loader/loader {:variant :pulse-dot}]
   [loader/loader {:variant :dots}]
   [loader/loader {:variant :typing}]
   [loader/loader {:variant :wave}]
   [loader/loader {:variant :bars}]
   [loader/loader {:variant :terminal}]])
