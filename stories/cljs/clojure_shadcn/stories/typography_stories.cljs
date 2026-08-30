(ns clojure-shadcn.stories.typography-stories
  (:refer-clojure :exclude [list])
  (:require
   [clojure-shadcn.stories.helpers :as helpers]
   [clojure-shadcn.ui.components.typography :as sut]
   [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default #js {:title "Components/Typography" :parameters #js {:layout "padded"}})

(defdoc Installation []
  (r/as-element
   [helpers/installation-scene
    {:description "Reusable shadcn-style semantic typography; a project extra rather than a registry module."
     :npm-install "No external dependencies"
     :source-code (embed-source "clojure-shadcn.ui.components.typography")
     :namespace-path "src/cljs/clojure_shadcn/ui/components/typography.cljs"
     :filename "typography.cljs"}]))

(defstory ApiReference
  []
  (r/as-element
   (helpers/wrap-component
    [:div {:class "max-w-4xl space-y-4 p-6"}
     [helpers/api-component-card
      {:component-name "h1 / h2 / h3 / h4 / p"
       :description "Semantic heading and paragraph elements using the canonical shadcn typography recipe classes."
       :props [{:name ":class" :type "string" :default nil :description "Classes merged with defaults."}
               {:name "DOM props" :type "map entries" :default nil :description "Forwarded to the semantic element."}]}]
     [helpers/api-component-card
      {:component-name "blockquote / list / inline-code"
       :description "Semantic quotation, unordered-list, and inline code recipes."
       :props [{:name ":class" :type "string" :default nil :description "Classes merged with defaults."}]}]
     [helpers/api-component-card
      {:component-name "lead / large / small / muted / table"
       :description "Display text recipes plus a responsive table wrapper. Table props target the nested table."
       :props [{:name ":class" :type "string" :default nil :description "Classes merged with defaults."}]}]
     [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4 text-xs text-muted-foreground"}
      "Typography is a data-oriented set of plain Reagent functions, not a context or global prose style. Use semantic variants according to document structure."]])))

(defstory TypeScale
  "Representative article composition showing every reusable typography recipe."
  []
  (r/as-element
   (helpers/wrap-component
    [:article {:class "max-w-2xl"}
     [sut/h1 {} "The Taxonomy of Type"]
     [sut/lead {} "A reusable semantic scale for product documentation."]
     [sut/h2 {} "Principles"]
     [sut/p {} "Typography should communicate hierarchy without coupling content to a page-specific stylesheet."]
     [sut/h3 {} "Composition"]
     [sut/blockquote {} "Good defaults are constraints that remain easy to override."]
     [sut/list {} [:li "Use semantic elements"] [:li "Merge local classes"]]
     [sut/h4 {} "Inline details"]
     [sut/p {} "Use " [sut/inline-code {} ":class"] " for intentional exceptions."]
     [sut/large {} "Large supporting text"]
     [sut/small {} "Small label text"]
     [sut/muted {} "Muted explanatory text"]
     [sut/table {}
      [:thead [:tr [:th {:class "text-left"} "Recipe"] [:th {:class "text-left"} "Element"]]]
      [:tbody [:tr {:class "border-t"} [:td "lead"] [:td "p"]]]]])))
