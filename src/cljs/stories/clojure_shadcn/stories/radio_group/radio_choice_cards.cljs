(ns clojure-shadcn.stories.radio-group.radio-choice-cards
  (:require
   [clojure-shadcn.ui.components.field       :as field]
   [clojure-shadcn.ui.components.radio-group :as radio-group]))

(defn example
  []
  [:div {:class "max-w-md p-6"}
   [radio-group/radio-group {:default-value "pro"
                             :aria-label "Plan"}
    (for [[value title description] [["starter" "Starter" "For personal projects."]
                                     ["pro" "Pro" "For growing teams."]]]
      ^{:key value}
      [field/field-label {}
       [field/field {:orientation :horizontal}
        [radio-group/radio-group-item {:id (str "card-" value)
                                       :value value}]
        [field/field-content {}
         [field/field-title {}
          title]
         [field/field-description {}
          description]]]])]])
