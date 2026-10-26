(ns clojure-shadcn.stories.tag-combobox.multiple-tag-comboboxes
  (:require
   [clojure-shadcn.ui.components.tag-combobox :as tag-combobox]
   [reagent.core                              :as r]))

(defn example
  []
  (let [priority-tags (r/atom #{"low" "medium" "high" "critical"})
        status-tags (r/atom #{"todo" "doing" "done" "archived"})
        selected-priority (r/atom nil)
        selected-status (r/atom nil)]
    (fn [] [:div {:class "p-8 space-y-6"}
            [:div
             [:h3 {:class "text-lg font-semibold mb-2"}
              "Task Management"]
             [:div {:class "flex gap-4 mt-4"}
              [:div {:class "space-y-2"}
               [:label {:class "text-sm font-medium"}
                "Priority"]
               [tag-combobox/tag-combobox {:tags @priority-tags
                                           :selected-tag @selected-priority
                                           :placeholder "+ Priority"
                                           :on-select #(reset! selected-priority %)
                                           :on-create (fn [new-tag]
                                                        (swap! priority-tags conj new-tag)
                                                        (reset! selected-priority new-tag))}]]
              [:div {:class "space-y-2"}
               [:label {:class "text-sm font-medium"}
                "Status"]
               [tag-combobox/tag-combobox {:tags @status-tags
                                           :selected-tag @selected-status
                                           :placeholder "+ Status"
                                           :on-select #(reset! selected-status %)
                                           :on-create (fn [new-tag]
                                                        (swap! status-tags conj new-tag)
                                                        (reset! selected-status new-tag))}]]]]
            [:div {:class "mt-6 p-4 bg-muted rounded-md"}
             [:p {:class "text-sm font-medium"}
              "Selection:"]
             [:p {:class "text-xs mt-2"}
              (str "Priority: " @selected-priority)]
             [:p {:class "text-xs"}
              (str "Status: " @selected-status)]]])))
