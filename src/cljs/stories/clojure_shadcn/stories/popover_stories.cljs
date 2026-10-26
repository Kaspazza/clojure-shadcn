(ns clojure-shadcn.stories.popover-stories
  "Storybook stories for the Popover component. Ported from mateuszmazurczak.portfolio.ui-components.popover."
  (:require
   [clojure-shadcn.stories.helpers                    :as helpers]
   [clojure-shadcn.stories.popover.combobox-popover   :as example-combobox-popover]
   [clojure-shadcn.stories.popover.playground         :as example-playground]
   [clojure-shadcn.stories.popover.popover-alignments :as example-popover-alignments]
   [clojure-shadcn.stories.popover.popover-demo       :as example-popover-demo]
   [clojure-shadcn.stories.popover.popover-rtl        :as example-popover-rtl]
   [clojure-shadcn.stories.popover.popover-text-only  :as example-popover-text-only]
   [reagent.core                                      :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Popover"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Popover component for displaying floating content relative to a trigger."
                        :npm-install "npm install @radix-ui/react-popover"
                        :source-code (embed-source "clojure-shadcn.ui.components.popover")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/popover.cljs"
                        :filename "popover.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "popover"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/popover"
              :label "Radix Popover Docs"}
       :description "Radix Popover root that controls open/close state for floating content."
       :props [{:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled open state."}
               {:name ":defaultOpen"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial open state."}
               {:name ":onOpenChange"
                :type "function"
                :default nil
                :description "Callback when open state changes: (fn [open?] ...)."}
               {:name ":modal"
                :type "boolean"
                :default "false"
                :description "Whether to trap focus like a modal."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Popover.Root."}]}]
     [helpers/api-component-card
      {:component-name "popover-trigger"
       :description "Interactive trigger element that opens/closes the popover."
       :props [{:name ":asChild"
                :type "boolean"
                :default nil
                :description "Compose with a child element instead of rendering default trigger."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Popover.Trigger."}]}]
     [helpers/api-component-card
      {:component-name "popover-anchor"
       :description "Optional positioning anchor. If omitted, trigger is used as anchor."
       :props [{:name ":asChild"
                :type "boolean"
                :default nil
                :description "Compose with child element."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Popover.Anchor."}]}]
     [helpers/api-component-card
      {:component-name "popover-header / popover-title / popover-description"
       :description
       "Semantic presentation helpers for a heading block inside popover-content. They render div, div, and p respectively, apply the upstream data slots and styles, and forward additional DOM props."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes merged with component defaults."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to the underlying DOM element."}]}]
     [helpers/api-component-card
      {:component-name "popover-content"
       :description
       "Portaled floating content container with animations and collision-aware positioning."
       :props [{:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name ":align"
                :type "string"
                :default "\"center\""
                :description "One of: \"start\" | \"center\" | \"end\"."}
               {:name ":side"
                :type "string"
                :default "\"bottom\""
                :description "One of: \"top\" | \"right\" | \"bottom\" | \"left\"."}
               {:name ":sideOffset"
                :type "number"
                :default "4"
                :description "Distance from trigger/anchor."}
               {:name ":alignOffset"
                :type "number"
                :default nil
                :description "Offset along alignment axis."}
               {:name ":collisionPadding"
                :type "number | map"
                :default nil
                :description "Viewport collision padding."}
               {:name ":avoidCollisions"
                :type "boolean"
                :default "true"
                :description "Enables collision avoidance."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Popover.Content."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "This wrapper uses Radix/cmdk camelCase prop names (e.g. :onOpenChange, :asChild, :sideOffset)."]
       [:li
        "popover-content is always portaled, so z-index/stacking should be handled at app layout level."]
       [:li
        "Use popover for non-blocking contextual UI; use dialog/sheet for interruptive workflows."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "(let [open? (r/atom false)]\n  [popover {:open @open?\n            :onOpenChange #(reset! open? %)}\n   [popover-trigger {:asChild true}\n    [button {:variant :outline} \"Open\"]]\n   [popover-content {:align \"start\" :sideOffset 8}\n    [:p {:class \"text-sm\"} \"Popover content\"]]])"]]
      [:div {:class "flex flex-wrap gap-2 mt-3"}]]]])))

(defstory
 PopoverDemo
 "Popover with form fields.

  Radix primitive: @radix-ui/react-popover

  Useful for lightweight edits or inline settings."
 {:source-code (embed-source "clojure-shadcn.stories.popover.popover-demo")}
 []
 (r/as-element (helpers/wrap-component [example-popover-demo/example])))

(defstory
 ComboboxPopover
 "Combobox built with popover + command list.

  Radix primitives: @radix-ui/react-popover, @radix-ui/react-dialog

  Use popover + command for searchable selections."
 {:source-code (embed-source "clojure-shadcn.stories.popover.combobox-popover")}
 []
 (r/as-element (helpers/wrap-component [example-combobox-popover/example])))

(defstory
 PopoverTextOnly
 "Simple popover with text content.

  Radix primitive: @radix-ui/react-popover

  Good for quick hints or short explanations."
 {:source-code (embed-source "clojure-shadcn.stories.popover.popover-text-only")}
 []
 (r/as-element (helpers/wrap-component [example-popover-text-only/example])))


(defstory
 PopoverRtl
 "Localized form popover in right-to-left reading order. This verifies forwarded directionality and start alignment."
 {:source-code (embed-source "clojure-shadcn.stories.popover.popover-rtl")}
 []
 (r/as-element (helpers/wrap-component [example-popover-rtl/example])))

(defstory PopoverAlignments
          "Start, center, and end alignment using the existing content primitive."
          {:source-code (embed-source "clojure-shadcn.stories.popover.popover-alignments")}
          []
          (r/as-element (helpers/wrap-component [example-popover-alignments/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:side "bottom"}
           :arg-types {:side {:name "Side"
                              :control {:type "select"}
                              :options ["top" "right" "bottom" "left"]}}
           :parameters {:playground {:description "Explore popover side with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "open" "default-open" "on-open-change"]}}
           :source-code (embed-source "clojure-shadcn.stories.popover.playground")}
          [args]
          (r/as-element (helpers/wrap-component [example-playground/example args])))
