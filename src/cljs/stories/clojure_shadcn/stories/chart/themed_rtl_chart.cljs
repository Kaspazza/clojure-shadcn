(ns clojure-shadcn.stories.chart.themed-rtl-chart
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
  [:div {:dir "rtl"
         :class "dark w-[560px] rounded-xl border bg-background p-6 text-foreground"}
   [:h3 {:class "mb-4 font-semibold"}
    "الزيارات حسب الجهاز"]
   [chart/chart-container {:config chart-config
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
       :fill "var(--color-mobile)"}]]]])
