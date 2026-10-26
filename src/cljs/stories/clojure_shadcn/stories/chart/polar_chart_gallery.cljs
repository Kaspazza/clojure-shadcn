(ns clojure-shadcn.stories.chart.polar-chart-gallery
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
     (chart/chart-tooltip-content props))))

(defn example
  []
  [:div {:class "grid w-[980px] gap-4 lg:grid-cols-3"}
   [:div {:class "rounded-xl border bg-card p-4"}
    [:h3 {:class "mb-3 font-semibold"}
     "Pie · browser share"]
    [chart/chart-container {:config polar-config
                            :class "mx-auto min-h-[230px] w-full"}
     [:>
      recharts/PieChart
      {:accessibilityLayer true}
      [:> chart/chart-tooltip {:content (standard-tooltip {:name-key "browser"})}]
      [:>
       recharts/Pie
       {:data polar-data
        :dataKey "visitors"
        :nameKey "browser"
        :innerRadius 52
        :outerRadius 82
        :strokeWidth 3}]
      [:> chart/chart-legend {:content (fn [props] (chart/chart-legend-content props))}]]]]
   [:div {:class "rounded-xl border bg-card p-4"}
    [:h3 {:class "mb-3 font-semibold"}
     "Radar · device profile"]
    [chart/chart-container {:config polar-config
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
      [:> chart/chart-tooltip {:content (standard-tooltip)}]
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
    [chart/chart-container {:config polar-config
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
       chart/chart-tooltip
       {:cursor false
        :content (standard-tooltip {:hide-label? true
                                    :name-key "browser"})}]
      [:>
       recharts/RadialBar
       {:dataKey "visitors"
        :background true
        :cornerRadius 6}]
      [:> chart/chart-legend {:content (fn [props] (chart/chart-legend-content props))}]]]]])
