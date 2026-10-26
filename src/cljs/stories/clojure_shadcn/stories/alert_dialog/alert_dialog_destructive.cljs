(ns clojure-shadcn.stories.alert-dialog.alert-dialog-destructive
  (:require
   [clojure-shadcn.ui.components.alert-dialog :as alert-dialog]
   [clojure-shadcn.ui.components.button :as button]))

(defn example []
  [:div {:class "max-w-md space-y-4 p-6"}
   [:div {:class "rounded-lg border p-4"}
    [:div {:class "flex items-start justify-between gap-4"}
     [:div {:class "space-y-1"}
      [:h3 {:class "font-medium"} "website-redesign"]
      [:p {:class "text-sm text-muted-foreground"} "Production project · 12 collaborators"]]
     [alert-dialog/alert-dialog {}
      [alert-dialog/alert-dialog-trigger {:as-child true}
       (button/button {:variant :destructive :size :sm} "Delete project")]
      [alert-dialog/alert-dialog-content {}
       [alert-dialog/alert-dialog-header {}
        [alert-dialog/alert-dialog-title {} "Delete website-redesign?"]
        [alert-dialog/alert-dialog-description {}
         "The project, its deployments, environment variables, and activity history will be permanently deleted. This cannot be undone."]]
       [alert-dialog/alert-dialog-footer {}
        [alert-dialog/alert-dialog-cancel {} "Keep project"]
        [alert-dialog/alert-dialog-action {:variant :destructive} "Delete project"]]]]]]])
