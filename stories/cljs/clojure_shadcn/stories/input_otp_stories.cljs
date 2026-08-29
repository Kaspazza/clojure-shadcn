(ns clojure-shadcn.stories.input-otp-stories
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.input-otp :as sut]
   [reagent.core :as r])
  (:require-macros
   [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title "Components/Input OTP"
       :parameters #js {:layout "centered"}})

(defn ^:export Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Accessible OTP input with keyboard and paste support."
     :npm-install "npm install input-otp lucide-react"
     :source-code (embed-source "clojure-shadcn.ui.components.input_otp")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/input_otp.cljs"
     :filename "input_otp.cljs"}]))

(defn- otp-example []
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

(defstory SixDigits
  []
  (r/as-element
   (helpers/wrap-component
    [otp-example])))
