(ns clojure-shadcn.stories.empty.empty-background
  (:require
   ["lucide-react"                      :refer [Bell RefreshCcw]]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.empty  :as empty]))

(defn example
  []
  [:div {:class "p-6"}
   [empty/empty {:class "from-muted/50 to-background bg-gradient-to-b from-30%"}
    [empty/empty-header {}
     [empty/empty-media {:variant :icon}
      [:> Bell]]
     [empty/empty-title {}
      "No Notifications"]
     [empty/empty-description {}
      "You're all caught up. New notifications will appear here."]]
    [empty/empty-content {}
     (button/button {:variant :outline
                     :size :sm}
                    [:> RefreshCcw]
                    "Refresh")]]])
