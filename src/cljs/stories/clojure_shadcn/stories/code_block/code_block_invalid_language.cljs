(ns clojure-shadcn.stories.code-block.code-block-invalid-language
  (:require
   [clojure-shadcn.ui.components.code-block :as code-block]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [code-block/code-block
    [code-block/code-block-code {:language "not-a-real-language"
                                 :code "This remains readable when highlighting fails."}]]])
