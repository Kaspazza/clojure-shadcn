(ns clojure-shadcn.stories.chart.interactive-series
  (:require
   ["recharts"                         :as recharts]
   [clojure-shadcn.ui.components.chart :as chart]
   [reagent.core                       :as r]))

(defn example
  []
  (r/with-let
   [chart-config {:desktop {:label "Desktop"
                            :color "var(--chart-1)"}
                  :mobile {:label "Mobile"
                           :color "var(--chart-2)"}}
    chart-data
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
              :mobile 190}]
    series
    (r/atom :desktop)]
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
    [chart/chart-container {:id "interactive-series"
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
       chart/chart-tooltip
       {:cursor false
        :content (fn [props] (chart/chart-tooltip-content props))}]
      [:>
       recharts/Bar
       {:dataKey (name @series)
        :fill (str "var(--color-" (name @series) ")")
        :radius 4}]]]]))
