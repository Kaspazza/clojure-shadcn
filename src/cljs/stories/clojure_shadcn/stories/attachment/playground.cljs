(ns clojure-shadcn.stories.attachment.playground
  (:require
   ["lucide-react" :refer [FileText X]]
   [clojure-shadcn.ui.components.attachment :as attachment]))

(defn example
  ([]
   (example {:state :done
             :size :default
             :orientation :horizontal
             :title "invoice.pdf"}))
  ([args]
  [attachment/attachment-group {}
   [attachment/attachment (select-keys args [:state :size :orientation])
    [attachment/attachment-media {} [:> FileText]]
    [attachment/attachment-content {}
     [attachment/attachment-title {} (:title args)]
     [attachment/attachment-description {}
      (let [state (:state args)]
        (cond
          (keyword? state) (name state)
          (some? state) (str state)
          :else ""))]]
    [attachment/attachment-actions {}
     [attachment/attachment-action {:aria-label "Remove"} [:> X]]]]]))
