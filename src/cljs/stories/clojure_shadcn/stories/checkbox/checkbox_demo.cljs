(ns clojure-shadcn.stories.checkbox.checkbox-demo
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.label    :as label]
   [reagent.core                          :as r]))

(defn example
  []
  (r/with-let [checked? (r/atom false)]
              [:div {:class "p-6 space-y-6"}
               [:div {:class "space-y-4"}
                [:p {:class "text-xs font-semibold uppercase tracking-wide text-muted-foreground"}
                 "Uncontrolled"]
                [:div {:class "flex items-center gap-2"}
                 [checkbox/checkbox {:id "terms"}]
                 [label/label {:html-for "terms"}
                  "Accept terms and conditions"]]
                [:div {:class "flex items-start gap-3"}
                 [checkbox/checkbox {:id "terms-2"
                                     :default-checked true}]
                 [:div {:class "grid gap-2"}
                  [label/label {:html-for "terms-2"}
                   "Accept terms and conditions"]
                  [:p {:class "text-muted-foreground text-sm"}
                   "By clicking this checkbox, you agree to the terms and conditions."]]]]
               [:div {:class "space-y-4"}
                [:p {:class "text-xs font-semibold uppercase tracking-wide text-muted-foreground"}
                 "Controlled"]
                [:div {:class "flex items-center gap-2"}
                 [checkbox/checkbox {:id "controlled"
                                     :checked @checked?
                                     :on-checked-change #(reset! checked? %)}]
                 [label/label {:html-for "controlled"}
                  "Enable feature"]]
                [:p {:class "text-sm text-muted-foreground"}
                 (str "State: " (if @checked? "checked" "unchecked"))]]]))
