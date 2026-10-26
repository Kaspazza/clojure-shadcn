(ns clojure-shadcn.stories.collapsible.collapsible-multiple
  (:require
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.collapsible :as collapsible]
   [reagent.core                             :as r]))

(defn example
  []
  [(fn []
     (let [open-ids (r/atom #{:one})
           set-open! (fn [id next-open?]
                       (swap! open-ids (fn [current]
                                         (if next-open? (conj current id) (disj current id)))))]
       (fn [] [:div {:class "p-6 space-y-4"}
               (doall
                (for [{:keys [id title body]} [{:id :one
                                                :title "Shipping"
                                                :body
                                                "Shipping details and estimated delivery windows."}
                                               {:id :two
                                                :title "Returns"
                                                :body "Return policy and refund timeline."}
                                               {:id :three
                                                :title "Support"
                                                :body "Contact information and support hours."}]]
                  ^{:key id}
                  [collapsible/collapsible {:open (contains? @open-ids id)
                                            :on-open-change (fn [next-open?]
                                                              (set-open! id next-open?))}
                   [collapsible/collapsible-trigger {:as-child true}
                    (button/button {:variant :outline
                                    :class "w-full justify-between"}
                                   title)]
                   [collapsible/collapsible-content {:class "mt-2"}
                    [:div {:class "rounded-md border bg-muted p-4 text-sm"}
                     body]]]))])))])
