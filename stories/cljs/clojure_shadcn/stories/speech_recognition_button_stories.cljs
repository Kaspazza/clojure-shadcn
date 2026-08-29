(ns clojure-shadcn.stories.speech-recognition-button-stories
  "Storybook stories for the Speech Recognition Button component. Ported from mateuszmazurczak.portfolio.ui-components.speech_recognition_button."
  (:require
   [clojure-shadcn.stories.helpers                         :as helpers]
   [clojure-shadcn.ui.components.prompt-input              :as prompt-input]
   [clojure-shadcn.ui.components.speech-recognition-button :as sut]
   [reagent.core                                             :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory]]))

(def ^:export default
  #js {:title      "Components/Speech Recognition Button"
       :parameters #js {:layout "padded"}})

(defn ^:export Installation
  "Install dependencies and copy the component code into your project."
  []
  (r/as-element
  [helpers/installation-scene
             {:description "Microphone button component with speech recognition functionality."
              :npm-install "npm install lucide-react react-speech-recognition"
              :source-code (embed-source "clojure-shadcn.ui.components.speech_recognition_button")
              :namespace-path "src/cljs/clojure_shadcn/ui/components/speech_recognition_button.cljs"
              :filename "speech_recognition_button.cljs"}]))

(defstory ApiReference
  []
  (r/as-element
  (helpers/wrap-component
             [:div {:class "p-6 max-w-4xl"}
              [:div {:class "space-y-4"}
               [helpers/api-component-card
                {:component-name "speech-recognition-button"
                 :description "Microphone action button built on react-speech-recognition. Starts/stops listening and streams transcript text through a callback."
                 :link {:href "https://www.npmjs.com/package/react-speech-recognition" :label "react-speech-recognition Docs"}
                 :props [{:name ":on-transcript-change" :type "function" :default nil      :description "Called whenever transcript updates: (fn [text] ...)."}
                         {:name ":language"             :type "string"   :default "\"en-US\"" :description "Speech recognition locale, e.g. \"pl-PL\" or \"en-US\"."}
                         {:name ":continuous"           :type "boolean"  :default "true"   :description "Continue listening after pauses in speech."}
                         {:name ":class"                :type "string"   :default nil      :description "Additional classes applied to the prompt-input action wrapper."}]}]
                [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
                 [:h4 {:class "text-sm font-semibold mb-2"} "⚠️ Important Notes"]
                 [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
                  [:li "Best used inside prompt-input-actions, because it renders a prompt-input-action wrapper internally."]
                  [:li "When browser speech recognition is unsupported, the component renders a disabled button fallback."]
                  [:li "For production UX, pair with visible text state so users can confirm recognized transcript."]]]
                [:div {:class "border rounded-lg p-4 bg-muted/50"}
                 [:h4 {:class "text-sm font-semibold mb-2"}
                  "Usage Example"]
                 [:pre {:class "text-xs overflow-x-auto"}
                  [:code "(let [value (r/atom \"\")]\n  [prompt-input {:value @value\n                 :on-value-change #(reset! value %)}\n   [prompt-input-textarea {:placeholder \"Speak or type...\"}]\n   [prompt-input-actions {}\n    [speech-recognition-button {:language \"en-US\"\n                                :on-transcript-change #(reset! value %)}]]])"]]
  ]]])))

(defstory SpeechRecognitionStandalone
  "Standalone speech recognition action.
  Wraps react-speech-recognition for microphone input.

  Use inside prompt-input actions for best UX."
  []
  (r/as-element [(fn [] (let [value (r/atom "")]
     (fn []
       (helpers/wrap-component
        [:div {:class "p-6 space-y-2"}
         [:div {:class "text-sm text-muted-foreground"}
          "Transcript: "
          (or @value "(empty)")]
         [prompt-input/prompt-input {:value @value
                                     :on-value-change #(reset! value %)}
          [prompt-input/prompt-input-textarea {:placeholder "Try voice input..."}]
          [prompt-input/prompt-input-actions {}
           [sut/speech-recognition-button {:on-transcript-change #(reset! value %)}]]]]))))]))

(defstory SpeechRecognitionInPrompt
  "Speech recognition inside prompt input.
  Demonstrates composition with prompt-input."
  []
  (r/as-element [(fn [] (let [value (r/atom "")]
     (fn []
       (helpers/wrap-component
        [:div {:class "p-6 max-w-xl"}
         [prompt-input/prompt-input {:value @value
                                     :on-value-change #(reset! value %)}
          [prompt-input/prompt-input-textarea {:placeholder "Speak or type a message..."}]
          [prompt-input/prompt-input-actions {}
           [sut/speech-recognition-button {:on-transcript-change #(reset! value %)}]]]]))))]))

(defstory SpeechRecognitionTranscript
  "Transcript display with live updates.
  Shows how to mirror transcript into the UI."
  []
  (r/as-element [(fn [] (let [value (r/atom "")]
     (fn []
       (helpers/wrap-component
        [:div {:class "p-6 space-y-3"}
         [:div {:class "rounded-md border bg-muted/50 p-3 text-sm"}
          "Current transcript: "
          (if (seq @value) @value "(none)")]
         [prompt-input/prompt-input {:value @value
                                     :on-value-change #(reset! value %)}
          [prompt-input/prompt-input-textarea {:placeholder "Start talking..."}]
          [prompt-input/prompt-input-actions {}
           [sut/speech-recognition-button {:on-transcript-change #(reset! value %)}]]]]))))]))
