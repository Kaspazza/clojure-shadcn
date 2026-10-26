(ns clojure-shadcn.stories.date-picker.playground
  (:require
   [clojure-shadcn.ui.components.date-picker :as date-picker]))

(defn example
  ([]
   (example {:default-value "2026-03-01"
             :disabled false
             :required false
             :min "2026-01-01"
             :max "2026-12-31"}))
  ([args]
   [:div {:class "max-w-sm p-6"}
    [date-picker/date-picker args]]))
