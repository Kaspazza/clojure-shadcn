(ns clojure-shadcn.stories.tabs.tabs-with-icons
  (:require
   ["lucide-react"                    :refer [Bell Settings User]]
   [clojure-shadcn.ui.components.tabs :as tabs]))

(defn example
  []
  [:div {:class "w-[420px] p-6"}
   [tabs/tabs {:default-value "profile"}
    [tabs/tabs-list {}
     [tabs/tabs-trigger {:value "profile"}
      [:> User {:aria-hidden true}]
      "Profile"]
     [tabs/tabs-trigger {:value "notifications"}
      [:> Bell {:aria-hidden true}]
      "Notifications"]
     [tabs/tabs-trigger {:value "settings"}
      [:> Settings {:aria-hidden true}]
      "Settings"]]
    [tabs/tabs-content {:value "profile"
                        :class "rounded-md border p-4"}
     "Update your public profile."]
    [tabs/tabs-content {:value "notifications"
                        :class "rounded-md border p-4"}
     "Choose which alerts you receive."]
    [tabs/tabs-content {:value "settings"
                        :class "rounded-md border p-4"}
     "Manage workspace defaults."]]])
