(ns clojure-shadcn.stories.chart-stories
  (:require
   ["recharts"                         :as recharts]
   [clojure-shadcn.stories.helpers     :as helpers]
   [clojure-shadcn.ui.components.chart :as sut]
   [reagent.core                       :as r])
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

(def chart-data
  #js [#js {:month "January"
            :desktop 186
            :mobile 80}
       #js {:month "February"
            :desktop 305
            :mobile 200}
       #js {:month "March"
            :desktop 237
            :mobile 120}
       #js {:month "April"
            :desktop 273
            :mobile 190}])

(def chart-config
  {:desktop {:label "Desktop"
             :color "var(--chart-1)"}
   :mobile {:label "Mobile"
            :color "var(--chart-2)"}})

(defstory
 BarChart
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "w-[560px] rounded-xl border bg-card p-6"}
    [:h3 {:class "mb-4 font-semibold"}
     "Visitors by device"]
    [sut/chart-container {:id "visitors-by-device"
                          :config chart-config
                          :class "min-h-[260px] w-full"}
     [:>
      recharts/BarChart
      {:data chart-data
       :accessibilityLayer true}
      [:> recharts/CartesianGrid {:vertical false}]
      [:>
       recharts/XAxis
       {:dataKey "month"
        :tickLine false
        :axisLine false
        :tickMargin 10
        :tickFormatter #(.slice % 0 3)}]
      [:>
       sut/chart-tooltip
       {:cursor false
        :content (fn [props] (r/as-element [sut/chart-tooltip-content props]))}]
      [:> sut/chart-legend {:content (fn [props] (r/as-element [sut/chart-legend-content props]))}]
      [:>
       recharts/Bar
       {:dataKey "desktop"
        :fill "var(--color-desktop)"
        :radius 4}]
      [:>
       recharts/Bar
       {:dataKey "mobile"
        :fill "var(--color-mobile)"
        :radius 4}]]]])))

(defn interactive-series-chart
  []
  (let [series (r/atom :desktop)]
    (fn []
      (helpers/wrap-component
       [:div {:class "w-[560px] rounded-xl border bg-card p-6"}
        [:div {:class "mb-4 flex items-center justify-between gap-4"}
         [:h3 {:class "font-semibold"}
          "Visitors by device"]
         [:div {:class "flex rounded-md border p-1"
                :aria-label "Visible series"}
          (for [candidate [:desktop :mobile]]
            ^{:key candidate}
            [:button {:type "button"
                      :class (str "rounded px-3 py-1 text-sm "
                                  (when (= candidate @series) "bg-primary text-primary-foreground"))
                      :aria-pressed (= candidate @series)
                      :on-click #(reset! series candidate)}
             (get-in chart-config [candidate :label])])]]
        [sut/chart-container {:id "interactive-series"
                              :config chart-config
                              :class "min-h-[260px] w-full"}
         [:>
          recharts/BarChart
          {:data chart-data
           :accessibilityLayer true}
          [:> recharts/CartesianGrid {:vertical false}]
          [:>
           recharts/XAxis
           {:dataKey "month"
            :tickLine false
            :axisLine false}]
          [:>
           sut/chart-tooltip
           {:cursor false
            :content (fn [props] (r/as-element [sut/chart-tooltip-content props]))}]
          [:>
           recharts/Bar
           {:dataKey (name @series)
            :fill (str "var(--color-" (name @series) ")")
            :radius 4}]]]]))))

(defstory InteractiveSeries
          "Consumer-controlled series selector; the chart remains a standard Recharts composition."
          []
          (r/as-element [interactive-series-chart]))

(defstory
 TooltipIndicators
 "Supported tooltip indicator matrix. Hover each chart to compare dot, line, dashed, and hidden indicators."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid w-[760px] grid-cols-2 gap-4"}
    (for [[indicator hide?] [[:dot false] [:line false] [:dashed false] [:dot true]]]
      ^{:key (str indicator hide?)}
      [:div {:class "rounded-lg border bg-card p-3"}
       [:p {:class "mb-2 text-sm font-medium"}
        (if hide? "Hidden indicator" (str (name indicator) " indicator"))]
       [sut/chart-container {:config chart-config
                             :class "min-h-[180px] w-full"}
        [:>
         recharts/BarChart
         {:data chart-data}
         [:>
          sut/chart-tooltip
          {:content (fn [props]
                      (aset props "indicator" indicator)
                      (aset props "hide-label?" false)
                      (aset props "hide-indicator?" hide?)
                      (r/as-element [sut/chart-tooltip-content props]))}]
         [:>
          recharts/Bar
          {:dataKey "desktop"
           :fill "var(--color-desktop)"}]]]])])))

(def polar-data
  #js [#js {:browser "chrome"
            :visitors 275
            :fill "var(--color-chrome)"}
       #js {:browser "safari"
            :visitors 200
            :fill "var(--color-safari)"}
       #js {:browser "firefox"
            :visitors 187
            :fill "var(--color-firefox)"}])

(def polar-config
  {:visitors {:label "Visitors"}
   :chrome {:label "Chrome"
            :color "hsl(221 83% 53%)"}
   :safari {:label "Safari"
            :color "hsl(160 84% 39%)"}
   :firefox {:label "Firefox"
             :color "hsl(38 92% 50%)"}
   :desktop {:label "Desktop"
             :color "hsl(221 83% 53%)"}
   :mobile {:label "Mobile"
            :color "hsl(160 84% 39%)"}})

(defn- standard-tooltip
  ([] (standard-tooltip {}))
  ([content-props]
   (fn [props]
     (doseq [[prop value] content-props] (aset props (name prop) value))
     (r/as-element [sut/chart-tooltip-content props]))))

(defstory
 CartesianChartGallery
 "Area and line charts compare axis, grid, legend, cursor, and tooltip formatting behaviors without repeating cosmetic permutations."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid w-[900px] gap-4 lg:grid-cols-2"}
    [:div {:class "rounded-xl border bg-card p-5"}
     [:h3 {:class "mb-1 font-semibold"}
      "Area · stacked trend"]
     [:p {:class "mb-4 text-xs text-muted-foreground"}
      "Horizontal grid, abbreviated axis, dashed tooltip indicators, and a bottom legend."]
     [sut/chart-container {:config chart-config
                           :class "min-h-[240px] w-full"}
      [:>
       recharts/AreaChart
       {:data chart-data
        :accessibilityLayer true}
       [:> recharts/CartesianGrid {:vertical false}]
       [:>
        recharts/XAxis
        {:dataKey "month"
         :axisLine false
         :tickLine false
         :tickFormatter #(.slice % 0 3)}]
       [:>
        sut/chart-tooltip
        {:cursor #js {:strokeDasharray "3 3"}
         :content (standard-tooltip {:indicator :dashed})}]
       [:>
        sut/chart-legend
        {:verticalAlign "bottom"
         :content (fn [props] (r/as-element [sut/chart-legend-content props]))}]
       [:>
        recharts/Area
        {:type "monotone"
         :dataKey "desktop"
         :stackId "visitors"
         :stroke "var(--color-desktop)"
         :fill "var(--color-desktop)"
         :fillOpacity 0.35}]
       [:>
        recharts/Area
        {:type "monotone"
         :dataKey "mobile"
         :stackId "visitors"
         :stroke "var(--color-mobile)"
         :fill "var(--color-mobile)"
         :fillOpacity 0.35}]]]]
    [:div {:class "rounded-xl border bg-card p-5"}
     [:h3 {:class "mb-1 font-semibold"}
      "Line · focused comparison"]
     [:p {:class "mb-4 text-xs text-muted-foreground"}
      "Both axes, no grid, active cursor, formatted labels and values, and line indicators."]
     [sut/chart-container {:config chart-config
                           :class "min-h-[240px] w-full"}
      [:>
       recharts/LineChart
       {:data chart-data
        :accessibilityLayer true}
       [:>
        recharts/XAxis
        {:dataKey "month"
         :tickLine false
         :tickMargin 8}]
       [:>
        recharts/YAxis
        {:axisLine false
         :tickLine false
         :width 32}]
       [:>
        sut/chart-tooltip
        {:content (standard-tooltip {:indicator :line
                                     :label-formatter #(str "Month: " %)
                                     :formatter
                                     (fn [value name]
                                       (r/as-element
                                        [:span {:class "flex flex-1 justify-between gap-4"}
                                         [:span (get-in chart-config [(keyword name) :label] name)]
                                         [:strong (.toLocaleString value)]]))})}]
       [:>
        recharts/Line
        {:type "monotone"
         :dataKey "desktop"
         :stroke "var(--color-desktop)"
         :strokeWidth 2
         :dot false}]
       [:>
        recharts/Line
        {:type "monotone"
         :dataKey "mobile"
         :stroke "var(--color-mobile)"
         :strokeWidth 2
         :dot true}]]]]])))

(defstory
 PolarChartGallery
 "Pie, radar, and radial-bar compositions cover category tooltips, polar grids/axes, legends, and compact progress-like charts."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid w-[980px] gap-4 lg:grid-cols-3"}
    [:div {:class "rounded-xl border bg-card p-4"}
     [:h3 {:class "mb-3 font-semibold"}
      "Pie · browser share"]
     [sut/chart-container {:config polar-config
                           :class "mx-auto min-h-[230px] w-full"}
      [:>
       recharts/PieChart
       {:accessibilityLayer true}
       [:> sut/chart-tooltip {:content (standard-tooltip {:name-key "browser"})}]
       [:>
        recharts/Pie
        {:data polar-data
         :dataKey "visitors"
         :nameKey "browser"
         :innerRadius 52
         :outerRadius 82
         :strokeWidth 3}]
       [:>
        sut/chart-legend
        {:content (fn [props] (r/as-element [sut/chart-legend-content props]))}]]]]
    [:div {:class "rounded-xl border bg-card p-4"}
     [:h3 {:class "mb-3 font-semibold"}
      "Radar · device profile"]
     [sut/chart-container {:config polar-config
                           :class "mx-auto min-h-[230px] w-full"}
      [:>
       recharts/RadarChart
       {:data chart-data
        :accessibilityLayer true}
       [:> recharts/PolarGrid {:gridType "circle"}]
       [:>
        recharts/PolarAngleAxis
        {:dataKey "month"
         :tickFormatter #(.slice % 0 3)}]
       [:> sut/chart-tooltip {:content (standard-tooltip)}]
       [:>
        recharts/Radar
        {:dataKey "desktop"
         :stroke "var(--color-desktop)"
         :fill "var(--color-desktop)"
         :fillOpacity 0.35}]
       [:>
        recharts/Radar
        {:dataKey "mobile"
         :stroke "var(--color-mobile)"
         :fill "var(--color-mobile)"
         :fillOpacity 0.2}]]]]
    [:div {:class "rounded-xl border bg-card p-4"}
     [:h3 {:class "mb-3 font-semibold"}
      "Radial · total by browser"]
     [sut/chart-container {:config polar-config
                           :class "mx-auto min-h-[230px] w-full"}
      [:>
       recharts/RadialBarChart
       {:data polar-data
        :innerRadius "30%"
        :outerRadius "90%"
        :startAngle 90
        :endAngle -270
        :accessibilityLayer true}
       [:>
        sut/chart-tooltip
        {:cursor false
         :content (standard-tooltip {:hide-label? true
                                     :name-key "browser"})}]
       [:>
        recharts/RadialBar
        {:dataKey "visitors"
         :background true
         :cornerRadius 6}]
       [:>
        sut/chart-legend
        {:content (fn [props] (r/as-element [sut/chart-legend-content props]))}]]]]])))

(defstory
 ThemedRtlChart
 "Theme-aware series colors and an honestly expressed RTL chart: DOM direction plus a reversed categorical axis."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:dir "rtl"
          :class "dark w-[560px] rounded-xl border bg-background p-6 text-foreground"}
    [:h3 {:class "mb-4 font-semibold"}
     "الزيارات حسب الجهاز"]
    [sut/chart-container {:config chart-config
                          :class "min-h-[260px] w-full"}
     [:>
      recharts/BarChart
      {:data chart-data
       :accessibilityLayer true}
      [:>
       recharts/XAxis
       {:dataKey "month"
        :reversed true
        :tickLine false
        :axisLine false}]
      [:>
       recharts/Bar
       {:dataKey "desktop"
        :fill "var(--color-desktop)"}]
      [:>
       recharts/Bar
       {:dataKey "mobile"
        :fill "var(--color-mobile)"}]]]])))

(defstory
 ChartPlayground
 "Controlled Storybook playground using only safe scalar component props."
 {:args {:class "min-h-[260px] w-full"}
  :arg-types {:class {:control {:type "text"}}}
  :parameters {:controls {:exclude ["children" "config" "data" "content" "tick-formatter"]}}}
 [args]
 (r/as-element (helpers/wrap-component [:div {:class "w-[560px] rounded-xl border bg-card p-6"}
                                        [sut/chart-container {:config chart-config
                                                              :class (:class args)}
                                         [:>
                                          recharts/BarChart
                                          {:data chart-data
                                           :accessibilityLayer true}
                                          [:>
                                           recharts/Bar
                                           {:dataKey "desktop"
                                            :fill "var(--color-desktop)"
                                            :radius 4}]]]])))
