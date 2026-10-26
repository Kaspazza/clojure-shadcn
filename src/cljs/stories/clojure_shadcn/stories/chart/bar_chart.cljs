(ns clojure-shadcn.stories.chart.bar-chart
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

(defn example
  []
  [:div {:class "w-[560px] rounded-xl border bg-card p-6"}
   [:h3 {:class "mb-4 font-semibold"}
    "Visitors by device"]
   [chart/chart-container {:id "visitors-by-device"
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
      chart/chart-tooltip
      {:cursor false
       :content (fn [props] (chart/chart-tooltip-content props))}]
     [:> chart/chart-legend {:content (fn [props] (chart/chart-legend-content props))}]
     [:>
      recharts/Bar
      {:dataKey "desktop"
       :fill "var(--color-desktop)"
       :radius 4}]
     [:>
      recharts/Bar
      {:dataKey "mobile"
       :fill "var(--color-mobile)"
       :radius 4}]]]])
