(ns clojure-shadcn.stories.stepper.playground
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
  ([]
   (example ((fn [{:keys [current-step variant label-orientation reverse-progress]
                   :as args}]
               (-> args
                   (assoc :current-step (keyword current-step)
                          :variant (keyword variant)
                          :label-orientation (keyword label-orientation)
                          :reverse-progress? reverse-progress)
                   (dissoc :reverse-progress)))
             {:current-step "details"
              :variant "horizontal"
              :label-orientation "horizontal"
              :reverse-progress false})))
  ([args]
   (r/with-let [current (r/atom (:current-step args))]
               [:div {:class "max-w-xl p-6"}
                [stepper/stepper
                 (assoc args :current-step @current :on-step-change #(reset! current %))
                 [stepper/stepper-navigation {}
                  (for [{:keys [id title description]} (step-data)]
                    ^{:key id}
                    [stepper/stepper-step {:id id}
                     [stepper/stepper-title {}
                      title]
                     [stepper/stepper-description {}
                      description]])]
                 [stepper/stepper-panel {:id :details}
                  "Account details."]
                 [stepper/stepper-panel {:id :billing}
                  "Billing preferences."]
                 [stepper/stepper-panel {:id :confirm}
                  "Review and confirm."]]])))
