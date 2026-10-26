(ns clojure-shadcn.stories.radio-group.radio-option-descriptions
  (:require
   [clojure-shadcn.ui.components.field       :as field]
   [clojure-shadcn.ui.components.radio-group :as radio-group]))

(defn example
  []
  [:div {:class "max-w-md p-6"}
   [radio-group/radio-group {:default-value "email"
                             :aria-label "Notification channel"}
    (for [[value title description] [["email" "Email" "Receive a daily summary."]
                                     ["push" "Push" "Receive alerts on this device."]]]
      ^{:key value}
      [field/field {:orientation :horizontal}
       [radio-group/radio-group-item {:id (str "channel-" value)
                                      :value value
                                      :aria-describedby (str "channel-" value "-description")}]
       [field/field-content {}
        [field/field-label {:html-for (str "channel-" value)}
         title]
        [field/field-description {:id (str "channel-" value "-description")}
         description]]])]])
