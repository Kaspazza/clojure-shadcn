(ns clojure-shadcn.stories.code-block.code-block-multiple-languages
  (:require
   [clojure-shadcn.ui.components.code-block :as code-block]))

(defn example
  []
  [:div {:class "p-6 grid gap-4"}
   [code-block/code-block {}
    [code-block/code-block-code {:language "javascript"
                                 :code "const greet = (name) => `Hello ${name}`"}]]
   [code-block/code-block {}
    [code-block/code-block-code {:language "python"
                                 :code "def greet(name):\n    return f'Hello {name}'"}]]])
