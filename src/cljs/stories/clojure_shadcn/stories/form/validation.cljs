(ns clojure-shadcn.stories.form.validation
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.form   :as form]
   [clojure-shadcn.ui.components.input  :as input]))

(defn example
  []
  [:f>
   (fn []
     (let [methods (form/use-form {:default-values {:email ""}})]
       [form/form {:methods methods}
        [:form {:class "w-full min-w-0 max-w-80 space-y-4"
                :no-validate true
                :on-submit
                (form/handle-submit methods (fn* [%1] (js/alert (str "Submitted " (:email %1)))))}
         [form/form-field
          {:control (.-control methods)
           :name "email"
           :rules #js {:required "Email is required"}
           :render (fn [{:keys [field]}]
                     [:f>
                      form/form-item
                      {}
                      [:f> form/form-label {} "Email"]
                      [:f>
                       form/form-control
                       {}
                       [input/input
                        (merge field
                               {:type "email"
                                :placeholder "you@example.com"})]]
                      [:f> form/form-description {} "Leave this empty and submit to see a validation error."]
                      [:f> form/form-message {}]])}]
         [button/button {:type "submit"}
          "Submit"]]]))])
