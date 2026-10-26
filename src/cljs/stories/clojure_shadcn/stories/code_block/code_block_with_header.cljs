(ns clojure-shadcn.stories.code-block.code-block-with-header
  (:require
   [clojure-shadcn.ui.components.button     :as button]
   [clojure-shadcn.ui.components.code-block :as code-block]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [code-block/code-block {}
    [code-block/code-block-group {:class "px-4 py-2 border-b text-xs text-muted-foreground"}
     [:span "handlers.cljs"]
     (button/button {:size :sm
                     :variant :ghost}
                    "Copy")]
    [code-block/code-block-code {:language "clojure"
                                 :code
                                 "(defn handle-request [req]\n  {:status 200 :body \"OK\"})"}]]])
