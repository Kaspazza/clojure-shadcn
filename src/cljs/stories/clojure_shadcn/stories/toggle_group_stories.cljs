(ns clojure-shadcn.stories.toggle-group-stories
  (:require
   ["lucide-react"                            :refer [AlignCenter
                                                      AlignLeft
                                                      AlignRight
                                                      Bold
                                                      Italic
                                                      Underline]]
   [clojure-shadcn.stories.helpers            :as helpers]
   [clojure-shadcn.ui.components.toggle-group :as sut]
   [reagent.core                              :as r])
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
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/toggle-group {:type "single"
                                                                    :default-value "left"
                                                                    :variant :outline
                                                                    :aria-label "Text alignment"}
                                                  [sut/toggle-group-item {:value "left"
                                                                          :aria-label "Align left"}
                                                   [:> AlignLeft]]
                                                  [sut/toggle-group-item {:value "center"
                                                                          :aria-label
                                                                          "Align center"}
                                                   [:> AlignCenter]]
                                                  [sut/toggle-group-item {:value "right"
                                                                          :aria-label "Align right"}
                                                   [:> AlignRight]]]])))

(defstory ToggleGroupMultiple
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/toggle-group {:type "multiple"
                                                                    :default-value ["bold" "italic"]
                                                                    :size :lg
                                                                    :aria-label "Text formatting"}
                                                  [sut/toggle-group-item {:value "bold"
                                                                          :aria-label "Bold"}
                                                   [:> Bold]]
                                                  [sut/toggle-group-item {:value "italic"
                                                                          :aria-label "Italic"}
                                                   [:> Italic]]
                                                  [sut/toggle-group-item {:value "underline"
                                                                          :disabled true
                                                                          :aria-label
                                                                          "Underline unavailable"}
                                                   [:> Underline]]]])))


(defstory
 Playground
 "Controlled Storybook playground using only safe scalar component props."
 {:args {:disabled false}
  :arg-types {:disabled {:name "Disabled"
                                  :control {:type "boolean"}}}
  :parameters {:playground {:description "Explore toggle group disabled with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "value" "default-value" "on-value-change"]}}}
 [args]
 (r/as-element
  (helpers/wrap-component
   [sut/toggle-group
    (assoc (select-keys args [:disabled]) :type "single" :default-value "left" :variant :outline)
    [sut/toggle-group-item {:value "left"}
     "Left"]
    [sut/toggle-group-item {:value "center"}
     "Center"]
    [sut/toggle-group-item {:value "right"}
     "Right"]])))

(defstory
 ToggleGroupPatterns
 "Behavior-distinct disabled, outline, spaced, vertical, RTL, and font-selector compositions. Spacing and vertical layout are class recipes over the current API."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid gap-6 p-6"}
    [sut/toggle-group {:type "multiple"
                       :variant :outline
                       :disabled true
                       :default-value ["bold"]}
     [sut/toggle-group-item {:value "bold"}
      [:> Bold]]
     [sut/toggle-group-item {:value "italic"}
      [:> Italic]]]
    [sut/toggle-group
     {:type "single"
      :default-value "serif"
      :variant :outline
      :class
      "gap-2 [&_[data-slot=toggle-group-item]]:rounded-md [&_[data-slot=toggle-group-item]]:border-l"}
     [sut/toggle-group-item {:value "sans"
                             :class "font-sans"}
      "Sans"]
     [sut/toggle-group-item {:value "serif"
                             :class "font-serif"}
      "Serif"]
     [sut/toggle-group-item {:value "mono"
                             :class "font-mono"}
      "Mono"]]
    [sut/toggle-group {:type "single"
                       :orientation "vertical"
                       :default-value "center"
                       :class "flex-col items-stretch [&_[data-slot=toggle-group-item]]:rounded-md"}
     [sut/toggle-group-item {:value "left"}
      [:> AlignLeft]
      "Left"]
     [sut/toggle-group-item {:value "center"}
      [:> AlignCenter]
      "Center"]
     [sut/toggle-group-item {:value "right"}
      [:> AlignRight]
      "Right"]]
    [:div {:dir "rtl"}
     [sut/toggle-group {:type "single"
                        :dir "rtl"
                        :default-value "right"
                        :variant :outline
                        :aria-label "محاذاة النص"}
      [sut/toggle-group-item {:value "right"
                              :aria-label "محاذاة لليمين"}
       [:> AlignRight]]
      [sut/toggle-group-item {:value "center"
                              :aria-label "توسيط"}
       [:> AlignCenter]]
      [sut/toggle-group-item {:value "left"
                              :aria-label "محاذاة لليسار"}
       [:> AlignLeft]]]]])))

(defstory ToggleGroupSizes
          "Static supported size variants only. Based on shadcn/ui Toggle Group — Size."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "flex flex-col items-start gap-4 p-6"}
             (for [[size label] [[:sm "Small"] [:default "Default"] [:lg "Large"]]]
               ^{:key (name size)}
               [sut/toggle-group {:type "single"
                                  :default-value "center"
                                  :variant :outline
                                  :size size
                                  :aria-label (str label " alignment")}
                [sut/toggle-group-item {:value "left"
                                        :aria-label "Align left"}
                 [:> AlignLeft]]
                [sut/toggle-group-item {:value "center"
                                        :aria-label "Align center"}
                 [:> AlignCenter]]
                [sut/toggle-group-item {:value "right"
                                        :aria-label "Align right"}
                 [:> AlignRight]]])])))
