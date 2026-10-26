(ns clojure-shadcn.stories.data-table.basic-data-table
  (:require
   ["lucide-react"                             :refer [MoreHorizontal]]
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

(defn make-overview-task-data
  []
  (into
   (make-task-data)
   (map-indexed
    (fn [idx title]
      (let [task-number (+ idx 6)]
        {:id (str "TASK-" (when (< task-number 10) "0") "0" task-number)
         :title title
         :status (nth ["todo" "in-progress" "done"] (mod idx 3))
         :priority (nth ["high" "medium" "low"] (mod (quot idx 3) 3))
         :assignee (nth ["John Doe" "Jane Smith" "Bob Johnson"] (mod idx 3))}))
    ["Improve keyboard navigation"
     "Add loading skeletons"
     "Review error messages"
     "Optimize image delivery"
     "Audit color contrast"
     "Build account settings"
     "Validate signup form"
     "Add search shortcuts"
     "Document API endpoints"
     "Fix mobile menu overflow"
     "Update empty states"
     "Add notification preferences"
     "Improve dashboard performance"
     "Test password recovery"
     "Implement activity history"
     "Review dependency updates"
     "Add export to CSV"
     "Fix timezone formatting"
     "Polish onboarding flow"
     "Add team invitations"
     "Handle expired sessions"
     "Improve table accessibility"
     "Add billing summary"
     "Test offline behavior"
     "Update help articles"
     "Add profile image upload"
     "Simplify filter controls"
     "Fix tooltip positioning"
     "Review responsive layouts"
     "Prepare release checklist"])))

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
     (let [data (r/atom (clj->js (make-overview-task-data)))
           columns (r/atom (make-columns))]
       (fn []
         [:div {:class "p-8"}
          [:h3 {:class "text-lg font-semibold mb-4"}
           "Tasks"]
          [:p {:class "mb-4 text-sm text-muted-foreground"}
           "A single TanStack Table v8 model drives sorting, filtering, pagination, visibility, and row selection."]
          [data-table/data-table
           {:columns @columns
            :data @data
            :initial-page-size 5
            :get-row-id (fn [row] (.-id row))
            :initial-column-visibility {}
            :toolbar-config {:text-filter {:column-id "title"
                                           :placeholder "Search tasks..."}
                             :faceted-filters [{:column-id "status"
                                                :title "Status"
                                                :options [{:label "Todo"
                                                           :value "todo"}
                                                          {:label "In Progress"
                                                           :value "in-progress"}
                                                          {:label "Done"
                                                           :value "done"}]}
                                               {:column-id "priority"
                                                :title "Priority"
                                                :options [{:label "High"
                                                           :value "high"}
                                                          {:label "Medium"
                                                           :value "medium"}
                                                          {:label "Low"
                                                           :value "low"}]}]}}]])))])
