(ns clojure-shadcn.stories.date-picker.date-picker-states
  (:require
   [clojure-shadcn.ui.components.date-picker :as date-picker]))

(defn example
  []
  [:div {:class "grid max-w-sm gap-5"}
   [:label {:class "grid gap-2 text-sm font-medium"}
    "Existing date-picker caller"
    [date-picker/date-picker {:name "start-date"
                              :default-value "2026-03-01"}]]
   [:label {:class "grid gap-2 text-sm font-medium"}
    "Explicit native-date-picker"
    [date-picker/native-date-picker {:min "2026-03-01"
                                     :max "2026-12-31"
                                     :required true}]]])
