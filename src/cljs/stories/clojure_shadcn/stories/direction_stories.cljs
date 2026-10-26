(ns clojure-shadcn.stories.direction-stories
  (:require
   [clojure-shadcn.stories.direction.direction-hook-context-and-override
    :as direction-hook-context-and-override]
   [clojure-shadcn.stories.direction.direction-radix-descendant          :as
                                                                         direction-radix-descendant]
   [clojure-shadcn.stories.direction.playground                          :as playground]
   [clojure-shadcn.stories.direction.right-to-left                       :as right-to-left]
   [clojure-shadcn.stories.helpers                                       :as helpers]
   [reagent.core                                                         :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Direction"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Writing-direction context for Radix primitives."
                        :npm-install "npm install @radix-ui/react-direction"
                        :source-code (embed-source "clojure-shadcn.ui.components.direction")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/direction.cljs"
                        :filename "direction.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "direction-provider"
      :link {:href "https://www.radix-ui.com/primitives/docs/utilities/direction-provider"
             :label "Radix Direction Provider Docs"}
      :description
      "Supplies writing direction to descendant Radix primitives. It is a context provider, not a DOM element; set a matching HTML dir attribute where native text/layout direction is also required."
      :props [{:name ":direction / :dir"
               :type ":ltr | :rtl"
               :default ":ltr"
               :description
               ":direction takes precedence; the selected value is converted to a string."}
              {:name "additional props"
               :type "map entries"
               :default nil
               :description "Forwarded to Radix DirectionProvider."}]}]
    [helpers/api-component-card
     {:component-name "use-direction"
      :description
      "React hook returning the resolved direction as :ltr or :rtl. Call only from a Reagent/React function component and obey the Rules of Hooks."
      :props
      [{:name "direction"
        :type "keyword, optional positional argument"
        :default nil
        :description
        "Local direction passed to Radix useDirection; without it, reads context or Radix's default."}]}]])))

(defstory RightToLeft
          {:source-code (embed-source "clojure-shadcn.stories.direction.right-to-left")}
          []
          (r/as-element (helpers/wrap-component [right-to-left/example])))




(defstory
 DirectionRadixDescendant
 "A real Radix radio-group descendant receives direction context; use arrow keys to observe RTL navigation."
 {:source-code (embed-source "clojure-shadcn.stories.direction.direction-radix-descendant")}
 []
 (r/as-element (helpers/wrap-component [direction-radix-descendant/example])))

(defstory DirectionHookContextAndOverride
          "The hook reads provider context unless an explicit local direction overrides it."
          {:source-code (embed-source
                         "clojure-shadcn.stories.direction.direction-hook-context-and-override")}
          []
          (r/as-element (helpers/wrap-component [direction-hook-context-and-override/example])))

(defstory
 Playground
 "Interactive provider direction; native dir is mirrored because the provider itself renders no DOM."
 {:args {:dir "ltr"}
  :arg-types {:dir {:name "Text direction"
                    :control {:type "select"}
                    :options ["ltr" "rtl"]}}
  :parameters {:playground {:description "Explore direction text direction with a live preview."}
               :controls {:expanded false
                          :exclude ["children"]}}
  :source-code (embed-source "clojure-shadcn.stories.direction.playground")}
 [args]
 (r/as-element (helpers/wrap-component [playground/example args])))
