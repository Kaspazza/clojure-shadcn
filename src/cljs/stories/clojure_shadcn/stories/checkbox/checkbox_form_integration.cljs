(ns clojure-shadcn.stories.checkbox.checkbox-form-integration
  (:require
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.label    :as label]))

(defn example
  []
  [:div {:class "p-6 max-w-sm"}
   [:form {:on-submit (fn [e]
                        (.preventDefault e)
                        (let [data (js/FormData. (.-target e))]
                          (js/alert (str "terms: " (.get data "terms")))))}
    [:div {:class "space-y-4"}
     [:div {:class "flex items-start gap-2"}
      [checkbox/checkbox {:id "terms-form"
                          :name "terms"
                          :value "accepted"
                          :required true}]
      [:div {:class "grid gap-1.5 leading-none"}
       [label/label {:html-for "terms-form"}
        "Accept terms and conditions"]
       [:p {:class "text-muted-foreground text-sm"}
        "Required to continue. Value submitted: \"accepted\"."]]]
     [:button
      {:type "submit"
       :class
       "mt-4 rounded-md bg-primary px-4 py-2 text-sm text-primary-foreground hover:bg-primary/90"}
      "Submit form"]]]])
