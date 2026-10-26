(ns clojure-shadcn.stories.prompt-input.prompt-input-file-selection
  (:require
   ["lucide-react"                            :refer [Paperclip X]]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.prompt-input :as prompt-input]
   [reagent.core                              :as r]))

(defn example
  []
  (let [value (r/atom "")
        file-name (r/atom nil)
        input-node (atom nil)]
    (fn [] [:div {:class "max-w-xl space-y-3"}
            [:input {:ref #(reset! input-node %)
                     :class "sr-only"
                     :type "file"
                     :on-change #(reset! file-name (some-> %
                                                           .-target
                                                           .-files
                                                           (aget 0)
                                                           .-name))}]
            [prompt-input/prompt-input {:value @value
                                        :on-value-change #(reset! value %)}
             [prompt-input/prompt-input-textarea {:placeholder "Add a note…"}]
             [prompt-input/prompt-input-actions {}
              [prompt-input/prompt-input-action {:tooltip "Choose file"}
               (button/button {:variant :outline
                               :size :icon
                               :aria-label "Choose file"
                               :on-click #(when-let [node @input-node] (.click ^js node))}
                              [:> Paperclip])]]]
            (when @file-name
              [:div {:class "flex items-center justify-between rounded-md border px-3 py-2 text-sm"}
               [:span {:class "truncate"}
                @file-name]
               (button/button {:variant :ghost
                               :size :icon-xs
                               :aria-label (str "Remove " @file-name)
                               :on-click #(do (when-let [node @input-node]
                                                (set! (.-value ^js node) ""))
                                              (reset! file-name nil))}
                              [:> X])])])))
