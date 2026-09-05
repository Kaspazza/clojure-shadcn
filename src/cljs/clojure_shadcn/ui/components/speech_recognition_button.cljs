(ns clojure-shadcn.ui.components.speech-recognition-button
  "Microphone button component with speech recognition functionality.

Version: 1.0.0
Last updated: 2026-02-06

Custom component implementation."
  (:require
   ["lucide-react"                            :refer [Mic]]
   ["react-speech-recognition"                :default SpeechRecognition
                                              :refer   [useSpeechRecognition]]
   [clojure-shadcn.ui.components.button       :as mateuszmazurczak-button]
   [clojure-shadcn.ui.components.loader       :as loader]
   [clojure-shadcn.ui.components.prompt-input :as prompt-input]
   [clojure-shadcn.utils.props                :refer [normalize-props]]
   [reagent.core                              :as    r
                                              :refer [defc]]
   [reagent.hooks                             :as rhooks]))

(defc speech-recognition-button
 "Microphone button with speech recognition that updates text on change.
  
  Props:
  - `:on-transcript-change` - Callback function called with transcript text (fn [text] ...)
  - `:on-error` - Optional callback invoked when recognition startup fails (fn [error] ...)
  - `:language` - Language code for speech recognition (default: \"en-US\")
                  Examples: \"en-US\", \"pl-PL\", \"es-ES\", \"fr-FR\", \"de-DE\"
  - `:continuous` - Whether to continue listening after speech stops (default: true)
  - `:class` - Additional CSS classes for the button wrapper
  Both kebab-case and camelCase prop spellings are accepted."
 [{:as raw-props}]
 (let [{:keys [on-transcript-change on-error language continuous class]
        :or {language "en-US"
             continuous true}}
       (normalize-props raw-props)]
   (let [;; Speech recognition hook
         speech-recognition (useSpeechRecognition)
         transcript (.-transcript speech-recognition)
         listening? (.-listening speech-recognition)
         browser-supports? (.-browserSupportsSpeechRecognition speech-recognition)
         reset-transcript! (.-resetTranscript speech-recognition)
         callback-ref (rhooks/use-ref on-transcript-change)
         error-callback-ref (rhooks/use-ref on-error)
         ownership-ref (rhooks/use-ref false)
         pending-start-ref (rhooks/use-ref false)
         mounted-ref (rhooks/use-ref true)
         start-generation-ref (rhooks/use-ref 0)
         _ (rhooks/use-effect (fn []
                                (set! (.-current callback-ref) on-transcript-change)
                                (set! (.-current error-callback-ref) on-error)
                                js/undefined)
                              [on-transcript-change on-error])
         _ (rhooks/use-effect (fn []
                                (when (seq transcript)
                                  (when-let [callback (.-current callback-ref)]
                                    (callback transcript)))
                                js/undefined)
                              [transcript])
         _ (rhooks/use-effect (fn []
                                (set! (.-current mounted-ref) true)
                                (fn []
                                  (let [owned-or-pending? (or (.-current ownership-ref)
                                                              (.-current pending-start-ref))]
                                    (set! (.-current mounted-ref) false)
                                    (set! (.-current ownership-ref) false)
                                    (set! (.-current pending-start-ref) false)
                                    (set! (.-current start-generation-ref)
                                          (inc (.-current start-generation-ref)))
                                    (when owned-or-pending? (.abortListening SpeechRecognition)))))
                              [])
         handle-mic-toggle
         (rhooks/use-callback
          (fn []
            (cond
              (.-current pending-start-ref) nil
              (and listening? (.-current ownership-ref)) (do (set! (.-current ownership-ref) false)
                                                             (.stopListening SpeechRecognition))
              :else (let [generation (inc (.-current start-generation-ref))]
                      (set! (.-current start-generation-ref) generation)
                      (set! (.-current pending-start-ref) true)
                      (set! (.-current ownership-ref) false)
                      (reset-transcript!)
                      (-> (.startListening SpeechRecognition
                                           #js {:continuous continuous
                                                :language language})
                          (.then (fn []
                                   (when (= generation (.-current start-generation-ref))
                                     (set! (.-current pending-start-ref) false)
                                     (when (.-current mounted-ref)
                                       (set! (.-current ownership-ref) true)))))
                          (.catch (fn [error]
                                    (when (= generation (.-current start-generation-ref))
                                      (set! (.-current pending-start-ref) false)
                                      (set! (.-current ownership-ref) false)
                                      (if-let [callback (.-current error-callback-ref)]
                                        (callback error)
                                        (js/console.error "Failed to start speech recognition"
                                                          error)))))))))
          [listening? reset-transcript! continuous language])]
     (if browser-supports?
       [prompt-input/prompt-input-action {:tooltip (if listening? "Stop recording" "Voice input")
                                          :class class}
        [mateuszmazurczak-button/button {:type "button"
                                         :variant :outline
                                         :size :icon
                                         :class "size-9 rounded-full"
                                         :on-click handle-mic-toggle}
         (if listening?
           [loader/loader {:variant :wave
                           :size :sm}]
           [:> Mic {:size 18}])]]
       [prompt-input/prompt-input-action {:tooltip
                                          "Speech recognition not supported in this browser"
                                          :class class}
        [mateuszmazurczak-button/button {:type "button"
                                         :variant :outline
                                         :size :icon
                                         :disabled true
                                         :class "size-9 rounded-full"}
         [:> Mic {:size 18}]]]))))
