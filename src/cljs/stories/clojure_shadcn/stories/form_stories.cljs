(ns clojure-shadcn.stories.form-stories
  (:require
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.button      :as button]
   [clojure-shadcn.ui.components.checkbox    :as checkbox]
   [clojure-shadcn.ui.components.form        :as sut]
   [clojure-shadcn.ui.components.input       :as input]
   [clojure-shadcn.ui.components.radio-group :as radio]
   [clojure-shadcn.ui.components.select      :as select]
   [clojure-shadcn.ui.components.switch      :as switch]
   [clojure-shadcn.ui.components.textarea    :as textarea]
   [reagent.core                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Form"
       :parameters #js {:layout "centered"}})

(defdoc
 Installation
 []
 (r/as-element
  [helpers/installation-scene
   {:description
    "Local Reagent facade over React Hook Form; this is project-specific adapter code, not a Radix primitive or a verbatim shadcn/ui registry module."
    :npm-install "npm install react-hook-form @radix-ui/react-slot"
    :source-code (embed-source "clojure-shadcn.ui.components.form")
    :namespace-path "src/cljs/clojure_shadcn/ui/components/form.cljs"
    :filename "form.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "use-form / form / handle-submit"
      :link {:href "https://react-hook-form.com/docs"
             :label "React Hook Form Docs"}
      :description
      "React Hook Form lifecycle adapters. Hooks must run in a Reagent/React function component; form provides methods through FormProvider but does not render an HTML form."
      :props
      [{:name "use-form opts"
        :type "CLJS map"
        :default nil
        :description
        "Passed to useForm after :default-values is converted to JavaScript; returns the native RHF methods object."}
       {:name ":methods"
        :type "RHF methods object, required"
        :default nil
        :description
        "form expands methods into FormProvider props; other props are also forwarded."}
       {:name "handle-submit callbacks"
        :type "methods, on-valid, optional on-invalid"
        :default nil
        :description
        "Returns RHF's submit handler; callback data/errors are keywordized CLJS maps."}]}]
    [helpers/api-component-card
     {:component-name "form-field"
      :description
      "Controller plus field-name context. Its render function receives {:field ... :field-state ... :form-state ...} as keywordized maps."
      :props
      [{:name ":name / :render"
        :type "string / function, required"
        :default nil
        :description "Field name and Reagent render callback."}
       {:name "Controller props"
        :type "map entries"
        :default nil
        :description
        "Forwarded to RHF Controller, including :control, :rules, :default-value, and :disabled."}]}]
    [helpers/api-component-card
     {:component-name "form-item / form-label / form-control"
      :description
      "Composition trio generating stable ids and wiring label, control, description, error, aria-invalid, and aria-describedby. All must be descendants of form-field; control uses Radix Slot and therefore requires exactly one element child."
      :props
      [{:name ":class / element props"
        :type "string / map entries"
        :default nil
        :description
        "Merged/forwarded to div or Label. form-control props are merged by Slot into its child while generated accessibility attributes take precedence."}]}]
    [helpers/api-component-card
     {:component-name "form-description / form-message"
      :description
      "Accessible supporting and error text. Message prefers the current RHF error message, falls back to its first child, and renders nothing when neither exists."
      :props [{:name ":class / paragraph props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to the p element."}]}]
    [helpers/api-component-card
     {:component-name "use-form-field"
      :description
      "Context hook returning ids, name, error, and invalid?/touched?/dirty? flags. Throws outside form-field and expects a form-item ancestor for meaningful generated ids."
      :props []}]
    [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4"}
     [:h4 {:class "mb-2 text-sm font-semibold"}
      "Adapter scope"]
     [:p {:class "text-xs text-muted-foreground"}
      "These stories document the local React Hook Form adapter. Upstream Formisch, TanStack Form, and Next.js server-action examples are inapplicable: they require different form state/runtime abstractions that this namespace does not expose."]]])))

(defn- example-form
  []
  (let [methods (sut/use-form {:default-values {:email ""}})]
    [sut/form {:methods methods}
     [:form {:class "w-80 space-y-4"
             :no-validate true
             :on-submit (sut/handle-submit methods #(js/alert (str "Submitted " (:email %))))}
      [sut/form-field
       {:control (.-control methods)
        :name "email"
        :rules #js {:required "Email is required"}
        :render (fn [{:keys [field]}]
                  [:f>
                   sut/form-item
                   {}
                   [:f> sut/form-label {} "Email"]
                   [:f>
                    sut/form-control
                    {}
                    [input/input
                     (merge field
                            {:type "email"
                             :placeholder "you@example.com"})]]
                   [:f> sut/form-description {} "We only use this for account messages."]
                   [:f> sut/form-message {}]])}]
      [button/button {:type "submit"}
       "Submit"]]]))

(defstory Validation [] (r/as-element (helpers/wrap-component [:f> example-form])))


(defn- playground-form
  [{:keys [email submit-label]}]
  (let [methods (sut/use-form {:default-values {:email email}})]
    [sut/form {:methods methods}
     [:form {:class "w-80 space-y-4"
             :no-validate true
             :on-submit (sut/handle-submit methods #(js/alert (str "Submitted " (:email %))))}
      [sut/form-field {:control (.-control methods)
                       :name "email"
                       :rules #js {:required "Email is required"}
                       :render (fn [{:keys [field]}] [:f>
                                                      sut/form-item
                                                      {}
                                                      [:f> sut/form-label {} "Email"]
                                                      [:f>
                                                       sut/form-control
                                                       {}
                                                       [input/input
                                                        (merge field
                                                               {:type "email"
                                                                :placeholder "you@example.com"})]]
                                                      [:f> sut/form-message {}]])}]
      [button/button {:type "submit"}
       submit-label]]]))

(defstory Playground
          "Interactive validation through the local React Hook Form facade."
          {:args {:email ""
                  :submit-label "Submit"}
           :arg-types {:email {:name "Email"
                               :control {:type "text"}}
                       :submit-label {:name "Submit label"
                                      :control {:type "text"}}}
           :parameters {:playground {:description "Explore form email and submit label with a live preview."}
                        :controls {:expanded false
                                   :exclude ["on-submit" "methods" "class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:f> playground-form args])))

(defn- form-shell
  [methods on-valid & children]
  [sut/form {:methods methods}
   (into [:form {:class "grid w-full max-w-xl gap-5"
                 :no-validate true
                 :on-submit (sut/handle-submit methods on-valid)}]
         children)])

(defn- rhf-controls-example
  []
  (let [methods (sut/use-form {:default-values {:framework "reagent"
                                                :updates true
                                                :biography ""
                                                :plan "free"}})
        control (.-control methods)]
    (form-shell
     methods
     #(js/alert (str "Submitted controls: " (pr-str %)))
     [sut/form-field
      {:control control
       :name "framework"
       :rules #js {:required "Choose a framework"}
       :render (fn [{:keys [field]}] [:f>
                                      sut/form-item
                                      {}
                                      [:f> sut/form-label {} "Framework"]
                                      [:f>
                                       sut/form-control
                                       {}
                                       [select/select {:value (:value field)
                                                       :on-value-change (:on-change field)}
                                        [select/select-trigger {}
                                         [select/select-value {:placeholder "Choose a framework"}]]
                                        [select/select-content {}
                                         [select/select-item {:value "reagent"}
                                          "Reagent"]
                                         [select/select-item {:value "react"}
                                          "React"]
                                         [select/select-item {:value "clojure"}
                                          "Clojure"]]]]
                                      [:f> sut/form-message {}]])}]
     [sut/form-field {:control control
                      :name "updates"
                      :render (fn [{:keys [field]}]
                                [:f>
                                 sut/form-item
                                 {:class "rounded-lg border p-4"}
                                 [:div {:class "flex items-center justify-between gap-4"}
                                  [:div
                                   [:f> sut/form-label {} "Product updates"]
                                   [:f> sut/form-description {} "Receive release notes by email."]]
                                  [:f>
                                   sut/form-control
                                   {}
                                   [switch/switch {:checked (boolean (:value field))
                                                   :on-checked-change (:on-change field)}]]]
                                 [:f> sut/form-message {}]])}]
     [sut/form-field {:control control
                      :name "biography"
                      :rules #js {:minLength #js {:value 10
                                                  :message "Use at least 10 characters"}}
                      :render (fn [{:keys [field]}]
                                [:f>
                                 sut/form-item
                                 {}
                                 [:f> sut/form-label {} "Biography"]
                                 [:f>
                                  sut/form-control
                                  {}
                                  [textarea/textarea
                                   (merge field {:placeholder "Tell us what you build"})]]
                                 [:f> sut/form-description {} "A short public introduction."]
                                 [:f> sut/form-message {}]])}]
     [sut/form-field
      {:control control
       :name "plan"
       :render (fn [{:keys [field]}] [:f>
                                      sut/form-item
                                      {}
                                      [:f> sut/form-label {} "Plan"]
                                      [:f>
                                       sut/form-control
                                       {}
                                       [radio/radio-group {:value (:value field)
                                                           :on-value-change (:on-change field)}
                                        [:div {:class "flex items-center gap-2"}
                                         [radio/radio-group-item {:id "plan-free"
                                                                  :value "free"}]
                                         [:label {:for "plan-free"}
                                          "Free"]]
                                        [:div {:class "flex items-center gap-2"}
                                         [radio/radio-group-item {:id "plan-pro"
                                                                  :value "pro"}]
                                         [:label {:for "plan-pro"}
                                          "Pro"]]]]
                                      [:f> sut/form-message {}]])}]
     [button/button {:type "submit"}
      "Save preferences"])))

(defstory RhfControls
          "React Hook Form integration for select, switch, textarea, and radio-group controls."
          []
          (r/as-element (helpers/wrap-component [:f> rhf-controls-example])))

(defn- rhf-account-example
  []
  (let [methods (sut/use-form {:default-values {:terms false
                                                :password ""
                                                :confirm-password ""
                                                :skills [{:name "Clojure"} {:name "ClojureScript"}]
                                                :profile {:email ""
                                                          :company ""}}})
        control (.-control methods)]
    (form-shell
     methods
     #(js/alert (str "Submitted account: " (pr-str %)))
     [sut/form-field
      {:control control
       :name "terms"
       :rules #js {:validate #(or % "Accept the terms to continue")}
       :render (fn [{:keys [field]}]
                 [:f>
                  sut/form-item
                  {:class "rounded-lg border p-4"}
                  [:div {:class "flex items-start gap-3"}
                   [:f>
                    sut/form-control
                    {}
                    [checkbox/checkbox {:checked (boolean (:value field))
                                        :on-checked-change (:on-change field)}]]
                   [:div
                    [:f> sut/form-label {} "Accept the terms"]
                    [:f> sut/form-description {} "Required before creating your account."]]]
                  [:f> sut/form-message {}]])}]
     [sut/form-field
      {:control control
       :name "password"
       :rules #js {:required "Password is required"
                   :minLength #js {:value 8
                                   :message "Use at least 8 characters"}}
       :render (fn [{:keys [field]}] [:f>
                                      sut/form-item
                                      {}
                                      [:f> sut/form-label {} "Password"]
                                      [:f>
                                       sut/form-control
                                       {}
                                       [input/input
                                        (merge field
                                               {:type "password"
                                                :auto-complete "new-password"})]]
                                      [:f> sut/form-message {}]])}]
     [sut/form-field {:control control
                      :name "confirm-password"
                      :rules #js {:validate #(or (= % (.getValues methods "password"))
                                                 "Passwords must match")}
                      :render (fn [{:keys [field]}] [:f>
                                                     sut/form-item
                                                     {}
                                                     [:f> sut/form-label {} "Confirm password"]
                                                     [:f>
                                                      sut/form-control
                                                      {}
                                                      [input/input
                                                       (merge field
                                                              {:type "password"
                                                               :auto-complete "new-password"})]]
                                                     [:f> sut/form-message {}]])}]
     [sut/form-field {:control control
                      :name "profile.email"
                      :rules #js {:required "Work email is required"}
                      :render (fn [{:keys [field]}] [:f>
                                                     sut/form-item
                                                     {}
                                                     [:f> sut/form-label {} "Work email"]
                                                     [:f>
                                                      sut/form-control
                                                      {}
                                                      [input/input
                                                       (merge field
                                                              {:type "email"
                                                               :placeholder "you@company.com"})]]
                                                     [:f> sut/form-message {}]])}]
     [sut/form-field
      {:control control
       :name "profile.company"
       :render
       (fn [{:keys [field]}]
         [:f>
          sut/form-item
          {}
          [:f> sut/form-label {} "Company"]
          [:f> sut/form-control {} [input/input field]]
          [:f> sut/form-description {} "Nested profile data demonstrates a complex form shape."]])}]
     [:fieldset {:class "grid gap-3 rounded-lg border p-4"}
      [:legend {:class "px-1 text-sm font-medium"}
       "Skills (array fields)"]
      (doall (for [[index label] [[0 "Primary skill"] [1 "Secondary skill"]]]
               ^{:key index}
               [sut/form-field {:control control
                                :name (str "skills." index ".name")
                                :rules #js {:required "Skill is required"}
                                :render (fn [{:keys [field]}]
                                          [:f>
                                           sut/form-item
                                           {}
                                           [:f> sut/form-label {} label]
                                           [:f> sut/form-control {} [input/input field]]
                                           [:f> sut/form-message {}]])}]))]
     [button/button {:type "submit"}
      "Create account"])))

(defstory
 RhfAccount
 "RHF checkbox, password confirmation, fixed array paths, and nested complex-form data. The local adapter does not expose useFieldArray, so this story intentionally demonstrates supported indexed Controller names rather than an unsupported dynamic-array abstraction."
 []
 (r/as-element (helpers/wrap-component [:f> rhf-account-example])))
