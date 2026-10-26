(ns clojure-shadcn.stories.input-otp.input-otp-disabled
  (:require
   [clojure-shadcn.ui.components.input-otp :as input-otp]))

(defn example
  []
  [input-otp/input-otp {:max-length 6
                        :disabled true
                        :value "482913"
                        :aria-label "Disabled verification code"}
   (into [input-otp/input-otp-group {}]
         (for [index (range 3)]
           ^{:key index} [input-otp/input-otp-slot {:index index}]))
   [input-otp/input-otp-separator {}]
   (into [input-otp/input-otp-group {}]
         (for [index (range 3 6)]
           ^{:key index} [input-otp/input-otp-slot {:index index}]))])
