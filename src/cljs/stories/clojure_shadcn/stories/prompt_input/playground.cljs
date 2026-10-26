(ns clojure-shadcn.stories.prompt-input.playground
  (:require
   [clojure-shadcn.ui.components.prompt-input :as prompt-input]
   [reagent.core                              :as r]))

(defn example
  ([]
   (example {:placeholder "Type your message…"
             :disabled false
             :max-height 240}))
  ([args]
   (r/with-let [value (r/atom "")]
               [:div {:class "max-w-xl p-6"}
                [prompt-input/prompt-input {:value @value
                                            :disabled? (:disabled args)
                                            :max-height (:max-height args)
                                            :on-value-change #(reset! value %)}
                 [prompt-input/prompt-input-textarea {:placeholder (:placeholder args)}]]])))
