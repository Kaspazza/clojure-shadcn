(ns clojure-shadcn.stories.collapsible.collapsible-file-tree
  (:require
   [clojure-shadcn.ui.components.collapsible :as collapsible]))

(defn- file-tree-node
  [{:keys [name children]}]
  (if (seq children)
    [collapsible/collapsible {:default-open true
                              :class "ps-3"}
     [collapsible/collapsible-trigger
      {:class "flex w-full items-center gap-2 py-1 text-sm hover:underline"}
      [:span {:aria-hidden true}
       "▾"]
      [:span name]]
     [collapsible/collapsible-content {:class "border-s ps-3"}
      (doall (map-indexed (fn [index child] ^{:key (str name "-" index)} [file-tree-node child])
                          children))]]
    [:div {:class "flex items-center gap-2 py-1 ps-8 text-sm text-muted-foreground"}
     [:span {:aria-hidden true}
      "•"]
     name]))

(defn example
  []
  [:div {:class "w-full max-w-sm rounded-lg border p-3 font-mono"}
   [file-tree-node {:name "src"
                    :children [{:name "components"
                                :children [{:name "button.cljs"} {:name "card.cljs"}]}
                               {:name "stories"
                                :children [{:name "accordion_stories.cljs"}]}
                               {:name "core.cljs"}]}]])
