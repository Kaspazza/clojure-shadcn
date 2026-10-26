(ns clojure-shadcn.stories.input-otp.six-digits
  (:require
   [clojure-shadcn.ui.components.input-otp :as input-otp]
   [reagent.core                           :as r]))

(defn example
  []
  [(fn []
     (r/with-let [value (r/atom "")]
                 [input-otp/input-otp {:max-length 6
                                       :value @value
                                       :on-change #(reset! value %)
                                       :aria-label "Six-digit verification code"}
                  (into [input-otp/input-otp-group {}]
                        (for [index (range 3)]
                          ^{:key index} [input-otp/input-otp-slot {:index index}]))
                  [input-otp/input-otp-separator {}]
                  (into [input-otp/input-otp-group {}]
                        (for [index (range 3 6)]
                          ^{:key index} [input-otp/input-otp-slot {:index index}]))]))])
