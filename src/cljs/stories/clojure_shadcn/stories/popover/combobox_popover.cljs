(ns clojure-shadcn.stories.popover.combobox-popover
  (:require
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.command :as command]
   [clojure-shadcn.ui.components.popover :as popover]
   [reagent.core                         :as r]))

(defn example
  []
  (let [open? (r/atom false)
        selected (r/atom nil)
        statuses [{:value "backlog"
                   :label "Backlog"}
                  {:value "todo"
                   :label "Todo"}
                  {:value "in-progress"
                   :label "In Progress"}
                  {:value "done"
                   :label "Done"}
                  {:value "canceled"
                   :label "Canceled"}]]
    (fn [] [:div {:class "p-6 flex items-center gap-4"}
            [:p {:class "text-muted-foreground text-sm"}
             "Status"]
            [popover/popover {:open @open?
                              :on-open-change #(reset! open? %)}
             [popover/popover-trigger {:as-child true}
              (button/button {:variant :outline
                              :class "w-[160px] justify-start"}
                             (or (:label @selected) "+ Set status"))]
             [popover/popover-content {:class "p-0"
                                       :side "right"
                                       :align "start"}
              [command/command {}
               [command/command-input {:placeholder "Change status..."}]
               [command/command-list {}
                [command/command-empty {}
                 "No results found."]
                [command/command-group {}
                 (for [{:keys [value label]} statuses]
                   ^{:key value}
                   [command/command-item {:value value
                                          :on-select (fn [_]
                                                       (reset! selected {:value value
                                                                         :label label})
                                                       (reset! open? false))}
                    label])]]]]]])))
