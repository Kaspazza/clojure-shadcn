(ns clojure-shadcn.stories.input-group-stories
  (:require
   [clojure-shadcn.stories.helpers                          :as helpers]
   [clojure-shadcn.stories.input-group.input-group-in-card  :as input-group-in-card]
   [clojure-shadcn.stories.input-group.input-group-patterns :as input-group-patterns]
   [clojure-shadcn.stories.input-group.playground           :as playground]
   [clojure-shadcn.stories.input-group.with-addon           :as with-addon]
   [reagent.core                                            :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Input Group"
       :parameters #js {:layout "centered"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description "Compound text inputs with semantic addons."
                        :npm-install "npm install lucide-react"
                        :source-code (embed-source "clojure-shadcn.ui.components.input_group")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/input_group.cljs"
                        :filename "input_group.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "input-group"
      :description
      "Compound role=group wrapper that coordinates focus and invalid styling for one input-group control plus addons."
      :props
      [{:name ":class / additional props"
        :type "string / map entries"
        :default nil
        :description
        "Classes are merged and remaining props forwarded to the div; role and data-slot are set."}]}]
    [helpers/api-component-card
     {:component-name "input-group-addon"
      :description
      "Adornment region. Clicking non-button content focuses the first input or textarea in its parent; nested buttons retain their own interaction."
      :props [{:name ":align"
               :type ":inline-start | :inline-end | :block-start | :block-end"
               :default ":inline-start"
               :description "Controls order, spacing, and block/inline layout."}
              {:name ":on-click"
               :type "function"
               :default nil
               :description "Called after built-in focus behavior."}
              {:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged/forwarded to the role=group div."}]}]
    [helpers/api-component-card
     {:component-name "input-group-button"
      :description
      "Button wrapper with compact group sizing. Defaults type to button to avoid accidental form submission."
      :props [{:name ":size"
               :type ":xs | :sm | :icon-xs | :icon-sm"
               :default ":xs"
               :description
               "Applies group-specific dimensions; the wrapped Button receives :size :xs."}
              {:name ":variant / :type"
               :type "keyword / string"
               :default ":ghost / button"
               :description "Defaults forwarded to Button; explicit values win."}
              {:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged/forwarded to Button."}]}]
    [helpers/api-component-card
     {:component-name "input-group-text / input-group-input / input-group-textarea"
      :description
      "Text adornment and the supported Input/Textarea controls. Controls remove their own border/ring so the parent group owns focus and invalid presentation."
      :props
      [{:name ":class / additional props"
        :type "string / map entries"
        :default nil
        :description
        "Merged and forwarded to span, Input, or Textarea. Supply an accessible label to the actual control."}]}]])))

(defstory WithAddon
          {:source-code (embed-source "clojure-shadcn.stories.input-group.with-addon")}
          []
          (r/as-element (helpers/wrap-component [with-addon/example])))


(defstory Playground
          "Interactive input-group playground."
          {:source-code (embed-source "clojure-shadcn.stories.input-group.playground")
           :args {:placeholder "Search…"
                  :disabled false
                  :addon-align "inline-end"}
           :arg-types {:placeholder {:name "Placeholder"
                                     :control {:type "text"}}
                       :disabled {:name "Disabled"
                                  :control {:type "boolean"}}
                       :addon-align {:name "Addon align"
                                     :control {:type "select"}
                                     :options ["inline-start" "inline-end"]}}
           :parameters
           {:playground
            {:description
             "Explore input group placeholder, disabled and addon align with a live preview."}
            :controls {:expanded false
                       :exclude ["on-click" "class"]}}
           :decode-args (fn [args] (update args :addon-align keyword))}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory
 InputGroupInCard
 "Input Group composed inside Card regions, preserving the Card's spacing and the group's single focus ring."
 {:source-code (embed-source "clojure-shadcn.stories.input-group.input-group-in-card")}
 []
 (r/as-element (helpers/wrap-component [input-group-in-card/example])))

(defstory
 InputGroupPatterns
 "Consolidated block addons, icon/text/button/Kbd/spinner, textarea, custom control, and RTL compositions."
 {:source-code (embed-source "clojure-shadcn.stories.input-group.input-group-patterns")}
 []
 (r/as-element (helpers/wrap-component [input-group-patterns/example])))
