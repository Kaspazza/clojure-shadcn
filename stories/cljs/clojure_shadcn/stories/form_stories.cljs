(ns clojure-shadcn.stories.form-stories
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.button :as button]
   [clojure-shadcn.ui.components.form :as sut]
   [clojure-shadcn.ui.components.input :as input]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Components/Form"
       :parameters #js {:layout "centered"}})

(defn ^:export Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Idiomatic Reagent adapter for React Hook Form."
     :npm-install "npm install react-hook-form @radix-ui/react-slot"
     :source-code (embed-source "clojure-shadcn.ui.components.form")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/form.cljs"
     :filename "form.cljs"}]))

(defn- example-form []
  (let [methods (sut/use-form {:default-values {:email ""}})]
    [sut/form {:methods methods}
     [:form {:class "w-80 space-y-4"
             :no-validate true
             :on-submit (sut/handle-submit
                         methods
                         #(js/alert (str "Submitted " (:email %))))}
      [sut/form-field
       {:control (.-control methods)
        :name "email"
        :rules #js {:required "Email is required"}
        :render (fn [{:keys [field]}]
                  [sut/form-item {}
                   [sut/form-label {} "Email"]
                   [sut/form-control {}
                    [input/input (merge field
                                        {:type "email"
                                         :placeholder "you@example.com"})]]
                   [sut/form-description {}
                    "We only use this for account messages."]
                   [sut/form-message {}]])}]
      [button/button {:type "submit"} "Submit"]]]))

(defstory Validation
  []
  (r/as-element
   (helpers/wrap-component
    [example-form])))
