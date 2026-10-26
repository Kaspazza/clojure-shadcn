(ns clojure-shadcn.stories.tabs.tabs-disabled
  (:require
   [clojure-shadcn.ui.components.tabs :as tabs]))

(defn example
  []
  [:div {:class "p-6"}
   [tabs/tabs {:default-value "active"}
    [tabs/tabs-list {}
     [tabs/tabs-trigger {:value "active"}
      "Active"]
     [tabs/tabs-trigger {:value "disabled"
                         :disabled true}
      "Disabled"]]
    [tabs/tabs-content {:value "active"}
     "The second tab cannot be selected."]]])
