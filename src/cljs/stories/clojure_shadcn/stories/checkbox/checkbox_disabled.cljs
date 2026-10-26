(ns clojure-shadcn.stories.checkbox.checkbox-disabled
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]))

(defn example
  []
  [:div {:class "p-6"}
   [:div {:class "flex items-center gap-2"}
    [checkbox/checkbox {:id "notifications"
                        :disabled true}]
    [:span {:class "text-sm text-muted-foreground"}
     "Enable notifications"]]])
