(ns clojure-shadcn.stories.speech-recognition-button.speech-recognition-transcript
  (:require
   [clojure-shadcn.ui.components.prompt-input              :as prompt-input]
   [clojure-shadcn.ui.components.speech-recognition-button :as speech-recognition-button]
   [reagent.core                                           :as r]))

(defn example
  []
  (let [value (r/atom "")]
    (fn [] [:div {:class "p-6 space-y-3"}
            [:div {:class "rounded-md border bg-muted/50 p-3 text-sm"}
             "Current transcript: "
             (if (seq @value) @value "(none)")]
            [prompt-input/prompt-input {:value @value
                                        :on-value-change #(reset! value %)}
             [prompt-input/prompt-input-textarea {:placeholder "Start talking..."}]
             [prompt-input/prompt-input-actions {}
              [speech-recognition-button/speech-recognition-button {:on-transcript-change
                                                                    #(reset! value %)}]]]])))
