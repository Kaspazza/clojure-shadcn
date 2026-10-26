(ns clojure-shadcn.stories.input-otp.input-otp-patterns
  (:require
   [clojure-shadcn.ui.components.input-otp :as input-otp]
   [reagent.core                           :as r]))

(defn example
  []
  [(fn []
     (let [otp-slots (fn [n]
                       (into [input-otp/input-otp-group {}]
                             (for [index (range n)]
                               ^{:key index} [input-otp/input-otp-slot {:index index}])))]
       (r/with-let
        [code (r/atom "")]
        [:div {:class "grid gap-7"}
         [:section {:class "grid gap-2"}
          [:label {:class "text-sm font-medium"}
           "Numeric code"]
          [input-otp/input-otp {:max-length 4
                                :pattern "[0-9]*"
                                :input-mode "numeric"
                                :value @code
                                :on-change #(reset! code %)
                                :aria-label "Four digit code"}
           [otp-slots 4]]
          [:output {:class "text-sm text-muted-foreground"
                    :aria-live "polite"}
           "Controlled value: "
           (if (seq @code) @code "empty")]]
         [:section {:class "grid gap-2"}
          [:label {:class "text-sm font-medium"}
           "Alphanumeric code"]
          [input-otp/input-otp {:max-length 6
                                :pattern "[A-Za-z0-9]*"
                                :aria-label "Six character code"}
           [otp-slots 3]
           [input-otp/input-otp-separator {}]
           (into [input-otp/input-otp-group {}]
                 (for [index (range 3 6)]
                   ^{:key index} [input-otp/input-otp-slot {:index index}]))]]
         [:form {:class "grid gap-2"
                 :on-submit #(.preventDefault %)}
          [:label {:class "text-sm font-medium"}
           "Invalid form value"]
          [input-otp/input-otp {:max-length 6
                                :name "verification-code"
                                :required true
                                :aria-label "Verification code"
                                :aria-invalid true}
           (into [input-otp/input-otp-group {}]
                 (for [index (range 6)]
                   ^{:key index}
                   [input-otp/input-otp-slot {:index index
                                              :aria-invalid true}]))]
          [:p {:class "text-sm text-destructive"}
           "Enter all six digits."]
          [:button {:type "submit"
                    :class "h-9 rounded-md bg-primary px-4 text-primary-foreground"}
           "Verify"]]
         [:section {:dir "rtl"
                    :class "grid gap-2"}
          [:label {:class "text-sm font-medium"}
           "رمز التحقق"]
          [input-otp/input-otp {:max-length 4
                                :input-mode "numeric"
                                :aria-label "رمز التحقق"}
           [otp-slots 4]]]])))])
