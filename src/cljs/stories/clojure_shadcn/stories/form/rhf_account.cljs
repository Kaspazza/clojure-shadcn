(ns clojure-shadcn.stories.form.rhf-account
  (:require
   [clojure-shadcn.ui.components.button   :as button]
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.form     :as form]
   [clojure-shadcn.ui.components.input    :as input]))

(defn example
  []
  [:f>
   (fn []
     (let [form-shell (fn [methods on-valid & children]
                        [form/form {:methods methods}
                         (into [:form {:class "grid w-full max-w-xl gap-5"
                                       :no-validate true
                                       :on-submit (form/handle-submit methods on-valid)}]
                               children)])
           methods (form/use-form {:default-values {:terms false
                                                    :password ""
                                                    :confirm-password ""
                                                    :skills [{:name "Clojure"}
                                                             {:name "ClojureScript"}]
                                                    :profile {:email ""
                                                              :company ""}}})
           control (.-control methods)]
       (form-shell
        methods
        (fn* [%1] (js/alert (str "Submitted account: " (pr-str %1))))
        [form/form-field
         {:control control
          :name "terms"
          :rules #js {:validate (fn* [%1] (or %1 "Accept the terms to continue"))}
          :render (fn [{:keys [field]}]
                    [:f>
                     form/form-item
                     {:class "rounded-lg border p-4"}
                     [:div {:class "flex items-start gap-3"}
                      [:f>
                       form/form-control
                       {}
                       [checkbox/checkbox {:checked (boolean (:value field))
                                           :on-checked-change (:on-change field)}]]
                      [:div
                       [:f> form/form-label {} "Accept the terms"]
                       [:f> form/form-description {} "Required before creating your account."]]]
                     [:f> form/form-message {}]])}]
        [form/form-field
         {:control control
          :name "password"
          :rules #js {:required "Password is required"
                      :minLength #js {:value 8
                                      :message "Use at least 8 characters"}}
          :render (fn [{:keys [field]}] [:f>
                                         form/form-item
                                         {}
                                         [:f> form/form-label {} "Password"]
                                         [:f>
                                          form/form-control
                                          {}
                                          [input/input
                                           (merge field
                                                  {:type "password"
                                                   :auto-complete "new-password"})]]
                                         [:f> form/form-message {}]])}]
        [form/form-field
         {:control control
          :name "confirm-password"
          :rules #js {:validate
                      (fn* [%1] (or (= %1 (.getValues methods "password")) "Passwords must match"))}
          :render (fn [{:keys [field]}] [:f>
                                         form/form-item
                                         {}
                                         [:f> form/form-label {} "Confirm password"]
                                         [:f>
                                          form/form-control
                                          {}
                                          [input/input
                                           (merge field
                                                  {:type "password"
                                                   :auto-complete "new-password"})]]
                                         [:f> form/form-message {}]])}]
        [form/form-field {:control control
                          :name "profile.email"
                          :rules #js {:required "Work email is required"}
                          :render (fn [{:keys [field]}] [:f>
                                                         form/form-item
                                                         {}
                                                         [:f> form/form-label {} "Work email"]
                                                         [:f>
                                                          form/form-control
                                                          {}
                                                          [input/input
                                                           (merge field
                                                                  {:type "email"
                                                                   :placeholder
                                                                   "you@company.com"})]]
                                                         [:f> form/form-message {}]])}]
        [form/form-field {:control control
                          :name "profile.company"
                          :render (fn [{:keys [field]}]
                                    [:f>
                                     form/form-item
                                     {}
                                     [:f> form/form-label {} "Company"]
                                     [:f> form/form-control {} [input/input field]]
                                     [:f>
                                      form/form-description
                                      {}
                                      "Nested profile data demonstrates a complex form shape."]])}]
        [:fieldset {:class "grid gap-3 rounded-lg border p-4"}
         [:legend {:class "px-1 text-sm font-medium"}
          "Skills (array fields)"]
         (doall (for [[index label] [[0 "Primary skill"] [1 "Secondary skill"]]]
                  ^{:key index}
                  [form/form-field {:control control
                                    :name (str "skills." index ".name")
                                    :rules #js {:required "Skill is required"}
                                    :render (fn [{:keys [field]}]
                                              [:f>
                                               form/form-item
                                               {}
                                               [:f> form/form-label {} label]
                                               [:f> form/form-control {} [input/input field]]
                                               [:f> form/form-message {}]])}]))]
        [button/button {:type "submit"}
         "Create account"])))])
