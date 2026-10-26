(ns clojure-shadcn.stories.tag-combobox.playground
  (:require
   [clojure-shadcn.ui.components.tag-combobox :as tag-combobox]
   [reagent.core                              :as r]))

(defn example
  ([]
   (example {:placeholder "+ Add tag"
             :allow-create true}))
  ([args]
   (r/with-let [tags (r/atom #{"bug" "feature" "urgent"}) selected (r/atom nil)]
               [:div {:class "p-6"}
                [tag-combobox/tag-combobox
                 {:tags @tags
                  :selected-tag @selected
                  :placeholder (:placeholder args)
                  :on-select #(reset! selected %)
                  :on-create (when (:allow-create args)
                               (fn [tag] (swap! tags conj tag) (reset! selected tag)))}]])))
