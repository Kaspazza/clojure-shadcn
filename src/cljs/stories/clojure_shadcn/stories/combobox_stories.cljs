(ns clojure-shadcn.stories.combobox-stories
  (:require
   [clojure-shadcn.stories.helpers        :as helpers]
   [clojure-shadcn.ui.components.combobox :as sut]
   [reagent.core                          :as r])
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

(def runtimes
  [{:value :clj
    :label "Clojure"
    :keywords ["jvm"]}
   {:value :cljs
    :label "ClojureScript"
    :keywords ["browser"]}
   {:value :bb
    :label "Babashka"
    :keywords ["cli"]}])

(defn- runtime-label [value] (or (:label (some #(when (= value (:value %)) %) runtimes)) "None"))

(defn- composed-example
  [{:keys [disabled? invalid? default-open rtl?]}]
  (r/with-let
   [value (r/atom nil)]
   [:div {:dir (when rtl? "rtl")
          :class "grid gap-2"}
    [:label {:class "text-sm font-medium"}
     "Runtime"]
    [sut/combobox-root {:value @value
                        :on-value-change #(reset! value %)
                        :default-open default-open
                        :disabled? disabled?}
     [:div {:class "flex items-center gap-1"}
      [sut/combobox-trigger {:aria-invalid invalid?}
       [sut/combobox-value {:placeholder "Select runtime…"
                            :render runtime-label}]
       [:span {:aria-hidden true}
        "⌄"]]
      (when @value
        [sut/combobox-clear {}])]
     [sut/combobox-content {:align (if rtl? "end" "start")}
      [sut/combobox-collection {}
       [sut/combobox-input {:placeholder "Search runtimes…"
                            :aria-label "Search runtimes"}]
       [sut/combobox-list {}
        [sut/combobox-empty {}
         "No runtime found."]
        [sut/combobox-group {:heading "Hosted"}
         [sut/combobox-item {:value :clj
                             :text-value "Clojure"
                             :keywords #js ["jvm"]}
          "Clojure"]
         [sut/combobox-item {:value :cljs
                             :text-value "ClojureScript"}
          "ClojureScript"]]
        [sut/combobox-separator {}]
        [sut/combobox-group {}
         [sut/combobox-label {}
          "Tools"]
         [sut/combobox-item {:value :bb
                             :text-value "Babashka"}
          "Babashka"]
         [sut/combobox-item {:value :disabled
                             :text-value "Unavailable"
                             :disabled true}
          "Unavailable"]]]]]]
    (when invalid?
      [:p {:class "text-sm text-destructive"}
       "Choose a supported runtime."])]))

(defstory Basic
          []
          (r/as-element (helpers/wrap-component [composed-example {}])))
(defstory Composition
          []
          (r/as-element (helpers/wrap-component [composed-example {}])))
(defstory Disabled
          []
          (r/as-element (helpers/wrap-component [composed-example {:disabled? true}])))
(defstory Invalid
          []
          (r/as-element (helpers/wrap-component [composed-example {:invalid? true}])))
(defstory PopupInitiallyOpen
          []
          (r/as-element (helpers/wrap-component [composed-example {:default-open true}])))
(defstory RightToLeft
          []
          (r/as-element (helpers/wrap-component [composed-example {:rtl? true}])))

(defn- multiple-example
  []
  (r/with-let [values (r/atom [:clj])]
              [:div {:class "grid w-[320px] gap-2"}
               [:div {:class "flex min-h-9 flex-wrap gap-1 rounded-md border p-1"}
                (for [value @values]
                  ^{:key (name value)}
                  [sut/combobox-chip {:on-remove #(swap! values (fn [xs]
                                                                  (vec (remove #{value} xs))))
                                      :remove-label (str "Remove " (runtime-label value))}
                   (runtime-label value)])]
               [sut/combobox-root {:multiple? true
                                   :value @values
                                   :on-value-change #(reset! values %)}
                [sut/combobox-trigger {:class "w-full"}
                 [sut/combobox-value {:placeholder "Select runtimes…"
                                      :render #(str (count %) " selected")}]
                 [:span {:aria-hidden true}
                  "⌄"]]
                [sut/combobox-content {:class "w-[320px]"}
                 [sut/combobox-collection {}
                  [sut/combobox-input {:placeholder "Search runtimes…"}]
                  [sut/combobox-list {}
                   (into [sut/combobox-group {}]
                         (for [{:keys [value label]} runtimes]
                           ^{:key label}
                           [sut/combobox-item {:value value
                                               :text-value label}
                            label]))]]]
                [sut/combobox-clear {:label "Clear all runtimes"}]]]))

(defstory MultipleWithChipsAndClear [] (r/as-element (helpers/wrap-component [multiple-example])))

(defn- simple-example
  []
  (r/with-let [value (r/atom nil)]
              [sut/simple-combobox {:items runtimes
                                    :value @value
                                    :on-value-change #(reset! value %)
                                    :placeholder "Select runtime…"
                                    :search-placeholder "Search runtimes…"
                                    :empty-text "No runtime found."
                                    :clearable? true}]))

(defstory BackwardsCompatibleSimpleApi [] (r/as-element (helpers/wrap-component [simple-example])))
