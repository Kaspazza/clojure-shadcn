(ns clojure-shadcn.stories.typography.type-scale
  (:require
   [clojure-shadcn.ui.components.typography :as typography]))

(defn example
  ([]
   (example
    {:heading "The Taxonomy of Type"
     :body
     "Typography should communicate hierarchy without coupling content to a page-specific stylesheet."}))
  ([args]
   [:article {:class "max-w-2xl"}
    [typography/h1 {}
     (:heading args)]
    [typography/p {}
     (:body args)]]))
