(ns clojure-shadcn.stories.tag-combobox.basic-tag-selection
  (:require
   [clojure-shadcn.ui.components.tag-combobox :as tag-combobox]
   [reagent.core                              :as r]))

(defn example
  []
  (let [available-tags (r/atom #{"urgent" "important" "review" "blocked" "in-progress"})
        selected-tag (r/atom nil)]
    (fn [] [:div {:class "p-8 space-y-4"}
            [:div
             [:h3 {:class "text-lg font-semibold mb-2"}
              "Select a Tag"]
             [:p {:class "text-sm text-muted-foreground mb-4"}
              "Choose from existing tags or create a new one"]
             [tag-combobox/tag-combobox {:tags @available-tags
                                         :selected-tag @selected-tag
                                         :on-select #(reset! selected-tag %)
                                         :on-create (fn [new-tag]
                                                      (swap! available-tags conj new-tag)
                                                      (reset! selected-tag new-tag))}]]])))
