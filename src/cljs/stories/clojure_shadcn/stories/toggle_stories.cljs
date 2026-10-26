(ns clojure-shadcn.stories.toggle-stories
  (:require
   [clojure-shadcn.stories.helpers                        :as helpers]
   [clojure-shadcn.stories.toggle.playground              :as example-playground]
   [clojure-shadcn.stories.toggle.toggle-basic            :as example-toggle-basic]
   [clojure-shadcn.stories.toggle.toggle-icon-and-text    :as example-toggle-icon-and-text]
   [clojure-shadcn.stories.toggle.toggle-outline-disabled :as example-toggle-outline-disabled]
   [clojure-shadcn.stories.toggle.toggle-rtl              :as example-toggle-rtl]
   [clojure-shadcn.stories.toggle.toggle-states           :as example-toggle-states]
   [reagent.core                                          :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Toggle"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Two-state button with accessible pressed semantics."
                        :npm-install "npm install @radix-ui/react-toggle"
                        :source-code (embed-source "clojure-shadcn.ui.components.toggle")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/toggle.cljs"
                        :filename "toggle.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "toggle / toggle-variants"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/toggle"
             :label "Radix Toggle Docs"}
      :description
      "Styled Radix Toggle.Root preserving keyboard activation and aria-pressed state. Props use kebab-case, are normalized, and additional props are forwarded."
      :props [{:name ":pressed / :default-pressed"
               :type "boolean"
               :default nil
               :description "Controlled or initial pressed state."}
              {:name ":on-pressed-change"
               :type "function"
               :default nil
               :description "Receives the next pressed state."}
              {:name ":variant"
               :type ":default | :outline"
               :default ":default"
               :description "Visual treatment."}
              {:name ":size"
               :type ":default | :sm | :lg"
               :default ":default"
               :description "Control dimensions."}
              {:name ":disabled"
               :type "boolean"
               :default nil
               :description "Prevents interaction."}
              {:name ":class"
               :type "string"
               :default nil
               :description "Classes merged after defaults."}]}]
    [:div
     {:class
      "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4 text-xs text-muted-foreground"}
     "Provide an accessible name with visible text or :aria-label when rendering only an icon."]])))

(defstory ToggleBasic
          {:source-code (embed-source "clojure-shadcn.stories.toggle.toggle-basic")}
          []
          (r/as-element (helpers/wrap-component [example-toggle-basic/example])))

(defstory ToggleStates
          {:source-code (embed-source "clojure-shadcn.stories.toggle.toggle-states")}
          []
          (r/as-element (helpers/wrap-component [example-toggle-states/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:variant "outline"
                  :size "default"}
           :arg-types {:variant {:name "Variant"
                                 :control {:type "select"}
                                 :options ["default" "outline"]}
                       :size {:name "Size"
                              :control {:type "select"}
                              :options ["default" "sm" "lg"]}}
           :parameters
           {:playground {:description "Explore toggle variant and size with a live preview."}
            :controls {:expanded false
                       :exclude ["children" "pressed" "default-pressed" "on-pressed-change"]}}
           :decode-args (fn [{:keys [variant size]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)
                            size (update :size keyword)))
           :source-code (embed-source "clojure-shadcn.stories.toggle.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))

(defstory ToggleIconAndText
          "Toggle with a visible icon and label. Based on shadcn/ui Toggle — With Text."
          {:source-code (embed-source "clojure-shadcn.stories.toggle.toggle-icon-and-text")}
          []
          (r/as-element (helpers/wrap-component [example-toggle-icon-and-text/example])))

(defstory ToggleOutlineDisabled
          "Enabled and disabled outline states. Based on shadcn/ui Toggle — Outline and Disabled."
          {:source-code (embed-source "clojure-shadcn.stories.toggle.toggle-outline-disabled")}
          []
          (r/as-element (helpers/wrap-component [example-toggle-outline-disabled/example])))

(defstory ToggleRtl
          "Right-to-left icon and text toggle. Based on shadcn/ui Toggle — RTL."
          {:source-code (embed-source "clojure-shadcn.stories.toggle.toggle-rtl")}
          []
          (r/as-element (helpers/wrap-component [example-toggle-rtl/example])))
