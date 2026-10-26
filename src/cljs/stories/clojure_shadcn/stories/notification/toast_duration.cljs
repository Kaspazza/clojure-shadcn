(ns clojure-shadcn.stories.notification.toast-duration
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.notification :as notification]))

(defn example
  []
  [:div {:class "p-6 space-y-4"}
   [notification/toaster]
   (button/button {:on-click #(notification/show-toast "Auto closes in 10 seconds"
                                                       {:duration 10000})}
                  "Show Long Toast")])
