(ns clojure-shadcn.stories.checkbox.checkbox-table
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.table    :as table]
   [reagent.core                          :as r]))

(defn example
  []
  (r/with-let
   [all-tasks
    [{:id "TASK-001"
      :title "Update documentation"
      :status "Done"
      :priority "Low"}
     {:id "TASK-002"
      :title "Fix login redirect"
      :status "In Progress"
      :priority "High"}
     {:id "TASK-003"
      :title "Add dark mode"
      :status "Todo"
      :priority "Medium"}
     {:id "TASK-004"
      :title "Optimize bundle size"
      :status "In Progress"
      :priority "High"}
     {:id "TASK-005"
      :title "Write unit tests"
      :status "Todo"
      :priority "Medium"}]
    selected
    (r/atom #{})]
   (let [all-ids (set (map :id all-tasks))
         all-selected? (= @selected all-ids)
         some-selected? (and (seq @selected) (not all-selected?))
         toggle-all! (fn [checked?] (reset! selected (if checked? all-ids #{})))
         toggle-row! (fn [id checked?] (swap! selected (if checked? conj disj) id))]
     [:div {:class "p-6"}
      [:div {:class "mb-3 text-sm text-muted-foreground"}
       (str (count @selected) " of " (count all-tasks) " row(s) selected.")]
      [table/table {}
       [table/table-header {}
        [table/table-row {}
         [table/table-head {:class "w-[50px]"}
          [checkbox/checkbox {:checked (if some-selected? :indeterminate all-selected?)
                              :on-checked-change toggle-all!
                              :aria-label "Select all"}]]
         [table/table-head {}
          "Task"]
         [table/table-head {}
          "Title"]
         [table/table-head {}
          "Status"]
         [table/table-head {:class "text-right"}
          "Priority"]]]
       [table/table-body {}
        ;; doall: @selected is derefed while realizing the seq; a
        ;; lazy seq would realize outside the reactive context
        ;; and not re-render.
        (doall (for [{:keys [id title status priority]} all-tasks]
                 ^{:key id}
                 [table/table-row {:class (when (contains? @selected id) "bg-muted/50")}
                  [table/table-cell {}
                   [checkbox/checkbox {:checked (contains? @selected id)
                                       :on-checked-change (partial toggle-row! id)
                                       :aria-label (str "Select " id)}]]
                  [table/table-cell {:class "font-medium"}
                   id]
                  [table/table-cell {}
                   title]
                  [table/table-cell {}
                   status]
                  [table/table-cell {:class "text-right"}
                   priority]]))]]])))
