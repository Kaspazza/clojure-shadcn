(ns clojure-shadcn.stories.markdown.markdown-single-line-fence
  (:require
   [clojure-shadcn.ui.components.markdown :as markdown]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [markdown/markdown {:children "```clojure\n(+ 1 2)\n```"}]])
