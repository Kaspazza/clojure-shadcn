(ns clojure-shadcn.stories.alert-dialog.alert-dialog-small
  (:require
   [clojure-shadcn.ui.components.alert-dialog :as alert-dialog]
   [clojure-shadcn.ui.components.button :as button]))

(defn example []
  [:div {:class "p-6"}
   [alert-dialog/alert-dialog {}
    [alert-dialog/alert-dialog-trigger {:as-child true}
     (button/button {:variant :outline} "Discard draft")]
    [alert-dialog/alert-dialog-content {:size :sm}
     [alert-dialog/alert-dialog-header {}
      [alert-dialog/alert-dialog-title {} "Discard draft?"]
      [alert-dialog/alert-dialog-description {} "Your unsaved edits will be lost."]]
     [alert-dialog/alert-dialog-footer {}
      [alert-dialog/alert-dialog-cancel {} "Cancel"]
      [alert-dialog/alert-dialog-action {} "Discard"]]]]])
