(ns clojure-shadcn.stories.markdown-stories
  "Storybook stories for the Markdown component. Ported from mateuszmazurczak.portfolio.ui-components.markdown."
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.markdown :as sut]
   [reagent.core                          :as r])
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
                        :npm-install "npm install marked react-markdown remark-breaks remark-gfm"
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
       "Markdown renderer with GFM support, automatic line breaks, and syntax-highlighted code blocks. Parses input into memoized blocks for better rendering performance."
       :link {:href "https://github.com/remarkjs/react-markdown"
              :label "react-markdown Docs"}
       :props
       [{:name ":children"
         :type "string"
         :default nil
         :description "Markdown content to render."}
        {:name ":id"
         :type "string"
         :default nil
         :description "Stable base ID used for generated block keys."}
        {:name ":class"
         :type "string"
         :default nil
         :description "Additional Tailwind classes for the wrapper container."}
        {:name ":components"
         :type "map"
         :default nil
         :description
         "Custom react-markdown component overrides. Defaults to built-in code/pre renderers."}]}]
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

(defstory
 MarkdownHeadings
 "Markdown headings and emphasis.
  Uses react-markdown with remark plugins.

  Useful for rich text content blocks."
 []
 (r/as-element (helpers/wrap-component
                [:div {:class "p-6 max-w-xl"}
                 [sut/markdown {:children
                                "# Heading 1\n\n## Heading 2\n\n**Bold** and _italic_ text."}]])))

(defstory
 MarkdownCodeBlocks
 "Markdown with fenced code blocks.
  Code blocks are highlighted via Shiki."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-xl"}
    [sut/markdown
     {:children
      "```clojure\n(defn hello []\n  (println \"Hello\"))\n```\n\n```javascript\nconsole.log('Hello')\n```"}]])))

(defstory
 MarkdownLinksLists
 "Markdown lists and links.
  Links receive underline styles via markdown component."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-xl"}
    [sut/markdown
     {:children
      "- Item one\n- Item two\n- Item three\n\nVisit [our docs](https://ui.shadcn.com)."}]])))

(defstory
 MarkdownCombined
 "Full markdown example combining features.
  Demonstrates headings, lists, links, and code together."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-xl"}
    [sut/markdown
     {:children
      "# Release Notes\n\n**Highlights**\n- Added realtime sync\n- Improved exports\n\nLearn more in the [changelog](https://example.com).\n\n```clojure\n(defn sync! [state]\n  (assoc state :status :ok))\n```"}]])))
