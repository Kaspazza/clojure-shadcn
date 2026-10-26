(ns clojure-shadcn.stories.code-block.code-block-rapid-async-changes
  (:require
   [clojure-shadcn.ui.components.button     :as button]
   [clojure-shadcn.ui.components.code-block :as code-block]
   [reagent.core                            :as r]))

(defn example
  []
  [(fn []
     (let [selection (r/atom 0)
           samples [{:language "clojure"
                     :theme "github-light"
                     :code "(def answer 42)"}
                    {:language "javascript"
                     :theme "github-dark"
                     :code "const answer = 42"}
                    {:language "python"
                     :theme "github-light"
                     :code "answer = 42"}]]
       (fn []
         (let [sample (nth samples @selection)]
           [:div {:class "p-6 max-w-xl space-y-3"}
            (button/button {:on-click (fn* []
                                        (dotimes [i 9]
                                          (js/setTimeout
                                           (fn [] (reset! selection (mod i (count samples))))
                                           (* i 35))))}
                           "Run rapid changes")
            [code-block/code-block [code-block/code-block-code sample]]]))))])
