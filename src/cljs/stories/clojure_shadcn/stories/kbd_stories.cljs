(ns clojure-shadcn.stories.kbd-stories
  (:require
   [clojure-shadcn.stories.helpers             :as helpers]
   [clojure-shadcn.stories.kbd.kbd-button      :as kbd-button]
   [clojure-shadcn.stories.kbd.kbd-group       :as kbd-group]
   [clojure-shadcn.stories.kbd.kbd-input-group :as kbd-input-group]
   [clojure-shadcn.stories.kbd.kbd-rtl         :as kbd-rtl]
   [clojure-shadcn.stories.kbd.kbd-tooltip     :as kbd-tooltip]
   [clojure-shadcn.stories.kbd.playground      :as playground]
   [reagent.core                               :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Kbd"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Keyboard input and shortcut hint elements."
                        :npm-install nil
                        :source-code (embed-source "clojure-shadcn.ui.components.kbd")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/kbd.cljs"
                        :filename "kbd.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "kbd"
      :description
      "Semantic HTML kbd element for an individual key or compact keyboard input hint. It is presentational and non-interactive."
      :props [{:name ":class"
               :type "string"
               :default nil
               :description "Classes merged with the key-cap styles."}
              {:name "additional props"
               :type "map entries"
               :default nil
               :description "Normalized and forwarded to the kbd element."}]}]
    [helpers/api-component-card
     {:component-name "kbd-group"
      :description
      "Inline layout for a shortcut sequence. It also renders a kbd element, so use it to group key tokens rather than as an interactive control."
      :props [{:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged and forwarded to the kbd element."}]}]])))

(defstory Playground
          "Interactive keyboard hint playground."
          {:source-code (embed-source "clojure-shadcn.stories.kbd.playground")
           :args {:prefix "Press"
                  :key "K"
                  :suffix "to search"}
           :arg-types {:prefix {:name "Prefix"
                                :control {:type "text"}}
                       :key {:name "Shortcut key"
                             :control {:type "text"}}
                       :suffix {:name "Suffix"
                                :control {:type "text"}}}
           :parameters {:playground
                        {:description
                         "Explore kbd prefix, shortcut key and suffix with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory KbdGroup
          {:source-code (embed-source "clojure-shadcn.stories.kbd.kbd-group")}
          []
          (r/as-element (helpers/wrap-component [kbd-group/example])))

(defstory
 KbdButton
 "Button — upstream command-button recipe pairs an action label with a visual shortcut hint."
 {:source-code (embed-source "clojure-shadcn.stories.kbd.kbd-button")}
 []
 (r/as-element (helpers/wrap-component [kbd-button/example])))

(defstory KbdTooltip
          "Tooltip — a shortcut hint enriches an icon button without becoming interactive itself."
          {:source-code (embed-source "clojure-shadcn.stories.kbd.kbd-tooltip")}
          []
          (r/as-element (helpers/wrap-component [kbd-tooltip/example])))

(defstory KbdInputGroup
          "Input Group — upstream search field shows its keyboard shortcut as an end addon."
          {:source-code (embed-source "clojure-shadcn.stories.kbd.kbd-input-group")}
          []
          (r/as-element (helpers/wrap-component [kbd-input-group/example])))

(defstory KbdRTL
          "RTL — shortcut tokens remain legible inside right-to-left instructional copy."
          {:source-code (embed-source "clojure-shadcn.stories.kbd.kbd-rtl")}
          []
          (r/as-element (helpers/wrap-component [kbd-rtl/example])))
