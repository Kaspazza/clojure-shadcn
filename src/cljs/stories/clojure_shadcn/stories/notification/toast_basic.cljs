(ns clojure-shadcn.stories.notification.toast-basic
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.notification :as notification]))

(defn example
  []
  [:div {:class "p-6 space-y-4"}
   [notification/toaster]
   (button/button {:on-click (fn [] (notification/show-toast "Event has been created"))}
                  "Show Toast")])
