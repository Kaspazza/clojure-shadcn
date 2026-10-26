(ns clojure-shadcn.stories.tabs.tabs-basic
  (:require
   [clojure-shadcn.ui.components.tabs :as tabs]))

(defn example
  []
  [:div {:class "w-[420px] p-6"}
   [tabs/tabs {:default-value "account"}
    [tabs/tabs-list {}
     [tabs/tabs-trigger {:value "account"}
      "Account"]
     [tabs/tabs-trigger {:value "password"}
      "Password"]]
    [tabs/tabs-content {:value "account"
                        :class "rounded-md border p-4"}
     "Manage account preferences."]
    [tabs/tabs-content {:value "password"
                        :class "rounded-md border p-4"}
     "Change your password."]]])
