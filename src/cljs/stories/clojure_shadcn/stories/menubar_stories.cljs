(ns clojure-shadcn.stories.menubar-stories
  (:require
   [clojure-shadcn.stories.helpers                    :as helpers]
   [clojure-shadcn.stories.menubar.menubar-demo       :as menubar-demo]
   [clojure-shadcn.stories.menubar.menubar-rtl        :as menubar-rtl]
   [clojure-shadcn.stories.menubar.menubar-selections :as menubar-selections]
   [clojure-shadcn.stories.menubar.playground         :as playground]
   [reagent.core                                      :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Menubar"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Accessible application menubar with nested and selectable items."
                        :npm-install "npm install @radix-ui/react-menubar lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.menubar")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/menubar.cljs"
                        :filename "menubar.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "menubar / menubar-menu / menubar-trigger / menubar-content"
      :link {:href "https://www.radix-ui.com/primitives/docs/components/menubar"
             :label "Radix Menubar Docs"}
      :description
      "Core Radix menubar composition with roving focus, keyboard navigation, menu ARIA, and automatically portaled content. Each Menu pairs a Trigger and Content beneath Root."
      :props [{:name ":align"
               :type "keyword | string"
               :default ":start"
               :description "Content alignment, converted to a string."}
              {:name ":align-offset / :side-offset"
               :type "number"
               :default "-4 / 8"
               :description "Content positioning offsets."}
              {:name "primitive props"
               :type "map entries"
               :default nil
               :description
               "Normalized, class-merged, and forwarded to the corresponding Radix primitive."}]}]
    [helpers/api-component-card
     {:component-name "menubar-item"
      :description "Action item supporting destructive styling and inset alignment."
      :props [{:name ":variant"
               :type ":default | :destructive"
               :default ":default"
               :description "Visual intent only; selection behavior remains Radix Item behavior."}
              {:name ":inset"
               :type "boolean"
               :default nil
               :description "Adds leading alignment space."}
              {:name "Item props"
               :type "map entries"
               :default nil
               :description "Remaining props such as :disabled and :on-select are forwarded."}]}]
    [helpers/api-component-card
     {:component-name "menubar-checkbox-item / menubar-radio-group / menubar-radio-item"
      :description
      "Selectable items with built-in indicators. Radio items must be nested under RadioGroup; use Radix controlled/uncontrolled state props."
      :props [{:name ":checked / :on-checked-change"
               :type "boolean | indeterminate / function"
               :default nil
               :description "Checkbox state API."}
              {:name ":value / :on-value-change"
               :type "string / function"
               :default nil
               :description "Radio item and group state API."}]}]
    [helpers/api-component-card
     {:component-name "menubar-sub / menubar-sub-trigger / menubar-sub-content"
      :description
      "Nested-menu composition; SubTrigger appends a decorative chevron and supports inset alignment."
      :props [{:name ":inset"
               :type "boolean"
               :default nil
               :description "SubTrigger-only alignment option."}
              {:name "primitive props"
               :type "map entries"
               :default nil
               :description "Normalized and forwarded."}]}]
    [helpers/api-component-card
     {:component-name
      "menubar-group / menubar-portal / menubar-label / menubar-separator / menubar-shortcut"
      :description
      "Grouping, explicit portal, label, separator, and presentational shortcut helpers. Shortcut text does not install a keyboard handler."
      :props [{:name ":inset"
               :type "boolean"
               :default nil
               :description "Label-only alignment option."}
              {:name "primitive or span props"
               :type "map entries"
               :default nil
               :description "Normalized, class-merged, and forwarded."}]}]])))

(defstory MenubarDemo
          {:source-code (embed-source "clojure-shadcn.stories.menubar.menubar-demo")}
          []
          (r/as-element (helpers/wrap-component [menubar-demo/example])))

(defstory MenubarSelections
          {:source-code (embed-source "clojure-shadcn.stories.menubar.menubar-selections")}
          []
          (r/as-element (helpers/wrap-component [menubar-selections/example])))


(defstory
 MenubarRtl
 "Right-to-left Radix Menubar with icons and checkbox state.

  Radix primitive: @radix-ui/react-menubar

  Set :dir on Root so arrow-key navigation follows reading direction."
 {:source-code (embed-source "clojure-shadcn.stories.menubar.menubar-rtl")}
 []
 (r/as-element (helpers/wrap-component [menubar-rtl/example])))

(defstory Playground
          "Controlled Storybook playground using only safe scalar component props."
          {:source-code (embed-source "clojure-shadcn.stories.menubar.playground")
           :args {:class ""}
           :arg-types {:class {:name "CSS classes"
                               :control {:type "text"}}}
           :parameters {:playground {:description
                                     "Explore menubar css classes with a live preview."}
                        :controls {:expanded false
                                   :exclude ["children" "on-value-change" "on-checked-change"]}}}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))
