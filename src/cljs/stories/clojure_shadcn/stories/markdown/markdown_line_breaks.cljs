(ns clojure-shadcn.stories.markdown.markdown-line-breaks
  (:require
   [clojure-shadcn.ui.components.markdown :as markdown]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [markdown/markdown {:children "Line one\nLine two\nLine three"}]])
