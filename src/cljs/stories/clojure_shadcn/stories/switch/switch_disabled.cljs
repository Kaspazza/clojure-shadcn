(ns clojure-shadcn.stories.switch.switch-disabled
  (:require
   [clojure-shadcn.ui.components.switch :as switch]))

(defn example
  []
  [:div {:class "p-6"}
   [:div {:class "flex items-center gap-2"}
    [switch/switch {:id "locked"
                    :disabled true
                    :checked true}]
    [:span {:class "text-sm text-muted-foreground"}
     "Locked setting"]]])
