(ns clojure-shadcn.stories.data-table-stories
  "Storybook stories for the Data Table component. Ported from mateuszmazurczak.portfolio.ui-components.data_table."
  (:require
   [clojure-shadcn.stories.data-table.basic-data-table              :as basic-data-table]
   [clojure-shadcn.stories.data-table.playground                    :as playground]
   [clojure-shadcn.stories.data-table.table-empty-state             :as table-empty-state]
   [clojure-shadcn.stories.data-table.table-with-dnd-and-expandable :as
                                                                    table-with-dnd-and-expandable]
   [clojure-shadcn.stories.data-table.table-with-drag-and-drop      :as table-with-drag-and-drop]
   [clojure-shadcn.stories.data-table.table-with-expandable-rows    :as table-with-expandable-rows]
   [clojure-shadcn.stories.data-table.table-with-toolbar            :as table-with-toolbar]
   [clojure-shadcn.stories.helpers                                  :as helpers]
   [reagent.core                                                    :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Data Table"
       :parameters #js {:layout "padded"}})











(defdoc
 Installation
 "Install dependencies and copy the component code into your project."
 []
 (r/as-element
  [helpers/installation-scene
   {:description "Data table component with sorting, filtering, pagination, and selection."
    :npm-install
    "npm install @dnd-kit/core @dnd-kit/modifiers @dnd-kit/sortable @dnd-kit/utilities @tanstack/react-table lucide-react"
    :source-code (embed-source "clojure-shadcn.ui.components.data_table")
    :namespace-path "src/cljs/clojure_shadcn/ui/components/data_table.cljs"
    :filename "data_table.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "data-table"
       :link {:href "https://tanstack.com/table/latest"
              :label "TanStack Table Docs"}
       :description
       "Project wrapper built against @tanstack/react-table v8 (package range ^8.21.3), using the v8 headless table/state model. The UI composition and DnD integration are local to this component; they are not a TanStack v9 API preview."
       :props [{:name ":columns"
                :type "js-array | vector"
                :default nil
                :description "TanStack column definitions."}
               {:name ":data"
                :type "js-array | vector"
                :default nil
                :description "Row data source."}
               {:name ":initial-page-size"
                :type "number"
                :default "25"
                :description "Initial page size."}
               {:name ":initial-column-visibility"
                :type "map"
                :default "{}"
                :description "Initial visibility by column id."}
               {:name ":toolbar-config"
                :type "map"
                :default nil
                :description "Toolbar configuration for search/faceted filters/custom end content."}
               {:name ":empty-state"
                :type "hiccup | component"
                :default nil
                :description "Rendered when there is no data at all."}
               {:name ":no-results-state"
                :type "hiccup | component | function"
                :default nil
                :description "Rendered when filters produce zero rows."}
               {:name ":render-sub-component"
                :type "function"
                :default nil
                :description "Expanded row renderer: (fn [row] ...)."}
               {:name ":get-row-can-expand"
                :type "function"
                :default nil
                :description "Row expandability predicate: (fn [row] boolean)."}
               {:name ":get-row-id"
                :type "function"
                :default nil
                :description
                "Stable identity from original row data; used by selection, expansion, and DnD."}
               {:name ":dnd-config"
                :type "map"
                :default nil
                :description "DnD config {:on-drag-end fn}; requires top-level :get-row-id."}]}]
     [helpers/api-component-card {:component-name "drag-handle-cell-ui"
                                  :description "Reusable drag handle cell for DnD-enabled tables."
                                  :props [{:name ":listeners"
                                           :type "map"
                                           :default nil
                                           :description "DnD listeners map from @dnd-kit."}
                                          {:name ":attributes"
                                           :type "map"
                                           :default nil
                                           :description "DnD attributes map from @dnd-kit."}]}]
     [helpers/api-component-card
      {:component-name "faceted-filter-ui"
       :description "Popover multi-select filter used by toolbar-ui."
       :props [{:name ":title"
                :type "string"
                :default nil
                :description "Human-readable filter title."}
               {:name ":options"
                :type "vector<map>"
                :default nil
                :description "Filter options: {:label :value :icon?}."}
               {:name ":selected-values"
                :type "set<string>"
                :default nil
                :description "Selected option values."}
               {:name ":on-change"
                :type "function"
                :default nil
                :description "Callback: (fn [new-selected-set])."}
               {:name ":facet-counts"
                :type "map<string, number>"
                :default nil
                :description "Count per option value."}]}]
     [helpers/api-component-card
      {:component-name "toolbar-ui"
       :description
       "Top toolbar with text filter, faceted filters, reset action, and optional right-side custom content."
       :props [{:name ":text-filter-value"
                :type "string"
                :default nil
                :description "Current text filter value."}
               {:name ":on-text-filter-change"
                :type "function"
                :default nil
                :description "Callback: (fn [value])."}
               {:name ":text-placeholder"
                :type "string"
                :default "\"Filter items...\""
                :description "Search placeholder."}
               {:name ":faceted-filters"
                :type "vector<map>"
                :default nil
                :description "Faceted filter configs."}
               {:name ":is-filtered?"
                :type "boolean"
                :default "false"
                :description "Whether any filters are active."}
               {:name ":on-reset-filters"
                :type "function"
                :default nil
                :description "Reset callback: (fn [])."}
               {:name ":toolbar-end"
                :type "hiccup | component"
                :default nil
                :description "Right-side content."}]}]
     [helpers/api-component-card
      {:component-name "column-header-ui"
       :description "Column header with sort controls and optional visibility dropdown actions."
       :props [{:name ":title"
                :type "string"
                :default nil
                :description "Column label."}
               {:name ":can-sort?"
                :type "boolean"
                :default "false"
                :description "Sorting enabled flag."}
               {:name ":sort-state"
                :type "string | false | nil"
                :default nil
                :description "'asc' | 'desc' | false | nil."}
               {:name ":on-toggle-sort"
                :type "function"
                :default nil
                :description "Callback: (fn [descending?])."}
               {:name ":on-clear-sort"
                :type "function"
                :default nil
                :description "Clears sorting: (fn [])."}
               {:name ":on-toggle-visibility"
                :type "function"
                :default nil
                :description "Hides column: (fn [])."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}]}]
     [helpers/api-component-card
      {:component-name "pagination-ui"
       :description "Bottom pagination controls with page size selector and navigation buttons."
       :props [{:name ":page-size"
                :type "number"
                :default nil
                :description "Current page size."}
               {:name ":page-index"
                :type "number"
                :default nil
                :description "Current 0-based page index."}
               {:name ":page-count"
                :type "number"
                :default nil
                :description "Total page count."}
               {:name ":selected-count"
                :type "number"
                :default "0"
                :description "Selected row count."}
               {:name ":total-count"
                :type "number"
                :default nil
                :description "Total row count."}
               {:name ":can-previous?"
                :type "boolean"
                :default nil
                :description "Previous page availability."}
               {:name ":can-next?"
                :type "boolean"
                :default nil
                :description "Next page availability."}
               {:name ":on-page-size-change"
                :type "function"
                :default nil
                :description "Callback: (fn [size])."}
               {:name ":on-first-page"
                :type "function"
                :default nil
                :description "Callback: (fn [])."}
               {:name ":on-previous-page"
                :type "function"
                :default nil
                :description "Callback: (fn [])."}
               {:name ":on-next-page"
                :type "function"
                :default nil
                :description "Callback: (fn [])."}
               {:name ":on-last-page"
                :type "function"
                :default nil
                :description "Callback: (fn [])."}]}]
     [:div {:class "rounded-lg border bg-muted/50 p-4"}
      [:h4 {:class "mb-2 text-sm font-semibold"}
       "Provenance"]
      [:p {:class "text-xs text-muted-foreground"}
       "Headless sorting, filtering, faceting, pagination, selection, and expansion come from TanStack Table v8. Rendering, toolbar controls, empty states, and @dnd-kit row reordering are this project's wrapper composition. This reference intentionally makes no v9 or localized-RTL compatibility claim."]]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "For stable selection, expansion, and DnD state, provide :get-row-id at the data-table root."]
       [:li
        "Use :no-results-state for filtered-empty UX and :empty-state for true empty datasets; they serve different states."]
       [:li
        "When enabling drag-and-drop, provide top-level :get-row-id and :dnd-config/:on-drag-end."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[data-table {:columns columns\n             :data rows\n             :initial-page-size 10\n             :toolbar-config {:text-filter {:column-id \"title\"\n                                            :placeholder \"Search tasks...\"}}\n             :get-row-id (fn [row] (aget row \"id\"))\n             :dnd-config {:on-drag-end (fn [active-id over-id] ... )}}]"]]]]])))

(defstory
 BasicDataTable
 "Basic data table with sorting and pagination.\n   \n   Click column headers to sort, use pagination controls at the bottom."
 {:source-code (embed-source "clojure-shadcn.stories.data-table.basic-data-table")}
 []
 (r/as-element (helpers/wrap-component [basic-data-table/example])))

(defstory
 TableWithToolbar
 "Data table with search and filters.\n   \n   Use the search box to filter by title, or use the faceted filters\n   for status and priority."
 {:source-code (embed-source "clojure-shadcn.stories.data-table.table-with-toolbar")}
 []
 (r/as-element (helpers/wrap-component [table-with-toolbar/example])))

(defstory
 TableWithExpandableRows
 "Data table with expandable rows.\n   \n   NEW FEATURE: Click the chevron to expand rows and see additional details."
 {:source-code (embed-source "clojure-shadcn.stories.data-table.table-with-expandable-rows")}
 []
 (r/as-element (helpers/wrap-component [table-with-expandable-rows/example])))

(defstory
 TableWithDragAndDrop
 "Data table with drag-and-drop reordering.\n   \n   NEW FEATURE: Drag rows by the handle on the left to reorder them."
 {:source-code (embed-source "clojure-shadcn.stories.data-table.table-with-drag-and-drop")}
 []
 (r/as-element (helpers/wrap-component [table-with-drag-and-drop/example])))

(defstory
 TableWithDndAndExpandable
 "Data table with BOTH drag-and-drop AND expandable rows.\n   \n   Demonstrates using both new features together."
 {:source-code (embed-source "clojure-shadcn.stories.data-table.table-with-dnd-and-expandable")}
 []
 (r/as-element (helpers/wrap-component [table-with-dnd-and-expandable/example])))

(defstory TableEmptyState
          "Data table with custom empty state."
          {:source-code (embed-source "clojure-shadcn.stories.data-table.table-empty-state")}
          []
          (r/as-element (helpers/wrap-component [table-empty-state/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:initial-page-size 5}
           :arg-types {:initial-page-size {:name "Initial page size"
                                           :control {:type "number"}
                                           :min 1
                                           :max 5
                                           :step 1}}
           :parameters {:playground {:description
                                     "Explore data table initial page size with a live preview."}
                        :controls {:expanded false
                                   :exclude ["columns" "data" "renderers" "on-row-click"]}}
           :source-code (embed-source "clojure-shadcn.stories.data-table.playground")}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
