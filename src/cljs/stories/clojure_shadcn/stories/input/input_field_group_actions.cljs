(ns clojure-shadcn.stories.input.input-field-group-actions
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.field  :as field]
   [clojure-shadcn.ui.components.input  :as input]))

(defn example
  []
  [:form {:class "max-w-md p-6"
          :on-submit #(.preventDefault %)}
   [field/field-group {}
    [field/field {}
     [field/field-label {:html-for "profile-name"}
      "Display name"]
     [input/input {:id "profile-name"
                   :name "display-name"
                   :default-value "Ada Lovelace"}]]
    [:div {:class "flex gap-2"}
     (button/button {:type "reset"
                     :variant :outline}
                    "Reset")
     (button/button {:type "submit"} "Save")]]])
