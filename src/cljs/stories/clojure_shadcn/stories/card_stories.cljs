(ns clojure-shadcn.stories.card-stories
  (:require
   [clojure-shadcn.stories.card.playground :as playground]
   [clojure-shadcn.stories.card.card-simple :as card-simple]
   [clojure-shadcn.stories.card.card-form :as card-form]
   [clojure-shadcn.stories.card.card-image :as card-image]
   [clojure-shadcn.stories.card.card-edge-spacing :as card-edge-spacing]
   [clojure-shadcn.stories.card.card-rtl :as card-rtl]
   [clojure-shadcn.stories.helpers :as helpers]
   [reagent.core :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Card"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Composable content container with header, action, content, and footer regions."
           :npm-install nil
           :source-code (embed-source "clojure-shadcn.ui.components.card")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/card.cljs"
           :filename "card.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "card"
      :description
      "Visual card root rendered as a div. Compose the region components below as needed; the API does not impose heading or landmark semantics."
      :props
      [{:name ":size"
        :type "keyword"
        :default ":default"
        :description
        "One of: :default | :sm. Exposed as data-size on the root so every Card region responds consistently."}
       {:name ":class"
        :type "string"
        :default nil
        :description "Classes merged with card defaults."}
       {:name "additional props"
        :type "map entries"
        :default nil
        :description "Normalized and forwarded to the div."}]}]
    [helpers/api-component-card
     {:component-name "card-header / card-title / card-description"
      :description
      "Header grid plus unopinionated div elements for title and supporting text. Use an actual heading element inside card-title when document hierarchy requires one."
      :props [{:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to each underlying div."}]}]
    [helpers/api-component-card
     {:component-name "card-action / card-content / card-footer"
      :description
      "Optional action, body, and footer layout regions. card-action is positioned by card-header's container grid and should therefore be nested in card-header."
      :props [{:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to each underlying div."}]}]
    [:div {:class "rounded-lg border bg-muted/50 p-4 text-xs text-muted-foreground"}
     "Card size belongs on the card root. The root publishes data-size and shared spacing; header, title, description, action, content, and footer retain their compositional APIs while responding to the root size."]])))

(defstory Playground
          "Interactive card playground."
          {:source-code (embed-source "clojure-shadcn.stories.card.playground")
           :args {:title "Create project"
                  :description "Deploy a new project in one click."
                  :action-label "Deploy"}
           :arg-types {:title {:name "Title"
                               :control {:type "text"}}
                       :description {:name "Description"
                                     :control {:type "text"}}
                       :action-label {:name "Action label"
                                      :control {:type "text"}}}
           :parameters {:playground {:description "Explore card title, description and action label with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class" "on-click"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory CardSimple
 {:source-code (embed-source "clojure-shadcn.stories.card.card-simple")}
 []
 (r/as-element (helpers/wrap-component [card-simple/example])))

(defstory CardForm
 "Form — the fuller upstream card recipe with labeled fields, header action, and footer actions."
 {:source-code (embed-source "clojure-shadcn.stories.card.card-form")}
 []
 (r/as-element (helpers/wrap-component [card-form/example])))

(defstory CardImage
 "Image — the upstream media card recipe composes a native image with Card regions."
 {:source-code (embed-source "clojure-shadcn.stories.card.card-image")}
 []
 (r/as-element (helpers/wrap-component [card-image/example])))

(defstory CardEdgeSpacing
 "Edge and size recipes. Flush media removes top padding; :size :sm applies compact spacing consistently across Card regions."
 {:source-code (embed-source "clojure-shadcn.stories.card.card-edge-spacing")}
 []
 (r/as-element (helpers/wrap-component [card-edge-spacing/example])))

(defstory CardRTL
 "RTL — the full card composition follows right-to-left reading order."
 {:source-code (embed-source "clojure-shadcn.stories.card.card-rtl")}
 []
 (r/as-element (helpers/wrap-component [card-rtl/example])))
