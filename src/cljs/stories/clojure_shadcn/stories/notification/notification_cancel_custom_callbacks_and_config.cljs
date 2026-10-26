(ns clojure-shadcn.stories.notification.notification-cancel-custom-callbacks-and-config
  (:require
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.notification :as notification]
   [reagent.core                              :as r]))

(defn example
  []
  (r/with-let [event (r/atom "No callback yet")]
              [:div {:class "space-y-3 p-6"}
               [notification/toaster {:position "bottom-center"
                                      :close-button true
                                      :visible-toasts 4}]
               [:output {:class "block text-sm text-muted-foreground"}
                @event]
               [:div {:class "flex flex-wrap gap-2"}
                [button/button {:on-click (fn []
                                            (notification/show-toast
                                             "Delete draft?"
                                             {:action {:label "Delete"
                                                       :on-click #(reset! event "Action clicked")}
                                              :cancel {:label "Cancel"
                                                       :on-click #(reset! event "Cancelled")}
                                              :on-dismiss #(reset! event "Dismissed")
                                              :on-auto-close #(reset! event "Auto-closed")}))}
                 "Callbacks"]
                [button/button {:variant :outline
                                :on-click #(notification/custom-toast
                                            [:div {:class "flex items-center gap-2 font-medium"}
                                             [:span "🚀"]
                                             [:span "Custom release content"]]
                                            {:duration 4000})}
                 "Custom content"]
                [button/button {:variant :ghost
                                :on-click notification/dismiss-toast}
                 "Dismiss all"]]]))
