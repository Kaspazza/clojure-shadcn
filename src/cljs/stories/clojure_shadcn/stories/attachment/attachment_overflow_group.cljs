(ns clojure-shadcn.stories.attachment.attachment-overflow-group
  (:require
   ["lucide-react" :refer [FileText]]
   [clojure-shadcn.ui.components.attachment :as attachment]))

(defn example []
  [attachment/attachment-group {:class "max-w-md" :aria-label "Project files"}
   (for [index (range 1 8)]
     ^{:key index}
     [attachment/attachment {:size :sm}
      [attachment/attachment-media {} [:> FileText]]
      [attachment/attachment-content {}
       [attachment/attachment-title {} (str "quarterly-report-final-" index ".pdf")]
       [attachment/attachment-description {} "2.4 MB"]]])])
