(ns clojure-shadcn.stories.prompt-input.prompt-input-multiple-actions
  (:require
   ["lucide-react"                            :refer [Mic Paperclip Send]]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.prompt-input :as prompt-input]
   [reagent.core                              :as r]))

(defn example
  []
  (let [value (r/atom "")]
    (fn [] [:div {:class "p-6 max-w-xl"}
            [prompt-input/prompt-input {:value @value
                                        :on-value-change #(reset! value %)}
             [prompt-input/prompt-input-textarea {:placeholder "Ask a question..."}]
             [prompt-input/prompt-input-actions {}
              [prompt-input/prompt-input-action {:tooltip "Attach file"}
               (button/button {:variant :outline
                               :size :icon}
                              [:> Paperclip {:class "size-4"}])]
              [prompt-input/prompt-input-action {:tooltip "Voice input"}
               (button/button {:variant :outline
                               :size :icon}
                              [:> Mic {:class "size-4"}])]
              [prompt-input/prompt-input-action {:tooltip "Send"}
               (button/button {:variant :outline
                               :size :icon}
                              [:> Send {:class "size-4"}])]]]])))
