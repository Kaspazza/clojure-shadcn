(ns clojure-shadcn.stories.command.command-scrollable
  (:require
   [clojure-shadcn.ui.components.command :as command]))

(defn example
  []
  [:div {:class "max-w-md p-6"}
   [command/command {:class "rounded-lg border"}
    [command/command-input {:placeholder "Jump to a section..."}]
    [command/command-list {:class "max-h-48 overflow-y-auto"}
     (for [[heading items] [["Workspace" ["Overview" "Activity" "Members" "Settings"]]
                            ["Projects" ["Website" "Mobile app" "Design system" "Research"]]
                            ["Account" ["Profile" "Billing" "Security" "Sign out"]]]]
       ^{:key heading}
       [command/command-group {:heading heading}
        (for [item items]
          ^{:key item}
          [command/command-item {:value (str heading " " item)}
           item])])]]])
