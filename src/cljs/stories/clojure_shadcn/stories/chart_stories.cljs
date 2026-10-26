(ns clojure-shadcn.stories.chart-stories
  (:require
   [clojure-shadcn.stories.chart.bar-chart               :as bar-chart]
   [clojure-shadcn.stories.chart.cartesian-chart-gallery :as cartesian-chart-gallery]
   [clojure-shadcn.stories.chart.interactive-series      :as interactive-series]
   [clojure-shadcn.stories.chart.playground              :as playground]
   [clojure-shadcn.stories.chart.polar-chart-gallery     :as polar-chart-gallery]
   [clojure-shadcn.stories.chart.themed-rtl-chart        :as themed-rtl-chart]
   [clojure-shadcn.stories.chart.tooltip-indicators      :as tooltip-indicators]
   [clojure-shadcn.stories.helpers                       :as helpers]
   [reagent.core                                         :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Chart"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Configured responsive Recharts container, tooltip, and legend."
                        :npm-install "npm install recharts"
                        :source-code (embed-source "clojure-shadcn.ui.components.chart")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/chart.cljs"
                        :filename "chart.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "chart-container"
      :link {:href "https://recharts.github.io/en-US/api/ResponsiveContainer"
             :label "Recharts ResponsiveContainer Docs"}
      :description
      "Provides chart config through context, emits config colors as scoped CSS variables, and renders exactly one Recharts child in ResponsiveContainer."
      :props
      [{:name ":config"
        :type "map, required"
        :default nil
        :description
        "Series key to metadata map. Entries may contain :label, :icon, :color, or :theme {:light ... :dark ...}."}
       {:name ":id"
        :type "string"
        :default nil
        :description
        "Forwarded as the outer div DOM id. CSS-variable scoping always uses an opaque React-generated identifier."}
       {:name ":initial-dimension"
        :type "map"
        :default "{:width 320 :height 200}"
        :description "Converted to JS and passed as ResponsiveContainer initialDimension."}
       {:name ":class / additional props"
        :type "string / map entries"
        :default nil
        :description "Merged/forwarded to the outer div; container-only props are removed."}]}]
    [helpers/api-component-card
     {:component-name "chart-tooltip / chart-legend"
      :description
      "Direct aliases of Recharts Tooltip and Legend. Their props are Recharts props and must be passed with React-compatible value shapes."
      :props [{:name ":content"
               :type "React renderer"
               :default nil
               :description "Usually wraps tooltip-content or legend-content with r/as-element."}]}]
    [helpers/api-component-card
     {:component-name "chart-tooltip-content / tooltip-content"
      :description
      "Recharts content renderer; must run beneath chart-container so use-chart can resolve labels, icons, and colors."
      :props [{:name ":indicator"
               :type ":dot | :line | :dashed"
               :default ":dot"
               :description "Marker style."}
              {:name ":hide-label? / :hide-indicator?"
               :type "boolean"
               :default nil
               :description "Suppresses the corresponding visual."}
              {:name ":formatter / :label-formatter"
               :type "function"
               :default nil
               :description "Custom value/item or label rendering callbacks."}
              {:name ":name-key / :class"
               :type "string"
               :default nil
               :description "Config lookup override and merged classes."}]}]
    [helpers/api-component-card
     {:component-name "chart-legend-content / legend-content / chart-style / use-chart"
      :description
      "Legend content renderer and context hook. Both require a chart-container ancestor; use-chart throws outside one."
      :props [{:name "Recharts renderer props"
               :type "JavaScript object"
               :default nil
               :description
               "legend-content consumes payload and verticalAlign supplied by Recharts."}]}]])))

(defstory BarChart
          {:source-code (embed-source "clojure-shadcn.stories.chart.bar-chart")}
          []
          (r/as-element (helpers/wrap-component [bar-chart/example])))

(defstory InteractiveSeries
          "Consumer-controlled series selector; the chart remains a standard Recharts composition."
          {:source-code (embed-source "clojure-shadcn.stories.chart.interactive-series")}
          []
          (r/as-element (helpers/wrap-component [interactive-series/example])))

(defstory
 TooltipIndicators
 "Supported tooltip indicator matrix. Hover each chart to compare dot, line, dashed, and hidden indicators."
 {:source-code (embed-source "clojure-shadcn.stories.chart.tooltip-indicators")}
 []
 (r/as-element (helpers/wrap-component [tooltip-indicators/example])))

(defstory
 CartesianChartGallery
 "Area and line charts compare axis, grid, legend, cursor, and tooltip formatting behaviors without repeating cosmetic permutations."
 {:source-code (embed-source "clojure-shadcn.stories.chart.cartesian-chart-gallery")}
 []
 (r/as-element (helpers/wrap-component [cartesian-chart-gallery/example])))

(defstory
 PolarChartGallery
 "Pie, radar, and radial-bar compositions cover category tooltips, polar grids/axes, legends, and compact progress-like charts."
 {:source-code (embed-source "clojure-shadcn.stories.chart.polar-chart-gallery")}
 []
 (r/as-element (helpers/wrap-component [polar-chart-gallery/example])))

(defstory
 ThemedRtlChart
 "Theme-aware series colors and an honestly expressed RTL chart: DOM direction plus a reversed categorical axis."
 {:source-code (embed-source "clojure-shadcn.stories.chart.themed-rtl-chart")}
 []
 (r/as-element (helpers/wrap-component [themed-rtl-chart/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:source-code (embed-source "clojure-shadcn.stories.chart.playground")
           :args {:class "min-h-[260px] w-full"}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description "Explore chart css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude
                                   ["children" "config" "data" "content" "tick-formatter"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
