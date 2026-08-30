(ns clojure-shadcn.stories.typography-stories
  (:refer-clojure :exclude [list])
  (:require
   [clojure-shadcn.stories.helpers          :as helpers]
   [clojure-shadcn.ui.components.typography :as sut]
   [reagent.core                            :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Typography"
       :parameters #js {:layout "padded"}})

(defdoc
 Installation
 []
 (r/as-element
  [helpers/installation-scene
   {:description
    "Reusable shadcn-style semantic typography; a project extra rather than a registry module."
    :npm-install "No external dependencies"
    :source-code (embed-source "clojure-shadcn.ui.components.typography")
    :namespace-path "src/cljs/clojure_shadcn/ui/components/typography.cljs"
    :filename "typography.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-4xl space-y-4 p-6"}
    [helpers/api-component-card
     {:component-name "h1 / h2 / h3 / h4 / p"
      :description
      "Semantic heading and paragraph elements using the canonical shadcn typography recipe classes."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Classes merged with defaults."}
              {:name "DOM props"
               :type "map entries"
               :default nil
               :description "Forwarded to the semantic element."}]}]
    [helpers/api-component-card {:component-name "blockquote / list / inline-code"
                                 :description
                                 "Semantic quotation, unordered-list, and inline code recipes."
                                 :props [{:name ":class"
                                          :type "string"
                                          :default nil
                                          :description "Classes merged with defaults."}]}]
    [helpers/api-component-card
     {:component-name "lead / large / small / muted / table"
      :description
      "Display text recipes plus a responsive table wrapper. Table props target the nested table."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Classes merged with defaults."}]}]
    [:div
     {:class
      "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4 text-xs text-muted-foreground"}
     "Typography is a data-oriented set of plain Reagent functions, not a context or global prose style. Use semantic variants according to document structure."]])))

(defstory
 TypeScale
 "Interactive typography playground."
 {:args
  {:heading "The Taxonomy of Type"
   :body
   "Typography should communicate hierarchy without coupling content to a page-specific stylesheet."}
  :arg-types {:heading {:control {:type "text"}}
              :body {:control {:type "text"}}}
  :parameters {:controls {:exclude ["class"]}}}
 [args]
 (r/as-element (helpers/wrap-component [:article {:class "max-w-2xl"}
                                        [sut/h1 {}
                                         (:heading args)]
                                        [sut/p {}
                                         (:body args)]])))
