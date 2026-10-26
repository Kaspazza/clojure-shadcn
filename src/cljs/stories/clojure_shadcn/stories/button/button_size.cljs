(ns clojure-shadcn.stories.button.button-size
  (:require
   ["lucide-react"                      :refer [ArrowUpRight]]
   [clojure-shadcn.ui.components.button :as button]))

(defn example
  []
  [:div {:class "p-6 flex flex-wrap items-center gap-3"}
   (button/button {:variant :outline
                   :size :xs}
                  "XS")
   (button/button {:variant :outline
                   :size :sm}
                  "Small")
   (button/button {:variant :outline} "Default")
   (button/button {:variant :outline
                   :size :lg}
                  "Large")
   (button/button {:variant :outline
                   :size :icon
                   :aria-label "Open in new tab"}
                  [:> ArrowUpRight])])
