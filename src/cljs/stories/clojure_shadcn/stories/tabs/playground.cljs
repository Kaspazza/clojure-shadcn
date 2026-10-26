(ns clojure-shadcn.stories.tabs.playground
  (:require
   [clojure-shadcn.ui.components.tabs :as tabs]))

(defn example
  ([] (example {:default-value "account"}))
  ([args]
   [tabs/tabs
    (select-keys args [:default-value])
    [tabs/tabs-list {}
     [tabs/tabs-trigger {:value "account"}
      "Account"]
     [tabs/tabs-trigger {:value "password"}
      "Password"]]
    [tabs/tabs-content {:value "account"}
     "Account settings"]
    [tabs/tabs-content {:value "password"}
     "Password settings"]]))
