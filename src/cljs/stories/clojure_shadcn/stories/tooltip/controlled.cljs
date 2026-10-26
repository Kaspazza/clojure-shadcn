(ns clojure-shadcn.stories.tooltip.controlled
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.tooltip :as tooltip]
   [reagent.core                         :as r]))



(defn example
  []
  (let [compound-tooltip
        (fn [{:keys [trigger content root content-props trigger-props portal arrow-props]}]
          [tooltip/tooltip-root
           root
           [tooltip/tooltip-trigger (merge {:as-child true} trigger-props) trigger]
           [tooltip/tooltip-portal
            portal
            [tooltip/tooltip-content
             content-props
             content
             (when arrow-props [tooltip/tooltip-arrow arrow-props])]]])]
    (r/with-let [open? (r/atom false)]
                [:div {:class "flex items-center gap-4"}
                 [button/button {:variant :outline
                                 :on-click #(swap! open? not)}
                  (if @open? "Close tooltip" "Open tooltip")]
                 [compound-tooltip {:root {:open @open?
                                           :on-open-change #(reset! open? %)}
                                    :trigger (button/button {:variant :secondary}
                                                            "Controlled trigger")
                                    :content "State is owned by the story."}]
                 [:span {:class "text-sm text-muted-foreground"}
                  (if @open? "Open" "Closed")]])))
