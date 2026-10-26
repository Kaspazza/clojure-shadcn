(ns clojure-shadcn.stories.notification.toast-with-description
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.notification :as notification]))

(defn example
  []
  [:div {:class "p-6 space-y-4"}
   [notification/toaster]
   (button/button {:on-click (fn []
                               (notification/show-toast "Event created"
                                                        {:description
                                                         "Sunday, December 3 at 9:00 AM"}))}
                  "Show Detailed Toast")])
