(ns clojure-shadcn.stories.markdown.markdown-raw-html-safety
  (:require
   [clojure-shadcn.ui.components.markdown :as markdown]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [markdown/markdown {:children
                       "Before <script>alert('unsafe')</script> <b>not interpreted</b> after"}]])
