(ns clojure-shadcn.stories.tag-combobox.tag-creation-workflow
  (:require
   [clojure-shadcn.ui.components.tag-combobox :as tag-combobox]
   [reagent.core                              :as r]))

(defn example
  []
  (let [tags (r/atom #{"existing-tag-1" "existing-tag-2"})
        selected (r/atom nil)
        creation-log (r/atom [])]
    (fn [] [:div {:class "p-8 space-y-4"}
            [:div
             [:h3 {:class "text-lg font-semibold mb-2"}
              "Tag Creation Demo"]
             [:p {:class "text-sm text-muted-foreground mb-4"}
              "Try typing 'new-tag' to see the create option"]
             [tag-combobox/tag-combobox {:tags @tags
                                         :selected-tag @selected
                                         :on-select #(reset! selected %)
                                         :on-create (fn [new-tag]
                                                      (swap! tags conj new-tag)
                                                      (reset! selected new-tag)
                                                      (swap! creation-log conj
                                                        {:tag new-tag
                                                         :timestamp (.toISOString (js/Date.))}))}]]
            [:div {:class "mt-6 p-4 bg-muted rounded-md"}
             [:p {:class "text-sm font-medium mb-2"}
              "Available tags:"]
             [:div {:class "flex flex-wrap gap-2 mb-4"}
              (for [tag @tags]
                ^{:key tag}
                [:span {:class "text-xs bg-primary text-primary-foreground px-2 py-1 rounded"}
                 tag])]
             (when (seq @creation-log)
               [:div
                [:p {:class "text-sm font-medium mb-2"}
                 "Creation log:"]
                [:div {:class "space-y-1"}
                 (for [[idx entry] (map-indexed vector @creation-log)]
                   ^{:key idx}
                   [:p {:class "text-xs"}
                    (str "Created: " (:tag entry))])]])]])))
