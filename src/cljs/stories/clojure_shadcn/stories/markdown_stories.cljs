(ns clojure-shadcn.stories.markdown-stories
  "Storybook stories for the Markdown component. Ported from mateuszmazurczak.portfolio.ui-components.markdown."
  (:require
   [clojure-shadcn.stories.helpers                             :as helpers]
   [clojure-shadcn.stories.markdown.markdown-code-blocks       :as markdown-code-blocks]
   [clojure-shadcn.stories.markdown.markdown-combined          :as markdown-combined]
   [clojure-shadcn.stories.markdown.markdown-headings          :as markdown-headings]
   [clojure-shadcn.stories.markdown.markdown-line-breaks       :as markdown-line-breaks]
   [clojure-shadcn.stories.markdown.markdown-links-lists       :as markdown-links-lists]
   [clojure-shadcn.stories.markdown.markdown-plugin-override   :as markdown-plugin-override]
   [clojure-shadcn.stories.markdown.markdown-raw-html-safety   :as markdown-raw-html-safety]
   [clojure-shadcn.stories.markdown.markdown-single-line-fence :as markdown-single-line-fence]
   [reagent.core                                               :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Markdown"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Markdown component with syntax highlighting and rich formatting."
                        :npm-install "npm install react-markdown remark-breaks remark-gfm"
                        :source-code (embed-source "clojure-shadcn.ui.components.markdown")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/markdown.cljs"
                        :filename "markdown.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "markdown"
       :description
       "Markdown renderer with GFM support, automatic line breaks, and syntax-highlighted code blocks. The complete document is rendered in one ReactMarkdown pass."
       :link {:href "https://github.com/remarkjs/react-markdown"
              :label "react-markdown Docs"}
       :props
       [{:name ":children"
         :type "string"
         :default nil
         :description "Markdown content to render."}
        {:name ":class"
         :type "string"
         :default nil
         :description "Additional Tailwind classes for the wrapper container."}
        {:name ":components"
         :type "map"
         :default nil
         :description
         "Complete react-markdown component map. Supplying it replaces (rather than merges with) built-in code/pre renderers."}
        {:name ":remark-plugins"
         :type "vector"
         :default "[remarkGfm remarkBreaks]"
         :description
         "Complete remark plugin list; override to replace the enabled GFM and hard-break defaults."}
        {:name ":rehype-plugins"
         :type "vector"
         :default nil
         :description "Optional rehype plugins forwarded to react-markdown."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "The props map is required; always pass at least {:children \"...\"}."]
       [:li
        "Code fences use the shared code-block component and infer language from class names like language-clojure."]
       [:li
        "For upstream markdown behavior and supported syntax, see react-markdown and remark-gfm docs."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[markdown {:children \"# Release Notes\\n\\n- Added sync\\n- Fixed edge cases\\n\\n```clojure\\n(defn ready? [state]\\n  (= :ok (:status state)))\\n```\"\n           :class \"prose prose-sm max-w-none\"}]"]]]]])))

(defstory MarkdownHeadings
          "Interactive markdown playground."
          {:source-code (embed-source "clojure-shadcn.stories.markdown.markdown-headings")
           :args {:children "# Heading 1\n\n## Heading 2\n\n**Bold** and _italic_ text."}
           :arg-types {:children {:name "Markdown source"
                                  :control {:type "text"}}}
           :parameters
           {:playground {:description "Explore markdown markdown source with a live preview."}
            :controls {:expanded false
                       :exclude ["class" "components" "remark-plugins" "rehype-plugins"]}}}
          [args]
          (r/as-element (helpers/wrap-component [markdown-headings/example args])))

(defstory MarkdownCodeBlocks
          "Markdown with fenced code blocks.
  Code blocks are highlighted via Shiki."
          {:source-code (embed-source "clojure-shadcn.stories.markdown.markdown-code-blocks")}
          []
          (r/as-element (helpers/wrap-component [markdown-code-blocks/example])))

(defstory MarkdownLinksLists
          "Markdown lists and links.
  Links receive underline styles via markdown component."
          {:source-code (embed-source "clojure-shadcn.stories.markdown.markdown-links-lists")}
          []
          (r/as-element (helpers/wrap-component [markdown-links-lists/example])))

(defstory
 MarkdownCombined
 "Full enabled-GFM example: table, task list, strikethrough, autolink, hard break, and code."
 {:source-code (embed-source "clojure-shadcn.stories.markdown.markdown-combined")}
 []
 (r/as-element (helpers/wrap-component [markdown-combined/example])))

(defstory MarkdownPluginOverride
          "A supported :components override replaces the default anchor renderer."
          {:source-code (embed-source "clojure-shadcn.stories.markdown.markdown-plugin-override")}
          []
          (r/as-element (helpers/wrap-component [markdown-plugin-override/example])))

(defstory MarkdownRawHtmlSafety
          "Raw HTML is escaped by default because no raw-HTML rehype plugin is enabled."
          {:source-code (embed-source "clojure-shadcn.stories.markdown.markdown-raw-html-safety")}
          []
          (r/as-element (helpers/wrap-component [markdown-raw-html-safety/example])))

(defstory MarkdownLineBreaks
          "Single newlines become hard line breaks through the default remark-breaks plugin."
          {:source-code (embed-source "clojure-shadcn.stories.markdown.markdown-line-breaks")}
          []
          (r/as-element (helpers/wrap-component [markdown-line-breaks/example])))

(defstory MarkdownSingleLineFence
          "A one-line fenced block exercises fence detection without relying on multiline content."
          {:source-code (embed-source "clojure-shadcn.stories.markdown.markdown-single-line-fence")}
          []
          (r/as-element (helpers/wrap-component [markdown-single-line-fence/example])))
