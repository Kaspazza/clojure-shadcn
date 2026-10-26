(ns clojure-shadcn.stories.markdown.markdown-plugin-override
  (:require
   [clojure-shadcn.ui.components.markdown :as markdown]
   [reagent.core                          :as r]))

(defn example
  []
  [:div {:class "p-6 max-w-xl"}
   [markdown/markdown {:children "Read the [component guide](https://example.com)."
                       :components
                       {:a (fn [props]
                             (r/as-element
                              [:a {:href (.-href props)
                                   :class "rounded bg-primary px-2 py-1 text-primary-foreground"}
                               (.-children props)]))}}]])
