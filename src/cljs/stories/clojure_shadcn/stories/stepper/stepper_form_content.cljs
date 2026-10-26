(ns clojure-shadcn.stories.stepper.stepper-form-content
  (:require
   [clojure-shadcn.ui.components.stepper :as stepper]
   [reagent.core                         :as r]))

(defn example
  []
  (let [current-step (r/atom :details)]
    (fn [] [:div {:class "p-6 max-w-xl space-y-4"}
            [stepper/stepper {:current-step @current-step
                              :on-step-change #(reset! current-step %)}
             [stepper/stepper-navigation {}
              [stepper/stepper-step {:id :details}
               [stepper/stepper-title {}
                "Details"]]
              [stepper/stepper-step {:id :billing}
               [stepper/stepper-title {}
                "Billing"]]
              [stepper/stepper-step {:id :confirm}
               [stepper/stepper-title {}
                "Confirm"]]]
             [stepper/stepper-panel {:id :details}
              [:div {:class "rounded-md border p-4 text-sm"}
               "Account details form fields go here."]]
             [stepper/stepper-panel {:id :billing}
              [:div {:class "rounded-md border p-4 text-sm"}
               "Billing preferences and card form fields."]]
             [stepper/stepper-panel {:id :confirm}
              [:div {:class "rounded-md border p-4 text-sm"}
               "Review summary and confirm action."]]]])))
