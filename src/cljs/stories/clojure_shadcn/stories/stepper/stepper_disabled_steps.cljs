(ns clojure-shadcn.stories.stepper.stepper-disabled-steps
  (:require
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
        current-step (r/atom :details)]
    (fn [] [:div {:class "p-6"}
            [stepper/stepper {:current-step @current-step
                              :on-step-change #(reset! current-step %)}
             [stepper/stepper-navigation {}
              (for [{:keys [id title description]} steps]
                ^{:key id}
                [stepper/stepper-step {:id id
                                       :disabled? (= id :billing)}
                 [stepper/stepper-title {}
                  title]
                 [stepper/stepper-description {}
                  description]])]]])))
