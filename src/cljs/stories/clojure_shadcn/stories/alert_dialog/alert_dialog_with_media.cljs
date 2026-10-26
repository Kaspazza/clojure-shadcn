(ns clojure-shadcn.stories.alert-dialog.alert-dialog-with-media
  (:require
   ["lucide-react" :refer [TriangleAlert]]
   [clojure-shadcn.ui.components.alert-dialog :as alert-dialog]
   [clojure-shadcn.ui.components.button :as button]))

(defn example []
  [:div {:class "p-6"}
   [alert-dialog/alert-dialog {}
    [alert-dialog/alert-dialog-trigger {:as-child true}
     (button/button {:variant :destructive} "Revoke access")]
    [alert-dialog/alert-dialog-content {}
     [alert-dialog/alert-dialog-header {}
      [alert-dialog/alert-dialog-media {} [:> TriangleAlert {:aria-hidden true}]]
      [alert-dialog/alert-dialog-title {} "Revoke API access?"]
      [alert-dialog/alert-dialog-description {} "All active credentials for this integration will stop working immediately."]]
     [alert-dialog/alert-dialog-footer {}
      [alert-dialog/alert-dialog-cancel {} "Keep access"]
      [alert-dialog/alert-dialog-action {:variant :destructive} "Revoke access"]]]]])
