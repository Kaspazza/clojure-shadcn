(ns clojure-shadcn.stories.markdown.markdown-code-blocks
  (:require
   [clojure-shadcn.ui.components.markdown :as markdown]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [markdown/markdown
    {:children
     "```clojure\n(defn hello []\n  (println \"Hello\"))\n```\n\n```javascript\nconsole.log('Hello')\n```"}]])
