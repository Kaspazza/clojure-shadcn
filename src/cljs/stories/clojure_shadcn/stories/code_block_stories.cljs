(ns clojure-shadcn.stories.code-block-stories
  "Storybook stories for the Code Block component. Ported from mateuszmazurczak.portfolio.ui-components.code_block."
  (:require
   [clojure-shadcn.stories.code-block.code-block-copy-action         :as code-block-copy-action]
   [clojure-shadcn.stories.code-block.code-block-empty-and-fallback  :as
                                                                     code-block-empty-and-fallback]
   [clojure-shadcn.stories.code-block.code-block-invalid-language    :as
                                                                     code-block-invalid-language]
   [clojure-shadcn.stories.code-block.code-block-multiple-languages  :as
                                                                     code-block-multiple-languages]
   [clojure-shadcn.stories.code-block.code-block-rapid-async-changes :as
                                                                     code-block-rapid-async-changes]
   [clojure-shadcn.stories.code-block.code-block-single              :as code-block-single]
   [clojure-shadcn.stories.code-block.code-block-with-header         :as code-block-with-header]
   [clojure-shadcn.stories.helpers                                   :as helpers]
   [reagent.core                                                     :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Code Block"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description "Code block component with syntax highlighting using Shiki."
                        :npm-install "npm install shiki"
                        :source-code (embed-source "clojure-shadcn.ui.components.code_block")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/code_block.cljs"
                        :filename "code_block.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "code-block"
       :link {:href "https://shiki.style/themes"
              :label "Shiki Docs"}
       :description
       "Root container for code blocks. Provides border, background, and rounded styling. The props map is optional — children can be passed directly without it. All additional props are forwarded to the underlying div element."
       :props
       [{:name ":class"
         :type "string"
         :default nil
         :description
         "Additional Tailwind classes merged with defaults (border, bg-card, rounded-xl, overflow-clip)."}]}]
     [helpers/api-component-card
      {:component-name "code-block-code"
       :description
       "The primary component — renders syntax-highlighted code using Shiki. Highlighting is async: a plain <pre><code> fallback is shown while Shiki loads, then replaced with highlighted HTML. If Shiki fails, the raw code string is displayed as fallback. All additional props are forwarded to the underlying div element."
       :props
       [{:name ":code"
         :type "string"
         :default nil
         :description "The code string to highlight. If nil or empty, renders an empty code block."}
        {:name ":language"
         :type "string"
         :default "\"tsx\""
         :description
         "Language for syntax highlighting. Must match a Shiki language identifier (e.g. \"clojure\", \"javascript\", \"python\", \"html\", \"css\")."}
        {:name ":theme"
         :type "string"
         :default "\"github-light\""
         :description
         "Shiki theme name. Common values: \"github-light\", \"github-dark\", \"one-dark-pro\", \"dracula\", \"nord\". See Shiki Themes for the full list."}
        {:name ":class"
         :type "string"
         :default nil
         :description
         "Additional Tailwind classes merged with defaults (overflow-x-auto, text-[13px], padding via [&>pre] selectors)."}]}]
     [helpers/api-component-card
      {:component-name "code-block-group"
       :description
       "Group container for header elements like filenames, language labels, or action buttons. Renders a flex row with items centered and spaced between. The props map is optional — children can be passed directly without it. All additional props are forwarded to the underlying div element."
       :props
       [{:name ":class"
         :type "string"
         :default nil
         :description
         "Additional Tailwind classes merged with defaults (flex, items-center, justify-between). Typically add px-4 py-2 border-b for a header row."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "code-block and code-block-group accept children with or without a leading props map — both [code-block [child]] and [code-block {} [child]] work."]
       [:li "code-block-code requires the props map (it destructures :code, :language, :theme)."]
       [:li
        "Highlighting is async — there is a brief flash of unstyled code on first render while Shiki loads."]
       [:li
        "The component uses dangerouslySetInnerHTML internally for Shiki output. Avoid passing untrusted user input as :code if XSS is a concern."]
       [:li
        "Shiki language identifiers are case-sensitive and must match exactly (e.g. \"clojure\" not \"Clojure\")."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        ";; Basic code block\n[code-block\n  [code-block-code {:code \"(+ 1 2)\" :language \"clojure\"}]]\n\n;; With filename header and copy button\n[code-block {}\n  [code-block-group {:class \"px-4 py-2 border-b text-xs text-muted-foreground\"}\n    [:span \"core.cljs\"]\n    [button {:size :sm :variant :ghost} \"Copy\"]]\n  [code-block-code {:code \"(defn hello [] ...)\" \n                    :language \"clojure\"\n                    :theme \"github-dark\"}]]"]]]]])))

(defstory CodeBlockSingle
          "Interactive code-block playground."
          {:args {:language "clojure"
                  :theme "github-light"
                  :code "(defn greet [name]\n  (str \"Hello, \" name \"!\"))"}
           :arg-types {:language {:name "Language"
                                  :control {:type "select"}
                                  :options ["clojure" "javascript" "python"]}
                       :theme {:name "Theme"
                               :control {:type "select"}
                               :options ["github-light" "github-dark" "one-dark-pro"]}
                       :code {:name "Code"
                              :control {:type "text"}}}
           :parameters {:playground
                        {:description
                         "Explore code block language, theme and code with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}
           :source-code (embed-source "clojure-shadcn.stories.code-block.code-block-single")}
          [args]
          (r/as-element (helpers/wrap-component [code-block-single/example args])))

(defstory CodeBlockWithHeader
          "Code block with filename header.\n  Use code-block-group to build headers or actions."
          {:source-code (embed-source "clojure-shadcn.stories.code-block.code-block-with-header")}
          []
          (r/as-element (helpers/wrap-component [code-block-with-header/example])))

(defstory
 CodeBlockMultipleLanguages
 "Code blocks in multiple languages.\n  Shows how to switch language prop for highlighting."
 {:source-code (embed-source "clojure-shadcn.stories.code-block.code-block-multiple-languages")}
 []
 (r/as-element (helpers/wrap-component [code-block-multiple-languages/example])))

(defstory CodeBlockCopyAction
          "Functional copy action with visible success feedback."
          {:source-code (embed-source "clojure-shadcn.stories.code-block.code-block-copy-action")}
          []
          (r/as-element (helpers/wrap-component [code-block-copy-action/example])))

(defstory CodeBlockEmptyAndFallback
          "Empty input and nil fallback remain stable while highlighting resolves."
          {:source-code (embed-source
                         "clojure-shadcn.stories.code-block.code-block-empty-and-fallback")}
          []
          (r/as-element (helpers/wrap-component [code-block-empty-and-fallback/example])))

(defstory CodeBlockInvalidLanguage
          "An invalid Shiki language demonstrates the component's raw-code fallback."
          {:source-code (embed-source
                         "clojure-shadcn.stories.code-block.code-block-invalid-language")}
          []
          (r/as-element (helpers/wrap-component [code-block-invalid-language/example])))

(defstory CodeBlockRapidAsyncChanges
          "Rapidly replace code, language, and theme to exercise stale async-result protection."
          {:source-code (embed-source
                         "clojure-shadcn.stories.code-block.code-block-rapid-async-changes")}
          []
          (r/as-element (helpers/wrap-component [code-block-rapid-async-changes/example])))
