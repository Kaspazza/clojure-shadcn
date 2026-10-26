(ns clojure-shadcn.stories.tag-combobox.tag-with-custom-width
  (:require
   [clojure-shadcn.ui.components.tag-combobox :as tag-combobox]
   [reagent.core                              :as r]))

(defn example
  []
  (let [tags (r/atom #{"red" "green" "blue" "yellow" "purple"})
        selected (r/atom nil)]
    (fn [] [:div {:class "p-8"}
            [:h3 {:class "text-lg font-semibold mb-4"}
             "Color Tag"]
            [tag-combobox/tag-combobox
             {:tags @tags
              :selected-tag @selected
              :class "w-[200px]"
              :placeholder "+ Choose color"
              :on-select #(reset! selected %)
              :on-create (fn [new-tag] (swap! tags conj new-tag) (reset! selected new-tag))}]])))
