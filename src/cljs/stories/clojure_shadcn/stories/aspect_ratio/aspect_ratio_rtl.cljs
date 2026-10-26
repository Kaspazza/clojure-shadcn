(ns clojure-shadcn.stories.aspect-ratio.aspect-ratio-rtl
  (:require [clojure-shadcn.ui.components.aspect-ratio :as aspect-ratio]))

(defn example []
  [:figure {:dir "rtl" :class "w-80 space-y-2 text-right"}
   [aspect-ratio/aspect-ratio {:ratio 1.7778 :class "overflow-hidden rounded-lg bg-muted"}
    [:img {:src "https://images.unsplash.com/photo-1472214103451-9374bd1c798e?auto=format&fit=crop&w=700&q=80"
           :alt "منظر طبيعي أخضر" :class "size-full object-cover"}]]
   [:figcaption {:class "text-sm text-muted-foreground"}
    "صورة بنسبة عرض إلى ارتفاع 16:9"]])
