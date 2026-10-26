(ns clojure-shadcn.stories.prompt-input.prompt-input-basic
  (:require
   ["lucide-react"                            :refer [Send]]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.prompt-input :as prompt-input]
   [reagent.core                              :as r]))

(defn example
  []
  (let [value (r/atom "")]
    (fn [] [:div {:class "p-6 max-w-xl"}
            [prompt-input/prompt-input {:value @value
                                        :on-value-change #(reset! value %)
                                        :on-submit #(js/console.log "submit" @value)}
             [prompt-input/prompt-input-textarea {:placeholder "Type your message..."}]
             [prompt-input/prompt-input-actions {}
              [prompt-input/prompt-input-action {:tooltip "Send"}
               (button/button {:variant :outline
                               :size :icon}
                              [:> Send {:class "size-4"}])]]]])))
