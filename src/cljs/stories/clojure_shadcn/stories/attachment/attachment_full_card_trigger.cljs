(ns clojure-shadcn.stories.attachment.attachment-full-card-trigger
  (:require
   ["lucide-react" :refer [FileText X]]
   [clojure-shadcn.ui.components.attachment :as attachment]))

(defn example []
  [attachment/attachment {}
   [attachment/attachment-trigger {:aria-label "Open project brief"
                                   :on-click #(js/console.log "open project brief")}]
   [attachment/attachment-media {} [:> FileText]]
   [attachment/attachment-content {}
    [attachment/attachment-title {} "project-brief.pdf"]
    [attachment/attachment-description {} "Click anywhere to open"]]
   [attachment/attachment-actions {}
    [attachment/attachment-action {:aria-label "Remove project brief"
                                   :on-click #(js/console.log "remove project brief")}
     [:> X]]]])
