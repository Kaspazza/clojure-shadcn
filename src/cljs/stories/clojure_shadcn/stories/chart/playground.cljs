(ns clojure-shadcn.stories.chart.playground
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
  ([]
   (example {:class "min-h-[260px] w-full"}))
  ([args]
  [:div {:class "w-[560px] rounded-xl border bg-card p-6"}
   [chart/chart-container {:config chart-config
                           :class (:class args)}
    [:>
     recharts/BarChart
     {:data chart-data
      :accessibilityLayer true}
     [:>
      recharts/Bar
      {:dataKey "desktop"
       :fill "var(--color-desktop)"
       :radius 4}]]]]))
