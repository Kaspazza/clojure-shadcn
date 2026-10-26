(ns clojure-shadcn.stories.switch.switch-controlled
  (:require
   [clojure-shadcn.ui.components.switch :as switch]
   [reagent.core                        :as r]))

(defn example
  []
  (let [enabled? (r/atom true)]
    (fn [] [:div {:class "p-6 flex items-center gap-3"}
            [switch/switch {:checked @enabled?
                            :on-checked-change #(reset! enabled? %)}]
            [:span {:class "text-sm"}
             (if @enabled? "Enabled" "Disabled")]])))
