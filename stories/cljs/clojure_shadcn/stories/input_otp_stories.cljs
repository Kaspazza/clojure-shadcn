(ns clojure-shadcn.stories.input-otp-stories
  (:require
   [clojure-shadcn.stories.helpers         :as helpers]
   [clojure-shadcn.ui.components.input-otp :as sut]
   [reagent.core                           :as r])
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

(defn- otp-example
  []
  (r/with-let [value (r/atom "")]
              [sut/input-otp {:max-length 6
                              :value @value
                              :on-change #(reset! value %)
                              :aria-label "Six-digit verification code"}
               (into [sut/input-otp-group {}]
                     (for [index (range 3)]
                       ^{:key index} [sut/input-otp-slot {:index index}]))
               [sut/input-otp-separator {}]
               (into [sut/input-otp-group {}]
                     (for [index (range 3 6)]
                       ^{:key index} [sut/input-otp-slot {:index index}]))]))

(defstory SixDigits [] (r/as-element (helpers/wrap-component [otp-example])))


(defstory InputOtpPlayground
          "Interactive OTP playground."
          {:args {:max-length 6
                  :disabled false
                  :placeholder "○"}
           :arg-types {:max-length {:control {:type "select"}
                                    :options [4 6]}
                       :disabled {:control {:type "boolean"}}
                       :placeholder {:control {:type "text"}}}
           :parameters {:controls {:exclude ["value" "on-change" "class" "container-class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [sut/input-otp {:max-length (:max-length args)
                                                                :disabled (:disabled args)
                                                                :aria-label "Verification code"}
                                                 (into [sut/input-otp-group {}]
                                                       (for [index (range (:max-length args))]
                                                         ^{:key index}
                                                         [sut/input-otp-slot
                                                          {:index index
                                                           :placeholder (:placeholder args)}]))])))
