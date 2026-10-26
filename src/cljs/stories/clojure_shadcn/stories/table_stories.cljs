(ns clojure-shadcn.stories.table-stories
  "Storybook stories for the Table component. Ported from mateuszmazurczak.portfolio.ui-components.table."
  (:require
   [clojure-shadcn.stories.helpers                  :as helpers]
   [clojure-shadcn.stories.table.playground         :as example-playground]
   [clojure-shadcn.stories.table.table-demo         :as example-table-demo]
   [clojure-shadcn.stories.table.table-rtl          :as example-table-rtl]
   [clojure-shadcn.stories.table.table-with-actions :as example-table-with-actions]
   [clojure-shadcn.stories.table.typography-table   :as example-typography-table]
   [reagent.core                                    :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Table"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Table component primitives for building data tables."
                        :npm-install "No external dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.table")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/table.cljs"
                        :filename "table.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "table"
       :description
       "Table wrapper that adds horizontal overflow container and forwards extra props to <table>."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to native <table>."}]}]
     [helpers/api-component-card {:component-name "table-header"
                                  :description "Semantic <thead> section with row border styling."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to <thead>."}]}]
     [helpers/api-component-card {:component-name "table-body"
                                  :description "Semantic <tbody> container for data rows."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to <tbody>."}]}]
     [helpers/api-component-card
      {:component-name "table-footer"
       :description "Semantic <tfoot> section with muted background and stronger typography."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to <tfoot>."}]}]
     [helpers/api-component-card
      {:component-name "table-row"
       :description "Table row with hover and selected-state styling via data-state attribute."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name ":data-state"
                :type "string"
                :default nil
                :description "e.g. \"selected\" for selected row styling."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to <tr>."}]}]
     [helpers/api-component-card
      {:component-name "table-head"
       :description "Header cell (<th>) with default left alignment and checkbox spacing helpers."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to <th>."}]}]
     [helpers/api-component-card
      {:component-name "table-cell"
       :description "Body/footer cell (<td>) with whitespace and checkbox alignment helpers."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to <td>."}]}]
     [helpers/api-component-card {:component-name "table-caption"
                                  :description
                                  "Caption text shown below table body (caption-bottom styling)."
                                  :props [{:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes."}
                                          {:name "additional props"
                                           :type "map entries"
                                           :default nil
                                           :description "Forwarded to <caption>."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "table wraps <table> in an overflow-x container, so horizontal scroll is built in."]
       [:li "Use table-row {:data-state \"selected\"} to activate selected-row styling."]
       [:li "Checkbox alignment utilities are already included in table-head/table-cell styles."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[table {}\n [table-header {}\n  [table-row {}\n   [table-head {} \"Name\"]\n   [table-head {} \"Status\"]]]\n [table-body {}\n  [table-row {:data-state \"selected\"}\n   [table-cell {} \"Project Alpha\"]\n   [table-cell {} \"Active\"]]]]"]]]]])))

(defstory
 TableDemo
 "Invoice table with header, body, and footer.

  Native elements: <table>, <thead>, <tbody>, <tfoot>

  Use for structured data with semantic table markup."
 {:source-code (embed-source "clojure-shadcn.stories.table.table-demo")}
 []
 (r/as-element (helpers/wrap-component [example-table-demo/example])))

(defstory
 TypographyTable
 "Typographic table styling example.

  Native elements: <table>

  This example mirrors the typography docs table layout."
 {:source-code (embed-source "clojure-shadcn.stories.table.typography-table")}
 []
 (r/as-element (helpers/wrap-component [example-typography-table/example])))

(defstory
 TableWithActions
 "Table with status badges and row actions.

  Shows how to combine badge and dropdown menu inside table cells.

  Useful for admin dashboards and data management."
 {:source-code (embed-source "clojure-shadcn.stories.table.table-with-actions")}
 []
 (r/as-element (helpers/wrap-component [example-table-with-actions/example])))


(defstory
 TableRtl
 "Semantic RTL table. Direction and logical text alignment are supplied through forwarded native props and classes."
 {:source-code (embed-source "clojure-shadcn.stories.table.table-rtl")}
 []
 (r/as-element (helpers/wrap-component [example-table-rtl/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:class ""}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description "Explore table css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children"]}}
           :source-code (embed-source "clojure-shadcn.stories.table.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))
