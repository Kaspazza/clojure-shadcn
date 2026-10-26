(ns clojure-shadcn.stories.form-stories
  (:require
   [clojure-shadcn.stories.form.playground   :as playground]
   [clojure-shadcn.stories.form.rhf-account  :as rhf-account]
   [clojure-shadcn.stories.form.rhf-controls :as rhf-controls]
   [clojure-shadcn.stories.form.validation   :as validation]
   [clojure-shadcn.stories.helpers           :as helpers]
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


(defstory Validation
          {:source-code (embed-source "clojure-shadcn.stories.form.validation")}
          []
          (r/as-element (helpers/wrap-component [validation/example])))


(defstory Playground
          "Interactive validation through the local React Hook Form facade."
          {:args {:email ""
                  :submit-label "Submit"}
           :arg-types {:email {:name "Email"
                               :control {:type "text"}}
                       :submit-label {:name "Submit label"
                                      :control {:type "text"}}}
           :parameters {:playground {:description
                                     "Explore form email and submit label with a live preview."}
                        :controls {:expanded false
                                   :exclude ["on-submit" "methods" "class"]}}
           :source-code (embed-source "clojure-shadcn.stories.form.playground")}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))


(defstory RhfControls
          "React Hook Form integration for select, switch, textarea, and radio-group controls."
          {:source-code (embed-source "clojure-shadcn.stories.form.rhf-controls")}
          []
          (r/as-element (helpers/wrap-component [rhf-controls/example])))


(defstory
 RhfAccount
 "RHF checkbox, password confirmation, fixed array paths, and nested complex-form data. The local adapter does not expose useFieldArray, so this story intentionally demonstrates supported indexed Controller names rather than an unsupported dynamic-array abstraction."
 {:source-code (embed-source "clojure-shadcn.stories.form.rhf-account")}
 []
 (r/as-element (helpers/wrap-component [rhf-account/example])))
