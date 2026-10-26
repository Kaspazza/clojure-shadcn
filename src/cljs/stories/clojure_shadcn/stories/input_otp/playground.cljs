(ns clojure-shadcn.stories.input-otp.playground
  (:require
   [clojure-shadcn.ui.components.input-otp :as input-otp]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:max-length 6
                      :disabled false
                      :placeholder "○"}
                     consumer-args)]
     [input-otp/input-otp {:max-length (:max-length args)
                           :disabled (:disabled args)
                           :aria-label "Verification code"}
      (into [input-otp/input-otp-group {}]
            (for [index (range (:max-length args))]
              ^{:key index}
              [input-otp/input-otp-slot {:index index
                                         :placeholder (:placeholder args)}]))])))
