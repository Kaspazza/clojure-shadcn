(ns clojure-shadcn.stories.field.field-patterns
  (:require
   [clojure-shadcn.ui.components.button   :as button]
   [clojure-shadcn.ui.components.checkbox :as checkbox]
   [clojure-shadcn.ui.components.field    :as field]
   [clojure-shadcn.ui.components.input    :as input]
   [clojure-shadcn.ui.components.slider   :as slider]))

(defn example
  []
  [:form {:class "grid max-w-2xl gap-8"
          :on-submit (fn* [%1] (.preventDefault %1))}
   [field/field-set {}
    [field/field-legend {}
     "Notification settings"]
    [field/field-group {}
     [field/field {:orientation :responsive}
      [field/field-label {:html-for "display-name"}
       "Display name"]
      [field/field-content {}
       [input/input {:id "display-name"
                     :name "display-name"
                     :required true}]
       [field/field-description {}
        "Shown to collaborators."]]]
     [field/field {}
      [field/field-label {:html-for "volume"}
       "Alert volume"]
      [slider/slider {:id "volume"
                      :name "volume"
                      :default-value [40]
                      :aria-label "Alert volume"}]]
     [field/field {:class "rounded-lg border p-4"
                   :orientation :horizontal}
      [checkbox/checkbox {:id "weekly-summary"
                          :name "weekly-summary"}]
      [field/field-content {}
       [field/field-label {:html-for "weekly-summary"}
        [field/field-title {}
         "Weekly summary"]]
       [field/field-description {}
        "Receive one concise activity email."]]]
     [field/field {}
      [field/field-label {:html-for "work-email"}
       "Work email"]
      [input/input {:id "work-email"
                    :type "email"
                    :aria-invalid true
                    :default-value "not-an-email"}]
      [field/field-error {:errors [{:message "Enter a valid work email."}]}]]]]
   [:div {:dir "rtl"}
    [field/field {:orientation :responsive}
     [field/field-label {:html-for "arabic-name"}
      "الاسم"]
     [field/field-content {}
      [input/input {:id "arabic-name"
                    :name "arabic-name"}]
      [field/field-description {}
       "يظهر هذا الاسم في ملفك الشخصي."]]]]
   [button/button {:type "submit"}
    "Save settings"]])
