(ns clojure-shadcn.stories.button-group.button-group-separator
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.button-group :as button-group]))

(defn example
  []
  [button-group/button-group {}
   [button/button {:variant :outline}
    "Save"]
   [button-group/button-group-separator {}]
   [button/button {:variant :outline}
    "Options"]])
