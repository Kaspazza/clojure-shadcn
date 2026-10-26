(ns clojure-shadcn.stories.checkbox.checkbox-invalid
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.field    :as field]
   [clojure-shadcn.ui.components.label    :as label]))

(defn example
  []
  [:div {:class "p-6 space-y-6"}
   [:div {:class "space-y-3"}
    [:p {:class "text-sm font-medium"}
     "Standalone invalid checkbox"]
    [:div {:class "flex items-center gap-2"}
     [checkbox/checkbox {:id "invalid-standalone"
                         :aria-invalid true}]
     [label/label {:html-for "invalid-standalone"}
      "I accept the terms and conditions"]]]
   [:div {:class "space-y-3"}
    [:p {:class "text-sm font-medium"}
     "Invalid checkbox inside Field with error messages"]
    [field/field {}
     [field/field-content {}
      [:div {:class "flex items-center gap-2"}
       [checkbox/checkbox {:id "invalid-field"
                           :aria-invalid true}]
       [field/field-label {:html-for "invalid-field"}
        "I accept the terms and conditions"]]
      [field/field-error {:errors [{:message "You must accept the terms to continue"}]}]]]]
   [:div {:class "space-y-3"}
    [:p {:class "text-sm font-medium"}
     "Comparison: valid vs invalid"]
    [:div {:class "flex items-start gap-6"}
     [:div {:class "flex items-center gap-2"}
      [checkbox/checkbox {:id "valid-example"
                          :default-checked true}]
      [label/label {:html-for "valid-example"}
       "Valid"]]
     [:div {:class "flex items-center gap-2"}
      [checkbox/checkbox {:id "invalid-example"
                          :aria-invalid true}]
      [label/label {:html-for "invalid-example"}
       "Invalid"]]]]])
