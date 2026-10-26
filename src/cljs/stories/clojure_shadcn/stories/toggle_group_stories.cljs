(ns clojure-shadcn.stories.toggle-group-stories
  (:require
   [clojure-shadcn.stories.helpers                            :as helpers]
   [clojure-shadcn.stories.toggle-group.playground            :as example-playground]
   [clojure-shadcn.stories.toggle-group.toggle-group-multiple :as example-toggle-group-multiple]
   [clojure-shadcn.stories.toggle-group.toggle-group-patterns :as example-toggle-group-patterns]
   [clojure-shadcn.stories.toggle-group.toggle-group-single   :as example-toggle-group-single]
   [clojure-shadcn.stories.toggle-group.toggle-group-sizes    :as example-toggle-group-sizes]
   [reagent.core                                              :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Toggle Group"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Related two-state controls with single or multiple selection and roving focus."
           :npm-install "npm install @radix-ui/react-toggle-group @radix-ui/react-toggle"
           :source-code (embed-source "clojure-shadcn.ui.components.toggle-group")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/toggle_group.cljs"
           :filename "toggle_group.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "toggle-group / toggle-group-item"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/toggle-group"
             :label "Radix Toggle Group Docs"}
      :description
      "Styled Radix ToggleGroup wrappers. The group shares variant and size through React context; items can override either value. Radix provides aria-pressed, keyboard activation and optional roving focus."
      :props [{:name ":type"
               :type ":single | :multiple"
               :default nil
               :description "Required selection model."}
              {:name ":value / :default-value"
               :type "string | vector<string>"
               :default nil
               :description "Controlled or initial selection matching :type."}
              {:name ":on-value-change"
               :type "function"
               :default nil
               :description "Receives the next selection."}
              {:name ":orientation / :dir / :loop / :roving-focus"
               :type "keyword|string|boolean"
               :default nil
               :description "Roving-focus keyboard configuration."}
              {:name ":variant"
               :type ":default | :outline"
               :default ":default"
               :description "Shared visual treatment."}
              {:name ":size"
               :type ":default | :sm | :lg"
               :default ":default"
               :description "Shared control dimensions."}
              {:name ":disabled"
               :type "boolean"
               :default nil
               :description "Disables a group or individual item."}]}]
    [:div
     {:class
      "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4 text-xs text-muted-foreground"}
     "The :type prop is required by Radix. In :single mode values are strings; in :multiple mode they are vectors. Icon-only items need :aria-label."]])))

(defstory ToggleGroupSingle
          {:source-code (embed-source "clojure-shadcn.stories.toggle-group.toggle-group-single")}
          []
          (r/as-element (helpers/wrap-component [example-toggle-group-single/example])))

(defstory ToggleGroupMultiple
          {:source-code (embed-source "clojure-shadcn.stories.toggle-group.toggle-group-multiple")}
          []
          (r/as-element (helpers/wrap-component [example-toggle-group-multiple/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:disabled false}
           :arg-types {:disabled {:name "Disabled"
                                  :control {:type "boolean"}}}
           :parameters {:playground {:description
                                     "Explore toggle group disabled with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "value" "default-value" "on-value-change"]}}
           :source-code (embed-source "clojure-shadcn.stories.toggle-group.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory
 ToggleGroupPatterns
 "Behavior-distinct disabled, outline, spaced, vertical, RTL, and font-selector compositions. Spacing and vertical layout are class recipes over the current API."
 {:source-code (embed-source "clojure-shadcn.stories.toggle-group.toggle-group-patterns")}
 []
 (r/as-element (helpers/wrap-component [example-toggle-group-patterns/example])))

(defstory ToggleGroupSizes
          "Static supported size variants only. Based on shadcn/ui Toggle Group — Size."
          {:source-code (embed-source "clojure-shadcn.stories.toggle-group.toggle-group-sizes")}
          []
          (r/as-element (helpers/wrap-component [example-toggle-group-sizes/example])))
