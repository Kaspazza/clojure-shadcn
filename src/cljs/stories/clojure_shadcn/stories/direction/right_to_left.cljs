(ns clojure-shadcn.stories.direction.right-to-left
  (:require
   [clojure-shadcn.ui.components.direction :as direction]))

(defn example
  []
  [direction/direction-provider {:direction :rtl}
   [:div {:dir "rtl"
          :class "w-80 rounded-lg border p-6 text-right"}
    [:h3 {:class "font-semibold"}
     "واجهة من اليمين إلى اليسار"]
    [:p {:class "text-muted-foreground"}
     "يحافظ المزود على اتجاه مكونات Radix المتداخلة."]]])
