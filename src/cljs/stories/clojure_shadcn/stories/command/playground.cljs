(ns clojure-shadcn.stories.command.playground
  (:require
   [clojure-shadcn.ui.components.command :as command]))

(defn example
  ([]
   (example {:placeholder "Type a command…"
             :loop false}))
  ([args]
   [:div {:class "p-6"}
    [command/command {:loop (:loop args)
                      :class "rounded-lg border shadow-md md:min-w-[450px]"}
     [command/command-input {:placeholder (:placeholder args)}]
     [command/command-list {}
      [command/command-empty {}
       "No results found."]
      [command/command-group {:heading "Suggestions"}
       [command/command-item {}
        "Calendar"]
       [command/command-item {}
        "Settings"]]]]]))
