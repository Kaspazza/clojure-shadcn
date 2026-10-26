(ns clojure-shadcn.stories.dialog.playground
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.dialog :as dialog]))

(defn example
  ([] (example {:modal true}))
  ([args]
   [:div {:class "p-6"}
    [dialog/dialog
     (select-keys args [:modal])
     [dialog/dialog-trigger {:as-child true}
      [button/button {:variant :outline}
       "Open dialog"]]
     [dialog/dialog-content {}
      [dialog/dialog-header {}
       [dialog/dialog-title {}
        "Edit profile"]
       [dialog/dialog-description {}
        "Make changes to your profile here."]]]]]))
