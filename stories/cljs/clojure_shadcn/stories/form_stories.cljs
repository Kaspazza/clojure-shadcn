(ns clojure-shadcn.stories.form-stories
  (:require
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.form   :as sut]
   [clojure-shadcn.ui.components.input  :as input]
   [reagent.core                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Form"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Idiomatic Reagent adapter for React Hook Form."
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
      :props []}]])))

(defn- example-form
  []
  (let [methods (sut/use-form {:default-values {:email ""}})]
    [sut/form {:methods methods}
     [:form {:class "w-80 space-y-4"
             :no-validate true
             :on-submit (sut/handle-submit methods #(js/alert (str "Submitted " (:email %))))}
      [sut/form-field {:control (.-control methods)
                       :name "email"
                       :rules #js {:required "Email is required"}
                       :render (fn [{:keys [field]}] [sut/form-item {}
                                                      [sut/form-label {}
                                                       "Email"]
                                                      [sut/form-control {}
                                                       [input/input
                                                        (merge field
                                                               {:type "email"
                                                                :placeholder "you@example.com"})]]
                                                      [sut/form-description {}
                                                       "We only use this for account messages."]
                                                      [sut/form-message {}]])}]
      [button/button {:type "submit"}
       "Submit"]]]))

(defstory Validation [] (r/as-element (helpers/wrap-component [example-form])))
