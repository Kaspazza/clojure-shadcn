(ns clojure-shadcn.stories.chart-stories
  (:require
   ["recharts" :as recharts]
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.chart :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Chart"
       :parameters #js {:layout "centered"}})

(defdoc Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Configured responsive Recharts container, tooltip, and legend."
     :npm-install "npm install recharts"
     :source-code (embed-source "clojure-shadcn.ui.components.chart")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/chart.cljs"
     :filename "chart.cljs"}]))

(defstory ApiReference []
  (r/as-element (helpers/wrap-component
    [:div {:class "space-y-4 p-6 max-w-4xl"}
     [helpers/api-component-card {:component-name "chart-container" :link {:href "https://recharts.github.io/en-US/api/ResponsiveContainer" :label "Recharts ResponsiveContainer Docs"} :description "Provides chart config through context, emits config colors as scoped CSS variables, and renders exactly one Recharts child in ResponsiveContainer." :props [{:name ":config" :type "map, required" :default nil :description "Series key to metadata map. Entries may contain :label, :icon, :color, or :theme {:light ... :dark ...}."} {:name ":id" :type "string" :default "generated React id" :description "Stable CSS-variable scope id; supply one for deterministic markup."} {:name ":initial-dimension" :type "map" :default "{:width 320 :height 200}" :description "Converted to JS and passed as ResponsiveContainer initialDimension."} {:name ":class / additional props" :type "string / map entries" :default nil :description "Merged/forwarded to the outer div; container-only props are removed."}]}]
     [helpers/api-component-card {:component-name "chart-tooltip / chart-legend" :description "Direct aliases of Recharts Tooltip and Legend. Their props are Recharts props and must be passed with React-compatible value shapes." :props [{:name ":content" :type "React renderer" :default nil :description "Usually wraps tooltip-content or legend-content with r/as-element."}]}]
     [helpers/api-component-card {:component-name "tooltip-content" :description "Recharts content renderer; must run beneath chart-container so use-chart can resolve labels, icons, and colors." :props [{:name ":indicator" :type ":dot | :line | :dashed" :default ":dot" :description "Marker style."} {:name ":hide-label? / :hide-indicator?" :type "boolean" :default nil :description "Suppresses the corresponding visual."} {:name ":formatter / :label-formatter" :type "function" :default nil :description "Custom value/item or label rendering callbacks."} {:name ":name-key / :class" :type "string" :default nil :description "Config lookup override and merged classes."}]}]
     [helpers/api-component-card {:component-name "legend-content / use-chart" :description "Legend content renderer and context hook. Both require a chart-container ancestor; use-chart throws outside one." :props [{:name "Recharts renderer props" :type "JavaScript object" :default nil :description "legend-content consumes payload and verticalAlign supplied by Recharts."}]}]])))

(def chart-data
  #js [#js {:month "January" :desktop 186 :mobile 80}
       #js {:month "February" :desktop 305 :mobile 200}
       #js {:month "March" :desktop 237 :mobile 120}
       #js {:month "April" :desktop 273 :mobile 190}])

(def chart-config
  {:desktop {:label "Desktop" :color "hsl(var(--chart-1))"}
   :mobile {:label "Mobile" :color "hsl(var(--chart-2))"}})

(defstory BarChart
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "w-[560px] rounded-xl border bg-card p-6"}
     [:h3 {:class "mb-4 font-semibold"} "Visitors by device"]
     [sut/chart-container {:config chart-config :class "min-h-[260px] w-full"}
      [:> recharts/BarChart {:data chart-data :accessibilityLayer true}
       [:> recharts/CartesianGrid {:vertical false}]
       [:> recharts/XAxis {:dataKey "month"
                           :tickLine false
                           :axisLine false
                           :tickMargin 10
                           :tickFormatter #(.slice % 0 3)}]
       [:> sut/chart-tooltip
        {:cursor false
         :content (fn [props]
                    (r/as-element [sut/tooltip-content props]))}]
       [:> sut/chart-legend
        {:content (fn [props]
                    (r/as-element [sut/legend-content props]))}]
       [:> recharts/Bar {:dataKey "desktop"
                         :fill "var(--color-desktop)"
                         :radius 4}]
       [:> recharts/Bar {:dataKey "mobile"
                         :fill "var(--color-mobile)"
                         :radius 4}]]]])))
