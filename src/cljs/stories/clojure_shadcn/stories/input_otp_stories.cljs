(ns clojure-shadcn.stories.input-otp-stories
  (:require
   [clojure-shadcn.stories.helpers                      :as helpers]
   [clojure-shadcn.stories.input-otp.input-otp-disabled :as input-otp-disabled]
   [clojure-shadcn.stories.input-otp.input-otp-patterns :as input-otp-patterns]
   [clojure-shadcn.stories.input-otp.playground         :as playground]
   [clojure-shadcn.stories.input-otp.six-digits         :as six-digits]
   [reagent.core                                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Input OTP"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Accessible OTP input with keyboard and paste support."
                        :npm-install "npm install input-otp lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.input_otp")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/input_otp.cljs"
                        :filename "input_otp.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "input-otp"
      :link {:href "https://input-otp.rodz.dev/"
             :label "input-otp Docs"}
      :description
      "Styled OTPInput preserving the library's single accessible input, keyboard, paste, and screen-reader behavior. Compose visual groups and slots as children."
      :props
      [{:name ":max-length"
        :type "number, required by input-otp"
        :default nil
        :description "Number of OTP characters and therefore valid slot indices."}
       {:name ":value / :on-change"
        :type "string / function"
        :default nil
        :description
        "Controlled value and update callback; input-otp also supports its documented uncontrolled props."}
       {:name ":container-class"
        :type "string"
        :default nil
        :description "Classes merged onto OTPInput's visual container."}
       {:name ":class"
        :type "string"
        :default nil
        :description "Classes merged onto the underlying accessible input."}
       {:name "additional props"
        :type "map entries"
        :default nil
        :description "Normalized and forwarded to OTPInput."}]}]
    [helpers/api-component-card
     {:component-name "input-otp-group"
      :description "Visual div grouping adjacent slots; it has no input semantics of its own."
      :props [{:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged/forwarded to the div."}]}]
    [helpers/api-component-card
     {:component-name "input-otp-slot"
      :description
      "Reads one visual slot from OTPInputContext and renders its character, active state, and fake caret. Must be nested under input-otp."
      :props [{:name ":index"
               :type "number, required"
               :default nil
               :description "Zero-based index into OTPInput slots; keep it below :max-length."}
              {:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged/forwarded to the slot div; :index is consumed."}]}]
    [helpers/api-component-card
     {:component-name "input-otp-separator"
      :description "Decorative group separator rendered with role=separator and a minus icon."
      :props
      [{:name "additional props"
        :type "map entries"
        :default nil
        :description
        "Forwarded to the separator div; role and data-slot are set by the component."}]}]])))


(defstory SixDigits
          {:source-code (embed-source "clojure-shadcn.stories.input-otp.six-digits")}
          []
          (r/as-element (helpers/wrap-component [six-digits/example])))

(defstory InputOtpDisabled
          "Disabled OTP state. Based on shadcn/ui Input OTP — Disabled."
          {:source-code (embed-source "clojure-shadcn.stories.input-otp.input-otp-disabled")}
          []
          (r/as-element (helpers/wrap-component [input-otp-disabled/example])))

(defstory Playground
          "Interactive OTP playground."
          {:source-code (embed-source "clojure-shadcn.stories.input-otp.playground")
           :args {:max-length 6
                  :disabled false
                  :placeholder "○"}
           :arg-types {:max-length {:name "Max length"
                                    :control {:type "select"}
                                    :options [4 6]}
                       :disabled {:name "Disabled"
                                  :control {:type "boolean"}}
                       :placeholder {:name "Placeholder"
                                     :control {:type "text"}}}
           :parameters
           {:playground
            {:description
             "Explore input otp max length, disabled and placeholder with a live preview."}
            :controls {:expanded false
                       :exclude ["value" "on-change" "class" "container-class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))


(defstory
 InputOtpPatterns
 "Numeric and alphanumeric patterns, controlled output, invalid form integration, and RTL context."
 {:source-code (embed-source "clojure-shadcn.stories.input-otp.input-otp-patterns")}
 []
 (r/as-element (helpers/wrap-component [input-otp-patterns/example])))
