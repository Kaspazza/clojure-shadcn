(ns clojure-shadcn.stories.markdown.markdown-links-lists
  (:require
   [clojure-shadcn.ui.components.markdown :as markdown]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [markdown/markdown
    {:children
     "- Item one\n- Item two\n- Item three\n\nVisit [our docs](https://ui.shadcn.com)."}]])
