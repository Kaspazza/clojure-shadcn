(ns clojure-shadcn.stories.tag-combobox.tag-combobox-case-handling
  (:require
   [clojure-shadcn.ui.components.tag-combobox :as tag-combobox]
   [reagent.core                              :as r]))

(defn example
  []
  (let [selected (r/atom nil)]
    (fn []
      [:div {:class "max-w-sm space-y-3 p-6"}
       [tag-combobox/tag-combobox {:tags ["Bug" " bug " "BUG" "Feature"]
                                   :selected-tag @selected
                                   :on-select #(reset! selected %)
                                   :on-create #(reset! selected %)}]
       [:p {:class "text-xs text-muted-foreground"}
        "Open the list: Bug appears once; searching ‘bug’ does not offer a duplicate create action."]])))
