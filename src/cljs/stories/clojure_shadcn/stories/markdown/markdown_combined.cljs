(ns clojure-shadcn.stories.markdown.markdown-combined
  (:require
   [clojure-shadcn.ui.components.markdown :as markdown]))

(defn example
  []
  [:div {:class "p-6 max-w-2xl"}
   [markdown/markdown
    {:children
     "# Release Notes\n\n- [x] Tables\n- [ ] Shipping\n\n| Feature | State |\n| --- | --- |\n| GFM | **ready** |\n\n~~Removed~~ and https://example.com\nfirst line\nsecond line\n\n```clojure\n(defn ready? [] true)\n```"}]])
