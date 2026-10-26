(ns clojure-shadcn.stories.alert-dialog.alert-dialog-basic
  (:require
   [clojure-shadcn.ui.components.alert-dialog :as alert-dialog]
   [clojure-shadcn.ui.components.button :as button]))

(defn example []
  [:div {:class "p-6"}
   [alert-dialog/alert-dialog {}
    [alert-dialog/alert-dialog-trigger {:as-child true}
     (button/button {:variant :outline} "Show dialog")]
    [alert-dialog/alert-dialog-content {}
     [alert-dialog/alert-dialog-header {}
      [alert-dialog/alert-dialog-title {} "Are you absolutely sure?"]
      [alert-dialog/alert-dialog-description {}
       "This action cannot be undone. This will permanently delete your account and remove your data from our servers."]]
     [alert-dialog/alert-dialog-footer {}
      [alert-dialog/alert-dialog-cancel {} "Cancel"]
      [alert-dialog/alert-dialog-action {} "Continue"]]]]])
