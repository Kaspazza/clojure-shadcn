(ns clojure-shadcn.stories.button-group-stories
  (:require
   [clojure-shadcn.stories.button-group.button-group-patterns  :as button-group-patterns]
   [clojure-shadcn.stories.button-group.button-group-separator :as button-group-separator]
   [clojure-shadcn.stories.button-group.button-group-vertical  :as button-group-vertical]
   [clojure-shadcn.stories.button-group.button-group-with-text :as button-group-with-text]
   [clojure-shadcn.stories.button-group.playground             :as playground]
   [clojure-shadcn.stories.helpers                             :as helpers]
   [reagent.core                                               :as r])
  (:require-macros [clojure-shadcn.stories.macros :refer [embed-source defstory defdoc]]))

(def ^:export default
  #js {:title "Components/Button Group"
       :parameters #js {:layout "padded"}})

(defdoc Installation
        []
        (r/as-element [helpers/installation-scene
                       {:description
                        "Groups related controls with shared borders and orientation-aware corners."
                        :npm-install "npm install @radix-ui/react-slot @radix-ui/react-separator"
                        :source-code (embed-source "clojure-shadcn.ui.components.button_group")
                        :namespace-path "src/cljs/clojure_shadcn/ui/components/button_group.cljs"
                        :filename "button_group.cljs"}]))

(defstory
 ApiReference
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "space-y-4 p-6 max-w-4xl"}
    [helpers/api-component-card
     {:component-name "button-group / button-group-variants"
      :description
      "A role=group container that joins adjacent controls and manages orientation-aware borders, corners, and focus stacking."
      :props
      [{:name ":orientation"
        :type ":horizontal | :vertical"
        :default ":horizontal"
        :description "Controls layout and joined-edge styling."}
       {:name ":class"
        :type "string"
        :default nil
        :description "Classes merged with the defaults."}
       {:name "additional props"
        :type "map entries"
        :default nil
        :description
        "Normalized and forwarded to the wrapper div; role and data attributes are set by the component."}]}]
    [helpers/api-component-card
     {:component-name "button-group-text"
      :description
      "Text or adornment region. With :as-child true, Radix Slot merges its behavior into exactly one child."
      :props [{:name ":as-child"
               :type "boolean"
               :default nil
               :description "Render through Slot instead of a div; requires one element child."}
              {:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Classes are merged; remaining props are normalized and forwarded."}]}]
    [helpers/api-component-card
     {:component-name "button-group-separator"
      :description "Styled Separator intended between controls in a group."
      :props [{:name ":orientation"
               :type ":horizontal | :vertical"
               :default ":vertical"
               :description "Separator axis; forwarded to separator."}
              {:name ":class / additional props"
               :type "string / map entries"
               :default nil
               :description "Merged/forwarded to the Separator component."}]}]])))

(defstory Playground
          "Interactive button-group playground."
          {:source-code (embed-source "clojure-shadcn.stories.button-group.playground")
           :args {:orientation "horizontal"
                  :first-label "Back"
                  :second-label "Next"}
           :arg-types {:orientation {:name "Orientation"
                                     :control {:type "select"}
                                     :options ["horizontal" "vertical"]}
                       :first-label {:name "First label"
                                     :control {:type "text"}}
                       :second-label {:name "Second label"
                                      :control {:type "text"}}}
           :parameters
           {:playground
            {:description
             "Explore button group orientation, first label and second label with a live preview."}
            :controls {:expanded false
                       :exclude ["class" "role"]}}
           :decode-args (fn [args] (update args :orientation keyword))}
          [args]
          (r/as-element (helpers/wrap-component [playground/example args])))

(defstory ButtonGroupWithText
          {:source-code (embed-source "clojure-shadcn.stories.button-group.button-group-with-text")}
          []
          (r/as-element (helpers/wrap-component [button-group-with-text/example])))

(defstory ButtonGroupVertical
          {:source-code (embed-source "clojure-shadcn.stories.button-group.button-group-vertical")}
          []
          (r/as-element (helpers/wrap-component [button-group-vertical/example])))

(defstory ButtonGroupSeparator
          {:source-code (embed-source "clojure-shadcn.stories.button-group.button-group-separator")}
          []
          (r/as-element (helpers/wrap-component [button-group-separator/example])))

(defstory
 ButtonGroupPatterns
 "Toolbar, sizes, nesting, split actions, inputs, local compound controls, accessible icon labels, and RTL-safe grouping."
 {:source-code (embed-source "clojure-shadcn.stories.button-group.button-group-patterns")}
 []
 (r/as-element (helpers/wrap-component [button-group-patterns/example])))
