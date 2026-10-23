(ns clojure-shadcn.stories.button-group-stories
  (:require
   ["lucide-react"                             :refer [Bold ChevronDown Italic Underline]]
   [clojure-shadcn.stories.helpers             :as helpers]
   [clojure-shadcn.ui.components.button        :as button]
   [clojure-shadcn.ui.components.button-group  :as sut]
   [clojure-shadcn.ui.components.dropdown-menu :as dropdown]
   [clojure-shadcn.ui.components.input         :as input]
   [clojure-shadcn.ui.components.input-group   :as input-group]
   [clojure-shadcn.ui.components.popover       :as popover]
   [clojure-shadcn.ui.components.select        :as select]
   [reagent.core                               :as r])
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

(defstory ButtonGroupDemo
          "Interactive button-group playground."
          {:args {:orientation "horizontal"
                  :first-label "Back"
                  :second-label "Next"}
           :arg-types {:orientation {:control {:type "select"}
                                     :options ["horizontal" "vertical"]}
                       :first-label {:control {:type "text"}}
                       :second-label {:control {:type "text"}}}
           :parameters {:controls {:exclude ["class" "role"]}}
           :decode-args (fn [{:keys [orientation firstLabel secondLabel]
                              :as args}]
                          (cond-> (-> args
                                      (assoc :first-label firstLabel :second-label secondLabel)
                                      (dissoc :firstLabel :secondLabel))
                            orientation (update :orientation keyword)))}
          [args]
          (r/as-element (helpers/wrap-component [sut/button-group
                                                 (select-keys args [:orientation])
                                                 [button/button {:variant :outline}
                                                  (:first-label args)]
                                                 [button/button {:variant :outline}
                                                  (:second-label args)]])))

(defstory ButtonGroupWithText
          []
          (r/as-element (helpers/wrap-component [sut/button-group {}
                                                 [sut/button-group-text {}
                                                  "https://"]
                                                 [button/button {:variant :outline}
                                                  "example.com"]])))

(defstory ButtonGroupVertical
          []
          (r/as-element (helpers/wrap-component [sut/button-group {:orientation :vertical}
                                                 [button/button {:variant :outline
                                                                 :size :icon
                                                                 :aria-label "Bold"}
                                                  [:> Bold]]
                                                 [button/button {:variant :outline
                                                                 :size :icon
                                                                 :aria-label "Italic"}
                                                  [:> Italic]]
                                                 [button/button {:variant :outline
                                                                 :size :icon
                                                                 :aria-label "Underline"}
                                                  [:> Underline]]])))

(defstory ButtonGroupSeparator
          []
          (r/as-element (helpers/wrap-component [sut/button-group {}
                                                 [button/button {:variant :outline}
                                                  "Save"]
                                                 [sut/button-group-separator {}]
                                                 [button/button {:variant :outline}
                                                  "Options"]])))

(defstory
 ButtonGroupPatterns
 "Toolbar, sizes, nesting, split actions, inputs, local compound controls, accessible icon labels, and RTL-safe grouping."
 []
 (r/as-element
  (helpers/wrap-component
   [:div {:class "grid gap-6"}
    [sut/button-group {:aria-label "Text formatting toolbar"}
     [button/button {:variant :outline
                     :size :icon-sm
                     :aria-label "Bold"}
      [:> Bold]]
     [button/button {:variant :outline
                     :size :icon-sm
                     :aria-label "Italic"}
      [:> Italic]]
     [button/button {:variant :outline
                     :size :icon-sm
                     :aria-label "Underline"}
      [:> Underline]]]
    [:div {:class "flex flex-wrap items-center gap-4"}
     (for [size [:sm :default :lg]]
       ^{:key size}
       [sut/button-group {}
        [button/button {:variant :outline
                        :size size}
         "Previous"]
        [button/button {:variant :outline
                        :size size}
         "Next"]])]
    [sut/button-group {}
     [button/button {}
      "Save"]
     [dropdown/dropdown-menu {}
      [dropdown/dropdown-menu-trigger {:as-child true}
       [button/button {:size :icon
                       :aria-label "More save options"}
        [:> ChevronDown]]]
      [dropdown/dropdown-menu-content {:align "end"}
       [dropdown/dropdown-menu-item {}
        "Save as draft"]
       [dropdown/dropdown-menu-item {}
        "Save a copy"]]]]
    [sut/button-group {:class "w-full"
                       :aria-label "Website address"}
     [sut/button-group-text {}
      "https://"]
     [input/input {:aria-label "Domain"
                   :placeholder "example.com"}]]
    [sut/button-group {}
     [input-group/input-group {:class "w-72"}
      [input-group/input-group-addon {}
       "@"]
      [input-group/input-group-input {:aria-label "Username"}]]
     [button/button {:variant :outline}
      "Invite"]]
    [sut/button-group {}
     [select/select {:default-value "viewer"}
      [select/select-trigger {:aria-label "Role"}
       [select/select-value {}]]
      [select/select-content {}
       [select/select-item {:value "viewer"}
        "Viewer"]
       [select/select-item {:value "editor"}
        "Editor"]]]
     [popover/popover {}
      [popover/popover-trigger {:as-child true}
       [button/button {:variant :outline}
        "Details"]]
      [popover/popover-content {:class "text-sm"}
       "Permissions apply immediately."]]]
    [sut/button-group {:aria-label "Nested pagination"}
     [button/button {:variant :outline}
      "First"]
     [sut/button-group {}
      [button/button {:variant :outline}
       "2"]
      [button/button {:variant :outline}
       "3"]]
     [button/button {:variant :outline}
      "Last"]]
    [:div {:dir "rtl"}
     [sut/button-group {:aria-label "التنقل"}
      [button/button {:variant :outline}
       "السابق"]
      [button/button {:variant :outline}
       "التالي"]]]
    [:p {:class "text-xs text-muted-foreground"}
     "RTL uses semantic content order only; the current component still joins horizontal edges with physical left/right classes."]])))
