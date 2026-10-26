(ns clojure-shadcn.stories.stepper.stepper-horizontal
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.stepper :as stepper]
   [reagent.core                         :as r]))

(defn- step-data
  []
  [{:id :details
    :title "Details"
    :description "Account info"}
   {:id :billing
    :title "Billing"
    :description "Payment setup"}
   {:id :confirm
    :title "Confirm"
    :description "Review"}])

(defn example
  []
  (let [steps (step-data)
        step-ids (mapv :id steps)
        current-step (r/atom (first step-ids))]
    (fn []
      (let [current-index (.indexOf step-ids @current-step)
            prev-id (get step-ids (dec current-index))
            next-id (get step-ids (inc current-index))]
        [:div {:class "p-6 max-w-xl space-y-6"}
         [stepper/stepper {:current-step @current-step
                           :on-step-change #(reset! current-step %)}
          [stepper/stepper-navigation {}
           (for [{:keys [id title description]} steps]
             ^{:key id}
             [stepper/stepper-step {:id id}
              [stepper/stepper-title {}
               title]
              [stepper/stepper-description {}
               description]])]
          [stepper/stepper-panel {:id :details}
           [:div {:class "text-sm text-muted-foreground"}
            "Fill in your account details."]]
          [stepper/stepper-panel {:id :billing}
           [:div {:class "text-sm text-muted-foreground"}
            "Add your billing information."]]
          [stepper/stepper-panel {:id :confirm}
           [:div {:class "text-sm text-muted-foreground"}
            "Review and confirm."]]
          [stepper/stepper-controls {}
           (button/button {:variant :outline
                           :disabled (nil? prev-id)
                           :on-click #(when prev-id (reset! current-step prev-id))}
                          "Back")
           (button/button {:disabled (nil? next-id)
                           :on-click #(when next-id (reset! current-step next-id))}
                          "Next")]]]))))
