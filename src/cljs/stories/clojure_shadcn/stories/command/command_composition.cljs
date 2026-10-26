(ns clojure-shadcn.stories.command.command-composition
  (:require
   [clojure-shadcn.ui.components.command :as command]))

(defn example
  []
  [:div {:class "p-6 max-w-md"}
   [:div {:class "rounded-lg border bg-card p-4 shadow-sm"}
    [:p {:class "text-sm font-medium mb-2"}
     "Quick Actions"]
    [command/command {}
     [command/command-input {:placeholder "Filter actions..."}]
     [command/command-list {}
      [command/command-item {}
       "Create project"]
      [command/command-item {}
       "Invite teammate"]
      [command/command-item {}
       "Open settings"]]]]])
