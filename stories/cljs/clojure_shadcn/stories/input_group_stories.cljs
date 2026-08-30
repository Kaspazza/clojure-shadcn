(ns clojure-shadcn.stories.input-group-stories
  (:require
   ["lucide-react"                           :refer [Search]]
   [clojure-shadcn.stories.helpers           :as helpers]
   [clojure-shadcn.ui.components.input-group :as sut]
   [reagent.core                             :as r])
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
          []
          (r/as-element (helpers/wrap-component [:div {:class "w-80"}
                                                 [sut/input-group {}
                                                  [sut/input-group-addon {}
                                                   [:> Search {:aria-hidden true}]]
                                                  [sut/input-group-input {:aria-label "Search"
                                                                          :placeholder "Search…"}]
                                                  [sut/input-group-addon {:align :inline-end}
                                                   [sut/input-group-text {}
                                                    "⌘K"]]]])))


(defstory InputGroupPlayground
  "Interactive input-group playground."
  {:args {:placeholder "Search…" :disabled false :addon-align "inline-end"}
   :arg-types {:placeholder {:control {:type "text"}} :disabled {:control {:type "boolean"}} :addon-align {:control {:type "select"} :options ["inline-start" "inline-end"]}}
   :parameters {:controls {:exclude ["on-click" "class"]}}
   :decode-args (fn [{:keys [addon-align] :as args}] (update args :addon-align keyword))}
  [args]
  (r/as-element (helpers/wrap-component [:div {:class "w-80"} [sut/input-group {} [sut/input-group-addon {:align (:addon-align args)} [:> Search {:aria-hidden true}]] [sut/input-group-input {:aria-label "Search" :placeholder (:placeholder args) :disabled (:disabled args)}]]])))
