(ns clojure-shadcn.stories.prompt-input.prompt-input-disabled
  (:require
   ["lucide-react"                            :refer [Send]]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.prompt-input :as prompt-input]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [prompt-input/prompt-input {:value "Saving..."
                               :disabled? true}
    [prompt-input/prompt-input-textarea {:placeholder "Disabled"}]
    [prompt-input/prompt-input-actions {}
     [prompt-input/prompt-input-action {:tooltip "Send"}
      (button/button {:variant :outline
                      :size :icon}
                     [:> Send {:class "size-4"}])]]]])
