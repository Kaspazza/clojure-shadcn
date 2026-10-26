(ns clojure-shadcn.stories.notification.playground
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.notification :as notification]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:message "Changes saved"} consumer-args)]
     [:div {:class "p-6"}
      [notification/toaster {}]
      [button/button {:on-click #(notification/show-success (:message args))}
       "Show success toast"]])))
