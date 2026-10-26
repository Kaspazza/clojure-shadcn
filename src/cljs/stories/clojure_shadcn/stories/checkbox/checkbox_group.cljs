(ns clojure-shadcn.stories.checkbox.checkbox-group
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.field    :as field]
   [clojure.string                        :as str]
   [reagent.core                          :as r]))

(defn example
  []
  (r/with-let
   [selected
    (r/atom #{"comments"})
    options
    [{:id "checkbox-group-comments"
      :value "comments"
      :label "Comments"}
     {:id "checkbox-group-mentions"
      :value "mentions"
      :label "Mentions"}
     {:id "checkbox-group-replies"
      :value "replies"
      :label "Replies"}]]
   [field/field-set {:class "max-w-md p-6"}
    [field/field-legend {:variant :label}
     "Email notifications"]
    [field/field-description {}
     "Choose which activity should trigger an email."]
    [field/field-group {:class "gap-3"}
     (doall (for [{:keys [id value label]} options]
              ^{:key value}
              [field/field {:orientation :horizontal}
               [checkbox/checkbox {:id id
                                   :name "notifications"
                                   :value value
                                   :checked (contains? @selected value)
                                   :on-checked-change
                                   (fn [checked?] (swap! selected (if checked? conj disj) value))}]
               [field/field-label {:html-for id
                                   :class "font-normal"}
                label]]))]
    [:p {:class "text-sm text-muted-foreground"}
     (str "Selected: " (if (seq @selected) (str/join ", " (sort @selected)) "none"))]]))
