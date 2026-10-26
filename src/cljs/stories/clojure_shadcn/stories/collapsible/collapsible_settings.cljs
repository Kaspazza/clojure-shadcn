(ns clojure-shadcn.stories.collapsible.collapsible-settings
  (:require
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.collapsible :as collapsible]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (let [open? (r/atom true)]
       (fn [] [collapsible/collapsible {:open @open?
                                        :on-open-change (fn* [%1] (reset! open? %1))
                                        :class "w-full max-w-md rounded-lg border"}
               [collapsible/collapsible-trigger {:as-child true}
                (button/button {:variant :ghost
                                :class "w-full justify-between rounded-b-none px-4"}
                               "Advanced settings"
                               [:span {:class "text-xs text-muted-foreground"}
                                (if @open? "Hide" "Show")])]
               [collapsible/collapsible-content {:class "space-y-3 border-t p-4 text-sm"}
                [:label {:class "flex items-center justify-between gap-4"}
                 "Usage analytics"
                 [:input {:type "checkbox"
                          :default-checked true}]]
                [:label {:class "flex items-center justify-between gap-4"}
                 "Beta updates"
                 [:input {:type "checkbox"}]]]])))])
