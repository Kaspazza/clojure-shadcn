(ns clojure-shadcn.stories.speech-recognition-button.playground
  (:require
   [clojure-shadcn.ui.components.prompt-input              :as prompt-input]
   [clojure-shadcn.ui.components.speech-recognition-button :as speech-recognition-button]
   [reagent.core                                           :as r]))

(defn example
  ([]
   (example {:language "en-US"
             :continuous true}))
  ([args]
   (r/with-let [value (r/atom "")]
               [:div {:class "max-w-xl p-6"}
                [prompt-input/prompt-input {:value @value
                                            :on-value-change #(reset! value %)}
                 [prompt-input/prompt-input-textarea {:placeholder "Speak or type…"}]
                 [prompt-input/prompt-input-actions {}
                  [speech-recognition-button/speech-recognition-button
                   (assoc args :on-transcript-change #(reset! value %))]]]])))
