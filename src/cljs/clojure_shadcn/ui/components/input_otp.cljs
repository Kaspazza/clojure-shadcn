(ns clojure-shadcn.ui.components.input-otp
  "One-time-password input preserving input-otp keyboard, paste and screen-reader behavior."
  (:require
   ["input-otp"                 :refer [OTPInput OTPInputContext]]
   ["lucide-react"              :refer [MinusIcon]]
   ["react"                     :as react]
   [clojure-shadcn.utils.props  :refer [normalize-props]]
   [clojure-shadcn.utils.styles :refer [merge-classes]]))

(defn input-otp
  [{:keys [class container-class]
    :as raw-props}
   &
   children]
  (let [props (normalize-props raw-props)]
    (into [:>
           OTPInput
           (-> props
               (assoc :data-slot "input-otp"
                      :className (merge-classes "disabled:cursor-not-allowed" class)
                      :containerClassName (merge-classes
                                           "flex items-center gap-2 has-disabled:opacity-50"
                                           container-class))
               (dissoc :class :class-name :container-class))]
          children)))

(defn input-otp-group
  [{:keys [class]
    :as props}
   &
   children]
  (into [:div
         (-> props
             (assoc :data-slot "input-otp-group" :class (merge-classes "flex items-center" class))
             (dissoc :class-name))]
        children))

(defn input-otp-slot
  [{:keys [index class]
    :as props}]
  (let [^js context (react/useContext OTPInputContext)
        ^js slot (some-> context
                         .-slots
                         (aget index))]
    [:div
     (->
       props
       (assoc
        :data-slot "input-otp-slot"
        :data-active (boolean (some-> slot
                                      .-isActive))
        :class
        (merge-classes
         "relative flex h-9 w-9 items-center justify-center border-y border-r border-input text-sm shadow-xs transition-all outline-none first:rounded-l-md first:border-l last:rounded-r-md aria-invalid:border-destructive data-[active=true]:z-10 data-[active=true]:border-ring data-[active=true]:ring-[3px] data-[active=true]:ring-ring/50 dark:bg-input/30"
         class))
       (dissoc :class-name :index))
     (some-> slot
             .-char)
     (when (some-> slot
                   .-hasFakeCaret)
       [:div {:class "pointer-events-none absolute inset-0 flex items-center justify-center"}
        [:div {:class "h-4 w-px animate-pulse bg-foreground"}]])]))

(defn input-otp-separator
  [props]
  [:div
   (assoc props :data-slot "input-otp-separator" :role "separator")
   [:> MinusIcon {:className "size-4"}]])
