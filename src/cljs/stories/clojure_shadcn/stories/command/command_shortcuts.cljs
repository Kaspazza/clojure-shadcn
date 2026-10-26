(ns clojure-shadcn.stories.command.command-shortcuts
  (:require
   [clojure-shadcn.ui.components.command :as command]))

(defn example
  []
  [:div {:class "max-w-md p-6"}
   [command/command {:class "rounded-lg border"}
    [command/command-input {:placeholder "Search actions..."}]
    [command/command-list {}
     [command/command-group {:heading "File"}
      [command/command-item {:value "new-file"}
       [:span "New file"]
       [command/command-shortcut {}
        "⌘N"]]
      [command/command-item {:value "open-file"}
       [:span "Open file"]
       [command/command-shortcut {}
        "⌘O"]]]]]])
