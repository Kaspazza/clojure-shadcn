(ns clojure-shadcn.stories.prompt-input.prompt-input-submit-lifecycle
  (:require
   ["lucide-react"                            :refer [Send]]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.prompt-input :as prompt-input]
   [reagent.core                              :as r]))

(defn example
  []
  (let [value (r/atom "")
        submitting? (r/atom false)
        status (r/atom "Ready")
        submit! (fn []
                  (when (seq @value)
                    (reset! submitting? true)
                    (reset! status (str "Sending: " @value))
                    (reset! value "")
                    (js/setTimeout #(do (reset! submitting? false) (reset! status "Sent")) 900)))]
    (fn [] [:div {:class "max-w-xl space-y-2"}
            [prompt-input/prompt-input {:value @value
                                        :is-loading? @submitting?
                                        :on-value-change #(reset! value %)
                                        :on-submit submit!}
             [prompt-input/prompt-input-textarea {:placeholder "Ask about the project…"}]
             [prompt-input/prompt-input-actions {:class "justify-end"}
              [prompt-input/prompt-input-action {:tooltip "Send message"}
               (button/button {:variant :default
                               :size :icon
                               :aria-label "Send message"
                               :disabled (or @submitting? (empty? @value))
                               :on-click submit!}
                              [:> Send])]]]
            [:p {:class "text-xs text-muted-foreground"
                 :aria-live "polite"}
             @status]])))
