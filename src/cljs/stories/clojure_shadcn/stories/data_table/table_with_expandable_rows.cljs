(ns clojure-shadcn.stories.data-table.table-with-expandable-rows
  (:require
   ["lucide-react"                             :refer [ChevronDown MoreHorizontal]]
   [clojure-shadcn.ui.components.badge         :as badge]
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.checkbox      :as checkbox]
   [clojure-shadcn.ui.components.data-table    :as data-table]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown-menu]
   [reagent.core                               :as r]))

(defn make-task-data
  []
  [{:id "TASK-001"
    :title "Fix navigation bug"
    :status "in-progress"
    :priority "high"
    :assignee "John Doe"}
   {:id "TASK-002"
    :title "Update documentation"
    :status "todo"
    :priority "medium"
    :assignee "Jane Smith"}
   {:id "TASK-003"
    :title "Refactor API calls"
    :status "in-progress"
    :priority "high"
    :assignee "John Doe"}
   {:id "TASK-004"
    :title "Design new landing page"
    :status "done"
    :priority "low"
    :assignee "Bob Johnson"}
   {:id "TASK-005"
    :title "Add unit tests"
    :status "todo"
    :priority "medium"
    :assignee "Jane Smith"}])

(defn make-checkbox-column
  []
  #js {:id "select"
       :header (fn [^js ctx]
                 (let [table (.-table ctx)]
                   [checkbox/checkbox
                    {:checked (or (.getIsAllPageRowsSelected table)
                                  (and (.getIsSomePageRowsSelected table) "indeterminate")
                                  false)
                     :on-checked-change (fn* [%1] (.toggleAllPageRowsSelected table %1))
                     :aria-label "Select all"}]))
       :cell (fn [^js ctx]
               (let [row (.-row ctx)]
                 [checkbox/checkbox {:checked (.getIsSelected row)
                                     :on-checked-change (fn* [%1] (.toggleSelected row %1))
                                     :aria-label "Select row"}]))
       :enableSorting false
       :enableHiding false})

(defn- adapt-reagent-renderers
  "Converts Reagent Hiccup returned by TanStack column renderers to React elements."
  [columns]
  (doseq [^js column columns
          property ["header" "cell"]]
    (when-let [renderer (aget column property)]
      (when (fn? renderer)
        (aset column property (fn [context] (r/as-element (renderer context)))))))
  columns)

(defn make-columns
  []
  (adapt-reagent-renderers
   #js [(make-checkbox-column)
        #js {:accessorKey "id"
             :header (fn [^js ctx] [data-table/column-header-ui
                                    {:title "ID"
                                     :can-sort? true
                                     :sort-state (.getIsSorted (.-column ctx))
                                     :on-toggle-sort (fn* [%1] (.toggleSorting (.-column ctx) %1))
                                     :on-clear-sort (fn* [] (.clearSorting (.-column ctx)))
                                     :on-toggle-visibility
                                     (fn* [] (.toggleVisibility (.-column ctx) false))}])
             :cell (fn [^js ctx] [:div {:class "font-mono text-xs"}
                                  (.getValue ctx)])}
        #js {:accessorKey "title"
             :header (fn [^js ctx] [data-table/column-header-ui
                                    {:title "Title"
                                     :can-sort? true
                                     :sort-state (.getIsSorted (.-column ctx))
                                     :on-toggle-sort (fn* [%1] (.toggleSorting (.-column ctx) %1))
                                     :on-clear-sort (fn* [] (.clearSorting (.-column ctx)))
                                     :on-toggle-visibility
                                     (fn* [] (.toggleVisibility (.-column ctx) false))}])
             :cell (fn [^js ctx] (.getValue ctx))}
        #js {:accessorKey "status"
             :header (fn [^js ctx] [data-table/column-header-ui
                                    {:title "Status"
                                     :can-sort? true
                                     :sort-state (.getIsSorted (.-column ctx))
                                     :on-toggle-sort (fn* [%1] (.toggleSorting (.-column ctx) %1))
                                     :on-clear-sort (fn* [] (.clearSorting (.-column ctx)))}])
             :cell (fn [^js ctx]
                     (let [status (.getValue ctx)]
                       [badge/badge {:variant (case status
                                                "done" :default
                                                "in-progress" :secondary
                                                "todo" :outline
                                                :default)}
                        status]))
             :filterFn (fn [^js row _column-id filter-value]
                         (boolean (some (fn* [%1] (= %1 (aget (.-original row) "status")))
                                        (js->clj filter-value))))}
        #js {:accessorKey "priority"
             :header (fn [^js ctx] [data-table/column-header-ui
                                    {:title "Priority"
                                     :can-sort? true
                                     :sort-state (.getIsSorted (.-column ctx))
                                     :on-toggle-sort (fn* [%1] (.toggleSorting (.-column ctx) %1))
                                     :on-clear-sort (fn* [] (.clearSorting (.-column ctx)))}])
             :cell (fn [^js ctx]
                     (let [priority (.getValue ctx)]
                       [:span {:class (case priority
                                        "high" "text-red-600 font-medium"
                                        "medium" "text-yellow-600"
                                        "low" "text-green-600"
                                        "")}
                        priority]))
             :filterFn (fn [^js row _column-id filter-value]
                         (boolean (some (fn* [%1] (= %1 (aget (.-original row) "priority")))
                                        (js->clj filter-value))))}
        #js {:accessorKey "assignee"
             :header "Assignee"
             :cell (fn [^js ctx] (.getValue ctx))}
        #js {:id "actions"
             :cell (fn [^js _ctx] [dropdown-menu/dropdown-menu {}
                                   [dropdown-menu/dropdown-menu-trigger {:as-child true}
                                    (button/button {:variant :ghost
                                                    :size :icon
                                                    :class "h-8 w-8"
                                                    :aria-label "Open row actions"}
                                                   [:>
                                                    MoreHorizontal
                                                    {:class "h-4 w-4"
                                                     :aria-hidden true}])]
                                   [dropdown-menu/dropdown-menu-content {:align "end"}
                                    [dropdown-menu/dropdown-menu-label {}
                                     "Actions"]
                                    [dropdown-menu/dropdown-menu-item {}
                                     "Edit"]
                                    [dropdown-menu/dropdown-menu-item {}
                                     "Delete"]]])
             :enableSorting false
             :enableHiding false}]))

(defn example
  []
  [(fn []
     (let [data (r/atom (clj->js (make-task-data)))
           expand-column #js {:id "expand"
                              :header ""
                              :cell (fn [^js ctx]
                                      (let [row (.-row ctx)]
                                        (when (.getCanExpand row)
                                          (button/button {:variant :ghost
                                                          :size :icon
                                                          :class "h-8 w-8"
                                                          :on-click (fn* [] (.toggleExpanded row))}
                                                         [:>
                                                          ChevronDown
                                                          {:class (str
                                                                   "h-4 w-4 transition-transform "
                                                                   (when (.getIsExpanded row)
                                                                     "rotate-180"))}]))))
                              :enableSorting false
                              :enableHiding false}
           columns (r/atom (into-array (cons expand-column (make-columns))))
           render-sub-component (fn [^js row]
                                  (let [task (js->clj (.-original row) :keywordize-keys true)]
                                    [:div {:class "p-4 bg-muted"}
                                     [:h4 {:class "font-semibold mb-2"}
                                      "Task Details"]
                                     [:div {:class "space-y-2 text-sm"}
                                      [:p [:strong "ID: "] (:id task)]
                                      [:p
                                       [:strong "Description: "]
                                       "Lorem ipsum dolor sit amet, consectetur adipiscing elit."]
                                      [:p [:strong "Created: "] "2024-01-15"]
                                      [:p [:strong "Due date: "] "2024-02-01"]]]))]
       (fn [] [:div {:class "p-8"}
               [:h3 {:class "text-lg font-semibold mb-4"}
                "Expandable Rows"]
               [:p {:class "text-sm text-muted-foreground mb-4"}
                "Click the chevron icon to expand a row and see more details"]
               [data-table/data-table {:columns @columns
                                       :data @data
                                       :render-sub-component render-sub-component
                                       :initial-page-size 5}]])))])
