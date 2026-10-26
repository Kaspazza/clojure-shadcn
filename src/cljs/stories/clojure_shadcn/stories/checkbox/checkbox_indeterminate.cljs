(ns clojure-shadcn.stories.checkbox.checkbox-indeterminate
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [reagent.core                          :as r]))

(defn example
  []
  (r/with-let [checked (r/atom :indeterminate)]
              [:div {:class "p-6 space-y-2"}
               [:div {:class "flex items-center gap-2"}
                [checkbox/checkbox {:checked @checked
                                    :on-checked-change #(reset! checked %)}]
                [:span {:class "text-sm"}
                 "Select all projects"]]
               [:p {:class "text-muted-foreground text-sm"}
                (str "Current state: " @checked)]]))
