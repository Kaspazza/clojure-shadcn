(ns clojure-shadcn.stories.select.select-patterns
  (:require
   [clojure-shadcn.ui.components.field  :as field]
   [clojure-shadcn.ui.components.select :as select]))

(defn- grouped-select
  [content-props trigger-props]
  [select/select {:default-value "paris"}
   [select/select-trigger
    (merge {:class "w-56"} trigger-props)
    [select/select-value {:placeholder "Choose a city"}]]
   [select/select-content
    content-props
    [select/select-group {}
     [select/select-label {}
      "Europe"]
     [select/select-item {:value "paris"}
      "Paris"]
     [select/select-item {:value "lisbon"}
      "Lisbon"]]
    [select/select-separator {}]
    [select/select-group {}
     [select/select-label {}
      "Asia"]
     [select/select-item {:value "tokyo"}
      "Tokyo"]
     [select/select-item {:value "riyadh"}
      "Riyadh"]]]])

(defn example
  []
  [:div {:class "grid gap-8 md:grid-cols-2"}
   [:section {:class "grid gap-3"}
    [:p {:class "text-sm font-medium"}
     "Start aligned"]
    [grouped-select {:align "start"}
     {}]
    [:p {:class "text-sm font-medium"}
     "End aligned"]
    [grouped-select {:align "end"}
     {}]]
   [field/field {}
    [field/field-label {:html-for "office"}
     "Office"]
    [select/select {}
     [select/select-trigger {:id "office"
                             :class "w-56"
                             :aria-invalid true
                             :aria-describedby "office-error"}
      [select/select-value {:placeholder "Choose an office"}]]
     [select/select-content {}
      [select/select-item {:value "remote"}
       "Remote"]
      [select/select-item {:value "hq"}
       "Headquarters"]]]
    [field/field-error {:id "office-error"
                        :errors [{:message "Choose an office."}]}]]
   [:section {:dir "rtl"
              :class "grid gap-2"}
    [:label {:class "text-sm font-medium"}
     "المدينة"]
    [grouped-select {:align "start"}
     {:aria-label "المدينة"}]]])
