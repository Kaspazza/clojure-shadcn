(ns clojure-shadcn.stories.prompt-input.prompt-input-loading
  (:require
   ["lucide-react"                            :refer [Send]]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.prompt-input :as prompt-input]
   [reagent.core                              :as r]))

(defn example
  []
  (let [value (r/atom "Working on it...")]
    (fn [] [:div {:class "p-6 max-w-xl"}
            [prompt-input/prompt-input {:value @value
                                        :is-loading? true
                                        :on-value-change #(reset! value %)}
             [prompt-input/prompt-input-textarea {:placeholder "Loading"}]
             [prompt-input/prompt-input-actions {}
              [prompt-input/prompt-input-action {:tooltip "Send"}
               (button/button {:variant :outline
                               :size :icon}
                              [:> Send {:class "size-4"}])]]]])))
