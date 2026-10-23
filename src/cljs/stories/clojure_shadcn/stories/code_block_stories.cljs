(ns clojure-shadcn.stories.code-block-stories
  "Storybook stories for the Code Block component. Ported from mateuszmazurczak.portfolio.ui-components.code_block."
  (:require
   [clojure-shadcn.stories.helpers          :as helpers]
   [clojure-shadcn.ui.components.button     :as button]
   [clojure-shadcn.ui.components.code-block :as sut]
   [reagent.core                            :as r])
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
           :arg-types {:language {:control {:type "select"}
                                  :options ["clojure" "javascript" "python"]}
                       :theme {:control {:type "select"}
                               :options ["github-light" "github-dark" "one-dark-pro"]}
                       :code {:control {:type "text"}}}
           :parameters {:controls {:exclude ["class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6 max-w-xl"}
                                                 [sut/code-block {}
                                                  [sut/code-block-code
                                                   (select-keys args [:language :theme :code])]]])))

(defstory CodeBlockWithHeader
          "Code block with filename header.
  Use code-block-group to build headers or actions."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6 max-w-xl"}
             [sut/code-block {}
              [sut/code-block-group {:class "px-4 py-2 border-b text-xs text-muted-foreground"}
               [:span "handlers.cljs"]
               (button/button {:size :sm
                               :variant :ghost}
                              "Copy")]
              [sut/code-block-code
               {:language "clojure"
                :code "(defn handle-request [req]\n  {:status 200 :body \"OK\"})"}]]])))

(defstory CodeBlockMultipleLanguages
          "Code blocks in multiple languages.
  Shows how to switch language prop for highlighting."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "p-6 grid gap-4"}
                          [sut/code-block {}
                           [sut/code-block-code {:language "javascript"
                                                 :code "const greet = (name) => `Hello ${name}`"}]]
                          [sut/code-block {}
                           [sut/code-block-code
                            {:language "python"
                             :code "def greet(name):\n    return f'Hello {name}'"}]]])))

(defstory CodeBlockCopyAction
          "Functional copy action with visible success feedback."
          []
          (r/as-element
           [(fn []
              (let [code "{:db/id :user/email\n :db/valueType :db.type/string}"
                    copied? (r/atom false)]
                (fn []
                  (helpers/wrap-component
                   [:div {:class "p-6 max-w-xl"}
                    [sut/code-block {}
                     [sut/code-block-group {:class
                                            "px-4 py-2 border-b flex items-center justify-between"}
                      [:span {:class "text-xs text-muted-foreground"}
                       "schema.edn"]
                      (button/button {:size :sm
                                      :variant :ghost
                                      :on-click #(-> (.writeText js/navigator.clipboard code)
                                                     (.then (fn [] (reset! copied? true))))}
                                     (if @copied? "Copied" "Copy"))]
                     [sut/code-block-code {:language "clojure"
                                           :code code}]]]))))]))

(defstory CodeBlockEmptyAndFallback
          "Empty input and nil fallback remain stable while highlighting resolves."
          []
          (r/as-element (helpers/wrap-component [:div {:class "grid gap-4 p-6"}
                                                 [:div
                                                  [:p {:class "mb-2 text-xs text-muted-foreground"}
                                                   "Empty string"]
                                                  [sut/code-block
                                                   [sut/code-block-code {:code ""
                                                                         :language "clojure"}]]]
                                                 [:div
                                                  [:p {:class "mb-2 text-xs text-muted-foreground"}
                                                   "Nil code"]
                                                  [sut/code-block
                                                   [sut/code-block-code {:code nil
                                                                         :language "clojure"}]]]])))

(defstory CodeBlockInvalidLanguage
          "An invalid Shiki language demonstrates the component's raw-code fallback."
          []
          (r/as-element (helpers/wrap-component
                         [:div {:class "p-6 max-w-xl"}
                          [sut/code-block
                           [sut/code-block-code
                            {:language "not-a-real-language"
                             :code "This remains readable when highlighting fails."}]]])))

(defstory CodeBlockRapidAsyncChanges
          "Rapidly replace code, language, and theme to exercise stale async-result protection."
          []
          (r/as-element
           [(fn []
              (let [selection (r/atom 0)
                    samples [{:language "clojure"
                              :theme "github-light"
                              :code "(def answer 42)"}
                             {:language "javascript"
                              :theme "github-dark"
                              :code "const answer = 42"}
                             {:language "python"
                              :theme "github-light"
                              :code "answer = 42"}]]
                (fn []
                  (let [sample (nth samples @selection)]
                    (helpers/wrap-component
                     [:div {:class "p-6 max-w-xl space-y-3"}
                      (button/button {:on-click #(dotimes [i 9]
                                                   (js/setTimeout (fn []
                                                                    (reset! selection
                                                                      (mod i (count samples))))
                                                                  (* i 35)))}
                                     "Run rapid changes")
                      [sut/code-block [sut/code-block-code sample]]])))))]))
