(ns clojure-shadcn.stories.tabs-stories
  (:require
   [clojure-shadcn.stories.helpers              :as helpers]
   [clojure-shadcn.stories.tabs.playground      :as example-playground]
   [clojure-shadcn.stories.tabs.tabs-basic      :as example-tabs-basic]
   [clojure-shadcn.stories.tabs.tabs-disabled   :as example-tabs-disabled]
   [clojure-shadcn.stories.tabs.tabs-rtl        :as example-tabs-rtl]
   [clojure-shadcn.stories.tabs.tabs-with-icons :as example-tabs-with-icons]
   [reagent.core                                :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Tabs"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keyboard-accessible tabbed content."
                        :npm-install "npm install @radix-ui/react-tabs"
                        :source-code (embed-source "clojure-shadcn.ui.components.tabs")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/tabs.cljs"
                        :filename "tabs.cljs"}]))

(defstory ApiReference
          []
          (r/as-element
           (helpers/wrap-component
            [helpers/api-component-card
             {:component-name "tabs / tabs-list / tabs-trigger / tabs-content"
              :link {:href "https://www.radix-ui.com/primitives/docs/components/tabs"
                     :label "Radix Tabs Docs"}
              :description
              "Radix Tabs wrappers preserving roving focus, activation and ARIA relationships."
              :props [{:name ":value / :default-value"
                       :type "string"
                       :default nil
                       :description "Controlled or initial tab."}
                      {:name ":on-value-change"
                       :type "function"
                       :default nil
                       :description "Selection callback."}
                      {:name ":orientation / :activation-mode"
                       :type "keyword|string"
                       :default nil
                       :description "Keyboard behavior."}]}])))

(defstory TabsBasic
          {:source-code (embed-source "clojure-shadcn.stories.tabs.tabs-basic")}
          []
          (r/as-element (helpers/wrap-component [example-tabs-basic/example])))

(defstory TabsDisabled
          {:source-code (embed-source "clojure-shadcn.stories.tabs.tabs-disabled")}
          []
          (r/as-element (helpers/wrap-component [example-tabs-disabled/example])))


(defstory
 TabsWithIcons
 "Radix Tabs triggers with decorative Lucide icons and visible labels.

  Radix primitive: @radix-ui/react-tabs

  Pair icons with text so each tab retains a clear accessible name."
 {:source-code (embed-source "clojure-shadcn.stories.tabs.tabs-with-icons")}
 []
 (r/as-element (helpers/wrap-component [example-tabs-with-icons/example])))

(defstory
 TabsRtl
 "Right-to-left Radix Tabs without custom orientation styling.

  Radix primitive: @radix-ui/react-tabs

  Set :dir on Root so horizontal arrow-key behavior follows reading direction."
 {:source-code (embed-source "clojure-shadcn.stories.tabs.tabs-rtl")}
 []
 (r/as-element (helpers/wrap-component [example-tabs-rtl/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:default-value "account"}
           :arg-types {:default-value {:name "Initial value"
                                       :control {:type "select"}
                                       :options ["account" "password"]}}
           :parameters {:playground {:description "Explore tabs initial value with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "value" "on-value-change"]}}
           :source-code (embed-source "clojure-shadcn.stories.tabs.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))
