(ns clojure-shadcn.stories.input.input-controlled
  (:require
   [clojure-shadcn.ui.components.input :as input]
   [clojure-shadcn.ui.components.label :as label]
   [reagent.core                       :as r]))

(defn example
  []
  [(fn []
     (let [value (r/atom "hello@company.com")]
       (fn [] [:div {:class "p-6 max-w-sm space-y-2"}
               [label/label {:html-for "controlled-email"}
                "Contact"]
               [input/input {:id "controlled-email"
                             :type "email"
                             :value @value
                             :on-change #(reset! value (.. % -target -value))}]
               [:p {:class "text-muted-foreground text-sm"}
                (str "Current value: " @value)]])))])
