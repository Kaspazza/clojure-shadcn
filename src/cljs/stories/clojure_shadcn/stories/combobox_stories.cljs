(ns clojure-shadcn.stories.combobox-stories
  (:require
   [clojure-shadcn.stories.combobox.backwards-compatible-simple-api :as
                                                                    backwards-compatible-simple-api]
   [clojure-shadcn.stories.combobox.basic                           :as basic]
   [clojure-shadcn.stories.combobox.composition                     :as composition]
   [clojure-shadcn.stories.combobox.disabled                        :as disabled]
   [clojure-shadcn.stories.combobox.invalid                         :as invalid]
   [clojure-shadcn.stories.combobox.multiple-with-chips-and-clear   :as
                                                                    multiple-with-chips-and-clear]
   [clojure-shadcn.stories.combobox.popup-initially-open            :as popup-initially-open]
   [clojure-shadcn.stories.combobox.right-to-left                   :as right-to-left]
   [clojure-shadcn.stories.helpers                                  :as helpers]
   [reagent.core                                                    :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Combobox"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element
         [helpers/installation-scene
          {:description
           "Compound Radix Popover + cmdk combobox, with a backwards-compatible data facade."
           :npm-install "npm install cmdk lucide-react @radix-ui/react-popover"
           :source-code (embed-source "clojure-shadcn.ui.components.combobox")
           :namespace-path "src/cljs/clojure_shadcn/ui/components/combobox.cljs"
           :filename "combobox.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid max-w-3xl gap-4 text-sm"}
    [:div {:class "grid gap-2"}
     [:h3 {:class "font-semibold"}
      "Compound API"]
     [:p {:class "text-muted-foreground"}
      "combobox-root provides controlled/uncontrolled value and popup state. Compose trigger/value/clear, then content/collection/input/list/group/label/item/empty/separator. combobox-chip is a presentational helper for multiple selection. All behavioral popup and option primitives remain Radix Popover and cmdk Command."]]
    [helpers/api-component-card
     {:component-name "combobox-root"
      :description
      "State Provider + Radix Popover.Root. Single selection closes after selection; multiple selection toggles values and leaves the popup open."
      :props
      [{:name ":value / :default-value"
        :type "any | vector"
        :default nil
        :description "Controlled or initial selection. Use a vector with :multiple? true."}
       {:name ":on-value-change"
        :type "function"
        :default nil
        :description "Receives the next scalar, nil, or multiple-selection vector."}
       {:name ":open / :default-open / :on-open-change"
        :type "boolean / function"
        :default false
        :description "Controlled or uncontrolled Radix Popover disclosure state."}
       {:name ":multiple? / :disabled?"
        :type "boolean"
        :default false
        :description
        "Enables toggle selection or disables interactions supplied by this compound API."}]}]
    [helpers/api-component-card
     {:component-name "combobox-item"
      :description
      "cmdk Command.Item connected to root selection. Includes a check indicator and aria-selected."
      :props
      [{:name ":value"
        :type "any, required"
        :default nil
        :description "Application selection value."}
       {:name ":text-value"
        :type "string"
        :default "(str value)"
        :description "Stable cmdk search/filter value; provide it when display children are rich."}
       {:name ":disabled / :keywords / :on-select"
        :type "cmdk props"
        :default nil
        :description
        "Disabled state, extra search terms, and an optional notification after selection."}]}]
    [:div {:class "rounded-lg border border-amber-500/30 bg-amber-500/10 p-4"}
     [:h4 {:class "mb-2 font-semibold"}
      "Radix/cmdk deviations from Base UI"]
     [:ul {:class "list-disc space-y-1 pl-5 text-muted-foreground"}
      [:li
       "Radix offers no Combobox primitive: popup anchoring, dismissal, portal, and focus come from Popover; filtering and active-option keyboard movement come from cmdk."]
      [:li
       "Trigger and search input are separate focus targets. There is no faithful Base UI input-anchor, inline autocomplete, virtualized collection, or inputValue/value parser contract."]
      [:li
       "Collection is cmdk's registry, not a public item metadata API. Label is visual; prefer group :heading so cmdk hides empty groups correctly."]
      [:li
       "Multiple selection is implemented in root state. Chips are explicitly composed and are not automatically synchronized or roving-focus managed."]
      [:li
       "ARIA is supplied by Radix/cmdk plus aria-expanded/haspopup/selected. This is not claimed to duplicate Base UI's exact DOM, data attributes, or announcement behavior."]]]
    [helpers/api-component-card
     {:component-name "simple-combobox (combobox alias)"
      :description
      "The original controlled, data-driven single-select facade. combobox remains a deprecated alias for source compatibility."
      :props [{:name ":items / :value / :on-value-change"
               :type "vector / any / function"
               :default nil
               :description "Original item-map contract and controlled selection."}
              {:name ":placeholder / :search-placeholder / :empty-text"
               :type "string"
               :default "built-in copy"
               :description "Empty trigger, search input, and no-results copy."}
              {:name ":disabled? / :clearable?"
               :type "boolean"
               :default false
               :description "Disables disclosure or adds an accessible clear action."}
              {:name ":item-render / :value-render"
               :type "function"
               :default nil
               :description "Render hooks receiving the complete item map."}]}]])))


(defstory Basic
          {:source-code (embed-source "clojure-shadcn.stories.combobox.basic")}
          []
          (r/as-element (helpers/wrap-component [basic/example])))
(defstory Composition
          {:source-code (embed-source "clojure-shadcn.stories.combobox.composition")}
          []
          (r/as-element (helpers/wrap-component [composition/example])))
(defstory Disabled
          {:source-code (embed-source "clojure-shadcn.stories.combobox.disabled")}
          []
          (r/as-element (helpers/wrap-component [disabled/example])))
(defstory Invalid
          {:source-code (embed-source "clojure-shadcn.stories.combobox.invalid")}
          []
          (r/as-element (helpers/wrap-component [invalid/example])))
(defstory PopupInitiallyOpen
          {:source-code (embed-source "clojure-shadcn.stories.combobox.popup-initially-open")}
          []
          (r/as-element (helpers/wrap-component [popup-initially-open/example])))
(defstory RightToLeft
          {:source-code (embed-source "clojure-shadcn.stories.combobox.right-to-left")}
          []
          (r/as-element (helpers/wrap-component [right-to-left/example])))


(defstory MultipleWithChipsAndClear
          {:source-code (embed-source
                         "clojure-shadcn.stories.combobox.multiple-with-chips-and-clear")}
          []
          (r/as-element (helpers/wrap-component [multiple-with-chips-and-clear/example])))


(defstory BackwardsCompatibleSimpleApi
          {:source-code (embed-source
                         "clojure-shadcn.stories.combobox.backwards-compatible-simple-api")}
          []
          (r/as-element (helpers/wrap-component [backwards-compatible-simple-api/example])))
