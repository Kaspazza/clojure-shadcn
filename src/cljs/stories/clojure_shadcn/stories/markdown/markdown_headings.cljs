(ns clojure-shadcn.stories.markdown.markdown-headings
  (:require
   [clojure-shadcn.ui.components.markdown :as markdown]))

(defn example
  ([] (example {}))
  ([consumer-args]
   (let [args (merge {:children "# Heading 1\n\n## Heading 2\n\n**Bold** and _italic_ text."}
                     consumer-args)]
     [:div {:class "p-6 max-w-xl"}
      [markdown/markdown (select-keys args [:children])]])))
