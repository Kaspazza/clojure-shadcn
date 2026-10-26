(ns clojure-shadcn.stories.code-block.code-block-single
  (:require
   [clojure-shadcn.ui.components.code-block :as code-block]))

(defn example
  ([]
   (example {:language "clojure"
             :theme "github-light"
             :code "(defn greet [name]\n  (str \"Hello, \" name \"!\"))"}))
  ([args]
   [:div {:class "p-6 max-w-xl"}
    [code-block/code-block {}
     [code-block/code-block-code (select-keys args [:language :theme :code])]]]))
