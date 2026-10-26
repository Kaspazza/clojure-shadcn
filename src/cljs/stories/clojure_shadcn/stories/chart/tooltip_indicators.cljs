(ns clojure-shadcn.stories.chart.tooltip-indicators
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
  [:div {:class "grid w-[760px] grid-cols-2 gap-4"}
   (for [[indicator hide?] [[:dot false] [:line false] [:dashed false] [:dot true]]]
     ^{:key (str indicator hide?)}
     [:div {:class "rounded-lg border bg-card p-3"}
      [:p {:class "mb-2 text-sm font-medium"}
       (if hide? "Hidden indicator" (str (name indicator) " indicator"))]
      [chart/chart-container {:config chart-config
                              :class "min-h-[180px] w-full"}
       [:>
        recharts/BarChart
        {:data chart-data}
        [:>
         chart/chart-tooltip
         {:content (fn [props]
                     (aset props "indicator" indicator)
                     (aset props "hide-label?" false)
                     (aset props "hide-indicator?" hide?)
                     (chart/chart-tooltip-content props))}]
        [:>
         recharts/Bar
         {:dataKey "desktop"
          :fill "var(--color-desktop)"}]]]])])
