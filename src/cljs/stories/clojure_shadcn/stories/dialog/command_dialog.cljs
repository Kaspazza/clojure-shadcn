(ns clojure-shadcn.stories.dialog.command-dialog
  (:require
   ["lucide-react"                       :refer [Calendar CreditCard Settings Smile User]]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.command :as command]
   [clojure-shadcn.ui.components.dialog  :as dialog]
   [reagent.core                         :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom false)]
       (fn [] [:div {:class "p-6 space-y-3"}
               [:p {:class "text-muted-foreground text-sm"}
                "Press the button to open the command palette."]
               [dialog/dialog {:open @open?
                               :on-open-change (fn* [%1] (reset! open? %1))}
                [dialog/dialog-trigger {:as-child true}
                 (button/button {:variant :outline} "Open Command Palette")]
                [dialog/dialog-content {:class "p-0 overflow-hidden"}
                 [command/command {:class "[&_[data-slot=command-input-wrapper]]:h-12"}
                  [command/command-input {:placeholder "Type a command or search..."}]
                  [command/command-list {}
                   [command/command-empty {}
                    "No results found."]
                   [command/command-group {:heading "Suggestions"}
                    [command/command-item {}
                     [:> Calendar]
                     [:span "Calendar"]]
                    [command/command-item {}
                     [:> Smile]
                     [:span "Search Emoji"]]
                    [command/command-item {:disabled true}
                     [:> CreditCard]
                     [:span "Calculator"]]]
                   [command/command-separator {}]
                   [command/command-group {:heading "Settings"}
                    [command/command-item {}
                     [:> User]
                     [:span "Profile"]]
                    [command/command-item {}
                     [:> Settings]
                     [:span "Settings"]]]]]]]])))])
