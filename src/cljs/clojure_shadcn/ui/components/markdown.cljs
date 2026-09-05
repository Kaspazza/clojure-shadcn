(ns clojure-shadcn.ui.components.markdown
  "Markdown rendering with GFM, line breaks, and syntax-highlighted code blocks."
  (:require
   ["react-markdown"                        :default ReactMarkdown]
   ["remark-breaks"                         :default remarkBreaks]
   ["remark-gfm"                            :default remarkGfm]
   [clojure-shadcn.ui.components.code-block :as code-block]
   [clojure-shadcn.utils.styles             :refer [merge-classes]]
   [reagent.core                            :as r]))

(defn- extract-language [class-name]
  (or (some->> class-name (re-find #"language-(\w+)") second)
      "plaintext"))

(def ^:private initial-components
  {:code (fn [props]
           (let [class-name (.-className props)
                 children (.-children props)
                 node (.-node props)
                 position (when node (.-position node))
                 start-line (when position (.. position -start -line))
                 end-line (when position (.. position -end -line))]
             (if (or (not start-line) (= start-line end-line))
               (r/as-element [:span {:class (merge-classes
                                            "bg-primary-foreground rounded-sm px-1 font-mono text-sm"
                                            class-name)}
                              children])
               (r/as-element
                [code-block/code-block {:class class-name}
                 [code-block/code-block-code {:code (if (string? children) children (str children))
                                              :language (extract-language class-name)}]]))))
   :pre (fn [props]
          (r/as-element [:<> (.-children props)]))})

(defn markdown
  "Render `:children` as one complete Markdown document.

  `:components`, `:remark-plugins`, and `:rehype-plugins` customize
  react-markdown. Remaining props are forwarded to the wrapper element."
  [{:keys [children class components remark-plugins rehype-plugins]
    :or {components initial-components
         remark-plugins [remarkGfm remarkBreaks]}
    :as props}]
  [:div
   (-> props
       (assoc :class class)
       (dissoc :children :components :remark-plugins :rehype-plugins :class-name))
   [:>
    ReactMarkdown
    (cond-> {:remarkPlugins (clj->js remark-plugins)
             :components (clj->js components)}
      rehype-plugins (assoc :rehypePlugins (clj->js rehype-plugins)))
    (or children "")]])
