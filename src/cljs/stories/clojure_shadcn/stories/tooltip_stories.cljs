(ns clojure-shadcn.stories.tooltip-stories
  "Storybook stories for the Radix-compatible Tooltip compound API."
  (:require
   [clojure-shadcn.stories.helpers       :as helpers]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.tooltip :as sut]
   [reagent.core                         :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defdoc defstory]]))

(def ^:export default
  #js {:title "Components/Tooltip"
       :parameters #js {:layout "padded"}})

(defn- compound-tooltip
  [{:keys [trigger content root content-props trigger-props portal arrow-props]}]
  [sut/tooltip-root
   root
   [sut/tooltip-trigger (merge {:as-child true} trigger-props) trigger]
   [sut/tooltip-portal
    portal
    [sut/tooltip-content
     content-props
     content
     (when arrow-props [sut/tooltip-arrow arrow-props])]]])

(defn- controlled-example
  []
  (r/with-let [open? (r/atom false)]
              [:div {:class "flex items-center gap-4"}
               [button/button {:variant :outline
                               :on-click #(swap! open? not)}
                (if @open? "Close tooltip" "Open tooltip")]
               [compound-tooltip {:root {:open @open?
                                         :on-open-change #(reset! open? %)}
                                  :trigger (button/button {:variant :secondary}
                                                          "Controlled trigger")
                                  :content "State is owned by the story."}]
               [:span {:class "text-sm text-muted-foreground"}
                (if @open? "Open" "Closed")]]))

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
 []
 (r/as-element (helpers/wrap-component
                [sut/tooltip-provider {}
                 [compound-tooltip {:trigger (button/button {:variant :outline} "Hover or focus")
                                    :content "Add to library"
                                    :arrow-props {}}]])))

(defstory KeyboardKbd
          "Keyboard-focusable triggers with shortcut hints rendered as KBD content."
          []
          (r/as-element
           (helpers/wrap-component
            [sut/tooltip-provider {}
             [:div {:class "flex gap-4 p-6"}
              (for [[label shortcut] [["Save" "⌘ S"] ["Search" "⌘ K"]]]
                ^{:key label}
                [compound-tooltip
                 {:trigger (button/button {:variant :outline
                                           :size :sm}
                                          label)
                  :content
                  [:span {:class "flex items-center gap-2"}
                   label
                   [:kbd
                    {:class
                     "rounded border bg-muted px-1.5 font-mono text-[10px] text-muted-foreground"}
                    shortcut]]}])]])))

(defstory Delayed
          "Provider delay with a root-level instant override, demonstrating both timing scopes."
          []
          (r/as-element
           (helpers/wrap-component
            [sut/tooltip-provider {:delay-duration 1200
                                   :skip-delay-duration 300}
             [:div {:class "flex gap-4 p-6"}
              [compound-tooltip {:trigger (button/button {:variant :outline} "1.2 second delay")
                                 :content "Uses the provider delay."}]
              [compound-tooltip {:root {:delay-duration 0}
                                 :trigger (button/button {:variant :outline} "Instant override")
                                 :content "This root opens immediately."}]]])))

(defstory Sides
          "Content positioned on every supported side."
          []
          (r/as-element (helpers/wrap-component
                         [sut/tooltip-provider {}
                          [:div {:class "grid min-h-72 grid-cols-2 place-items-center gap-20 p-16"}
                           (for [side [:top :right :bottom :left]]
                             ^{:key side}
                             [compound-tooltip {:trigger (button/button {:variant :outline}
                                                                        (name side))
                                                :content (str "Tooltip on the " (name side))
                                                :content-props {:side side
                                                                :side-offset 8}}])]])))

(defstory
 DisabledTrigger
 "A focusable wrapper explains a disabled control because disabled buttons cannot reliably receive pointer or focus events."
 []
 (r/as-element
  (helpers/wrap-component
   [sut/tooltip-provider {}
    [compound-tooltip
     {:trigger
      [:span
       {:tab-index 0
        :aria-label "Why publishing is unavailable"
        :class
        "inline-flex rounded-md focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring"}
       (button/button {:disabled true
                       :class "pointer-events-none"}
                      "Publish")]
      :content "Complete all required fields before publishing."}]])))

(defstory Controlled
          "A controlled Root whose open state is shared with an external button and status label."
          []
          (r/as-element (helpers/wrap-component [controlled-example])))

(defstory
 Rtl
 "Provider-level RTL direction with start alignment, Arabic trigger text, and localized content."
 []
 (r/as-element (helpers/wrap-component
                [sut/tooltip-provider {:dir "rtl"}
                 [:div {:class "p-12 text-right"
                        :dir "rtl"}
                  [compound-tooltip {:trigger (button/button {:variant :outline} "معلومات إضافية")
                                     :content "تظهر هذه المعلومة عند التركيز أو المرور."
                                     :content-props {:side :top
                                                     :align :start}}]]])))

(defstory
 SimpleTooltipCompatibility
 "The convenience behavior now has the honest simple-tooltip name; tooltip remains its backwards-compatible alias."
 []
 (r/as-element (helpers/wrap-component
                [sut/tooltip-provider {}
                 [sut/simple-tooltip {:trigger (button/button {:variant :outline} "Convenience API")
                                      :trigger-as-child? true
                                      :content "One map still composes all Radix parts."
                                      :side :bottom
                                      :arrow? true}]])))
