(ns clojure-shadcn.stories.button-group.button-group-with-text
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.button-group :as button-group]))

(defn example
  []
  [button-group/button-group {}
   [button-group/button-group-text {}
    "https://"]
   [button/button {:variant :outline}
    "example.com"]])
