(ns clojure-shadcn.stories.stepper.stepper-custom-icons
  (:require
   ["lucide-react"                       :refer [Check CircleDollarSign User]]
   [clojure-shadcn.ui.components.stepper :as stepper]
   [reagent.core                         :as r]))

(defn example
  []
  (let [steps [{:id :account
                :title "Account"
                :icon [:> User]}
               {:id :billing
                :title "Billing"
                :icon [:> CircleDollarSign]}
               {:id :done
                :title "Complete"
                :icon [:> Check]}]
        current-step (r/atom :billing)]
    (fn [] [:div {:class "p-6"}
            [stepper/stepper {:current-step @current-step
                              :on-step-change #(reset! current-step %)}
             [stepper/stepper-navigation {}
              (for [{:keys [id title icon]} steps]
                ^{:key id}
                [stepper/stepper-step {:id id
                                       :icon icon}
                 [stepper/stepper-title {}
                  title]])]]])))
