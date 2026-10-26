(ns clojure-shadcn.stories.alert-dialog.alert-dialog-controlled
  (:require
   [clojure-shadcn.ui.components.alert-dialog :as alert-dialog]
   [clojure-shadcn.ui.components.button :as button]
   [reagent.core :as r]))

(defn example []
  (let [open? (r/atom false)
        published? (r/atom false)]
    (fn []
      [:div {:class "max-w-md space-y-4 p-6"}
       [:div {:class "rounded-lg border p-4"}
        [:div {:class "flex items-center justify-between gap-4"}
         [:div
          [:h3 {:class "font-medium"} "Version 2.4.0"]
          [:p {:class "text-sm text-muted-foreground"}
           (if @published? "Published to all customers" "Draft · ready to publish")]]
         [:span {:class (if @published?
                          "text-sm font-medium text-emerald-600"
                          "text-sm text-muted-foreground")}
          (if @published? "Published" "Draft")]]]
       (if @published?
         (button/button {:variant :outline :disabled true} "Release published")
         [alert-dialog/alert-dialog {:open @open? :on-open-change #(reset! open? %)}
          [alert-dialog/alert-dialog-trigger {:as-child true}
           (button/button {} "Publish release")]
          [alert-dialog/alert-dialog-content {}
           [alert-dialog/alert-dialog-header {}
            [alert-dialog/alert-dialog-title {} "Publish version 2.4.0?"]
            [alert-dialog/alert-dialog-description {}
             "All customers will immediately receive this release. Rollbacks require a new deployment."]]
           [alert-dialog/alert-dialog-footer {}
            [alert-dialog/alert-dialog-cancel {} "Review again"]
            [alert-dialog/alert-dialog-action {:on-click #(reset! published? true)} "Publish now"]]]])])))
