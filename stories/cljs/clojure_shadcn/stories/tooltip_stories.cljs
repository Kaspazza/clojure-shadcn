(ns clojure-shadcn.stories.tooltip-stories
  "Storybook stories for the Tooltip component. Ported from mateuszmazurczak.portfolio.ui-components.tooltip."
  (:require
   [clojure-shadcn.stories.helpers       :as helpers]
   [clojure-shadcn.ui.components.button  :as button]
   [clojure-shadcn.ui.components.tooltip :as sut]
   [reagent.core                         :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Tooltip"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Self-contained tooltip component for displaying contextual information on hover/focus."
           :npm-install "npm install @radix-ui/react-tooltip"
           :source-code (embed-source "clojure-shadcn.ui.components.tooltip")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/tooltip.cljs"
           :filename "tooltip.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "tooltip"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/tooltip"
              :label "Radix Tooltip Docs"}
       :description
       "Self-contained tooltip with configurable placement, delay, controlled state, and Radix slot-based trigger composition."
       :props [{:name ":trigger"
                :type "hiccup"
                :default nil
                :description "Tooltip trigger element"}
               {:name ":content"
                :type "string | hiccup"
                :default nil
                :description "Tooltip content"}
               {:name ":side"
                :type "keyword"
                :default ":top"
                :description "One of: :top | :right | :bottom | :left"}
               {:name ":side-offset"
                :type "number"
                :default "4"
                :description "Distance from trigger"}
               {:name ":align"
                :type "keyword"
                :default ":center"
                :description "One of: :start | :center | :end"}
               {:name ":align-offset"
                :type "number"
                :default "0"
                :description "Alignment offset in pixels"}
               {:name ":collision-padding"
                :type "number"
                :default "0"
                :description "Viewport collision padding"}
               {:name ":avoid-collisions?"
                :type "boolean"
                :default "true"
                :description "Auto reposition to stay in viewport"}
               {:name ":sticky"
                :type "keyword"
                :default ":partial"
                :description "One of: :partial | :always"}
               {:name ":delay-duration"
                :type "number"
                :default "700"
                :description "Open delay in ms"}
               {:name ":skip-delay-duration"
                :type "number"
                :default "300"
                :description "Delay-skip window in ms"}
               {:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled open state"}
               {:name ":default-open"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial open state"}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Callback (fn [open?])"}
               {:name ":content-class"
                :type "string"
                :default nil
                :description "Additional classes for content"}
               {:name ":content-hidden?"
                :type "boolean"
                :default "false"
                :description "Hide tooltip content"}
               {:name ":trigger-as-child?"
                :type "boolean"
                :default "false"
                :description "Render trigger via Slot"}
               {:name ":disable-hoverable-content?"
                :type "boolean"
                :default "false"
                :description "Disable hoverable content"}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li "Prefer trigger-as-child? false when trigger is a Reagent component wrapper."]
       [:li
        "Tooltips should provide supplementary info only; don't hide critical actions/content inside them."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[tooltip {:trigger [button {:variant :outline} \"Hover me\"]\n          :content \"Helpful context\"\n          :side :top}]"]]]]])))

(defstory
 TooltipDemo
 "Basic tooltip with a button trigger.

  Radix primitive: @radix-ui/react-tooltip

  Use tooltips for concise, contextual hints."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/tooltip {:trigger [button/button {:variant :outline}
                                                                "Hover"]
                                                      :content "Add to library"}]])))

(defstory
 KbdTooltip
 "Tooltip with keyboard shortcut hints.

  Radix primitive: @radix-ui/react-tooltip

  Use inline <kbd> tags to show shortcuts."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 flex flex-wrap gap-4"}
    [sut/tooltip
     {:trigger [button/button {:size :sm
                               :variant :outline}
                "Save"]
      :content
      [:div {:class "flex items-center gap-2"}
       "Save Changes"
       [:kbd
        {:class
         "bg-muted text-muted-foreground inline-flex h-5 items-center rounded border px-1.5 font-mono text-[10px]"}
        "S"]]}]
    [sut/tooltip
     {:trigger [button/button {:size :sm
                               :variant :outline}
                "Print"]
      :content
      [:div {:class "flex items-center gap-2"}
       "Print Document"
       [:span {:class "flex items-center gap-1"}
        [:kbd
         {:class
          "bg-muted text-muted-foreground inline-flex h-5 items-center rounded border px-1.5 font-mono text-[10px]"}
         "Ctrl"]
        [:kbd
         {:class
          "bg-muted text-muted-foreground inline-flex h-5 items-center rounded border px-1.5 font-mono text-[10px]"}
         "P"]]]}]])))

(defstory
 TooltipCustomContent
 "Tooltip with rich content.

  Radix primitive: @radix-ui/react-tooltip

  Rich content works well for onboarding hints."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                        [sut/tooltip {:trigger [button/button {:variant :ghost}
                                                                "Hover for info"]
                                                      :content
                                                      [:div {:class "space-y-1"}
                                                       [:p {:class "font-semibold"}
                                                        "Pro tip"]
                                                       [:p {:class "text-xs text-muted-foreground"}
                                                        "Use ⌘K to search across projects."]]
                                                      :content-class "max-w-xs"}]])))
