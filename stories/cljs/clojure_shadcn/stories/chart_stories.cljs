(ns clojure-shadcn.stories.chart-stories
  (:require
   ["recharts" :as recharts]
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.chart :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Components/Chart"
       :parameters #js {:layout "centered"}})

(defn ^:export Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Configured responsive Recharts container, tooltip, and legend."
     :npm-install "npm install recharts"
     :source-code (embed-source "clojure-shadcn.ui.components.chart")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/chart.cljs"
     :filename "chart.cljs"}]))

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
