(ns clojure-shadcn.stories.loader-stories
  "Storybook stories for the Loader component. Ported from mateuszmazurczak.portfolio.ui-components.loader."
  (:require
   [clojure-shadcn.stories.helpers      :as helpers]
   [clojure-shadcn.ui.components.loader :as sut]
   [reagent.core                        :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Loader"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Comprehensive loader/spinner components with multiple variants and sizes."
                        :npm-install "No external dependencies"
                        :source-code (embed-source "clojure-shadcn.ui.components.loader")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/loader.cljs"
                        :filename "loader.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card {:component-name "circular-loader"
                                  :description "Circular loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "classic-loader"
                                  :description "Classic loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "pulse-loader"
                                  :description "Pulse loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "pulse-dot-loader"
                                  :description "Pulse dot loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "dots-loader"
                                  :description "Dots loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "typing-loader"
                                  :description "Typing loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "wave-loader"
                                  :description "Wave loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "bars-loader"
                                  :description "Bars loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "terminal-loader"
                                  :description "Terminal loader component"
                                  :props [{:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "text-blink-loader"
                                  :description "Text blink loader component"
                                  :props [{:name ":text"
                                           :type "string"
                                           :default "\"Thinking\""
                                           :description "Text label"}
                                          {:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "text-shimmer-loader"
                                  :description "Text shimmer loader component"
                                  :props [{:name ":text"
                                           :type "string"
                                           :default "\"Thinking\""
                                           :description "Text label"}
                                          {:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card {:component-name "text-dots-loader"
                                  :description "Text dots loader component"
                                  :props [{:name ":text"
                                           :type "string"
                                           :default "\"Thinking\""
                                           :description "Text label"}
                                          {:name ":size"
                                           :type "keyword"
                                           :default ":md"
                                           :description "One of: :sm | :md | :lg"}
                                          {:name ":class"
                                           :type "string"
                                           :default nil
                                           :description "Additional Tailwind classes"}]}]
     [helpers/api-component-card
      {:component-name "loader"
       :description "Loader component"
       :props
       [{:name ":variant"
         :type "keyword"
         :default ":circular"
         :description
         "One of: :circular | :classic | :pulse | :pulse-dot | :dots | :typing | :wave | :bars | :terminal | :text-blink | :text-shimmer | :loading-dots"}
        {:name ":size"
         :type "keyword"
         :default ":md"
         :description "One of: :sm | :md | :lg"}
        {:name ":text"
         :type "string"
         :default "\"Thinking\""
         :description "Text label"}
        {:name ":class"
         :type "string"
         :default nil
         :description "Additional Tailwind classes"}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Text variants use :text, while non-text variants ignore it; choose variant intentionally."]
       [:li
        "Prefer semantic loading copy for long operations (e.g., 'Syncing invoices...') over generic labels."]
       [:li
        "Every loader defaults to role=status and aria-label=Loading. Pass :label or :aria-label to name the actual operation."]
       [:li
        "All animation classes include motion-reduce:animate-none, so reduced-motion users see a static status indicator."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "[:div {:class \"flex items-center gap-3\"}\n [loader {:variant :circular :size :sm}]\n [loader {:variant :pulse-dot :size :md}]\n [loader {:variant :text-shimmer :text \"Generating\"}]]"]]]]])))

(defstory LoaderAllVariants
          []
          (r/as-element (helpers/wrap-component [:div {:class
                                                       "p-6 flex flex-wrap items-center gap-6"}
                                                 [sut/loader {:variant :circular}]
                                                 [sut/loader {:variant :classic}]
                                                 [sut/loader {:variant :pulse}]
                                                 [sut/loader {:variant :pulse-dot}]
                                                 [sut/loader {:variant :dots}]
                                                 [sut/loader {:variant :typing}]
                                                 [sut/loader {:variant :wave}]
                                                 [sut/loader {:variant :bars}]
                                                 [sut/loader {:variant :terminal}]])))

(defstory LoaderSizes
          "Size-by-variant matrix. Every variant supports :sm, :md, and :lg."
          []
          (r/as-element
           (helpers/wrap-component
            [:div {:class "p-6 overflow-x-auto"}
             (into [:div {:class "grid grid-cols-4 items-center gap-5 text-sm"}
                    [:strong "Variant"]
                    [:strong "Small"]
                    [:strong "Medium"]
                    [:strong "Large"]]
                   (for [variant [:circular
                                  :classic
                                  :pulse
                                  :pulse-dot
                                  :dots
                                  :typing
                                  :wave
                                  :bars
                                  :terminal
                                  :text-blink
                                  :text-shimmer
                                  :loading-dots]
                         item (concat [[:span {:class "font-mono text-xs"}
                                        (name variant)]]
                                      (for [size [:sm :md :lg]]
                                        [sut/loader {:variant variant
                                                     :size size
                                                     :text "Loading"
                                                     :label
                                                     (str (name variant) " " (name size))}]))]
                     ^{:key (str variant "-" (hash item))} item))])))

(defstory LoaderAccessibleLabel
          "Custom accessible labels describe the operation without adding duplicate visible text."
          []
          (r/as-element (helpers/wrap-component [:div {:class "p-6 flex items-center gap-3"}
                                                 [sut/loader {:variant :circular
                                                              :label "Saving account settings"}]
                                                 [:span {:class "text-sm"}
                                                  "Saving account settings"]])))

(defstory
 LoaderTextVariants
 "Text-based loaders with custom text.
  Text variants: :text-blink, :text-shimmer, :loading-dots."
 []
 (r/as-element (helpers/wrap-component [:div {:class "p-6 space-y-3"}
                                        [sut/loader {:variant :text-blink
                                                     :text "Thinking"
                                                     :size :md}]
                                        [sut/loader {:variant :text-shimmer
                                                     :text "Processing"
                                                     :size :md}]
                                        [sut/loader {:variant :loading-dots
                                                     :text "Loading"
                                                     :size :md}]])))

(defstory Playground
          "Interactive unified loader playground."
          {:args {:variant "circular"
                  :size "md"
                  :text "Thinking"}
           :arg-types {:variant {:name "Variant"
                                 :control {:type "select"}
                                 :options ["circular"
                                           "classic"
                                           "pulse"
                                           "pulse-dot"
                                           "dots"
                                           "typing"
                                           "wave"
                                           "bars"
                                           "terminal"
                                           "text-blink"
                                           "text-shimmer"
                                           "loading-dots"]}
                       :size {:name "Size"
                              :control {:type "select"}
                              :options ["sm" "md" "lg"]}
                       :text {:name "Text"
                              :control {:type "text"}}}
           :parameters {:playground {:description "Explore loader variant, size and text with a live preview."}
                        :controls {:expanded false
                                   :exclude ["class"]}}
           :decode-args (fn [{:keys [variant size]
                              :as args}]
                          (cond-> args
                            variant (update :variant keyword)
                            size (update :size keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [:div {:class "p-6"}
                                                 [sut/loader
                                                  (select-keys args [:variant :size :text])]])))
