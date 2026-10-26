(ns clojure-shadcn.stories.speech-recognition-button.speech-recognition-standalone
  (:require
   [clojure-shadcn.ui.components.prompt-input              :as prompt-input]
   [clojure-shadcn.ui.components.speech-recognition-button :as speech-recognition-button]
   [reagent.core                                           :as r]))

(defn example
  []
  (let [value (r/atom "")]
    (fn [] [:div {:class "p-6 space-y-2"}
            [:div {:class "text-sm text-muted-foreground"}
             "Transcript: "
             (or @value "(empty)")]
            [prompt-input/prompt-input {:value @value
                                        :on-value-change #(reset! value %)}
             [prompt-input/prompt-input-textarea {:placeholder "Try voice input..."}]
             [prompt-input/prompt-input-actions {}
              [speech-recognition-button/speech-recognition-button {:on-transcript-change
                                                                    #(reset! value %)}]]]])))
