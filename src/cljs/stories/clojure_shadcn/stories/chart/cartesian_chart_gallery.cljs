(ns clojure-shadcn.stories.chart.cartesian-chart-gallery
  (:require
   ["recharts"                         :as recharts]
   [clojure-shadcn.ui.components.chart :as chart]))

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

(defn- standard-tooltip
  ([] (standard-tooltip {}))
  ([content-props]
   (fn [props]
     (doseq [[prop value] content-props] (aset props (name prop) value))
     (chart/chart-tooltip-content props))))

(defn example
  []
  [:div {:class "grid w-[900px] gap-4 lg:grid-cols-2"}
   [:div {:class "rounded-xl border bg-card p-5"}
    [:h3 {:class "mb-1 font-semibold"}
     "Area · stacked trend"]
    [:p {:class "mb-4 text-xs text-muted-foreground"}
     "Horizontal grid, abbreviated axis, dashed tooltip indicators, and a bottom legend."]
    [chart/chart-container {:config chart-config
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
       chart/chart-tooltip
       {:cursor #js {:strokeDasharray "3 3"}
        :content (standard-tooltip {:indicator :dashed})}]
      [:>
       chart/chart-legend
       {:verticalAlign "bottom"
        :content (fn [props] (chart/chart-legend-content props))}]
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
    [chart/chart-container {:config chart-config
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
       chart/chart-tooltip
       {:content (standard-tooltip {:indicator :line
                                    :label-formatter #(str "Month: " %)
                                    :formatter
                                    (fn [value name]
                                      [:span {:class "flex flex-1 justify-between gap-4"}
                                       [:span (get-in chart-config [(keyword name) :label] name)]
                                       [:strong (.toLocaleString value)]])})}]
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
        :dot true}]]]]])
