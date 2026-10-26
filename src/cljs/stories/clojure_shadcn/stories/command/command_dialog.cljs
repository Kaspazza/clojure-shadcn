(ns clojure-shadcn.stories.command.command-dialog
  (:require
   ["lucide-react"                       :refer [Calendar CreditCard Settings User]]
   [clojure-shadcn.ui.components.command :as command]
   [reagent.core                         :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)]
       (fn [] [:div {:class "p-6 space-y-3"}
               [:button {:type "button"
                         :class "rounded-md border px-4 py-2 text-sm font-medium"
                         :on-click (fn* [] (reset! open? true))}
                "Open command palette"]
               [command/command-dialog {:open @open?
                                        :onOpenChange (fn* [%1] (reset! open? %1))
                                        :title "Quick Actions"
                                        :description
                                        "Search grouped workspace and account actions."}
                [command/command-input {:placeholder "Type a command or search..."}]
                [command/command-list {}
                 [command/command-empty {}
                  "No results found."]
                 [command/command-group {:heading "Workspace"}
                  [command/command-item {:value "calendar"
                                         :onSelect (fn* [] (reset! open? false))}
                   [:> Calendar {:aria-hidden true}]
                   [:span "Open calendar"]
                   [command/command-shortcut {}
                    "⌘C"]]
                  [command/command-item {:value "calculator"
                                         :onSelect (fn* [] (reset! open? false))}
                   [:> CreditCard {:aria-hidden true}]
                   [:span "Open billing"]]]
                 [command/command-separator {}]
                 [command/command-group {:heading "Account"}
                  [command/command-item {:value "profile"
                                         :onSelect (fn* [] (reset! open? false))}
                   [:> User {:aria-hidden true}]
                   [:span "Profile"]]
                  [command/command-item {:value "settings"
                                         :onSelect (fn* [] (reset! open? false))}
                   [:> Settings {:aria-hidden true}]
                   [:span "Settings"]]]]]])))])
