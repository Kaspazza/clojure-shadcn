(ns clojure-shadcn.stories.button-group-stories
  (:require
   ["lucide-react"                            :refer [Bold Italic Underline]]
   [clojure-shadcn.stories.helpers            :as helpers]
   [clojure-shadcn.ui.components.button       :as button]
   [clojure-shadcn.ui.components.button-group :as sut]
   [reagent.core                              :as r])
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
     {:component-name "button-group"
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

(defstory ButtonGroupDemo "Interactive button-group playground." {:args {:orientation "horizontal" :first-label "Back" :second-label "Next"} :arg-types {:orientation {:control {:type "select"} :options ["horizontal" "vertical"]} :first-label {:control {:type "text"}} :second-label {:control {:type "text"}}} :parameters {:controls {:exclude ["class" "role"]}} :decode-args (fn [{:keys [orientation] :as args}] (cond-> args orientation (update :orientation keyword)))} [args] (r/as-element (helpers/wrap-component [sut/button-group (select-keys args [:orientation]) [button/button {:variant :outline} (:first-label args)] [button/button {:variant :outline} (:second-label args)]])))

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
