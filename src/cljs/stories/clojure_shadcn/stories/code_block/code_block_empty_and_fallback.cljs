(ns clojure-shadcn.stories.code-block.code-block-empty-and-fallback
  (:require
   [clojure-shadcn.ui.components.code-block :as code-block]))

(defn example
  []
  [:div {:class "grid gap-4 p-6"}
   [:div
    [:p {:class "mb-2 text-xs text-muted-foreground"}
     "Empty string"]
    [code-block/code-block
     [code-block/code-block-code {:code ""
                                  :language "clojure"}]]]
   [:div
    [:p {:class "mb-2 text-xs text-muted-foreground"}
     "Nil code"]
    [code-block/code-block
     [code-block/code-block-code {:code nil
                                  :language "clojure"}]]]])
