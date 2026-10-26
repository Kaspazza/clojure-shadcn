(ns clojure-shadcn.stories.tooltip-stories
  "Storybook stories for the Radix-compatible Tooltip compound API."
  (:require
   [clojure-shadcn.stories.helpers                              :as helpers]
   [clojure-shadcn.stories.tooltip.controlled                   :as example-controlled]
   [clojure-shadcn.stories.tooltip.delayed                      :as example-delayed]
   [clojure-shadcn.stories.tooltip.disabled-trigger             :as example-disabled-trigger]
   [clojure-shadcn.stories.tooltip.keyboard-kbd                 :as example-keyboard-kbd]
   [clojure-shadcn.stories.tooltip.rtl                          :as example-rtl]
   [clojure-shadcn.stories.tooltip.sides                        :as example-sides]
   [clojure-shadcn.stories.tooltip.simple-tooltip-compatibility
    :as example-simple-tooltip-compatibility]
   [clojure-shadcn.stories.tooltip.tooltip-demo                 :as example-tooltip-demo]
   [reagent.core                                                :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defdoc defstory]]))

(def ^:export default
  #js {:title "Components/Tooltip"
       :parameters #js {:layout "padded"}})




(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Upstream-shaped Radix Tooltip parts plus an optional convenience wrapper."
                        :npm-install "npm install @radix-ui/react-tooltip"
                        :source-code (embed-source "clojure-shadcn.ui.components.tooltip")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/tooltip.cljs"
                        :filename "tooltip.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "max-w-4xl space-y-4 p-6"}
    [helpers/api-component-card
     {:component-name "tooltip-provider / tooltip-root / tooltip-trigger"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/tooltip"
             :label "Radix Tooltip Docs"}
      :description
      "Direct Reagent wrappers for Radix Provider, Root, and Trigger. Each part forwards upstream props and adds a matching data-slot."
      :props
      [{:name "provider"
        :type "component"
        :default nil
        :description ":delay-duration, :skip-delay-duration, :disable-hoverable-content, and :dir."}
       {:name "root"
        :type "component"
        :default nil
        :description
        ":open, :default-open, :on-open-change, :delay-duration, and :disable-hoverable-content."}
       {:name "trigger"
        :type "component"
        :default nil
        :description
        ":as-child composes Radix behavior into one focusable child; disabled controls need a focusable wrapper."}]}]
    [helpers/api-component-card
     {:component-name "tooltip-portal / tooltip-content / tooltip-arrow"
      :description
      "Direct wrappers for Radix Portal, Content, and Arrow. Content and Arrow retain the library's styled slots; portal placement is explicit rather than hidden inside content."
      :props
      [{:name "portal"
        :type "component"
        :default nil
        :description ":container and :force-mount; all additional upstream props are forwarded."}
       {:name "content"
        :type "component"
        :default nil
        :description
        ":side, :side-offset, :align, :align-offset, collision options, :sticky, :class, and all Radix Content/DOM props."}
       {:name "arrow"
        :type "component"
        :default nil
        :description
        "Optional styled arrow; width, height, :class, and SVG props are forwarded."}]}]
    [helpers/api-component-card
     {:component-name "simple-tooltip (tooltip compatibility alias)"
      :description
      "Monolithic map-based convenience API. The historical tooltip name remains an exact alias where technically possible; it is not the compound root. New compound code must use tooltip-root."
      :props [{:name ":trigger / :content"
               :type "hiccup"
               :default nil
               :description "Required convenience slots."}
              {:name ":root-props / :trigger-props / :portal-props / :content-props / :arrow-props"
               :type "map"
               :default nil
               :description "Explicit per-part forwarding maps. Convenience keys win on conflicts."}
              {:name ":arrow?"
               :type "boolean"
               :default "false"
               :description "Renders tooltip-arrow when true."}]}]
    [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4"}
     [:h4 {:class "mb-2 text-sm font-semibold"}
      "Naming compatibility"]
     [:p {:class "text-xs text-muted-foreground"}
      "tooltip still means the legacy monolithic component. This avoids breaking callers, but differs from libraries that name Root `Tooltip`. Use tooltip-root for an unambiguous upstream-shaped compound composition. tooltip-provider-component is retained only for old [:> ...] usage; prefer tooltip-provider."]]
    [:pre {:class "overflow-x-auto rounded-lg border bg-muted/50 p-4 text-xs"}
     [:code
      "[tooltip-provider {:delay-duration 300}\n [tooltip-root {}\n  [tooltip-trigger {:as-child true} [:button \"Hover\"]]\n  [tooltip-portal {}\n   [tooltip-content {:side :top}\n    \"Helpful context\"\n    [tooltip-arrow {}]]]]]"]]])))

(defstory
 TooltipDemo
 "Basic compound tooltip with explicit Provider, Root, Trigger, Portal, Content, and Arrow."
 {:source-code (embed-source "clojure-shadcn.stories.tooltip.tooltip-demo")}
 []
 (r/as-element (helpers/wrap-component [example-tooltip-demo/example])))

(defstory KeyboardKbd
          "Keyboard-focusable triggers with shortcut hints rendered as KBD content."
          {:source-code (embed-source "clojure-shadcn.stories.tooltip.keyboard-kbd")}
          []
          (r/as-element (helpers/wrap-component [example-keyboard-kbd/example])))

(defstory Delayed
          "Provider delay with a root-level instant override, demonstrating both timing scopes."
          {:source-code (embed-source "clojure-shadcn.stories.tooltip.delayed")}
          []
          (r/as-element (helpers/wrap-component [example-delayed/example])))

(defstory Sides
          "Content positioned on every supported side."
          {:source-code (embed-source "clojure-shadcn.stories.tooltip.sides")}
          []
          (r/as-element (helpers/wrap-component [example-sides/example])))

(defstory
 DisabledTrigger
 "A focusable wrapper explains a disabled control because disabled buttons cannot reliably receive pointer or focus events."
 {:source-code (embed-source "clojure-shadcn.stories.tooltip.disabled-trigger")}
 []
 (r/as-element (helpers/wrap-component [example-disabled-trigger/example])))

(defstory Controlled
          "A controlled Root whose open state is shared with an external button and status label."
          {:source-code (embed-source "clojure-shadcn.stories.tooltip.controlled")}
          []
          (r/as-element (helpers/wrap-component [example-controlled/example])))

(defstory
 Rtl
 "Provider-level RTL direction with start alignment, Arabic trigger text, and localized content."
 {:source-code (embed-source "clojure-shadcn.stories.tooltip.rtl")}
 []
 (r/as-element (helpers/wrap-component [example-rtl/example])))

(defstory
 SimpleTooltipCompatibility
 "The convenience behavior now has the honest simple-tooltip name; tooltip remains its backwards-compatible alias."
 {:source-code (embed-source "clojure-shadcn.stories.tooltip.simple-tooltip-compatibility")}
 []
 (r/as-element (helpers/wrap-component [example-simple-tooltip-compatibility/example])))
