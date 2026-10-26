(ns clojure-shadcn.stories.notification.toast-with-action
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.notification :as notification]))

(defn example
  []
  [:div {:class "p-6 space-y-4"}
   [notification/toaster]
   (button/button {:on-click (fn []
                               (notification/show-toast
                                "File deleted"
                                {:action {:label "Undo"
                                          :on-click (fn [] (js/console.log "undo"))}}))}
                  "Show Action Toast")])
