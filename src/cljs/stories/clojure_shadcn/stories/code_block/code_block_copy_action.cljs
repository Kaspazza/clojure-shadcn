(ns clojure-shadcn.stories.code-block.code-block-copy-action
  (:require
   [clojure-shadcn.ui.components.button     :as button]
   [clojure-shadcn.ui.components.code-block :as code-block]
   [reagent.core                            :as r]))

(defn example
  []
  [(fn []
     (let [code "{:db/id :user/email\n :db/valueType :db.type/string}"
           copied? (r/atom false)]
       (fn [] [:div {:class "p-6 max-w-xl"}
               [code-block/code-block {}
                [code-block/code-block-group
                 {:class "px-4 py-2 border-b flex items-center justify-between"}
                 [:span {:class "text-xs text-muted-foreground"}
                  "schema.edn"]
                 (button/button {:size :sm
                                 :variant :ghost
                                 :on-click (fn* []
                                             (-> (.writeText js/navigator.clipboard code)
                                                 (.then (fn [] (reset! copied? true)))))}
                                (if @copied? "Copied" "Copy"))]
                [code-block/code-block-code {:language "clojure"
                                             :code code}]]])))])
