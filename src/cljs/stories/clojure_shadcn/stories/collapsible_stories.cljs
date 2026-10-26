(ns clojure-shadcn.stories.collapsible-stories
  "Storybook stories for the Collapsible component. Ported from mateuszmazurczak.portfolio.ui-components.collapsible."
  (:require
   [clojure-shadcn.stories.collapsible.collapsible-basic           :as collapsible-basic]
   [clojure-shadcn.stories.collapsible.collapsible-default-open    :as collapsible-default-open]
   [clojure-shadcn.stories.collapsible.collapsible-disabled        :as collapsible-disabled]
   [clojure-shadcn.stories.collapsible.collapsible-file-tree       :as collapsible-file-tree]
   [clojure-shadcn.stories.collapsible.collapsible-multiple        :as collapsible-multiple]
   [clojure-shadcn.stories.collapsible.collapsible-rtl             :as collapsible-rtl]
   [clojure-shadcn.stories.collapsible.collapsible-settings        :as collapsible-settings]
   [clojure-shadcn.stories.collapsible.collapsible-summary-details :as collapsible-summary-details]
   [clojure-shadcn.stories.collapsible.playground                  :as playground]
   [clojure-shadcn.stories.helpers                                 :as helpers]
   [reagent.core                                                   :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Collapsible"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        "Install dependencies and copy the component code into your project."
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Collapsible component for showing and hiding content with animation."
                        :npm-install "npm install @radix-ui/react-collapsible"
                        :source-code (embed-source "clojure-shadcn.ui.components.collapsible")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/collapsible.cljs"
                        :filename "collapsible.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "p-6 max-w-4xl"}
    [:div {:class "space-y-4"}
     [helpers/api-component-card
      {:component-name "collapsible"
       :link {:href "https://www.radix-ui.com/primitives/docs/components/collapsible"
              :label "Radix Collapsible Docs"}
       :description
       "Radix Collapsible.Root wrapper that controls open/closed state for collapsible sections. Additional props are forwarded to the Radix root primitive."
       :props [{:name ":open"
                :type "boolean"
                :default nil
                :description "Controlled open state."}
               {:name ":default-open"
                :type "boolean"
                :default nil
                :description "Uncontrolled initial open state."}
               {:name ":on-open-change"
                :type "function"
                :default nil
                :description "Callback when open state changes: (fn [open?] ...)."}
               {:name ":disabled"
                :type "boolean"
                :default nil
                :description "Disables toggling."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix Collapsible.Root."}]}]
     [helpers/api-component-card
      {:component-name "collapsible-trigger"
       :description
       "Toggle control for a collapsible block. Typically wraps a button label or a custom button when using :as-child."
       :props [{:name ":as-child"
                :type "boolean"
                :default nil
                :description "Use child element as trigger via Radix Slot."}
               {:name ":on-click"
                :type "function"
                :default nil
                :description "Additional click handler composed with Radix toggle behavior."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix CollapsibleTrigger."}]}]
     [helpers/api-component-card
      {:component-name "collapsible-content"
       :description
       "Expandable/collapsible content container with Radix state attributes for animation styling."
       :props [{:name ":force-mount"
                :type "boolean"
                :default nil
                :description
                "Forces mounting even when collapsed (useful for animation libraries)."}
               {:name ":class"
                :type "string"
                :default nil
                :description "Additional Tailwind classes."}
               {:name "additional props"
                :type "map entries"
                :default nil
                :description "Forwarded to Radix CollapsibleContent."}]}]
     [:div {:class "border rounded-lg p-4 bg-amber-500/10 border-amber-500/30 mb-4"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "⚠️ Important Notes"]
      [:ul {:class "text-xs text-muted-foreground space-y-1 list-disc pl-4"}
       [:li
        "Use either controlled (:open + :on-open-change) or uncontrolled (:default-open) mode, not both."]
       [:li
        "Use :as-child on the trigger when its child is a Button — otherwise Radix renders nested <button> elements (invalid HTML)."]
       [:li
        "When using :as-child on trigger, ensure your child element is interactive and keyboard-accessible."]
       [:li
        "Style animations using data-state attributes: data-[state=open] and data-[state=closed]."]]]
     [:div {:class "border rounded-lg p-4 bg-muted/50"}
      [:h4 {:class "text-sm font-semibold mb-2"}
       "Usage Example"]
      [:pre {:class "text-xs overflow-x-auto"}
       [:code
        "(let [open? (r/atom false)]\n  [collapsible {:open @open?\n                :on-open-change #(reset! open? %)}\n   [collapsible-trigger {:as-child true}\n    (button {:variant :outline} \"Toggle details\")]\n   [collapsible-content {:class \"mt-3\"}\n    [:div {:class \"rounded-md border p-3\"} \"Collapsible content\"]]])"]]]]])))

(defstory
 CollapsibleBasic
 "Collapsible content with toggle.\n\n  Radix primitive: @radix-ui/react-collapsible\n\n  Use to reveal secondary details without leaving the page."
 {:source-code (embed-source "clojure-shadcn.stories.collapsible.collapsible-basic")}
 []
 (r/as-element (helpers/wrap-component [collapsible-basic/example])))

(defstory
 CollapsibleDefaultOpen
 "Collapsible starting in the open state.\n\n  Radix primitive: @radix-ui/react-collapsible\n\n  Use :default-open for uncontrolled open state."
 {:source-code (embed-source "clojure-shadcn.stories.collapsible.collapsible-default-open")}
 []
 (r/as-element (helpers/wrap-component [collapsible-default-open/example])))

(defstory
 CollapsibleDisabled
 "Disabled collapsible trigger.\n\n  Radix primitive: @radix-ui/react-collapsible\n\n  Use :disabled to lock the collapsible state."
 {:source-code (embed-source "clojure-shadcn.stories.collapsible.collapsible-disabled")}
 []
 (r/as-element (helpers/wrap-component [collapsible-disabled/example])))

(defstory
 CollapsibleMultiple
 "Multiple collapsibles in a list.\n\n  Radix primitive: @radix-ui/react-collapsible\n\n  Useful for FAQ sections or grouped settings."
 {:source-code (embed-source "clojure-shadcn.stories.collapsible.collapsible-multiple")}
 []
 (r/as-element (helpers/wrap-component [collapsible-multiple/example])))


(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:args {:default-open false}
           :arg-types {:default-open {:name "Initially open"
                                      :control {:type "boolean"}}}
           :parameters {:playground {:description
                                     "Explore collapsible initially open with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "open" "on-open-change"]}}
           :source-code (embed-source "clojure-shadcn.stories.collapsible.playground")}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory
 CollapsibleSummaryDetails
 "Summary / details — compares the native HTML disclosure with the styled Radix Collapsible pattern."
 {:source-code (embed-source "clojure-shadcn.stories.collapsible.collapsible-summary-details")}
 []
 (r/as-element (helpers/wrap-component [collapsible-summary-details/example])))

(defstory
 CollapsibleSettings
 "Settings — state-aware rows expose their current open/closed label and data-state styling."
 {:source-code (embed-source "clojure-shadcn.stories.collapsible.collapsible-settings")}
 []
 (r/as-element (helpers/wrap-component [collapsible-settings/example])))



(defstory CollapsibleFileTree
          "Recursive file tree — nested Collapsible nodes model independently expandable folders."
          {:source-code (embed-source "clojure-shadcn.stories.collapsible.collapsible-file-tree")}
          []
          (r/as-element (helpers/wrap-component [collapsible-file-tree/example])))

(defstory CollapsibleRTL
          "RTL — state-aware Basic disclosure and alignment in right-to-left content."
          {:source-code (embed-source "clojure-shadcn.stories.collapsible.collapsible-rtl")}
          []
          (r/as-element (helpers/wrap-component [collapsible-rtl/example])))
