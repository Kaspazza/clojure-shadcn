(ns clojure-shadcn.stories.form.playground
  (:require
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.form   :as form]
   [clojure-shadcn.ui.components.input  :as input]))

(defn- form-example
  [{:keys [email submit-label]}]
  (let [methods (form/use-form {:default-values {:email email}})]
    [form/form {:methods methods}
     [:form {:class "w-80 space-y-4"
             :no-validate true
             :on-submit
             (form/handle-submit methods (fn [values] (js/alert (str "Submitted " (:email values)))))}
      [form/form-field {:control (.-control methods)
                        :name "email"
                        :rules #js {:required "Email is required"}
                        :render (fn [{:keys [field]}] [:f>
                                                       form/form-item
                                                       {}
                                                       [:f> form/form-label {} "Email"]
                                                       [:f>
                                                        form/form-control
                                                        {}
                                                        [input/input
                                                         (merge field
                                                                {:type "email"
                                                                 :placeholder
                                                                 "you@example.com"})]]
                                                       [:f> form/form-message {}]])}]
      [button/button {:type "submit"}
       submit-label]]]))

(defn example
  ([]
   (example {:email ""
             :submit-label "Submit"}))
  ([args]
   [:f> form-example args]))
